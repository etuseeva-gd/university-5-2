package SecondTask;

import java.util.List;

public class FistSubTask {
    private static final String eng = "abcdefghijklmnopqrstuvwxyz";
    private static final String rus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public void init() {

    }

    private String genRandomSequence(int len) {
        StringBuilder rand = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (this.getRandNum(0, 1) == 0) {
                rand.append(eng.charAt(this.getRandNum(0, eng.length())));
            } else {
                rand.append(eng.charAt(this.getRandNum(0, rus.length())));
            }
        }
        return "";
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
