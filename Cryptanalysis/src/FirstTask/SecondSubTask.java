package FirstTask;


import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class SecondSubTask {
    private static final String input = "input_1.2.txt", output = "output_1.2.txt";

    //todo rewrite!! шифр вижинера как сложение по модулю!
    public void init() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Что вы хотите сделать?");
        System.out.println("1. Вычисление множества запретных биграмм языка открытых сообщений");
        System.out.println("2. Построение вспомогательной таблицы для анализа шифра " +
                "перестановки при известной длине периода");

        int action = scanner.nextInt();

        switch (action) {
            case 1: {
                this.first();
                break;
            }
            case 2: {
                this.second(scanner);
                break;
            }
            default: {
                System.out.println("Неверная операция!");
            }
        }
        scanner.close();
    }

    private void first() throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(output)))) {
            StringBuilder text = Main.readText(in);

            int blockLen = 3;
            Map<Integer, List<Integer>> answer = new HashMap<>();
            while (blockLen < text.length() / 2 + 1) {
                boolean findSameSubStr = false;

                Set<Integer> distances = new HashSet<>();
                for (int i = 0; i < text.length() - blockLen; i++) {
                    String firstSubStr = text.substring(i, i + blockLen);
                    for (int j = i + blockLen; j < text.length() - blockLen; j++) {
                        String secondSubStr = text.substring(j, j + blockLen);

                        if (firstSubStr.equals(secondSubStr)) {
                            findSameSubStr = true;
                            distances.add(j - i);
                        }
                    }
                }

                if (!findSameSubStr) {
                    break;
                }

                Map<Integer, Integer> gcds = new HashMap<>();
                List<Integer> listDistances = new ArrayList<>(distances);
                for (int i = 0; i < listDistances.size(); i++) {
                    for (int j = i + 1; j < listDistances.size(); j++) {
                        int gcd = this.gcd(listDistances.get(i), listDistances.get(j));
                        if (gcds.containsKey(gcd)) {
                            gcds.put(gcd, gcds.get(gcd) + 1);
                        } else {
                            gcds.put(gcd, 1);
                        }
                    }
                }

                List<Pair<Integer, Integer>> listDistAndAmount = new ArrayList<>();
                gcds.forEach((key, value) -> {
                    listDistAndAmount.add(new Pair<>(key, value));
                });

                Collections.sort(listDistAndAmount,
                        (a, b) -> a.getValue() > b.getValue() ? -1 : Objects.equals(a.getValue(), b.getValue()) ? 0 : 1);

                List<Integer> mostCommonGCD = new ArrayList<>();
                final int[] mostCommonCount = {0};
                listDistAndAmount.forEach(pair -> {
                    int key = pair.getKey();

                    //@todo maybe only 1
                    if (key != 1 && key != 2 && ++mostCommonCount[0] < 10) {
                        mostCommonGCD.add(key);
                    }
                });

                if (mostCommonGCD.size() > 0) {
                    Collections.sort(mostCommonGCD);
                    answer.put(blockLen, mostCommonGCD);
                }
                blockLen++;
            }

            out.println("Первая колонка: длина блока;");
            out.println("Вторая колонка: самые часто встречаемые НОД;");
            out.println();
            answer.forEach((key, value) -> {
                out.println(key + " : " + value);
            });
        }
    }

    //@todo rewrite
    private static final String output1 = "output_1.3.txt";

    private void second(Scanner scanner) throws FileNotFoundException {
        System.out.println("Ведите длину ключа:");

        int keyLength = scanner.nextInt();
        List<int[]> permutations = new MonocyclicPermutation().genKeys(keyLength);

        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(output1)))) {
            permutations.forEach(permutation -> {
                for (int num : permutation) {
                    out.print(num + " ");
                }
                out.println();
            });
        }
    }

    private int gcd(int a, int b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }
}
