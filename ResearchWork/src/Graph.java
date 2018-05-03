import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class Graph {
    private List<List<Integer>> vertexes = new ArrayList<>();
    private List<Pair<Integer, Integer>> edges = new ArrayList<>();
    private int[][] matrix = null;

    Graph(int[][] matrix) {
        this.matrix = matrix;

        int n = matrix.length;

        for (int[] mItem : matrix) {
            vertexes.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (matrix[i][j] == 1) {
                    vertexes.get(i).add(j);
                    vertexes.get(j).add(i);

                    edges.add(new Pair<>(i, j));
                    //edges.add(new Pair<>(j, i)); // Может пригодится
                }
            }
        }
    }

    public List<List<Integer>> getVertexes() {
        return vertexes;
    }

    public List<Pair<Integer, Integer>> getEdges() {
        return edges;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Получить количество вершин графа
     *
     * @return
     */
    public int getVertexesSize() {
        return vertexes.size();
    }

    public Coloring getColoring() throws FileNotFoundException {
        // Получаем максимальную степень вершины графа
        int maxDegree = getMaxDegree();

        // Получаем начальную перестановку
        int[] indexes = new int[edges.size()];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        List<Pair<Integer, Integer>> permutationEdges = new ArrayList<>(edges);

        Coloring minColoring = new Coloring();
        int i = 0;
        while (true) {
            Coloring coloring = localColoring(permutationEdges);

            int colorAmount = coloring.getColorSize();
            if (i == 0 || minColoring.getColorSize() > colorAmount) {
                // @todo Проверить насчет ссылки
                minColoring = coloring;
            }

            if (!nextPermutation(indexes, permutationEdges) || colorAmount == maxDegree) {
                // узнали что граф равен типу 1
                break;
            }

            if (++i == 1000000) {
                // @todo добавить представление графа
                minColoring.setError("Было прервано!");
                break;
            }
        }

        return minColoring;
    }

    private boolean nextPermutation(int[] array, List<Pair<Integer, Integer>> permutation) {
        int i = array.length - 1;

        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        int j = array.length - 1;
        while (array[j] <= array[i - 1]) {
            j--;
        }

        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        for (int k = 0; k < array.length; k++) {
            permutation.set(k, edges.get(array[k]));
        }

        return true;
    }

    /**
     * Получить максимальную степень вершины графа
     *
     * @return
     */
    public int getMaxDegree() {
        // @todo переписать с stream
        int maxDegree = Integer.MIN_VALUE;
        for (List<Integer> endVertexes : vertexes) {
            maxDegree = Math.max(maxDegree, endVertexes.size());
        }
        return maxDegree;
    }

    /**
     * Окраска графа в "тупую". В том порядке в котором были
     * поданы ребра
     *
     * @param edges
     * @return
     */
    private Coloring localColoring(List<Pair<Integer, Integer>> edges) {
        Map<Pair<Integer, Integer>, Integer> used = new HashMap<>();
        edges.forEach(edge -> {
            used.put(edge, -1);
        });

        List<Integer> colors = new ArrayList<>();
        edges.forEach(edge -> {
            if (used.get(edge) == -1) {
                int v = edge.getKey(), u = edge.getValue();

                Set<Integer> usedColors = new HashSet<>();
                for (int i = 0; i < vertexes.get(v).size(); i++) {
                    this.addColors(usedColors, used, i, v);
                }
                for (int i = 0; i < vertexes.get(u).size(); i++) {
                    this.addColors(usedColors, used, i, u);
                }

                if (colors.size() == usedColors.size()) {
                    Integer color = 0;
                    if (colors.size() != 0) {
                        color = colors.get(colors.size() - 1) + 1;
                    }
                    colors.add(color);
                    used.put(edge, color);
                } else {
                    for (int color : colors) {
                        if (!usedColors.contains(color)) {
                            used.put(edge, color);
                            break;
                        }
                    }
                }
            }
        });

        return new Coloring(colors, used);
    }

    // Фунция для добавления в множество usedColors уже использованные цвета.
    private void addColors(Set<Integer> usedColors,
                           Map<Pair<Integer, Integer>, Integer> used,
                           int i, int v) {
        Pair<Integer, Integer> e1 = new Pair<>(v, vertexes.get(v).get(i));
        Pair<Integer, Integer> e2 = new Pair<>(vertexes.get(v).get(i), v);
        if (used.containsKey(e1) && used.get(e1) != -1) {
            usedColors.add(used.get(e1));
        }
        if (used.containsKey(e2) && used.get(e2) != -1) {
            usedColors.add(used.get(e2));
        }
    }

    /**
     * Проверяет граф на полноту. Граф является полным,
     * в котором каждая пара различных вершин смежна.
     *
     * @return
     */
    public boolean isFullGraph() {
        // Количество вершин
        int n = vertexes.size();

        for (List<Integer> endVertexes : vertexes) {
            if (endVertexes.size() != n - 1) {
                return false;
            }
        }

        return true;
    }

    /**
     * Проверяет граф на кубичность. Граф является кубическим,
     * если все степени его вершин = 3.
     *
     * @return
     */
    public boolean isCubicGraph() {
        for (List<Integer> endVertexes : vertexes) {
            if (endVertexes.size() != 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверяет циклический ли это граф. То есть в нем
     * есть только один цикл.
     *
     * @return
     */
    public boolean isCyclicGraph() {
        // @todo implement

        return false;
    }

    /**
     * Проверяет граф на двудольность.
     *
     * @return
     */
    public boolean isBigraph() {
        List<Integer> colors = new ArrayList<>(Collections.nCopies(vertexes.size(), 0));

        for (int s = 0; s < vertexes.size(); s++) {
            if (colors.get(s) == 0) {
                colors.set(s, 1);
                if (!this.dfs(s, colors)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Поиск в ширину
     */
    public void bfs() {

    }

    /**
     * Поиск в глубину
     */
    public boolean dfs(int u, List<Integer> colors) {
        for (int i = 0; i < vertexes.get(u).size(); i++) {
            int v = vertexes.get(u).get(i);
            if (colors.get(v) == 0) {
                colors.set(v, 3 - colors.get(u));
                this.dfs(v, colors);
            } else {
                if (Objects.equals(colors.get(u), colors.get(v))) {
                    return false;
                }
            }
        }
        return true;
    }
}
