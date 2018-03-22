package main;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final String TOUCH_COMMAND = "тык";
    private static final String AWAY_COMMAND = "уходи";
    private static final Object MONITOR = new Object();

    private static Runnable task = () -> {
        int i = 0;
        System.out.println("И кому я тут понадобился? Начнём с " + i++);
        while (true){
            System.out.println("Z-z-z...");
            synchronized (MONITOR){
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    System.out.println("Злые вы, уйду я от вас.");
                    return;
                }
            }
            System.out.println("Да не сплю я! Вот, посчитал даже: " + i++);
        }
    };

    public static void main(String[] args) {
        System.out.println("Начепятайте \"" + TOUCH_COMMAND +"\" чтобы разбудить фоновый поток.");
        System.out.println("Напепятайте \"" + AWAY_COMMAND +"\" дабы прервать фоновый поток.");
        Scanner scanner = new Scanner(System.in);

        Thread thread = null;

        while (true){
            String message = scanner.nextLine();
            if(thread == null || thread.getState() == Thread.State.TERMINATED) {
                thread = new Thread(task);
                thread.start();
            }
            if(message.equalsIgnoreCase(TOUCH_COMMAND)){
                synchronized (MONITOR){
                    MONITOR.notify();
                }
            }
            if(message.equalsIgnoreCase(AWAY_COMMAND)){
                thread.interrupt();
            }
        }
    }

    private static void sleep(int sleepSeconds) {
        try {
            TimeUnit.SECONDS.sleep(sleepSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}