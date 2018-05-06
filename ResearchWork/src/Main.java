import Graphs.Graph;
import Multithreading.ColoringGraphs;
import Multithreading.ReadingGraphs;
import Multithreading.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        double startTime = System.currentTimeMillis();

        BlockingQueue<Graph> queue = new LinkedBlockingQueue<Graph>();
        Report report = new Report();

        // @todo раскоментить для работы
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Введите файл с данными:");
        // String fileWithGraphs = scanner.nextLine();
        // scanner.close();

        String fileWithGraphs = "g5.txt";
        int numberOfThreads = 2;

        Thread production = new Thread(new ReadingGraphs(queue, fileWithGraphs));

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            threads.add(new Thread(new ColoringGraphs(queue, report)));
        }

        production.start();
        for (int i = 0; i < numberOfThreads; i++) {
            threads.get(i).start();
        }

        production.join();
        for (int i = 0; i < numberOfThreads; i++) {
            threads.get(i).join();
        }

        double endTime = System.currentTimeMillis();
        double diff = endTime - startTime;

        System.out.println("Программа отработала за " + (diff / 1000) + " сек. (" + (diff / 60000) + " мин.)");
        System.out.println(report.toString());
    }
}
