package malakhov.study.сoncurrency.producer_consumer.kt_practice;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Please refer to the task description - {@link  dev.reed.core.threads._03092024.main.producer_consumer.task.md}
 */
public class Consumer extends Thread {

    // TODO implement consumer

    private ArrayBlockingQueue<SyncItem> sharedResource;
    private String name;

    public Consumer(String name, ArrayBlockingQueue<SyncItem> sharedResource) {
        this.name = name;
        this.sharedResource = sharedResource;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Thread.sleep(3000);
            SyncItem item = sharedResource.take();
            System.out.println(name + " consumed " + item.getDescription());
        }
    }
}
