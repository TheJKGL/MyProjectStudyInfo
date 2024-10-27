package malakhov.study.сoncurrency.producer_consumer.simple_solution2;

import lombok.SneakyThrows;

import java.util.List;

public class Consumer extends Thread {

    private List<Integer> sharedResource;
    private String name;

    public Consumer(String name, List<Integer> sharedResource) {
        this.name = name;
        this.sharedResource = sharedResource;
    }

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            System.out.println(name + " sleep for 3 sec");
            Thread.sleep(3000);

            synchronized (sharedResource) {
                if (sharedResource.isEmpty()) {
                    System.out.println(name + " is waiting");
                    sharedResource.wait();
                }
                
                Integer retrievedValue = sharedResource.removeLast();
                System.out.println(name + " retrieve value: " + retrievedValue);
                sharedResource.notify();
            }
        }
    }
}
