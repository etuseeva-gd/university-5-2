package FirstTask;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class ThirdSubTask {
    private static final String output1 = "output_1.3.txt";

    public void init() throws IOException {
        System.out.println("Переребор ключей моноциклической перестановки.");

        Scanner scanner = new Scanner(System.in);
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
        scanner.close();
    }
}
