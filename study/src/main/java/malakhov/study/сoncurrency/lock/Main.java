package malakhov.study.сoncurrency.lock;

public class Main {
    public static void main(String[] args) {
        PrinterCountDownLatch printer = new PrinterCountDownLatch();

        Runnable printFirst = () -> System.out.println("first");
        Runnable printSecond = () -> System.out.println("second");
        Runnable printThird = () -> System.out.println("third");

        Thread thread2 = new Thread(() -> {
            try {
                printer.second(printSecond);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread1 = new Thread(() -> {
            try {
                printer.first(printFirst);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                printer.third(printThird);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
