package Graphs;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Coloring {
    // Массив использованных цветов
    private List<Integer> colors = null;

    // Мапа соответствия ребра и цвета
    private Map<Pair<Integer, Integer>, Integer> colorsOfEdges = null;

    private String error = null;

    public Coloring(List<Integer> colors, Map<Pair<Integer, Integer>, Integer> colorsOfEdges) {
        this.colors = colors;
        this.colorsOfEdges = colorsOfEdges;
    }

    public Coloring() {
        this.colors = new ArrayList<>();
        this.colorsOfEdges = new HashMap<>();
    }

    public List<Integer> getColors() {
        return colors;
    }

    public Map<Pair<Integer, Integer>, Integer> getColorsOfEdges() {
        return colorsOfEdges;
    }

    public void setError(String error) {
//        this.colors = null;
//        this.colorsOfEdges = null;

        this.error = error;
    }

    public String getError() {
        return error;
    }

    /**
     * Получить количество цветов
     *
     * @return
     */
    public int getColorSize() {
        return this.colors.size();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
//        str.append(getColorSize()).append('\n');
        colorsOfEdges.forEach((edge, color) -> {
            str.append(edge).append(": ").append(color).append('\n');
        });
        return String.valueOf(str);
    }
}
