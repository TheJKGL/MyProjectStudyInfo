package malakhov.study.сoncurrency.count_down_latch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BrokenWorker implements Runnable {

    private List<String> outputScraper;
    private CountDownLatch countDownLatch;

    public BrokenWorker(List<String> outputScraper, CountDownLatch countDownLatch) {
        this.outputScraper = outputScraper;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        if (true) {
            throw new RuntimeException("Oh dear, I'm a BrokenWorker");
        }
        countDownLatch.countDown();
        outputScraper.add("Counted down");
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Thread> workers = Stream
                .generate(() -> new Thread(new BrokenWorker(outputScraper, countDownLatch)))
                .limit(5)
                .collect(toList());

        workers.forEach(Thread::start);
        //countDownLatch.await();
        boolean completed = countDownLatch.await(3L, TimeUnit.SECONDS);//complete must be false
    }
}
