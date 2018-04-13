package FirstTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {
        System.out.println("ПЕРВЫЙ БЛОК ЗАДАНИЙ!!!");
        System.out.println("Введите номер под задания:");
        System.out.println("1. Шифр простой перестановки на основе " +
                "генерации ключа моноциклической перестановки");
        System.out.println("2. Тест Казиски по вычислению длины ключа простой перестановки");
        System.out.println("3. Перебор ключей моноциклической перестановки при известной длине ключа");

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
            case 3: {
                new ThirdSubTask().init();
                break;
            }
            default: {
                System.out.println("Неккоректная операция!");
            }
        }
        scanner.close();
    }

    static StringBuilder readText(BufferedReader inInput) throws IOException {
        StringBuilder text = new StringBuilder();
        String line = inInput.readLine();
        while (line != null) {
            text.append(line);
            line = inInput.readLine();
        }
        return text;
    }
}
