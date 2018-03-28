package SecondTaskAnya;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task3 {

    public void task3() throws IOException {

        Scanner sc = new Scanner(System.in);
        BufferedReader in_alph = new BufferedReader(new FileReader("task3_alph.txt"));
        BufferedReader in_key = new BufferedReader(new FileReader("task3_key.txt"));

        String alph = in_alph.readLine();
        List<String> table = new ArrayList<>();
        table.add(alph);
        for (int i = 0; i < alph.length() - 1; i++) {
            String s = alph.substring(i + 1) + alph.substring(0, i + 1);
            table.add(s);
        }
        String key = in_key.readLine();

        System.out.println("1. зашифровать открытый текст");
        System.out.println("2. расшифровать криптограмму");
        int meth = sc.nextInt();

        switch (meth) {
            case 1: {
                BufferedReader in_text = new BufferedReader(new FileReader("task3_input.txt"));
                PrintWriter out_cipher = new PrintWriter("task3_outputCipher.txt");
                PrintWriter outShiftText = new PrintWriter("task3_shiftText.txt");
                PrintWriter outShiftCipher = new PrintWriter("task3_shiftCipher.txt");

                StringBuilder str_y = new StringBuilder();
                String y = in_text.readLine();
                while (y != null) {
                    y = y.toLowerCase();
                    for (int i = 0; i < y.length(); i++) {
                        boolean flag = true;
                        char x = y.charAt(i);
                        if (alph.indexOf(x) == -1)
                            flag = false;
                        if (!flag) {
                            y = y.substring(0, i) + y.substring(i + 1);
                            i--;
                        }
                    }
                    str_y.append(y);
                    y = in_text.readLine();
                }

                String ans = "";

                int posKey = 0;
                for (int i = 0; i < str_y.length(); i++) {
                    int posI = alph.indexOf(key.charAt(posKey));
                    String x = table.get(posI);
                    int posJ = alph.indexOf(str_y.charAt(i));
                    out_cipher.print(x.charAt(posJ));
                    ans += x.charAt(posJ);
                    posKey = (posKey + 1) % key.length();
                }


                outShiftText.println(str_y);
                for (int i = 0; i < 15; i++) {
                    StringBuilder x = new StringBuilder();
                    x.append(str_y.substring(i + 1)).append(str_y.substring(0, i + 1));
                    outShiftText.println(x);
                }

                outShiftCipher.println(ans);
                for (int i = 0; i < 15; i++) {
                    StringBuilder x = new StringBuilder();
                    x.append(ans.substring(i + 1)).append(ans.substring(0, i + 1));
                    outShiftCipher.println(x);
                }

                in_alph.close();
                in_key.close();
                out_cipher.close();
                outShiftCipher.close();
                outShiftText.close();

                break;
            }

            case 2: {

                BufferedReader in_cipher = new BufferedReader(new FileReader("task3_outputCipher.txt"));
                PrintWriter out_text = new PrintWriter("task3_outputText.txt");

                StringBuilder str_y = new StringBuilder();
                String y = in_cipher.readLine();
                while (y != null) {
                    y = y.toLowerCase();
                    str_y.append(y);
                    y = in_cipher.readLine();
                }

                int posKey = 0;
                for (int i = 0; i < str_y.length(); i++) {
                    int posI = alph.indexOf(key.charAt(posKey));
                    String x = table.get(posI);
                    int posJ = x.indexOf(str_y.charAt(i));
                    out_text.print(table.get(0).charAt(posJ));
                    posKey = (posKey + 1) % key.length();
                }

                in_alph.close();
                in_key.close();
                in_cipher.close();
                out_text.close();

                break;
            }
        }
    }
}
