package FourthTask;

import Utils.Utils;
import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

public class SecondSubTask {
    public void init() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Что вы хотите сделать?");
        System.out.println("1. Вычисление таблицы наиболее частых слов в языке открытых сообщений");
        System.out.println("2. Вычисление ключа шифра Виженере при известной длине ключа");

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
    }

    /**
     * Вычисление таблицы наиболее частых слов в языке открытых сообщений
     * Вход: Файл большого текста на языке открытых сообщений.
     * Выход: Файл словаря наиболее частых слов.
     */
    private void firstSubTask() throws IOException {
        String inputText = "input_3.1.1_text.txt"; //@todo refactor!
        String inputAlph = "input_3.1.1_alph.txt"; //@todo refactor!

        String text = Utils.read(inputText);
        String alph = Utils.read(inputAlph);

        text = Utils.cleanString(text, alph) + " "; //@todo проверить насчет пробелов (они должны быть!)

        Map<String, Double> words = new HashMap<>();
        int index = 0;
        while (index < text.length()) {
            int nextIndex = text.indexOf(" ", index);
            if (nextIndex != -1) {
                String word = text.substring(index, nextIndex);
                if (!Objects.equals(word, " ")) {
                    double value = 1;
                    if (words.containsKey(word)) {
                        value = words.get(word) + 1;
                    }
                    words.put(word, value);
                }
            } else {
                // @todo ???
                break;
            }
            index = nextIndex + 1;
        }

        List<Pair<String, Double>> listWords = new ArrayList<>();
        words.forEach((key, value) -> {
            listWords.add(new Pair<>(key, value));
        });

        // @todo maybe move to utils
        Collections.sort(listWords,
                (a, b) -> a.getValue() > b.getValue() ? -1 : Objects.equals(a.getValue(), b.getValue()) ? 0 : 1);

        //@todo out to file
        listWords.forEach(pair -> {
            String symbol = pair.getKey();
            double amount = pair.getValue();

            System.out.println(symbol + " = " + (amount / listWords.size()));
        });
    }

    /**
     * Вычисление ключа шифра Виженере при известной длине ключа
     * <p>
     * Вход: файл шифрограммы; файл словаря наиболее частых слов; длина ключа.
     * Выход: список вариантов расшифрованной криптограммы при проведении
     * вероятного слова (наиболее частого слова).
     */
    private void secondSubTask() {

    }
}
