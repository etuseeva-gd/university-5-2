import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("g8.txt"))) {
            String line = null;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                Graph graph = new Graph(Utils.parseGraph(line));
                graph.coloringGraph();
                System.out.println(i++);
            }
        }
    }
}
