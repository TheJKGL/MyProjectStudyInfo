package malakhov.study.сoncurrency.producer_consumer.simple_solution2;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        List<Integer> sharedResource = new ArrayList<>();

        Producer producer = new Producer("Producer #1", sharedResource);
        Consumer consumer1 = new Consumer("Consumer #1", sharedResource);
        Consumer consumer2 = new Consumer("Consumer #2", sharedResource);

        producer.start();
        consumer1.start();
        consumer2.start();
    }
}
