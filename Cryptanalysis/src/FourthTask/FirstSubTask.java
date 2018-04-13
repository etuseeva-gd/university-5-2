package FourthTask;

import Utils.Utils;
import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

public class FirstSubTask {
    public void init() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Что вы хотите сделать?");
        System.out.println("1. Вычисление таблицы частот языка открытых сообщений");
        System.out.println("2. Вычисление ключа шифра Виженера при известной длине ключа");

        int action = scanner.nextInt();
        switch (action) {
            case 1: {
                this.firstSubTask();
                break;
            }
            case 2: {
                this.secondSubTask();
                break;
            }
            default: {
                System.out.println("Неверная операция!");
            }
        }
        scanner.close();
    }

    /**
     * Вычисление таблицы частот языка открытых сообщений
     *
     * Вход: файл большого текста на языке открытых сообщений.
     * Выход: файл алфавита со значениями частот в порядке их убывания.
     */
    private void firstSubTask() throws IOException {
        String inputText = "input_3.1.1_text.txt"; //@todo refactor!

        String text = Utils.read(inputText);
        text = Utils.cleanString(text); //@todo проверить насчет пробелов

        Map<Character, Double> frequencies = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            double value = 1;
            if (frequencies.containsKey(c)) {
                value = frequencies.get(c) + 1;
            }

            frequencies.put(c, value);
        }

        List<Pair<Character, Double>> listFrequencies = new ArrayList<>();
        frequencies.forEach((key, value) -> {
            listFrequencies.add(new Pair<>(key, value));
        });

        // @todo maybe move to utils
        Collections.sort(listFrequencies,
                (a, b) -> a.getValue() > b.getValue() ? -1 : Objects.equals(a.getValue(), b.getValue()) ? 0 : 1);

        //@todo out to file
        listFrequencies.forEach(pair -> {
            char symbol = pair.getKey();
            double amount = pair.getValue();

            System.out.println(symbol + " = " + (amount / listFrequencies.size()));
        });
    }

    /**
     * Вычисление ключа шифра Виженера при известной длине ключа
     *
     * Вход: файл шифрограммы, файл алфавита со значениями частот, длина ключа.
     * Выход: Список наиболее вероятных ключей и расшифрованной ими шифрограммы
     */
    private void secondSubTask() {

    }
}
