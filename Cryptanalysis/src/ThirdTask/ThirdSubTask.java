package ThirdTask;

import Utils.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ThirdSubTask {

    /**
     * Построение ориентированного леса возможных перестановок.
     * <p>
     * Вход: файл вспомогательной таблицы.
     * Выход: файл ориентированного леса возможных перестановок
     */
    public void init() throws IOException {
        int[][] table = Files.readTable(Files.TABLE);
        int n = table.length; //Длина ключа

        //Считаем, кол-во нулей
        int[] zeroAmount = new int[n];
        for (int i = 0; i < n; i++) {
            zeroAmount[i] = 0;
            for (int j = 0; j < n; j++) {
                if (table[j][i] == 0) {
                    zeroAmount[i]++;
                }
            }
        }

        int minIndex = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (zeroAmount[i] < minIndex) {
                minIndex = zeroAmount[i];
            }
        }

        StringBuilder probableKeys = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (zeroAmount[i] == minIndex) {
                List<Integer> key = new ArrayList<>();
                key.add(i);

                boolean[] used = new boolean[n];
                genKey(table, i, key, used);

                for (Integer keyElement : key) {
                    probableKeys.append(keyElement).append(" ");
                }
                probableKeys.append('\n');
            }
        }

        Utils.print(Files.PROBABLE_KEYS, String.valueOf(probableKeys));
    }

    private List<Integer> genKey(int[][] table, int index, List<Integer> key, boolean[] used) {
        used[index] = true;
        for (int i = 0; i < table.length; i++) {
            if (table[index][i] == 0 && !key.contains(i)) {
                key.add(i);
                genKey(table, i, key, used);
            }
        }
        return key;
    }
}
