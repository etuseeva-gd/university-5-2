package ThirdTask;

import java.util.Scanner;

public class ThirdSubTask {
    public void init() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Что вы хотите сделать?");
        System.out.println("1. Вычисление значений гипотезы Н(0)");
        System.out.println("2. Вычисление значений гипотезы Н(d) с наиболее вероятной длиной периода d");

        int action = scanner.nextInt();
        switch (action) {
            case 1: {
                this.first();
                break;
            }
            case 2: {
                this.second();
                break;
            }
            default: {
                System.out.println("Неверная операция!");
            }
        }
    }

    private void first() {

    }

    private void second() {

    }
}
