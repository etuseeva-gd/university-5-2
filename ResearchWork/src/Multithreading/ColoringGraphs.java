package Multithreading;

import Graphs.Coloring;
import Graphs.Graph;
import javafx.util.Pair;

import java.io.*;
import java.util.List;
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
            String fileName = this.getReportFileName(threadName, indexFile);

            this.cleanReport(fileName);
            this.cleanReport(getNeedToCheckFileName());

            Graph graph;
            while (true) {
                graph = queue.take();

                if (graph.getStrView() == null) {
                    queue.add(new Graph(null));
                    break;
                }

                report.incrementCurrentGraph();

                PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(new File(getReportFileName(threadName, indexFile)), true)));
                out.print(this.getReport(graph));
                out.close();

                passedGraphsForOneFile++;

                if (passedGraphsForOneFile == graphsPerFile) {
                    passedGraphsForOneFile = 0;
                    fileName = this.getReportFileName(threadName, ++indexFile);
                    this.cleanReport(fileName);
                }
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка!");
            System.out.println(threadName + " " + e.getMessage());
        }

        System.out.println("Закончил работу поток: " + threadName);
    }

    private StringBuilder getReport(Graph graph) throws FileNotFoundException {
        StringBuilder graphReport = new StringBuilder();
        graphReport.append("----------").append('\n');

        graphReport.append("Было сгенерировано: ").append(graph.getStrView()).append('\n');
        List<Pair<Integer, Integer>> edges = graph.getEdges();
        graphReport.append("Список ребер: ").append(edges).append('\n');

        int n = graph.getVertexesSize();
        int maxDegree = graph.getMaxDegree();

        graphReport.append("Максимальная степень: ").append(maxDegree).append('\n');
        graphReport.append("Хроматический индекс: ");

        int index = -1;
        String typeOfGraph = null;
        Coloring coloring = null;

        if (graph.isCubicGraph()) {
            index = maxDegree;
            typeOfGraph = "Это кубический граф!";
        } else if (graph.isFullGraph()) {
            index = n % 2 == 0 ? n - 1 : n;
            typeOfGraph = "Это полный граф!";
        } else if (graph.isBigraph()) {
            index = maxDegree;
            typeOfGraph = "Это двудольный граф!";
        } else if (graph.isCyclicGraph()) {
            index = n % 2 == 0 ? 2 : 3;
            typeOfGraph = "Это циклический граф!";
        } else {
            coloring = graph.getColoring();
            index = coloring.getColorSize();
        }

        graphReport.append(index).append('\n');
        if (typeOfGraph != null) {
            graphReport.append(typeOfGraph).append('\n');
        }

        boolean hasError = false;
        if (coloring != null) {
            if (maxDegree == index - 1) {
                graphReport.append(coloring.toString());
                graphReport.append("!Проверить!").append('\n');
            }
            String error = coloring.getError();
            if (error != null) {
                graphReport.append(error).append('\n');
                hasError = true;
                this.saveNeedToCheckGraph(graph.getStrView());
            }
        }

        this.updateReport(maxDegree, index, hasError);
        return graphReport;
    }

    private void updateReport(int maxDegree, int index, boolean hasError) {
        if (maxDegree == index) {
            this.report.incrementFirstType();
        } else if (!hasError) {
            this.report.incrementSecondType();
        } else {
            this.report.incrementNeedToCheck();
        }
    }

    private void saveNeedToCheckGraph(String graphView) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(new File(getNeedToCheckFileName()), true)));
        out.print(graphView + "\n");
        out.close();
    }

    private String getReportFileName(String threadName, int indexFile) {
        return "research_work_report_" + threadName + "_" + indexFile + ".txt";
    }

    private String getNeedToCheckFileName() {
        return "need_to_check.txt";
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
