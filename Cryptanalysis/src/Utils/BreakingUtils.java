package Utils;

import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

public class BreakingUtils {
    public static List<Pair<Character, Double>> getFrequencyOfLetters(String fileName) throws IOException {
        String text = Utils.read(fileName);
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

        List<Pair<Character, Double>> result = new ArrayList<>();
        listFrequencies.forEach(pair -> {
            char symbol = pair.getKey();
            double amount = pair.getValue();
            result.add(new Pair<>(symbol, amount / listFrequencies.size()));
        });
        return result;
    }
}
