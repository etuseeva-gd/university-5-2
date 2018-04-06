package Utils;

import java.io.*;

public class Utils {
    public static final String eng = "abcdefghijklmnopqrstuvwxyz";
    public static final String rus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static String read(String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            StringBuilder text = new StringBuilder();
            String line = input.readLine();
            while (line != null) {
                text.append(line);
                line = input.readLine();
            }
            return String.valueOf(text);
        }
    }

    public static void print(String fileName, String text) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            out.println(text);
        }
    }

    public static String cleanString(String initString) {
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

    public static String cleanString(String initString, String alph) {
        initString = initString.toLowerCase();
        StringBuilder updatedString = new StringBuilder();

        for (int i = 0; i < initString.length(); i++) {
            char c = initString.charAt(i);
            if (alph.indexOf(c) != -1) {
                updatedString.append(c);
            }
        }
        return String.valueOf(updatedString);
    }

    public static String genRandomSequence(int len) {
        StringBuilder rand = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (Utils.getRandNum(0, 1) == 0) {
                rand.append(eng.charAt(Utils.getRandNum(0, eng.length() - 1)));
            } else {
                rand.append(rus.charAt(Utils.getRandNum(0, rus.length() - 1)));
            }
        }
        return String.valueOf(rand);
    }

    public static int getRandNum(int minValue, int maxValue) {
        int range = (maxValue - minValue) + 1;
        return (int) (Math.random() * range) + minValue;
    }

}
