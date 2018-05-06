package Multithreading;

import Graphs.Graph;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class ReadingGraphs implements Runnable {
    private final BlockingQueue<Graph> queue;
    // Имя файла в котором содержатся графы
    private String fileWithGraphs = null;

    public ReadingGraphs(BlockingQueue<Graph> queue, String fileWithGraphs) {
        this.queue = queue;
        this.fileWithGraphs = fileWithGraphs;
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileWithGraphs))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Graph graph = new Graph(this.parseGraph(line), line.trim());
                queue.put(graph);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        // Чтобы потоки поняли что графы все закончились
        queue.add(new Graph(null));
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