package SecondTask;


import java.io.*;
import java.util.Scanner;

public class FirstSubTask {
    private static final String firstInput = "input_2.1.1.txt",
            secondInput = "input_2.1.2.txt";

    public void init() throws IOException {
        System.out.println("Выберите режим программы, вычислить индекс для:");
        System.out.println("1. Последовательностей рус./англ. языка");
        System.out.println("2. Случайно сгенерированных последовательностей");

        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();

        switch (action) {
            case 1: {
                String y = Utils.read(firstInput);
                String z = Utils.read(secondInput);

                y = Utils.cleanString(y);
                z = Utils.cleanString(z);

                this.printIndex(y, z);

                break;
            }
            case 2: {
                System.out.println("Введите длину последовательности: ");
                int len = scanner.nextInt();
                this.printIndex(Utils.genRandomSequence(len), Utils.genRandomSequence(len));
                break;
            }
            default: {
                System.out.println("Неккоректная операция!");
            }
        }

        scanner.close();
    }

    private void printIndex(String y, String z) {
        System.out.println("Для последовательностей:");
        System.out.println(y);
        System.out.println(z);
        System.out.println("Индекс равен = " + this.getIndex(y, z));
    }

    private double getIndex(String y, String z) {
        if (y.length() != z.length()) {
            System.out.println("Длины входных последовательностей не совпадают!");
            System.out.println("!!!Индекс будет подсчитан по мин. длине!!!");
        }

        int n = Math.min(y.length(), z.length());
        System.out.println("Минимальная длина = " + n);

        double index = 0;
        for (int i = 0; i < n; i++) {
            index += this.delta(y.charAt(i), z.charAt(i));
        }

        return index / n;
    }

    private int delta(char y, char z) {
        return y == z ? 1 : 0;
    }
}
