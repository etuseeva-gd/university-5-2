import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

public class Screening {

    /**
     * http://e-maxx.ru/algo/bipartite_checking
     *
     * @return
     */
    public boolean isBigraph(List<List<Integer>> vertexes) {
        List<Boolean> used = new ArrayList<>(Collections.nCopies(vertexes.size(), false));
        List<Integer> part = new ArrayList<>(Collections.nCopies(vertexes.size(), -1));

        for (int i = 0; i < vertexes.size(); i++) {
            // вершина не находится ни в какой доле
            if (part.get(i) == -1) {
                int h = 0, t = 0;

                part.set(i, 0);

            }
        }

        return false;
    }



    public boolean hasCycle() {
        return false;
    }

    public boolean isConnected() {
        return false;
    }

    /**
     * Поиск в ширину
     */
    public void bfs() {

    }

    /**
     * Поиск в глубину
     */
    public void dfs(int startVertex, List<Boolean> used, List<List<Integer>> vertexes) {
        // List<Boolean> used = new ArrayList<>(Collections.nCopies(vertexes.size(), false));
        used.set(startVertex, true);
        for (int i = 0; i < vertexes.get(startVertex).size(); i++) {
            int endVertex = vertexes.get(startVertex).get(i);
            if (!used.get(endVertex)) {
                this.dfs(endVertex, used, vertexes);
            }
        }
    }

    /**
     * Поиск в глубину
     */
    public void dfs2(int startVertex, List<Integer> colors, List<List<Integer>> vertexes) {
        for (int i = 0; i < vertexes.get(startVertex).size(); i++) {
            int endVertex = vertexes.get(startVertex).get(i);
            if (colors.get(endVertex) == 0) {
                colors.set(endVertex, 3 - colors.get(startVertex));
                this.dfs2(endVertex, colors, vertexes);
            } else if (true) {

            }
        }
    }
}
