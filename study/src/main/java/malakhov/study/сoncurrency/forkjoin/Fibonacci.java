package malakhov.study.сoncurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {

    final int n;
    Fibonacci(int n) { this.n = n; }


    public Integer compute() {
        if (n <= 1)
            return n;
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        f2.fork();
        return f2.join() + f1.join();
    }

    //               4
    //            /     \
    //           3        2
    //        /    \    /   \
    //       2      1  1     0
    //     /   \
    //    1     0
    public static void main(String[] args) {
        //Calculate the n-th fibonacci number
        //№:      0  1  2  3  4  5  6  7   8   9
        //output: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
        Fibonacci fibonacci = new Fibonacci(4);
        System.out.println(fibonacci.compute());
    }
}
