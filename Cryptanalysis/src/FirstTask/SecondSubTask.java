package FirstTask;

import java.io.*;
import java.util.*;

public class SecondSubTask {
    private static final String input = "input_1.2.txt", output = "output_1.2.txt";

    public void init() throws IOException {
        try (BufferedReader inInput = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(output)))) {
            StringBuilder text = Main.readText(inInput);

            int blockLen = 2;
            Map<Integer, Integer> answer = new HashMap<>();
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

                Integer gcd = null; //@todo change
                for (Integer distance : distances) {
                    if (gcd == null) {
                        gcd = distance;
                    } else {
                        gcd = this.gcd(gcd, distance);
                    }
                }

                if (!findSameSubStr) {
                    break;
                }

                answer.put(blockLen, gcd);
                blockLen++;
            }

            out.println("Длина, НОД:");
            answer.forEach((key, value) -> {
                out.println(key + ": " + value);
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
