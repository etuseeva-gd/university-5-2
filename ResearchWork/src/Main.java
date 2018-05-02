import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        PrintWriter writer = new PrintWriter("nir_report.txt");
        writer.print("");
        writer.close();

        try (BufferedReader reader = new BufferedReader(new FileReader("g9.txt"))) {
            String line = null;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                Graph graph = new Graph(Utils.parseGraph(line));

                // Максимальная степень вершины графа
                int maxDegree = graph.getMaxDegree();

                // Количество вершин графа
                int n = graph.getVertexesSize();

                if (graph.isCubicGraph() || graph.isBigraph()) {
                    // Если граф кубический или двудольный
                    // Хром индекс = max degree
                } else if (graph.isCyclicGraph()) {
                    // Если граф является циклом
                    if (n % 2 == 0) {
                        // 2
                    } else {
                        // 3
                    }
                } else if (graph.isFullGraph()) {
                    // Если граф является полным
                    if (n % 2 == 0) {
                        // n - 1
                    } else {
                        // n
                    }
                } else {
                    graph.coloringGraph();
                }

                System.out.println(i++);
            }
        }
    }
}

//GCZVRs
