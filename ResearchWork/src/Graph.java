import javafx.util.Pair;

import javax.swing.*;
import java.io.*;
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
                    //edges.add(new Pair<>(j, i)); // Может пригодится
                }
            }
        }
    }

    public List<List<Integer>> getVertexes() {
        return vertexes;
    }

    public void coloringGraph() throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(new File("nir_report.txt"), true)));

        out.println("Граф:");
        out.println(vertexes);
        out.println(edges);

        int maxDegree = getDegree();

        int[] indexes = new int[edges.size()];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        List<Pair<Integer, Integer>> permutationEdges = new ArrayList<>(edges);

        int minColorAmount = maxDegree + 2;

        int i = 0;
        while (true) {
            int colorAmount = coloring(permutationEdges);
            minColorAmount = Math.min(colorAmount, minColorAmount);

            //@todo check
//            colorAmount == maxDegree || colorAmount == maxDegree + 1
            if (!nextPermutation(indexes, permutationEdges) || colorAmount == maxDegree) {
                break;
            }

            //out.println(permutationEdges);
            if (++i == 1000000) {
                System.out.println("Было прервано, результат не точен!");
                break;
            }
        }

        out.println("Максимальная степень = " + maxDegree);
        out.println("Точная покраска = " + minColorAmount + " цвета(ов)" + (minColorAmount == maxDegree + 1 ? " !!!!!" : ""));
        out.println("-----------");

        out.close();
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
//                    System.out.println(edge + " " + color);
                } else {
                    for (int color : colors) {
                        if (!usedColors.contains(color)) {
                            used.put(edge, color);
//                            System.out.println(edge + " " + color);
                            break;
                        }
                    }
                }
            }
        });

        /*System.out.println("---");
        used.forEach((edge, color) -> {
            System.out.println(edge + " = " + color);
        });
        System.out.println(colors.size());
        System.out.println("---");*/

        return colors.size();
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

}
