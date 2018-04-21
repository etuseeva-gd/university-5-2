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
                    //edges.add(new Pair<>(j, i)); //закоментить лдя color3
                }
            }
        }
    }

    public List<List<Integer>> getVertexes() {
        return vertexes;
    }

    public void coloringGraph() {
        System.out.println("Граф:");
        System.out.println(vertexes);
        System.out.println(edges);

        int maxDegree = getDegree();

        List<Integer> indexes = new ArrayList<>(Collections.nCopies(edges.size(), 0));
        List<Pair<Integer, Integer>> permutationEdges = new ArrayList<>(edges);
        while (true) {
            int colorAmount = coloring(permutationEdges);

            //@todo check
            if (colorAmount == maxDegree || colorAmount == maxDegree + 1) {
                System.out.println("Максимальная степень = " + maxDegree);
                System.out.println("Точная покраска = " + colorAmount + " цвета(ов)");
                break;
            }

            getNextPermutation(indexes, permutationEdges);
            //System.out.println(permutationEdges);
        }

        System.out.println("----");
    }

    public boolean getNextPermutation(List<Integer> indexes, List<Pair<Integer, Integer>> permutation) {
        int len1 = edges.size() - 1, i;
        for (i = permutation.size() - 1; i >= 0 && permutation.get(i).equals(edges.get(len1)); --i) {
            indexes.set(i, 0);
            permutation.set(i, edges.get(0));
        }

        if (i < 0) {
            return false;
        }

        permutation.set(i, edges.get(indexes.set(i, indexes.get(i) + 1)));
        return true;
    }

    public int getDegree() {
        int maxDegree = Integer.MIN_VALUE;
        for (List<Integer> pairs : vertexes) {
            if (pairs.size() > maxDegree) {
                maxDegree = pairs.size();
            }
        }
        return maxDegree;
    }

    public int coloring(List<Pair<Integer, Integer>> edges) {
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
                    Pair<Integer, Integer> e1 = new Pair<>(v, vertexes.get(v).get(i));
                    Pair<Integer, Integer> e2 = new Pair<>(vertexes.get(v).get(i), v);
                    if (used.containsKey(e1) && used.get(e1) != -1) {
                        usedColors.add(used.get(e1));
                    }
                    if (used.containsKey(e2) && used.get(e2) != -1) {
                        usedColors.add(used.get(e2));
                    }
                }
                for (int i = 0; i < vertexes.get(u).size(); i++) {
                    Pair<Integer, Integer> e1 = new Pair<>(u, vertexes.get(u).get(i));
                    Pair<Integer, Integer> e2 = new Pair<>(vertexes.get(u).get(i), u);
                    if (used.containsKey(e1) && used.get(e1) != -1) {
                        usedColors.add(used.get(e1));
                    }
                    if (used.containsKey(e2) && used.get(e2) != -1) {
                        usedColors.add(used.get(e2));
                    }
                }

                if (colors.size() == usedColors.size()) {
                    Integer color = 0;
                    if (colors.size() != 0) {
                        color = colors.get(colors.size() - 1) + 1;
                    }
                    colors.add(color);
                    used.put(edge, color);
                    //used.put(oppositeEdge, color);
//                    System.out.println(edge + " " + color);
                } else {
                    for (int color : colors) {
                        if (!usedColors.contains(color)) {
                            used.put(edge, color);
                            //used.put(oppositeEdge, color);
//                            System.out.println(edge + " " + color);
                            break;
                        }
                    }
                }
            }
        });

        return colors.size();
    }

    /*public void coloringGraph2() {
        Map<Pair<Integer, Integer>, Integer> used = new HashMap<>();
        edges.forEach(edge -> {
            used.put(edge, -1);
        });

        List<Integer> colors = new ArrayList<>();
        for (int u = 0; u < vertexes.size(); u++) {
            List<Integer> pairs = vertexes.get(u);
            for (int i = 0; i < pairs.size(); i++) {
                int v = pairs.get(i);
                Pair<Integer, Integer> edge = new Pair<>(u, v);
                if (used.get(edge) == -1) {
                    //coloring
                    Pair<Integer, Integer> oppositeEdge = new Pair<>(v, u);
                    Set<Integer> usedColors = new HashSet<>();

                    for (int j = 0; j < vertexes.get(v).size(); j++) {
                        Pair<Integer, Integer> e = new Pair<>(v, vertexes.get(v).get(j));
                        if (used.get(e) != -1) {
                            usedColors.add(used.get(e));
                        }
                    }
                    for (int j = 0; j < vertexes.get(u).size(); j++) {
                        Pair<Integer, Integer> e = new Pair<>(u, vertexes.get(u).get(j));
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
            }
        }

        System.out.println(vertexes);
        System.out.println(edges);
        System.out.println(colors.size());
        System.out.println("----");
    }

    public void coloringGraph3() {
        int n = edges.size(); //количество вершин в новом графе
        List<List<Integer>> vertexes = new ArrayList<>();
        edges.forEach(edge -> {
            vertexes.add(new ArrayList<>());
        });

        Map<Pair<Integer, Integer>, Integer> edgeIndexes = new HashMap<>();
        int index = 0;
        for (Pair<Integer, Integer> edge : edges) {
            edgeIndexes.put(edge, index);
            edgeIndexes.put(new Pair<>(edge.getValue(), edge.getKey()), index);
            index++;
        }

        System.out.println("===============");
        System.out.println(edges);
        for (int i = 0; i < edges.size(); i++) {
            int u = edges.get(i).getKey(), v = edges.get(i).getValue();

            for (int j = 0; j < edges.size(); j++) {
                int uu = edges.get(j).getKey(), vv = edges.get(j).getValue();
                if ((u == uu && v != vv) || (u == vv && v != uu) || (u != uu && v == vv) ||(u != vv && v == uu)) {
                    System.out.println(edges.get(i) + " - " + edges.get(j));
                    vertexes.get(i).add(j);
                }
            }
        }

        vertexes.forEach(System.out::println);
        System.out.println("===============");

        // in vertexes - находится нов граф
    }*/
}
