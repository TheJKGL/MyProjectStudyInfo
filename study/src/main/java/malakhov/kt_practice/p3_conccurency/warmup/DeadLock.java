package malakhov.kt_practice.p3_conccurency.warmup;

public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        Object lock1 = new Object();
        Object lock2 = new Object();
        //Lock lock1 = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {

                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                synchronized (lock1) {

                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
