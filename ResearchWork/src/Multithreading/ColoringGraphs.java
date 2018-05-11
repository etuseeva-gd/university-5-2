package Multithreading;

import Graphs.Graph;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class ColoringGraphs implements Runnable {
    private final BlockingQueue<Graph> queue;
    private Report report;

    public ColoringGraphs(BlockingQueue<Graph> queue, Report report) {
        this.queue = queue;
        this.report = report;
    }

    public void run() {
        // Зона констант
        String threadName = Thread.currentThread().getName().toLowerCase();
        int graphsPerFile = 5000;
        // Окончание зоны
        try {
            System.out.println("Начал работу поток: " + threadName);

            int indexFile = 0, passedGraphsForOneFile = 0;
//            String fileName = this.getReportFileName(threadName, indexFile);
//            this.cleanReport(fileName);



            Graph graph;
            while (true) {
                graph = queue.take();

                if (graph.getStrView() == null) {
                    queue.add(new Graph(null));
                    break;
                }

                report.incrementCurrentGraph();

                StringBuilder report = this.getReport(graph);

//                PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(new File(getReportFileName(threadName, indexFile)), true)));
//                out.print(report);
//                out.close();

                passedGraphsForOneFile++;

                if (passedGraphsForOneFile == graphsPerFile) {
                    passedGraphsForOneFile = 0;
//                    fileName = this.getReportFileName(threadName, ++indexFile);
//                    this.cleanReport(fileName);
                }
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка!");
            System.out.println(threadName + " " + e.getMessage());
        }

        System.out.println("Закончил работу поток: " + threadName);
    }

    private StringBuilder getReport(Graph graph) {
        StringBuilder graphReport = new StringBuilder();
        //graphReport.append("----------").append('\n');
        //graphReport.append("Было сгенерировано: ").append(graph.getStrView()).append('\n');
        //List<Pair<Integer, Integer>> edges = graph.getEdges();
        //graphReport.append("Список ребер: ").append(edges).append('\n');
        //graphReport.append("Максимальная степень: ").append(graph.getMaxDegree()).append('\n');

        boolean isFirstType = graph.isFirstType();
        if (isFirstType) {
            this.report.incrementFirstType();
            graphReport.append(graph.getStrView()).append("\n");
        } else {
            this.report.incrementSecondType();
        }
        //graphReport.append(isFirstType ? "ТИП 1" : "ТИП 2").append('\n');

        // @todo не всегда нужно
        if (!isFirstType) {
            int n = graph.getVertexesSize();
            int maxDegree = graph.getMaxDegree();
            if (graph.isRegularGraph() && n % 2 == 0 && maxDegree >= (n / 2)) {
                this.report.addNotCompleteChetwyndHilton(graph.getStrView());
            }
        }

        // @todo не всегда нужно
        int numberOfTriangles = graph.getNumberOfTriangles();
        if (isFirstType) {
            this.report.addTrianglesOfFirstType(numberOfTriangles);
        } else {
            this.report.addTrianglesOfSecondType(numberOfTriangles);
        }

        return graphReport;
    }

    private String getReportFileName(String threadName, int indexFile) {
        return "research_work_report_" + threadName + "_" + indexFile + ".txt";
    }

    /**
     * Очищаем предыдущий файл с репортом.
     *
     * @throws FileNotFoundException
     */
    private void cleanReport(String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.print("");
        writer.close();
    }
}
