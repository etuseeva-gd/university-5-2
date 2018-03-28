import org.omg.PortableInterceptor.INACTIVE;

import java.io.*;
import java.util.*;

public class Task2 {

    public void task() throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("task2_input.txt"));
        PrintWriter out = new PrintWriter("task2_output.txt");
        PrintWriter out_nod = new PrintWriter("task2_nod.txt");

        StringBuilder str = new StringBuilder();
        String s = in.readLine();
        while (s != null) {
            str.append(s);
            s = in.readLine();
        }


        HashSet<Integer> mbans = new HashSet<>();
        int sz = 3;
        while (sz <= str.length() / 2) {
            HashMap<Integer, Integer> ans = new HashMap<>();
            boolean flag = false;
            for (int i = 0; i < str.length() - sz; i++) {
                String x = str.substring(i, i + sz);
                for (int j = i + sz; j < str.length() - sz; j++) {
                    String y = str.substring(j, j + sz);
                    if (x.equals(y)) {
                        flag = true;
                        if (ans.containsKey(j - i)) {
                            Integer xx = ans.get(j - i) + 1;
                            ans.remove(j - i);
                            ans.put(j - i, xx);
                        } else
                            ans.put(j - i, 1);
                    }
                }
            }

            List<Integer> nod = new ArrayList<>();
            Set<Integer> k = ans.keySet();
            int max = 0;
            for (int x : k) {
                max = Math.max(max, ans.get(x));
            }
            for (int x : k) {
                if (ans.get(x) >= max / 2)
                    nod.add(x);
            }

            HashSet<Integer> set_nod = new HashSet<>();
            for (int i = 0; i < nod.size(); i++) {
                for (int j = i + 1; j < nod.size(); j++) {
                    set_nod.add(gcd(nod.get(i), nod.get(j)));
                }
            }

            if (set_nod.size() != 0) {
                out_nod.print(sz + " : ");
                for (Integer x : set_nod) {
                    mbans.add(x);
                    out_nod.print(x + " ");
                }
                out_nod.println();
            }


            if (!flag)
                break;
            sz++;
        }

        List<Integer> ans = new ArrayList<>();
        for (int x : mbans) {
            ans.add(x);
        }

        HashSet<Integer> set_nod = new HashSet<>();
        for (int i = 0; i < ans.size(); i++) {
            for (int j = 0; j < ans.size(); j++) {
                set_nod.add(gcd(ans.get(i), ans.get(j)));
            }
        }

        out.print("Возможная длина ключа: ");
        for (int x : set_nod) {
            out.print(x + " ");
        }

        out_nod.close();
        in.close();
        out.close();
    }

    public Integer gcd(Integer a, Integer b) {
        while (a != b) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }
        return a;
    }
}
