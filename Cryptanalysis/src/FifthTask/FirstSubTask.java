package FifthTask;

import Utils.BreakingUtils;
import Utils.Utils;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FirstSubTask {
    private static final String input = "input_5.1.txt";
    private static final String outputFrequency = "input_5.1_frequency.txt", outputHypothesis = "input_5.1_hypothesis.txt";

    /**
     * Вычисление значений гипотезы Н(0)
     */
    public void init() throws IOException {
        List<Pair<Character, Double>> p = BreakingUtils.getFrequencyOfLetters(input);

        StringBuilder out = new StringBuilder();
        p.forEach(pair -> {
            char symbol = pair.getKey();
            double value = pair.getValue();
            out.append(symbol).append(" = ").append(value).append('\n');
        });
        Utils.print(outputFrequency, String.valueOf(out));

        int m = p.size(); //размер алфавита

        List<Double> hypothesis = new ArrayList<>();
        for (int j = 0; j < m; j++) {
            double h = 0.0;
            for (int i = 0; i < m; i++) {
                for (int k = 0; k < m; k++) {
                    if ((i - k) % m == j % m) {
                        h += p.get(i).getValue() * p.get(k).getValue();
                    }
                }
            }
            hypothesis.add(h);
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < hypothesis.size(); i++) {
            answer.append("P").append(i).append(" = ").append(hypothesis.get(i)).append('\n');
        }
        Utils.print(outputHypothesis, String.valueOf(answer));
    }
}
