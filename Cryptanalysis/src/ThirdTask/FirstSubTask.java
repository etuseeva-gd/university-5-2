package ThirdTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import Utils.Utils;

public class FirstSubTask {
    void init() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Что вы хотите сделать?");
        System.out.println("1. Вычисление множества запретных биграмм языка открытых сообщений");
        System.out.println("2. Построение вспомогательной таблицы для анализа шифра " +
                "перестановки при известной длине периода");
        System.out.println("3. Построение ориентированного леса возможных перестановок");
        System.out.println("4. Перебор ключей по ориентированному лесу возможных перестановок");

        int action = scanner.nextInt();

        switch (action) {
            case 1: {
                this.first();
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                break;
            }
            default: {
                System.out.println("Неверная операция!");
            }
        }
        scanner.close();
    }

    /**
     * Вычисление множества запретных биграмм языка открытых сообщений
     */
    private void first() throws IOException {
        String inputAlph = "input_3.1.1_alph.txt", inputText = "input_3.1.1_text.txt";

        //Сгенерировать все биграммы языка
        //@todo проверить еще раз на наличие пробела
        String alph = Utils.read(inputAlph);
        Set<String> allBiagrams = new HashSet<>();
        for (int i = 0; i < alph.length(); i++) {
            for (int j = i; j < alph.length(); j++) {
                allBiagrams.add(alph.charAt(i) + "" + alph.charAt(j));
                allBiagrams.add(alph.charAt(j) + "" + alph.charAt(i));
            }
        }

        //Подсчитать все биграммы полученные в тексте
        //@todo нужен текст длиннее
        String text = Utils.read(inputText);
        text = Utils.cleanString(text, alph);

        Set<String> textBiagrams = new HashSet<>();
        for (int i = 0; i < text.length() - 1; i++) {
            textBiagrams.add(text.charAt(i) + "" + text.charAt(i + 1));
        }

        //Взять разницу
        Set<String> bagBiagrams = new HashSet<>(allBiagrams);
        bagBiagrams.removeAll(textBiagrams);

        //@todo вывести по нормальному
        System.out.println(bagBiagrams);
    }

    /**
     * Построение вспомогательной таблицы для анализа шифра перестановки при известной длине периода
     */
    private void second() {

    }
}
