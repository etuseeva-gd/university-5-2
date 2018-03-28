import java.util.ArrayList;
import java.util.List;

public class MonocyclicPermutation {
    private List<int[]> permutations = new ArrayList<>();

    public List<int[]> genMonocyclicPermutations(int[] array, int n) {
        if (n <= 0) {
            int[] permutation = array.clone();
            if (isMonocyclicPermutation(permutation)) {
                permutations.add(permutation);
            }
        } else {
            genMonocyclicPermutations(array, n - 1);
            int currentPos = array.length - n;
            for (int i = currentPos + 1; i < array.length; i++) {
                swap(array, currentPos, i);
                genMonocyclicPermutations(array, n - 1);
                swap(array, i, currentPos);
            }
        }
        return permutations;
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private boolean isMonocyclicPermutation(int[] permutation) {
        boolean[] used = new boolean[permutation.length]; //todo: maybe need to initialize
        int currentIndex = 0;

        while(true) {
            int nextIndex = permutation[currentIndex];
            if (!used[nextIndex]) {
                used[nextIndex] = true;
            } else {
                return false;
            }
            if (nextIndex == 0) {
                break;
            }
            currentIndex = nextIndex;
        }

        for (boolean u : used) {
            if (!u) {
                return false;
            }
        }
        return true;
    }
}
