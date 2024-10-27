package malakhov.study.сoncurrency.producer_consumer.simple_solution2;

import lombok.SneakyThrows;

import java.util.List;

public class Producer extends Thread {

    private List<Integer> sharedResource;
    private String name;

    public Producer(String name, List<Integer> sharedResource) {
        this.name = name;
        this.sharedResource = sharedResource;
    }

    @Override
    @SneakyThrows
    public void run() {
        int counter = 0;
        while (true) {
            Thread.sleep(1000);
            synchronized (sharedResource) {
                if (sharedResource.size() >= 10) {
                    System.out.println(name + " is waiting for 10 sec");
                    Thread.sleep(10000);
                }

                System.out.println(name + " produced item #" + counter);
                sharedResource.add(counter++);
                sharedResource.notify();
            }
        }
    }
}
