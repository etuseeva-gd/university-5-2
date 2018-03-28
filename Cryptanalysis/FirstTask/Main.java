import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        new FirstTask.Main().run();
    }

    private void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер задания:");
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


    static int[] readKey(String line) {
        String[] numbersStr = line.split(" ");
        int[] array = new int[numbersStr.length];
        for (int i = 0; i < numbersStr.length; i++) {
            array[i] = Integer.parseInt(numbersStr[i]);
        }
        return array;
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
