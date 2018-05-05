import javafx.util.Pair;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {
        this.cleanReport();

        try (BufferedReader reader = new BufferedReader(new FileReader("g5.txt"))) {
            String line = null;
            int i = 1;
            while ((line = reader.readLine()) != null) {
                PrintWriter out = new PrintWriter(new BufferedOutputStream(
                        new FileOutputStream(new File("nir_report.txt"), true)));

                Graph graph = new Graph(Utils.parseGraph(line), line.trim());
                out.print(this.coloringGraph(graph));
                System.out.println(i++);

                out.close();
            }

        }
    }

    private StringBuilder coloringGraph(Graph graph) throws FileNotFoundException {
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

//        graphReport.append("~Результат~").append('\n');
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

    /**
     * Очищаем предыдущий файл с репортом.
     *
     * @throws FileNotFoundException
     */
    private void cleanReport() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("nir_report.txt");
        writer.print("");
        writer.close();
    }
}

//GCZVRs
