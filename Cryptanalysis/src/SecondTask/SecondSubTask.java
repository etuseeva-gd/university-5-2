package SecondTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SecondSubTask {
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
        System.out.println("Индекс равен = " + this.getIndex(y, z) * 100);
    }

    private double getIndex(String y, String z) {
        if (y.length() != z.length()) {
            System.out.println("Длины входных последовательностей не совпадают!");
            System.out.println("!!!Индекс будет подсчитан по мин. длине!!!");
        }

        double n = Math.min(y.length(), z.length());
        System.out.println("Минимальная длина = " + n);

        Map<Character, Double> amountSymY = new HashMap<>(), amountSymZ = new HashMap<>();

        for (int i = 0; i < Utils.rus.length(); i++) {
            amountSymY.put(Utils.rus.charAt(i), 0.0);
            amountSymZ.put(Utils.rus.charAt(i), 0.0);
        }
        for (int i = 0; i < Utils.eng.length(); i++) {
            amountSymY.put(Utils.eng.charAt(i), 0.0);
            amountSymZ.put(Utils.eng.charAt(i), 0.0);
        }

        for (int i = 0; i < n; i++) {
            char keyY = y.charAt(i), keyZ = z.charAt(i);
            amountSymY.put(keyY, amountSymY.get(keyY) + 1);
            amountSymZ.put(keyZ, amountSymZ.get(keyZ) + 1);
        }

        double index = 0;

        for (int i = 0; i < Utils.rus.length(); i++) {
            char keyY = Utils.rus.charAt(i), keyZ = Utils.rus.charAt(i);
            double pY = amountSymY.get(keyY) / n, pZ = amountSymZ.get(keyZ) / n;
            index += pY * pZ;
        }

        for (int i = 0; i < Utils.eng.length(); i++) {
            char keyY = Utils.eng.charAt(i), keyZ = Utils.eng.charAt(i);
            double pY = amountSymY.get(keyY) / n, pZ = amountSymZ.get(keyZ) / n;
            index += pY * pZ;
        }

        return index;
    }
}
