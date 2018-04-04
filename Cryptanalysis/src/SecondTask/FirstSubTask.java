package SecondTask;


import java.util.Scanner;

public class FirstSubTask {
    private static final String eng = "abcdefghijklmnopqrstuvwxyz";
    private static final String rus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public void init() {
        System.out.println("Выберите режим программы, вычислить индекс для:");
        System.out.println("1. Последовательностей рус./англ. языка");
        System.out.println("2. Случайно сгенерированных последовательностей");

        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();

        switch (action) {
            case 1: {
                break;
            }
            case 2: {
                System.out.println("Введите длину последовательности: ");
                int len = scanner.nextInt();

                String y = this.genRandomSequence(len);
                String z = this.genRandomSequence(len);

                System.out.println("Для последовательностей:");
                System.out.println(y);
                System.out.println(z);
                System.out.println("Индекс равен = " + this.getIndex(y, z));

                break;
            }
            default: {
                System.out.println("Неккоректная операция!");
            }
        }

        scanner.close();
    }

    private String cleanString(String initString) {
        initString = initString.toLowerCase();
        StringBuilder updatedString = new StringBuilder();

        for (int i = 0; i < initString.length(); i++) {
            char c = initString.charAt(i);
            if (Character.isAlphabetic(c)) {
                updatedString.append(c);
            }
        }
        return String.valueOf(updatedString);
    }

    private String genRandomSequence(int len) {
        StringBuilder rand = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (this.getRandNum(0, 1) == 0) {
                rand.append(eng.charAt(this.getRandNum(0, eng.length() - 1)));
            } else {
                rand.append(rus.charAt(this.getRandNum(0, rus.length() - 1)));
            }
        }
        return String.valueOf(rand);
    }

    private int getRandNum(int minValue, int maxValue) {
        int range = (maxValue - minValue) + 1;
        return (int) (Math.random() * range) + minValue;
    }

    private double getIndex(String y, String z) {
        if (y.length() != z.length()) {
            System.out.println("Длины входных последовательностей не совпадают!");
        }

        int n = y.length();

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
