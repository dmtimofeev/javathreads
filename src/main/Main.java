package main;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final String EXIT_COMMAND = "exit";
    private static Runnable task = () -> {
        //Выполняеется в отдельном потоке
        Thread.currentThread().setName("Фоновый поток");
        int i = 0;
        while (true){
            sleep(3);
            System.err.print(getCurrentThreadName());
            System.err.println("Считаю что-то в фоновом потоке: " + i++);
        }
    };

    public static void main(String[] args) {
        Thread.currentThread().setName("Основной поток");

        System.out.println("Type \"" + EXIT_COMMAND + "\" for exit.");
        Scanner scanner = new Scanner(System.in);

        Thread thread = new Thread(task);
        thread.start();

        while (true){
            String message = scanner.nextLine();
            if(message.equalsIgnoreCase(EXIT_COMMAND)){
                System.exit(0);
            }
            System.out.print(getCurrentThreadName());
            System.out.println("Кто сказал \"" +  message + "\"?");
        }
    }

    private static String getCurrentThreadName(){
        return "[" + Thread.currentThread().getName() + "] ";
    }

    private static void sleep(int sleepSeconds) {
        try {
            TimeUnit.SECONDS.sleep(sleepSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}