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
                graph.coloringGraph();
                System.out.println(i++);
            }
        }
    }
}

//GCZVRs
