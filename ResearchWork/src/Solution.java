import Graphs.Graph;
import Multithreading.ColoringGraphs;
import Multithreading.ReadingGraphs;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Solution {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        BlockingQueue<Graph> queue = new LinkedBlockingQueue<Graph>();

        cleanReport("report.txt");

        Thread production = new Thread(new ReadingGraphs(queue));

        Thread firstThread = new Thread(new ColoringGraphs(queue));
        Thread secondThread = new Thread(new ColoringGraphs(queue));
        Thread thirdThread = new Thread(new ColoringGraphs(queue));

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
