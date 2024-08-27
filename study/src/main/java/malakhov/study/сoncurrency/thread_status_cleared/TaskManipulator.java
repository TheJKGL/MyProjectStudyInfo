package malakhov.study.сoncurrency.thread_status_cleared;

public class TaskManipulator implements Runnable, CustomThreadManipulator {

    private Thread thread;

    @Override
    public void run() {
        while (!thread.isInterrupted()) {
            System.out.println(thread.getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @Override
    public void start(String threadName) {
        thread = new Thread(this, threadName);
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }
}