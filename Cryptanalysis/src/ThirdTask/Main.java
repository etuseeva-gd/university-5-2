package ThirdTask;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {
        System.out.println("ТРЕТИЙ БЛОК ЗАДАНИЙ!!!");
        System.out.println("Введите номер под задания:");

        Scanner scanner = new Scanner(System.in);
        int taskNum = scanner.nextInt();
        switch (taskNum) {
            case 1: {
                new FirstSubTask().init();
                break;
            }
            case 2: {
                new SecondSubTask().init();
                break;
            }
//            case 3: {
//                new ThirdSubTask().init();
//                break;
//            }
            default: {
                System.out.println("Неккоректная операция!");
            }
        }
        scanner.close();
    }
}
