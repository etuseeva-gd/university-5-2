package ThirdTask;

import Utils.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SecondSubTask {

    /**
     * Построение вспомогательной таблицы для анализа шифра перестановки при известной длине периода
     * Вход: длина периода k; файл шифрограммы перестановки.
     * Выход: файл вспомогательной таблицы
     */
    public void init() throws IOException {
        System.out.println("Введите длину периода:");
        Scanner scan = new Scanner(System.in);
        int keyLength = scan.nextInt(); //Длина периода

        String text = Utils.read(Files.CRYPT_TEXT).toLowerCase(); // @todo clean text???
        List<String> textBlocks = new ArrayList<>();
        for (int i = 0; i < text.length() - keyLength; i += keyLength) {
            textBlocks.add(text.substring(i, i + keyLength));
        }

        List<String> forbiddenBalsams = Files.readBiagrams(Files.FORBIDDEN_BIAGRAMS);

        int[][] table = new int[keyLength][keyLength];
        textBlocks.forEach(block -> {
            for (int i =0; i < block.length(); i++) {
                for (int j = 0; j < block.length(); j++) {
                    String biagram = block.substring(i, i + 1) + block.substring(j, j + 1);
                    if (forbiddenBalsams.contains(biagram)) {
                        System.out.println(biagram);
                        table[i][j] = 1; //@todo 'X'
                    }
                }
            }
        });

        Files.printTable(Files.TABLE, table);
    }
}