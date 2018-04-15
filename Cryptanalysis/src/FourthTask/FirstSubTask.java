package FourthTask;

import Utils.*;
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
     * <p>
     * Вход: файл большого текста на языке открытых сообщений.
     * Выход: файл алфавита со значениями частот в порядке их убывания.
     */
    private void firstSubTask() throws IOException {
        String inputText = "input_4.1.1_text.txt", output = "output_4.1.1.txt"; //@todo refactor!
        List<Pair<Character, Double>> listFrequencies = BreakingUtils.getFrequencyOfLetters(inputText);
        StringBuilder out = new StringBuilder();
        listFrequencies.forEach(pair -> {
            char symbol = pair.getKey();
            double value = pair.getValue();
            out.append(symbol).append(" = ").append(value).append('\n');
        });
        Utils.print(output, String.valueOf(out));
    }

    /**
     * Вычисление ключа шифра Виженера при известной длине ключа
     * <p>
     * Вход: файл шифрограммы, файл алфавита со значениями частот, длина ключа.
     * Выход: Список наиболее вероятных ключей и расшифрованной ими шифрограммы
     */
    private void secondSubTask() {

    }
}
