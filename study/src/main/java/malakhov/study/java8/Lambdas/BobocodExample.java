package malakhov.study.java8.Lambdas;


import java.util.function.*;

public class BobocodExample {

    public static void main(String[] args) {
        Upper upper = s -> s.toUpperCase();

        Function<String, String> upperFunction = s -> s.toUpperCase();
        UnaryOperator<String> unaryOperator = s -> s.toUpperCase();

        Predicate<String> predicate = s -> s.isEmpty();
        BiPredicate<String, String> biPredicate = (s1, s2) -> s1.equals(s2);

        Consumer<String> consumer = s -> System.out.println(s);
        Supplier<String> supplier = () -> "Hello";

        System.out.println(upperFunction.apply("hello"));
    }
}

@FunctionalInterface
interface Upper {
    String toUpperCase(String string);
}
