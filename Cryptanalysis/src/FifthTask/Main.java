package FifthTask;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ПЯТЫЙ БЛОК ЗАДАНИЙ!!!");
        System.out.println("Выберите номер под задания:");
        System.out.println("1. Вычисление значений гипотезы Н(0)");
        System.out.println("2. Вычисление значений гипотезы Н(d) с наиболее вероятной длиной периода d");

        int action = scanner.nextInt();
        switch (action) {
            case 1: {
                new FirstSubTask().init();
                break;
            }
            case 2: {
                new SecondSubTask().init();
                break;
            }
            default: {
                System.out.println("Неверная операция!");
            }
        }
    }
}
