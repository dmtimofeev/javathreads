package main;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final String EXIT_COMMAND = "exit";
    private static final String STOP_COMMAND = "stop";

    private static boolean stopFlag = false;

    private static Runnable task = () -> {
        //Выполняеется в отдельном потоке
        Thread.currentThread().setName("Фоновый поток");
        while (!stopFlag){
            //якобы усиленно делаем что-то, пока не будет установлен флаг остановки... и никогда не видим его!
            sleep(1);
        }
        System.err.print(getCurrentThreadName());
        System.err.println("Фоновый поток обнаружил флаг остановки и завершился");
    };

    public static void main(String[] args) {
        Thread.currentThread().setName("Основной поток");

        System.out.println("Type \"" + EXIT_COMMAND + "\" for exit.");
        System.out.println("Type \"" + STOP_COMMAND + "\" for stopping background thread.");
        Scanner scanner = new Scanner(System.in);

        Thread thread = new Thread(task);
        thread.start();

        while (true){
            String message = scanner.nextLine();
            if(message.equalsIgnoreCase(EXIT_COMMAND)){
                System.exit(0);
            }
            System.out.print(getCurrentThreadName());
            if(message.equalsIgnoreCase(STOP_COMMAND)){
                stopFlag = true;
                System.out.println("Установлен флаг остановки фонового процесса: stopFlag=" + stopFlag);
                continue;
            }
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