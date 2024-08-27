package malakhov.kt_practice.p3_conccurency.custom_thread_pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class DynamicThreadPoolManager {

    private final int minThreads;
    private final int maxThreads;
    private final BlockingQueue<Runnable> taskQueue;
    private final AtomicBoolean running;
    private int activeThreads;
    private Thread monitorThread;

    public DynamicThreadPoolManager(int minThreads, int maxThreads) {
        // TODO: Implement parameter validation (check if minThreads, maxThreads are valid)
        // Example: if (minThreads < 0) { throw new IllegalArgumentException("Minimum threads cannot be negative"); }
        if (minThreads < 0) {
            throw new IllegalArgumentException("Minimum threads cannot be negative");
        }
        if (maxThreads < 0) {
            throw new IllegalArgumentException("Maximum threads cannot be zero or negative");
        }

        if (minThreads > maxThreads) {
            throw new IllegalArgumentException("Minimum threads should be low tha maximum");
        }

        // Initialize fields
        this.minThreads = minThreads;
        this.maxThreads = maxThreads;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.running = new AtomicBoolean(false);
        this.activeThreads = 0;
    }

    synchronized void incrementActiveThreads() {
        // TODO: Increment the number of active threads.
        // - Increase activeThreads by 1.
        // - Print a message indicating the number of active threads.
        System.out.println("Increment. Now number of active threads: " + ++activeThreads);
    }

    synchronized void decrementActiveThreads() {
        // TODO: Decrement the number of active threads.
        // - Ensure activeThreads does not go below minThreads.
        // - If activeThreads is greater than minThreads, decrease it by 1.
        // - Print a message indicating the number of active threads.
        if (activeThreads > minThreads) {
            System.out.println("Decrement. Now number of active threads: " + --activeThreads);
        }
    }

    void createWorkerThread() {
        // TODO: Increment the activeThreads count using incrementActiveThreads().
        // TODO: Create a new Thread that:
        // - Continuously checks for new tasks in the taskQueue.
        // - If a task is found, execute it.
        // - If interrupted or if no tasks are found, exit the loop.
        // TODO: Start the new worker thread.

        incrementActiveThreads();
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started");
            while (running.get() && !Thread.currentThread().isInterrupted()) {
                try {
                    //Do we need sync here?
                    //Rewrite with take();
//                    Runnable runnable;
//                    synchronized (this) {
//                        runnable = taskQueue.poll();
//                        if (runnable == null) {
//                            break;
//                        }
//                    }
                    Runnable task = taskQueue.take();
                    System.out.println(Thread.currentThread().getName() + " executing task.");
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + " interrupted.");
                }
            }
            decrementActiveThreads();
            System.out.println(Thread.currentThread().getName() + "finished");
        }, "Worker-" + activeThreads);
        thread.start();
    }

    void adjustThreadCount() {
        // TODO: Implement the logic to adjust the number of worker threads.
        // - If the taskQueue size > activeThreads and activeThreads < maxThreads, create new worker threads.
        // - If the taskQueue is empty and activeThreads > minThreads, reduce the number of active threads.
        int queueSize = taskQueue.size();
        System.out.println("Adjusting thread count. Current queue size: " + queueSize);

        if (queueSize > activeThreads && activeThreads < maxThreads) {
            System.out.println("Increasing thread count. Current active threads: " + activeThreads);
            createWorkerThread();
        }

        if (queueSize == 0 && activeThreads > minThreads) {
            System.out.println("Decreasing thread count. Current active threads: " + activeThreads);
            decrementActiveThreads();
        }
    }

    void monitor() {
        // TODO: Implement the logic for the monitor thread.
        // - This thread should continuously run while the pool is running.
        // - Periodically check the size of the taskQueue.
        // - Adjust the number of active worker threads by calling adjustThreadCount().
        // - If the monitor thread is interrupted, it should gracefully exit.
        monitorThread = new Thread(() -> {
            while (running.get() && !monitorThread.isInterrupted()) {
                //Do we need sync here?
                synchronized (this) {
                    adjustThreadCount();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Monitor thread interrupted");
                    return;
                }
            }
        });
        monitorThread.start();
    }

    public synchronized void start() {
        // TODO: Implement the logic to start the thread pool.
        // - Check if the pool is already running using the running flag.
        // - If running, print a message and return.
        // - Set the running flag to true.
        // - Create initial worker threads using createWorkerThread().
        // - Start a monitor thread that will handle adjusting the thread count.
        if (running.get()) {
            System.out.println("DynamicThreadPoolManager is already running");
        } else {
            running.set(true);
            System.out.println("Starting thread pool with minimum threads: " + minThreads);
            for (int i = 0; i < minThreads; i++) {
                createWorkerThread();
            }
            monitor();
        }
    }

    public synchronized void stop() {
        // TODO: Implement the logic to stop the thread pool.
        // - Check if the pool is running.
        // - If not running, print a message and return.
        // - Set the running flag to false.
        // - Interrupt the monitor thread.
        // - Interrupt all worker threads.
        // - Clear the task queue.
        // - Reset activeThreads to 0.
        if (!running.get()) {
            System.out.println("DynamicThreadPoolManager is not running");
        } else {
            running.set(false);
            monitorThread.interrupt();
            System.out.println("Stopping thread pool and clearing task queue.");
            for (Thread thread : Thread.getAllStackTraces().keySet()) {
                if (thread.getName().startsWith("Worker-")) {
                    thread.interrupt();
                    System.out.println("Interrupted " + thread.getName());
                }
            }
            taskQueue.clear();
            activeThreads = 0;
        }
    }

    public void submitTask(Runnable task) {
        // TODO: Implement the logic to submit a task to the pool.
        // - Check if the pool is running.
        // - If running, add the task to the taskQueue.
        // - Notify waiting worker threads that there is a new task available.
        // - If not running, print a message and do not accept the task.
        if (running.get()) {
            taskQueue.add(task);
            notifyAll();
        } else {
            System.out.println("DynamicThreadPoolManager is not running");
        }
    }

    public synchronized int getActiveThreads() {
        // TODO: Return the number of active threads.
        return activeThreads;
    }

    public synchronized int getTaskQueueSize() {
        // TODO: Return the size of the task queue.
        return taskQueue.size();
    }
}