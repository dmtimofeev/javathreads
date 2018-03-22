package main;

import java.util.Scanner;

public class Main {
    private static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        System.out.println("Type \"" + EXIT_COMMAND + "\" for exit.");
        Scanner scanner = new Scanner(System.in);

        while (true){
            String message = scanner.nextLine();
            if(message.equalsIgnoreCase(EXIT_COMMAND)){
                System.exit(0);
            }
            System.out.println("Кто сказал \"" +  message + "\"?");
        }
    }
}