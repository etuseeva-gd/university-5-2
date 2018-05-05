import Graphs.Coloring;
import Graphs.Graph;
import javafx.util.Pair;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {
        // Зона констант
        int graphsPerFile = 10000;
        // Окончание зоны

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите файл с данными:");
        String fileWithGraphs = scanner.nextLine();
        scanner.close();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileWithGraphs))) {
            int passedGraphs = 0;
            int indexFile = 0, passedGraphsForOneFile = 0;

            String line = null;
            String fileName = this.getReportFileName(indexFile);
            this.cleanReport(fileName);
            while ((line = reader.readLine()) != null) {
                PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(new File(fileName), true)));

                Graph graph = new Graph(this.parseGraph(line), line.trim());
                out.print(this.coloringGraph(graph));
                out.close();

                System.out.println(passedGraphs + 1);
                passedGraphs++;

                passedGraphsForOneFile++;

                if (passedGraphsForOneFile == graphsPerFile) {
                    passedGraphsForOneFile = 0;
                    fileName = this.getReportFileName(++indexFile);
                    this.cleanReport(fileName);
                }
            }
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            System.out.println("Такой файл не был найден!");
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
    private void cleanReport(String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.print("");
        writer.close();
    }

    private String getReportFileName(int indexFile) {
        return "research_work_report_" + indexFile + ".txt";
    }

    /**
     * Декодирование графа полученного из генератора
     *
     * @param strGraph
     * @return
     */
    public int[][] parseGraph(String strGraph) {
        StringBuilder stringBuilderMatrix = new StringBuilder();
        for (int i = 1; i < strGraph.length(); i++) {
            int number = this.parseChar(strGraph.charAt(i));

            StringBuilder str = new StringBuilder();
            str.append(Integer.toBinaryString(number));
            while (str.length() < 6) {
                str.reverse().append("0").reverse();
            }

            stringBuilderMatrix.append(str);
        }
        String stringMatrix = String.valueOf(stringBuilderMatrix);

        int vertexes = this.parseChar(strGraph.charAt(0));
        int[][] matrix = new int[vertexes][vertexes];
        int index = 0;
        for (int i = 1; i < vertexes; i++) {
            for (int j = 0; j < i; j++, index++) {
                matrix[i][j] = Integer.parseInt(stringMatrix.substring(index, index + 1));
                matrix[j][i] = Integer.parseInt(stringMatrix.substring(index, index + 1));
            }
        }
        return matrix;
    }

    private int parseChar(char c) {
        return ((int) c) - 63;
    }
}
