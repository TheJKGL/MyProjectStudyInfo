package malakhov.study.сoncurrency.example_with_volatile;


public class Increment {
    private static volatile int count = 0;
    //private static AtomicInteger count = new AtomicInteger(0);

    //ABA -> A B A
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[2];

        long start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new IncrementTask());
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        long end = System.currentTimeMillis();

        System.out.println(count);
        System.out.println(end - start);
    }

    private static class IncrementTask implements Runnable {
        @Override
        public void run() {
            for(int index = 0; index < 1000; index++) {
                increment();
            }
        }
    }

    private static void increment() {
        //count.incrementAndGet();
        count = count + 1;
        /*try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }
}
