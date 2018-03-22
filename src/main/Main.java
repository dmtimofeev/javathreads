package main;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    private static Runnable task = () -> {
        int i = 0;
        while (true){
            print("Что-то делаем в фоне: " + i++, "Background");
            sleep(1);
        }
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Thread thread = new Thread(task);
        thread.start();

        while (true){
            String message = scanner.nextLine();
            print(message, "Main");
            sleep(1);
        }
    }

    private static synchronized void print(String message, String who){
        System.err.println(who + " thread print start...");
        sleep(3);
        System.out.println(who + ": " + message);
        sleep(3);
        System.err.println(who + " thread print end.");
    }

    private static void sleep(int sleepSeconds) {
        try {
            TimeUnit.SECONDS.sleep(sleepSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}