package main;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Main {
    private static final int THREAD_POOL_SIZE = 3;
    private static final Random rnd = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        ExecutorService service = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);

        for (int i = 0; i < 10; i++){
            service.submit(new Task(i));
        }
    }

    private static class Task implements Runnable {
        private final int id;

        private Task(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            for(int count = 0; count < 10; count++){
                try {
                    Thread.sleep(Math.abs(rnd.nextLong() % 3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": " +
                        "I'm a task № " + id + " and my internal count=" + count);
            }
        }
    }
}