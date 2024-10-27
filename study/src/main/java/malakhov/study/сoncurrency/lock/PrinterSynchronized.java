package malakhov.study.сoncurrency.lock;

public class PrinterSynchronized {

    private volatile boolean isFirstFinished;
    private volatile boolean isSecondFinished;

    public synchronized void first(Runnable printFirst) {
        printFirst.run();
        isFirstFinished = true;
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        while (!isFirstFinished) {
            wait();
        }
        printSecond.run();
        isSecondFinished = true;
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while (!isSecondFinished) {
            wait();
        }
        printThird.run();
        notifyAll();
    }
}
