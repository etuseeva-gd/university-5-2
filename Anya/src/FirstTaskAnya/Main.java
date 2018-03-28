package FirstTaskAnya;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Шифр простой перестановки на основе генерации ключа моноциклической перестановки");
        System.out.println("2. Тест Казиски по вычислению длины ключа простой перестановки");
        System.out.println("3. Перебор ключей моноциклической перестановки при известной длине ключа");

        int met = sc.nextInt();
        switch (met){
            case 1:{
                Task1 task1 = new Task1();
                task1.task();
                break;
            }
            case 2:{
                Task2 task2 = new Task2();
                task2.task();
                break;
            }
            case 3:{
                Task3 task3 = new Task3();
                task3.task();
                break;
            }
        }
    }
}
