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
        System.out.println("1. Вычисление множества запретных биграмм языка открытых сообщений");
        System.out.println("2. Сгенерировать ключ (моноциклическая перестановка)");
        System.out.println("3. Зашифровать текст (шифр простой перестановки)");
        System.out.println("4. Построение вспомогательной таблицы для анализа шифра " +
                "перестановки при известной длине периода");
        System.out.println("5. Построение ориентированного леса возможных перестановок");
        System.out.println("6. Перебор ключей по ориентированному лесу возможных перестановок");

        Scanner scanner = new Scanner(System.in);
        int taskNum = scanner.nextInt();
        switch (taskNum) {
            case 1: {
                new FirstSubTask().init();
                break;
            }
            case 2: {
                //gen key
                break;
            }
            case 3: {
                //crypt text
                break;
            }
            case 4: {
                new SecondSubTask().init();
                break;
            }
            case 5: {
                new ThirdSubTask().init();
                break;
            }
            case 6: {
                new FourthSubTask().init();
                break;
            }
            default: {
                System.out.println("Неккоректная операция!");
            }
        }
        scanner.close();
    }
}
