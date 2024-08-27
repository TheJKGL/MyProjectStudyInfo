package malakhov.study.сoncurrency.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock2 {
    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            lock1.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock2.lock();

            lock1.unlock();
            lock2.unlock();
            System.out.println(Thread.currentThread().getName() + " end");
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            lock2.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock1.lock();

            lock2.unlock();
            lock1.unlock();
            System.out.println(Thread.currentThread().getName() + " end");
        }, "Thread2");

        thread1.start();
        thread2.start();

    }
}
