package malakhov.study.сoncurrency.forkjoin.fork_join_list_sum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT] [%4$-7s] %5$s %n");

        int noOfProcessors = Runtime.getRuntime().availableProcessors();

        logger.info(() -> "Available processors: " + noOfProcessors);

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        int initialPoolSize = forkJoinPool.getPoolSize();
        int commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism();
        logger.info(() -> "Common Pool parallelism :" + commonPoolParallelism);
        logger.info(() -> "Common Pool size before:" + initialPoolSize);

        Random rnd = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            list.add(1 + rnd.nextInt(10));
        }

        SumRecursiveTask sumRecursiveTask = new SumRecursiveTask(list);
        Integer sumAll = forkJoinPool.invoke(sumRecursiveTask);
        logger.info(() -> "Final sum: " + sumAll);

        int afterPoolSize = forkJoinPool.getPoolSize();
        logger.info(() -> "Common Pool size after  :" + afterPoolSize);
    }

}