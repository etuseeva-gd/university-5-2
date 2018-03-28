package FirstTaskAnya;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Task3 {

    public void task() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter("task3_output.txt");

        System.out.println("Введите длину ключа");
        int keySize = sc.nextInt();

        Permut permut = new Permut();
        int[] str = new int[keySize];
        for (int i = 0; i < keySize; i++) {
            str[i] = i;
        }
        List<int[]> permutation = permut.perm(str ,keySize);

        for (int i = 0; i < permutation.size(); i++) {
            for (int j = 0; j < permutation.get(i).length; j++) {
                out.print(permutation.get(i)[j] + 1);
            }
            out.println();
        }

        out.close();
    }
}
