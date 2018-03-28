import java.io.IOException;
import java.util.Scanner;

public class Main {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Main app = new Main();
        app.run();
    }

    public void run() throws IOException {

        System.out.println("1. Индекс совпадения");
        System.out.println("2. Средний индекс совпадения");
        System.out.println("3. Шифр Виженера");

        int meth = sc.nextInt();
        switch (meth) {
            case 1: {
                Task1 task = new Task1();
                task.task1();
                break;
            }
            case 2: {
                Task2 task = new Task2();
                task.task2();
                break;
            }
            case 3: {
                Task3 task = new Task3();
                task.task3();
                break;
            }
        }
    }
}
