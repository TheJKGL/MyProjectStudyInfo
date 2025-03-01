package malakhov.kt_practice.p5_conccurency.starvation;

import java.util.concurrent.TimeUnit;

public class SharedResourceAccessStarvation {

    private int sharedResource;

    public synchronized void doWork() {
        sharedResource++;
        System.out.println(Thread.currentThread().getName() + " acquired a shared resource. Current value: " + sharedResource);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

    public static void main(String[] args) {
        SharedResourceAccessStarvation starvation = new SharedResourceAccessStarvation();

        Runnable task = () -> {
            while (true) {
                starvation.doWork();
            }
        };

        Thread first = new Thread(task, "Thread-1");
        Thread second = new Thread(task, "Thread-2");
        Thread third = new Thread(task, "Thread-3");

        first.start();
        second.start();
        third.start();
    }
}