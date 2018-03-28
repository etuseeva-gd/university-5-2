import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task1 {

    public void task() throws IOException {

        Scanner sc = new Scanner(System.in);



        while (true) {

            System.out.println("1. Сгенерировать ключ");
            System.out.println("2. Зашифровать");
            int meth = sc.nextInt();

            switch (meth) {
                case 1:{
                    PrintWriter out_key = new PrintWriter("task1_key.txt");

                    System.out.println("Введите длину ключа");
                    int keySize = sc.nextInt();

                    int[] str = new int[keySize];
                    for (int i = 0; i < keySize; i++) {
                        str[i] = i;
                    }
                    Permut permut = new Permut();
                    List<int[]> permutation = permut.perm(str, keySize);

                    int numbKey = (int) (Math.random() * permut.factorial(keySize - 1) + 1);
                    for (int i = 0; i < keySize; i++) {
                        out_key.print(permutation.get(numbKey - 1)[i] + 1);
                    }
                    out_key.close();

                    break;
                }
                case 2:{

                    BufferedReader in = new BufferedReader(new FileReader("task1_input.txt"));
                    BufferedReader in_key = new BufferedReader(new FileReader("task1_key.txt"));
                    PrintWriter out = new PrintWriter("task1_output.txt");

                    String skey = in_key.readLine();
                    int[] key = new int[skey.length()];
                    for (int i = 0; i < skey.length(); i++) {
                        key[i] = (skey.charAt(i) - '0') - 1;
                    }

                    StringBuilder stringBuilder = new StringBuilder();
                    String s = in.readLine();
                    while (s != null) {
                        stringBuilder.append(s);//.append("\n");
                        s = in.readLine();
                    }

                    if (stringBuilder.length() % skey.length() != 0)
                        for (int i = 0; i < stringBuilder.length() % skey.length(); i++) {
                            stringBuilder.append(" ");
                        }
                    for (int i = 0; i < stringBuilder.length(); i += skey.length()) {
                        String block = stringBuilder.substring(i, skey.length() + i);
                        char[] x = new char[block.length()];
                        for (int j = 0; j < block.length(); j++) {
                            x[key[j]] = block.charAt(j);
                        }
                        for (int j = 0; j < block.length(); j++) {
                            out.print(x[j]);
                        }
                    }

                    in_key.close();
                    in.close();
                    out.close();

                    return;
                }
            }
        }
    }
}
