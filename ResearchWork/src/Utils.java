public class Utils {

    /**
     * Декодирование графа полученного из генератора
     *
     * @param strGraph
     * @return
     */
    static public int[][] parseGraph(String strGraph) {
        StringBuilder stringBuilderMatrix = new StringBuilder();
        for (int i = 1; i < strGraph.length(); i++) {
            int number = Utils.parseChar(strGraph.charAt(i));

            StringBuilder str = new StringBuilder();
            str.append(Integer.toBinaryString(number));
            while (str.length() < 6) {
                str.reverse().append("0").reverse();
            }

            stringBuilderMatrix.append(str);
        }
        String stringMatrix = String.valueOf(stringBuilderMatrix);

        int vertexes = Utils.parseChar(strGraph.charAt(0));
        int[][] matrix = new int[vertexes][vertexes];
        int index = 0;
        for (int i = 1; i < vertexes; i++) {
            for (int j = 0; j < i; j++, index++) {
                matrix[i][j] = Integer.parseInt(stringMatrix.substring(index, index + 1));
                matrix[j][i] = Integer.parseInt(stringMatrix.substring(index, index + 1));
            }
        }
        return matrix;
    }

    static private int parseChar(char c) {
        return ((int) c) - 63;
    }

}
