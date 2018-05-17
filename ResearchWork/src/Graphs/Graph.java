package Graphs;

import javafx.util.Pair;

import java.util.*;

public class Graph {
    private String strView = null;
    private List<List<Integer>> vertexes = new ArrayList<>();
    private List<Pair<Integer, Integer>> edges = new ArrayList<>();
    private int[][] matrix = null;

    private Map<Pair<Integer, Integer>, Set<Pair<Integer, Integer>>> edgesList = new HashMap<>();

    /**
     * Нужно для того чтобы прервать многопоточноть
     *
     * @param strView
     */
    public Graph(String strView) {
        this.strView = strView;
    }

    /**
     * Обычный конструктор
     *
     * @param matrix
     * @param strView
     */
    public Graph(int[][] matrix, String strView) {
        // Нужно для отладки и возможно последующего перезапуска программы
        this.strView = strView;
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

        edges.forEach(edge -> {
            int u = edge.getKey(), v = edge.getValue();
            Set<Pair<Integer, Integer>> neighborsEdges = new HashSet<>();
            edges.forEach(locEdge -> {
                if (edge != locEdge) {
                    int uu = locEdge.getKey(), vv = locEdge.getValue();
                    if (u == uu || v == vv || u == vv || v == uu) {
                        neighborsEdges.add(locEdge);
                    }
                }
            });
            edgesList.put(edge, neighborsEdges);
        });
    }

    public List<Pair<Integer, Integer>> getEdges() {
        return edges;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public String getStrView() {
        return strView;
    }

    /**
     * Получить количество вершин графа
     *
     * @return
     */
    public int getVertexesSize() {
        return vertexes.size();
    }

    private List<Pair<Integer, Integer>> getPermutation() {
        List<Pair<Integer, Integer>> edgesAmount = new ArrayList<>();
        int i = 0;
        for (Pair<Integer, Integer> edge : edges) {
            int amount = vertexes.get(edge.getKey()).size() + vertexes.get(edge.getValue()).size();
            edgesAmount.add(new Pair<>(i++, amount));
        }

        edgesAmount.sort((a, b) -> a.getValue() > b.getValue() ? -1 : Objects.equals(a.getValue(), b.getValue()) ? 0 : 1);
        Collections.reverse(edgesAmount);

        List<Pair<Integer, Integer>> updatedEdges = new ArrayList<>();
        edgesAmount.forEach(pair -> {
            updatedEdges.add(edges.get(pair.getKey()));
        });

        Collections.reverse(updatedEdges);

        return updatedEdges;
    }

    private int stopper = 0;

    /**
     * Проверят является граф, графом типа 1
     *
     * @return
     */
    public boolean isFirstType() {
        // @todo remove
        this.stopper = 0;

        int maxDegree = this.getMaxDegree();
        int n = this.getVertexesSize();
        int index;
        if (isCubicGraph() || isBigraph()) {
            index = maxDegree;
        } else if (isFullGraph()) {
            index = n % 2 == 0 ? n - 1 : n;
        } else if (isCyclicGraph()) {
            index = n % 2 == 0 ? 2 : 3;
        } else {
            Map<Pair<Integer, Integer>, Integer> used = new HashMap<>();
            List<Pair<Integer, Integer>> edges = this.getPermutation();
            index = this.coloringEdge(edges.get(0), maxDegree, used, edges) ? maxDegree : maxDegree + 1;
        }

        return maxDegree == index;
    }

    /**
     * Пробует покрасить ребро в один из 0, ..., numberOfColors - 1 цветов
     *
     * @param edge
     * @param numberOfColors
     * @param used
     * @param edges
     * @return
     */
    private boolean coloringEdge(Pair<Integer, Integer> edge,
                                 int numberOfColors,
                                 Map<Pair<Integer, Integer>, Integer> used,
                                 List<Pair<Integer, Integer>> edges) {
        List<Integer> colors = this.getColor(edge, numberOfColors, used);
        if (colors == null) {
            stopper++;
            return false;
        }

        for (Integer color : colors) {
            used.put(edge, color);

            Pair<Integer, Integer> nextEdge = this.getNextEdge(edge, edges);
            if (nextEdge == null) {
                return true;
            }

            boolean isOkColoring = this.coloringEdge(nextEdge, numberOfColors, used, edges);

            //@todo remove
            if (stopper > 100000000) {
                return false;
            }

            if (!isOkColoring) {
                used.remove(edge);
            } else {
                break;
            }
        }

        return used.size() == edges.size();
    }

    private Pair<Integer, Integer> getNextEdge(Pair<Integer, Integer> edge, List<Pair<Integer, Integer>> edges) {
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).equals(edge) && i + 1 < edges.size()) {
                return edges.get(i + 1);
            }
        }
        return null;
    }

    private List<Integer> getColor(Pair<Integer, Integer> edge, int numberOfColors, Map<Pair<Integer, Integer>, Integer> used) {
        Set<Pair<Integer, Integer>> neighborsEdges = edgesList.get(edge);
        Set<Integer> usedColors = new HashSet<>();
        for (Pair<Integer, Integer> locEdge : neighborsEdges) {
            if (used.containsKey(locEdge)) {
                usedColors.add(used.get(locEdge));
            }
        }

        if (usedColors.size() == numberOfColors) {
            return null;
        }

        List<Integer> colors = new ArrayList<>();
        for (int i = 0; i < numberOfColors; i++) {
            if (!usedColors.contains(i)) {
                colors.add(i);
            }
        }

        return colors;
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
     * Свойство говорит о том, есть ли в текущем графе циклы
     */
    private boolean hasCycle = false;

    /**
     * Проверяет циклический ли это граф. То есть в нем
     * есть только один цикл.
     *
     * @return
     */
    public boolean isCyclicGraph() {
        // Проверить что все степени равны 2
        for (List<Integer> pairs : vertexes) {
            if (pairs.size() != 2) {
                return false;
            }
        }

        List<Integer> colors = new ArrayList<>(Collections.nCopies(vertexes.size(), 0));

        // Запустили dfs из 0 вершины
        this.dfsForCyclicGraph(0, colors);

        // Проверяем связность (все вершины были посещены и их цвет не равен 0
        for (int i = 0; i < vertexes.size(); i++) {
            if (colors.get(i) == 0) {
                return false;
            }
        }

        return this.hasCycle;
    }

    /**
     * Поиск в глубину
     */
    private void dfsForCyclicGraph(int u, List<Integer> colors) {
        colors.set(u, 1);
        for (int i = 0; i < vertexes.get(u).size(); i++) {
            int v = vertexes.get(u).get(i);
            if (colors.get(v) == 0) {
                this.dfsForCyclicGraph(v, colors);
            } else if (colors.get(v) == 1) {
                this.hasCycle = true;
            }
        }
        colors.set(u, 2);
    }

    /**
     * Свойство говорит о том, двудольный ли наш граф.
     */
    private boolean isBigraphFlag = true;

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
                this.dfsForBigraph(s, colors);
                if (!isBigraphFlag) {
                    return false;
                }
            }
        }

        return isBigraphFlag;
    }

    /**
     * Поиск в глубину
     */
    private void dfsForBigraph(int u, List<Integer> colors) {
        for (int i = 0; i < vertexes.get(u).size(); i++) {
            int v = vertexes.get(u).get(i);
            if (colors.get(v) == 0) {
                colors.set(v, 3 - colors.get(u));
                this.dfsForBigraph(v, colors);
            } else if (Objects.equals(colors.get(u), colors.get(v))) {
                this.isBigraphFlag = false;
            }
        }
    }

    /**
     * Возвращает количество треугольников, содержащихся в данном графе
     *
     * @return
     */
    public int getNumberOfTriangles() {
        int numberOfTriangles = 0;
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrix[i][j] == 1) {
                    for (int k = j + 1; k < n; k++) {
                        if (matrix[j][k] == 1 && matrix[i][k] == 1) {
                            numberOfTriangles++;
                        }
                    }
                }
            }
        }
        return numberOfTriangles;
    }

    /**
     * Проверяет является ли данный граф регулярным
     *
     * @return
     */
    public boolean isRegularGraph() {
        int degree = vertexes.get(0).get(0);
        for (List<Integer> pairs : vertexes) {
            if (degree != pairs.size()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Graph G {");
        for (int i = 0; i < vertexes.size(); i++) {
            stringBuilder.append(i).append(" ");
        }
        edges.forEach(edge -> {
            stringBuilder.append(edge.getKey()).append("--").append(edge.getValue()).append(";");
        });
        stringBuilder.append("}\n");
        return String.valueOf(stringBuilder);
    }
}
