package malakhov.study.сoncurrency.thread_status_cleared;

public interface CustomThreadManipulator {
    void start(String threadName);

    void stop();
}