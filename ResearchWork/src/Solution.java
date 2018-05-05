import Graphs.Graph;
import Multithreading.GraphRunnable;
import Multithreading.PrepareGraphs;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Solution {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        BlockingQueue<Graph> queue = new LinkedBlockingQueue<Graph>();

        cleanReport("report.txt");

        Thread production = new Thread(new PrepareGraphs(queue));

        Thread firstThread = new Thread(new GraphRunnable(queue));
        Thread secondThread = new Thread(new GraphRunnable(queue));
        Thread thirdThread = new Thread(new GraphRunnable(queue));

        production.start();
        firstThread.start();
        secondThread.start();
        thirdThread.start();

        production.join();
        firstThread.join();
        secondThread.join();
        thirdThread.join();

        System.out.println("Done.");
    }


    /**
     * Очищаем предыдущий файл с репортом.
     *
     * @throws FileNotFoundException
     */
    private static void cleanReport(String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.print("");
        writer.close();
    }
}
