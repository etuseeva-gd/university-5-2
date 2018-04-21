import javafx.util.Pair;

import javax.swing.*;
import java.util.*;

public class Graph {
    private List<List<Integer>> vertexes = new ArrayList<>();
    private List<Pair<Integer, Integer>> edges = new ArrayList<>();

    Graph(int[][] matrix) {
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
                    edges.add(new Pair<>(j, i));
                }
            }
        }
    }

    public List<List<Integer>> getVertexes() {
        return vertexes;
    }

    public void coloringGraph() {
        // edge, color number
        Map<Pair<Integer, Integer>, Integer> used = new HashMap<>();
        edges.forEach(edge -> {
            used.put(edge, -1);
        });

        List<Integer> colors = new ArrayList<>();
        edges.forEach(edge -> {
            if (used.get(edge) == -1) {
                int v = edge.getKey(), u = edge.getValue();
                Pair<Integer, Integer> oppositeEdge = new Pair<>(u, v);

                Set<Integer> usedColors = new HashSet<>();
                for (int i = 0; i < vertexes.get(v).size(); i++) {
                    Pair<Integer, Integer> e = new Pair<>(v, vertexes.get(v).get(i));
                    if (used.get(e) != -1) {
                        usedColors.add(used.get(e));
                    }
                }
                for (int i = 0; i < vertexes.get(u).size(); i++) {
                    Pair<Integer, Integer> e = new Pair<>(u, vertexes.get(u).get(i));
                    if (used.get(e) != -1) {
                        usedColors.add(used.get(e));
                    }
                }

                if (colors.size() == usedColors.size()) {
                    Integer color = 0;
                    if (colors.size() != 0) {
                        color = colors.get(colors.size() - 1) + 1;
                    }
                    colors.add(color);

                    used.put(edge, color);
                    used.put(oppositeEdge, color);

                    System.out.println(edge + " " + color);
                } else {
                    for (int color : colors) {
                        if (!usedColors.contains(color)) {
                            used.put(edge, color);
                            used.put(oppositeEdge, color);

                            System.out.println(edge + " " + color);
                            break;
                        }
                    }
                }
            }
        });

        //todo строить от вершин!
        System.out.println(vertexes);
        System.out.println(edges);
        System.out.println(colors.size());
        System.out.println("----");
    }

    public void coloringGraph2() {
        Map<Pair<Integer, Integer>, Integer> used = new HashMap<>();
        edges.forEach(edge -> {
            used.put(edge, -1);
        });

        for (int u = 0; u < vertexes.size(); u++) {
            List<Integer> pairs = vertexes.get(u);
            for (int i = 0; i < pairs.size(); i++) {
                int v = pairs.get(i);
                Pair<Integer, Integer> edge = new Pair<>(u, v);
                if (used.get(edge) == -1) {
                    //coloring

                }
            }
        }
    }
}
