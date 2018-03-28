package FirstTask;

import java.io.*;
import java.util.*;

public class SecondSubTask {
    private static final String input = "input_1.2.txt", output = "output_1.2.txt", outputGDC = "output_gcd_1.2.txt";

    public void init() throws IOException {
        try (BufferedReader inInput = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
             PrintWriter outGCD = new PrintWriter(new BufferedOutputStream(new FileOutputStream(outputGDC)));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(output)))) {
            StringBuilder text = Main.readText(inInput);

            Set<Integer> test = new HashSet<>();

            int blockLen = 2;
            while (blockLen < text.length() / 2 + 1) {
                boolean findSameSubStr = false;

                Map<Integer, Integer> pairs = new HashMap<>();

                for (int i = 0; i < text.length() - blockLen; i++) {
                    String firstSubStr = text.substring(i, i + blockLen);
                    for (int j = i + blockLen; j < text.length() - blockLen; j++) {
                        String secondSubStr = text.substring(j, j + blockLen);

                        if (firstSubStr.equals(secondSubStr)) {
                            findSameSubStr = true;

                            int dist = j - i;
                            if (pairs.containsKey(dist)) {
                                pairs.put(dist, pairs.get(dist) + 1);
                            } else {
                                pairs.put(dist, 1);
                            }
                        }
                    }
                }

                int maxDist = 0;
                for (int key : pairs.keySet()) {
                    maxDist = Math.max(maxDist, pairs.get(key));
                }

                List<Integer> gcd = new ArrayList<>();
                for (int key : pairs.keySet()) {
                    if (pairs.get(key) >= maxDist / 2) {
                        gcd.add(key);
                    }
                }

                Set<Integer> gcdOfPairs = new HashSet<>();
                for (int i = 0; i < gcd.size(); i++) {
                    for (int j = i + 1; j < gcd.size(); j++) {
                        gcdOfPairs.add(this.gcd(gcd.get(i), gcd.get(j)));
                    }
                }

                if (gcdOfPairs.size() != 0) {
                    outGCD.print(blockLen + " : ");
                    for (int gcdOfPair : gcdOfPairs) {
                        test.add(gcdOfPair);
                        outGCD.print(gcdOfPair + " ");
                    }
                    outGCD.println();
                }


                if (!findSameSubStr) {
                    break;
                }

                blockLen++;
            }

            List<Integer> gcd = new ArrayList<>(test);
            Set<Integer> gcdOfPairs = new HashSet<>();
            for (int i = 0; i < gcd.size(); i++) {
                for (int j = 0; j < gcd.size(); j++) {
                    gcdOfPairs.add(this.gcd(gcd.get(i), gcd.get(j)));
                }
            }

            out.println("Возможная длина ключа: ");
            for (int dist :  gcdOfPairs) {
                out.print(dist + " ");
            }
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
