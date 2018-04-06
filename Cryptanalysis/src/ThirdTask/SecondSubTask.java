package ThirdTask;

import java.io.IOException;
import java.util.*;

import Utils.Utils;
import javafx.util.Pair;

public class SecondSubTask {
    public void init() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите атаку:");
        System.out.println("1. Атака по частотному анализу");
        System.out.println("2. Атака по вероятному слову");
        int attackCode = scanner.nextInt();

        switch (attackCode) {
            case 1: {
                this.firstAttack(scanner);
                break;
            }
            case 2: {
                this.secondAttack(scanner);
                break;
            }
            default: {
                System.out.println("Неверная операция!");
            }
        }

        scanner.close();
    }

    private void firstAttack(Scanner scanner) throws IOException {
        System.out.println("Что вы хотите сделать?");
        /*
            Вход: файл большого текста на языке открытых сообщений.
            Выход: файл алфавита со значениями частот в порядке их убывания.
         */
        System.out.println("1. Вычисление таблицы частот языка открытых сообщений");

        /*
            Вход: файл шифрограммы, файл алфавита со значениями частот, длина ключа.
            Выход: Список наиболее вероятных ключей и расшифрованной ими шифрограммы
         */
        System.out.println("2. Вычисление ключа шифра Виженера при известной длине ключа");

        int action = scanner.nextInt();
        switch (action) {
            case 1: {
                String inputText = "input_3.1.1_text.txt"; //@todo refactor!

                String text = Utils.read(inputText);
                text = Utils.cleanString(text); //@todo проверить насчет пробелов

                Map<Character, Double> frequencies = new HashMap<>();
                for (int i = 0; i < text.length(); i++) {
                    char c = text.charAt(i);

                    double value = 0;
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
                break;
            }
            case 2: {
                break;
            }
            default: {
                System.out.println("Неверная операция!");
            }
        }
    }

    private void secondAttack(Scanner scanner) {
        System.out.println("Что вы хотите сделать?");
        /*
            Вход: Файл большого текста на языке открытых сообщений.
            Выход: Файл словаря наиболее частых слов.
         */
        System.out.println("1. Вычисление таблицы наиболее частых слов в языке открытых сообщений");

        /*
            Вход: файл шифрограммы; файл словаря наиболее частых слов; длина ключа.
            Выход: список вариантов расшифрованной криптограммы при проведении
            вероятного слова (наиболее частого слова).
         */
        System.out.println("2. Вычисление ключа шифра Виженере при известной длине ключа");

        int action = scanner.nextInt();
        switch (action) {
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            default: {
                System.out.println("Неверная операция!");
            }
        }
    }
}
