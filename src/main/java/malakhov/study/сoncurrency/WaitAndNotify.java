package malakhov.study.сoncurrency;

import java.util.Scanner;

public class WaitAndNotify {
    public static void main(String[] args) throws InterruptedException {

        WaitAndNotify2 wn = new WaitAndNotify2();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}

class WaitAndNotify2 {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread started...");
            this.wait();// 1 - отдаем монитор, 2 - ждем пока будет вызван notify().
            System.out.println("Producer thread resumed...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);
        synchronized (this) {
            System.out.println("Waiting for return key pressed");
            scanner.nextLine();
            this.notify();// - не освобождает монитор.
            Thread.sleep(5000);
        }
    }
}
