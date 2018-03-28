package FirstTask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Permut {

    List<int[]> permut = new ArrayList<>();

    /**
     * вычисление n!
     */
    public int factorial(int n) {
        if (n == 1)
            return n;
        else
            return n * factorial(n - 1);
    }

    /**
     * всевозможные перестановки из n чисел
     */
    public List<int[]> perm(int[] str, int n) {

        if (n <= 0) {
            int[] per = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                per[i] = str[i];
            }
            if (check(per))
                permut.add(per);
        } else {
            perm(str, n - 1);
            int currPos = str.length - n;
            for (int i = currPos + 1; i < str.length; i++) {
                swap(str, currPos, i);
                perm(str, n - 1);
                swap(str, i, currPos);
            }
        }

        return permut;
    }

    private void swap(int[] str, int pos1, int pos2) {
        int t1 = str[pos1];
        str[pos1] = str[pos2];
        str[pos2] = t1;
   }

    private boolean check(int[] permut){
        int num = 0;
        HashSet<Integer> ch = new HashSet<>();
        for (int j = 0; j < permut.length; j++) {
            ch.add(permut[num]);
            num = permut[num];
        }
        if (ch.size() == permut.length)
            return  true;
        else
            return false;
    }
}
