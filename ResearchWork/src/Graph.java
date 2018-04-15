import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<List<Integer>> vertexes = new ArrayList<>();

    Graph(int[][] matrix) {
        int n = matrix.length;

        for (int[] mItem : matrix) {
            vertexes.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    vertexes.get(i).add(j);
                    vertexes.get(j).add(i);
                }
            }
        }
    }

    public List<List<Integer>> getVertexes() {
        return vertexes;
    }
}
