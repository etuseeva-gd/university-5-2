import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.min;

public class Task2 {
    public void task2() throws IOException {

        BufferedReader in_y = new BufferedReader(new FileReader("task2_input_y.txt"));
        BufferedReader in_z = new BufferedReader(new FileReader("task2_input_z.txt"));
        PrintWriter out = new PrintWriter("task2_output.txt");
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
                } else if (meth == 3) {
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
                } else if (meth == 3) {
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

            BufferedReader in_alph = new BufferedReader(new FileReader("task2_alph.txt"));
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

        List<Double> p_y = new ArrayList<>();
        List<Double> p_z = new ArrayList<>();

        for (int i = 0; i < 70; i++) {
            p_y.add((double) 0);
            p_z.add((double) 0);
        }

        for (int i = 0; i < stringBuilderY.length(); i++) {
            char x = stringBuilderY.charAt(i);
            int ind_x = 0;
            if (meth == 1){
                if (eng.indexOf(x) == -1)
                    ind_x = rus.indexOf(x) + 26;
                else
                    ind_x = eng.indexOf(x);
            }
            else if (meth == 2)
                ind_x = eng.indexOf(x);
            else if (meth == 3)
                ind_x = rus.indexOf(x);
            if (ind_x != -1) {
                if (p_y.get(ind_x) == 0) {
                    int count = 0;
                    for (int j = 0; j < stringBuilderY.length(); j++) {
                        if (x == stringBuilderY.charAt(j))
                            count++;
                    }
                    p_y.set(ind_x, (double) count / stringBuilderY.length());
                }
            }
        }

        for (int i = 0; i < stringBuilderZ.length(); i++) {
            char x = stringBuilderZ.charAt(i);
            int ind_x = 0;
            if (meth == 1){
                if (eng.indexOf(x) == -1)
                    ind_x = rus.indexOf(x) + 26;
                else
                    ind_x = eng.indexOf(x);
            }
            else if (meth == 2)
                ind_x = eng.indexOf(x);
            else if (meth == 3)
                ind_x = rus.indexOf(x);
            if (ind_x != -1) {
                if (p_z.get(ind_x) == 0) {
                    int count = 0;
                    for (int j = 0; j < stringBuilderZ.length(); j++) {
                        if (x == stringBuilderZ.charAt(j))
                            count++;
                    }
                    p_z.set(ind_x, (double) count / stringBuilderZ.length());
                }
            }
        }
        PrintWriter out_y = new PrintWriter("task2_output_y.txt");
        PrintWriter out_z = new PrintWriter("task2_output_z.txt");
        out_y.print(stringBuilderY);
        out_z.print(stringBuilderZ);
        out_y.close();
        out_z.close();


        double middleInd = 0;
        for (int i = 0; i < p_y.size(); i++) {
            middleInd += p_y.get(i) * p_z.get(i);
        }
        System.out.println("Средний индекс совпадения = " + middleInd);
        out.print(middleInd);

        in_y.close();
        in_z.close();
        out.close();
    }
}
