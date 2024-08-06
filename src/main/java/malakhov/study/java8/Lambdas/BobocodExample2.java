package malakhov.study.java8.Lambdas;

import java.util.stream.IntStream;

public class BobocodExample2 {

    public static void main(String[] args) {
        NonFunctionalSolution();
        FunctionalSolution();
    }

    public static void FunctionalSolution() {
        IntStream.iterate(0, i -> i + 1)
                .filter(BobocodExample2::isPrimeFun)
                .limit(20)
                .reduce((a, b) -> a + b)
                .ifPresent(sum -> System.out.println("Sum of first 20 primes: " + sum));
    }

    public static boolean isPrimeFun(int n) {
        return IntStream.range(2, n)
                .noneMatch(i -> n % i == 0);
    }

    public static void NonFunctionalSolution() {
        int sumOfPrimes = 0;
        for (int i = 0, primes = 0; primes < 20; i++) {
            if (isPrime(i)) {
                sumOfPrimes += i;
                primes++;
            }
        }
        System.out.println("Sum of first 20 primes: " + sumOfPrimes);
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
