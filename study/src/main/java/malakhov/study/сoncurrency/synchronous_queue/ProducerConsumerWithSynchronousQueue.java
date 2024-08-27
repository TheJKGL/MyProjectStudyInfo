package malakhov.study.сoncurrency.synchronous_queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ProducerConsumerWithSynchronousQueue {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        Runnable producer = () -> {
            Integer producedElement = ThreadLocalRandom
                    .current()
                    .nextInt();
            try {
                System.out.println("Saving an element: " + producedElement + " to the exchange point");
                queue.put(producedElement);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };

        Runnable consumer = () -> {
            try {
                Integer consumedElement = queue.take();
                System.out.println("Consumed an element: " + consumedElement + " from the exchange point");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };

        executor.execute(producer);
        executor.execute(consumer);

        executor.awaitTermination(500, TimeUnit.MILLISECONDS);
        executor.shutdown();
        assertEquals(queue.size(), 0);
    }
}
