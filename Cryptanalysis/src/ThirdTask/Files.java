package ThirdTask;

import Utils.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Files {
    public static final String FORBIDDEN_BIAGRAMS = "3_forbidden_biagrams.txt";
    public static final String ALPHABET = "3_alphabet.txt";
    public static final String BIG_TEXT = "3_big_text.txt";
    public static final String CRYPT_TEXT = "3_crypt_text.txt";
    public static final String KEY = "3_key.txt";
    public static final String TABLE = "3_table.txt";
    public static final String PROBABLE_KEYS = "3_probable_keys.txt";
    public static final String PROBABLE_DECRYPTION = "3_probable_decryption.txt";

    //@todo maybe remove
    public void printBiagrams() {

    }

    static public List<String> readBiagrams(String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            List<String> biagrams = new ArrayList<>();
            String line = input.readLine();
            while (line != null) {
                if (!line.equals("") && !line.equals("  ")) {
                    biagrams.add(line);
                }
                line = input.readLine();
            }
            return biagrams;
        }
    }

    static public void printTable(String fileName, int[][] table) throws IOException {
        int n = table[0].length;

        StringBuilder tableString = new StringBuilder();
        tableString.append(n).append('\n');
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tableString.append(table[i][j]).append(" ");
            }
            tableString.append('\n');
        }

        Utils.print(fileName, String.valueOf(tableString));
    }

    static public int[][] readTable(String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line = input.readLine();
            int n = Integer.parseInt(line);
            int[][] table = new int[n][n];
            for (int i = 0; i < n; i++) {
                line = input.readLine();
                String[] numbers = line.split(" ");
                for (int j = 0; j < n; j++) {
                    table[i][j] = Integer.parseInt(numbers[j]);
                }
            }
            return table;
        }
    }

    static public List<int[]> readKeys(String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line = input.readLine();
            List<int[]> keys = new ArrayList<>();
            while (line != null) {
                String[] numbers = line.split(" ");
                int[] key = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    key[i] = Integer.parseInt(numbers[i]);
                }
                keys.add(key);
                line = input.readLine();
            }
            return keys;
        }
    }
}
