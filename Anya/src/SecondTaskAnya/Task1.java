package SecondTaskAnya;

import java.io.*;
import java.util.Scanner;

import static java.lang.Integer.min;

public class Task1 {

    public void task1() throws IOException {

        BufferedReader in_y = new BufferedReader(new FileReader("task1_input_y.txt"));
        BufferedReader in_z = new BufferedReader(new FileReader("task1_input_z.txt"));
        PrintWriter out = new PrintWriter("task1_output.txt");
        Scanner sc = new Scanner(System.in);

        String eng = "abcdefghijklmnopqrstuvwxyz";
        String rus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

        System.out.println("1. случайная последовательность");
        System.out.println("2. последовательность английского языка");
        System.out.println("3. последовательность русского языка");
        int meth = sc.nextInt();

        StringBuilder stringBuilderY = new StringBuilder();
        StringBuilder stringBuilderZ = new StringBuilder();

        if (meth != 1) {
            StringBuilder str_y = new StringBuilder();
            String y = in_y.readLine();
            while (y != null) {
                y = y.toLowerCase();
                if (meth == 2) {
                    for (int i = 0; i < y.length(); i++) {
                        boolean flag = true;
                        char x = y.charAt(i);
                        if (eng.indexOf(x) == -1)
                            flag = false;
                        if (!flag) {
                            y = y.substring(0, i) + y.substring(i + 1);
                            i--;
                        }
                    }
                }
                else if (meth == 3) {
                    for (int i = 0; i < y.length(); i++) {
                        boolean flag = true;
                        char x = y.charAt(i);
                        if (rus.indexOf(x) == -1)
                            flag = false;
                        if (!flag) {
                            y = y.substring(0, i) + y.substring(i + 1);
                            i--;
                        }
                    }
                }
                str_y.append(y);
                y = in_y.readLine();
            }
            String z = in_z.readLine();
            StringBuilder str_z = new StringBuilder();
            while (z != null) {
                z = z.toLowerCase();
                if (meth == 2) {
                    for (int i = 0; i < z.length(); i++) {
                        boolean flag = true;
                        char x = z.charAt(i);
                        if (eng.indexOf(x) == -1)
                            flag = false;
                        if (!flag) {
                            z = z.substring(0, i) + z.substring(i + 1);
                            i--;
                        }
                    }
                }
                else if (meth == 3) {
                    for (int i = 0; i < z.length(); i++) {
                        boolean flag = true;
                        char x = z.charAt(i);
                        if (rus.indexOf(x) == -1)
                            flag = false;
                        if (!flag) {
                            z = z.substring(0, i) + z.substring(i + 1);
                            i--;
                        }
                    }
                }
                str_z.append(z);
                z = in_z.readLine();
            }

            int min_sz = min(str_y.length(), str_z.length());

            stringBuilderY = new StringBuilder(str_y.substring(0, min_sz));
            stringBuilderZ = new StringBuilder(str_z.substring(0, min_sz));
        }
        else if (meth == 1){

            BufferedReader in_alph = new BufferedReader(new FileReader("task1_alph.txt"));
            String alph = in_alph.readLine();
            System.out.println("Введите длину последовательности");
            int size = sc.nextInt();

            for (int i = 0; i < size; i++) {
                int x1 = (int) (Math.random() * alph.length());
                int x2 = (int) (Math.random() * alph.length());

                stringBuilderY.append(alph.charAt(x1));
                stringBuilderZ.append(alph.charAt(x2));
            }

            in_alph.close();
        }

        PrintWriter out_y = new PrintWriter("task1_output_y.txt");
        PrintWriter out_z = new PrintWriter("task1_output_z.txt");
        out_y.print(stringBuilderY);
        out_z.print(stringBuilderZ);
        out_y.close();
        out_z.close();

        int count = 0;
        for (int i = 0; i < stringBuilderY.length(); i++) {
            if (stringBuilderY.charAt(i) == stringBuilderZ.charAt(i))
                count++;
        }

        double ind = (double) count / stringBuilderY.length();

        System.out.println("Индекс совпадения = " + ind);
        out.print(ind);

        in_y.close();
        in_z.close();
        out.close();
    }
}
