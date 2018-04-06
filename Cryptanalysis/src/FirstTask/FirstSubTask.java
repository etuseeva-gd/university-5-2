package FirstTask;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import Utils.Utils;

public class FirstSubTask {
    private static final String input = "input_1.1.txt", inputKey = "input_1.1_key.txt", ouputCrypt = "ouput_1.1_crypt.txt";

    public void init() throws IOException {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1: Сгенерировать ключ");
        System.out.println("2: Зашифровать");

        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();

        switch (action) {
            case 1: {
                this.genKey(scanner);
                break;
            }
            case 2: {
                this.crypt();
                break;
            }
            default: {
                System.out.println("Не корректная операция!");
            }
        }
        scanner.close();
    }

    private void genKey(Scanner scanner) throws IOException {
        System.out.println("Ведите длину ключа:");

        int keyLength = scanner.nextInt();
        List<int[]> permutations = new MonocyclicPermutation().genKeys(keyLength);

        int start = 1, end = permutations.size() - 1;
        int index = start + (int) (Math.random() * (end - start + 1));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(inputKey)))) {
            for (int i = 0; i < keyLength; i++) {
                out.print(permutations.get(index)[i] + " ");
            }
        }
    }

    private void crypt() throws IOException {
        try (BufferedReader inKey = new BufferedReader(new InputStreamReader(new FileInputStream(inputKey)));
             BufferedReader inInput = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(ouputCrypt)))) {

            int[] key = Utils.readKey(inKey.readLine());
            StringBuilder text = Main.readText(inInput);

            int keyLength = key.length;
            while (text.length() % keyLength != 0) {
                text.append(" ");
            }

            for (int i = 0; i < text.length(); i += keyLength) {
                String block = text.substring(i, i + keyLength);
                char[] blockCrypt = new char[keyLength];
                for (int j = 0; j < keyLength; j++) {
                    blockCrypt[key[j]] = block.charAt(j);
                }
                out.print(blockCrypt);
            }
        }
    }
}
