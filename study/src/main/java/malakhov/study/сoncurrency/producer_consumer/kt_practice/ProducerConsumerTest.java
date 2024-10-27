package malakhov.study.сoncurrency.producer_consumer.kt_practice;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Please refer to the task description - {@link  dev.reed.core.threads._03092024.main.producer_consumer.task.md}
 */
public class ProducerConsumerTest {

    public static void main(String[] args) throws InterruptedException {
        // TODO test your code here
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);

        Producer producer = new Producer("Produce#1", arrayBlockingQueue);
        Consumer consumer1 = new Consumer("Consumer#1", arrayBlockingQueue);
        Consumer consumer2 = new Consumer("Consumer#2", arrayBlockingQueue);

        consumer1.start();
        consumer2.start();
        producer.start();
    }
}
