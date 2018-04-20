package ThirdTask;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import Utils.Utils;

public class FirstSubTask {
    /**
     * Вычисление множества запретных биграмм языка открытых сообщений

     * Вход: файл с большим текстом на языке открытых сообщений.
     * Выход: файл алфавита; файл запретных биграмм.
     */
    void init() throws IOException {
        //@todo проверить еще раз на наличие пробела
        String alph = Utils.read(Files.ALPHABET);
        Set<String> allBiagrams = new HashSet<>();
        for (int i = 0; i < alph.length(); i++) {
            for (int j = i; j < alph.length(); j++) {
                allBiagrams.add(alph.charAt(i) + "" + alph.charAt(j));
                allBiagrams.add(alph.charAt(j) + "" + alph.charAt(i));
            }
        }

        //Подсчитать все биграммы полученные в тексте
        //@todo нужен текст длиннее
        String text = Utils.read(Files.BIG_TEXT);
        text = Utils.cleanString(text, alph);

        Set<String> textBiagrams = new HashSet<>();
        for (int i = 0; i < text.length() - 1; i++) {
            textBiagrams.add(text.charAt(i) + "" + text.charAt(i + 1));
        }

        //Взять разницу
        Set<String> bagBiagrams = new HashSet<>(allBiagrams);
        bagBiagrams.removeAll(textBiagrams);

        StringBuilder outBiagrams = new StringBuilder();
        bagBiagrams.forEach(biagram -> {
            outBiagrams.append(biagram).append("\n");
        });

        Utils.print(Files.FORBIDDEN_BIAGRAMS, String.valueOf(outBiagrams));
    }
}
