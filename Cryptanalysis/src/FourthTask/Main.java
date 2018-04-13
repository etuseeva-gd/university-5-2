package FourthTask;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private void run() throws IOException {
        System.out.println("ЧЕТВЕРТЫЙ БЛОК ЗАДАНИЙ!!!");
        System.out.println("Введите номер под задания:");
        System.out.println("1. Атака по частотному анализу");
        System.out.println("2. Атака по вероятному слову");

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
            default: {
                System.out.println("Неккоректная операция!");
            }
        }
        scanner.close();
    }
}
