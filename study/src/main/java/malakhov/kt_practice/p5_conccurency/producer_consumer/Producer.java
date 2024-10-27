package malakhov.kt_practice.p5_conccurency.producer_consumer;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Please refer to the task description - {@link  dev.reed.core.threads._03092024.main.producer_consumer.task.md}
 */
public class Producer extends Thread {
    // TODO implement producer
    private ArrayBlockingQueue<SyncItem> sharedResource;
    private String name;

    public Producer(String name, ArrayBlockingQueue<SyncItem> sharedResource) {
        this.sharedResource = sharedResource;
        this.name = name;
    }

    @SneakyThrows
    @Override
    public void run() {
        int counter = 0;
        while (true) {
            Thread.sleep(1000);
            if (sharedResource.size() >= 10) {
                System.out.println(name + " sleep for 10 sec");
                Thread.sleep(10000);
            }
            SyncItem syncItem = SyncItem.builder().description("item " + counter++).build();
            System.out.println(name + " produced " + syncItem.getDescription());
            sharedResource.put(syncItem);
        }
    }
}
