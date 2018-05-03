import javafx.util.Pair;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {
        this.cleanReport();

        try (BufferedReader reader = new BufferedReader(new FileReader("g5.txt"))) {
            String line = null;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                Graph graph = new Graph(Utils.parseGraph(line));

                StringBuilder graphReport = new StringBuilder();
                graphReport.append("----------").append('\n');

                // @todo перепроверить, может переписать
                int[][] matrix = graph.getMatrix();
                graphReport.append("Матрица смежности: ").append(Arrays.deepToString(matrix)).append('\n');
                List<Pair<Integer, Integer>> edges = graph.getEdges();
                graphReport.append("Список ребер: ").append(edges).append('\n');

                // Количество вершин графа
                int n = graph.getVertexesSize();
                graphReport.append("Количество вершин: ").append(n).append('\n');

                int maxDegree = graph.getMaxDegree();

                graphReport.append("~Результат~").append('\n');
                graphReport.append("Максимальная степень: ").append(maxDegree).append('\n');
                graphReport.append("Хроматический индекс: ");
                if (graph.isCubicGraph()) {
                    // Если граф кубический
//                    graphReport.append("Это кубический граф!").append('\n');
                    graphReport.append(maxDegree).append('\n');
                } else if (graph.isBigraph()) {
                    // Если граф двудольный
                    graphReport.append("Это двудольный граф!").append('\n');
                    graphReport.append(maxDegree).append('\n');
                }else if (graph.isCyclicGraph()) {
                    // Если граф является циклом
//                    graphReport.append("Это циклический граф!").append('\n');
                    if (n % 2 == 0) {
                        // 2
                        graphReport.append(2).append('\n');
                    } else {
                        // 3
                        graphReport.append(3).append('\n');
                    }
                } else if (graph.isFullGraph()) {
                    // Если граф является полным
//                    graphReport.append("Это полный граф!").append('\n');
                    if (n % 2 == 0) {
                        // n - 1
                        graphReport.append(n - 1).append('\n');
                    } else {
                        // n
                        graphReport.append(n).append('\n');
                    }
                } else {
                    Coloring coloring = graph.getColoring();
                    String error = coloring.getError();
                    if (error != null) {
                        graphReport.append(error).append('\n');
                    } else {
                        graphReport.append(coloring.toString());
                        if (maxDegree == coloring.getColorSize() - 1) {
                            graphReport.append("!Проверить!").append('\n');
                        }
                    }
                }

                PrintWriter out = new PrintWriter(new BufferedOutputStream(
                        new FileOutputStream(new File("nir_report.txt"), true)));
                out.print(graphReport);
                out.close();

                System.out.println(i++);
            }
        }
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
