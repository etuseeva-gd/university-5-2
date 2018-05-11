import Graphs.Graph;
import Multithreading.ColoringGraphs;
import Multithreading.ReadingGraphs;
import Multithreading.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        double startTime = System.currentTimeMillis();

        BlockingQueue<Graph> queue = new LinkedBlockingQueue<Graph>();
        Report report = new Report();

        // @todo раскоментить для работы
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Введите файл с данными:");
//        String fileWithGraphs = scanner.nextLine();
//        System.out.println("Введите параметры в строку через пробел:");
//        System.out.println("/f input.txt - файл со входными данными");
//        System.out.println("/t - проверка типа");
//        System.out.println("/h - проверка гипотезы");
//        System.out.println("/t - подсчет треугольников");
//        System.out.println("Пример: /f input.txt /t");

//        String fileWithGraphs = "C:\\Users\\lenok\\Desktop\\graphs\\g10.txt";
        String fileWithGraphs = "C:\\Users\\lenok\\Desktop\\g\\g10.16.txt";
//        767264
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
