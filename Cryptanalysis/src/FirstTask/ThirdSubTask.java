package FirstTask;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ThirdSubTask {
    private static final String output = "output_1.3.txt";

    public void init() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ведите длину ключа:");

        int keyLength = scanner.nextInt();
        List<int[]> permutations = new MonocyclicPermutation().genKeys(keyLength);

        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(output)))) {
            permutations.forEach(permutation -> {
                for (int num : permutation) {
                    out.print(num + " ");
                }
                out.println();
            });
        }
    }
}
