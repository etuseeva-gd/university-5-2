import java.io.*;

public class Main1 {
    public static void main(String[] args) {
        String fileWithGraphs = "C:\\Users\\lenok\\Desktop\\graphs\\g10.txt";

        int i = 0;
        int k = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileWithGraphs))) {
            String fileName = "C:\\Users\\lenok\\Desktop\\g\\g10.";
            String line = null;
            PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(new File(fileName + k + ".txt"), true)));
            while ((line = reader.readLine()) != null) {
                out.println(line);
                if (++i > 500000) {
                    i = 0;
                    k++;
                    out.close();
                    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(new File(fileName + k + ".txt"), true)));
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
