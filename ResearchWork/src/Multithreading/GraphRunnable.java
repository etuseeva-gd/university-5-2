package Multithreading;

import Graphs.Coloring;
import Graphs.Graph;
import javafx.util.Pair;

import java.io.*;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class GraphRunnable implements Runnable {
    private final BlockingQueue<Graph> queue;

    public GraphRunnable(BlockingQueue<Graph> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println("Start " + Thread.currentThread().getName());
            int i = 0;
            Graph graph;
            while (true) {
                graph = queue.take();

                if (graph.getEmpty()) {
                    queue.add(new Graph(true));
                    break;
                }

                PrintWriter out = new PrintWriter(new BufferedOutputStream(
                        new FileOutputStream(new File("report.txt"), true)));
                out.print(this.coloringGraph(graph));
                out.close();

                System.out.println(++i);

            }
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " " + e.getMessage());
        }
    }

    public StringBuilder coloringGraph(Graph graph) throws FileNotFoundException {
        StringBuilder graphReport = new StringBuilder();
        graphReport.append("----------").append('\n');

        // @todo перепроверить, может переписать
        graphReport.append("Было сгенерировано: ").append(graph.getStrView()).append('\n');
        int[][] matrix = graph.getMatrix();
        //graphReport.append("Матрица смежности: ").append(Arrays.deepToString(matrix)).append('\n');
        List<Pair<Integer, Integer>> edges = graph.getEdges();
        graphReport.append("Список ребер: ").append(edges).append('\n');

        // Количество вершин графа
        int n = graph.getVertexesSize();

        // Максимальная степень вершин графа
        int maxDegree = graph.getMaxDegree();

        graphReport.append("Максимальная степень: ").append(maxDegree).append('\n');
        graphReport.append("Хроматический индекс: ");
        if (graph.isCubicGraph()) {
            // Если граф кубический
            graphReport.append(maxDegree).append('\n');
            graphReport.append("Это кубический граф!").append('\n');
        } else if (graph.isFullGraph()) {
            // Если граф является полным
            if (n % 2 == 0) {
                // n - 1
                graphReport.append(n - 1).append('\n');
            } else {
                // n
                graphReport.append(n).append('\n');
            }
            graphReport.append("Это полный граф!").append('\n');
        } else if (graph.isBigraph()) {
            // Если граф двудольный
            graphReport.append(maxDegree).append('\n');
            graphReport.append("Это двудольный граф!").append('\n');
        } else if (graph.isCyclicGraph()) {
            // Если граф является циклом
            if (n % 2 == 0) {
                // 2
                graphReport.append(2).append('\n');
            } else {
                // 3
                graphReport.append(3).append('\n');
            }
            graphReport.append("Это циклический граф!").append('\n');
        } else {
            Coloring coloring = graph.getColoring();
//            Coloring coloring = graph.getAnotherColoring();
            graphReport.append(coloring.getColorSize()).append('\n');

            if (maxDegree == coloring.getColorSize() - 1) {
                graphReport.append(coloring.toString());
                graphReport.append("!Проверить!").append('\n');
            }

            String error = coloring.getError();
            if (error != null) {
                graphReport.append(error).append('\n');
            }
        }

        return graphReport;
    }
}
