package malakhov.study.java8.Stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        //forEach
        int[] array = {1,2,3,4,56,7,82};
        Arrays.stream(array).forEach(a-> System.out.print(a+" "));
        System.out.println("\n=============================");

        //map method
        array = Arrays.stream(array).map(a -> a + 1).toArray();
        Arrays.stream(array).forEach(a-> System.out.print(a+" "));
        System.out.println("\n=============================");

        //filter method
        array = Arrays.stream(array).filter(a -> a % 2 == 0).toArray();
        Arrays.stream(array).forEach(a-> System.out.print(a+" "));
        System.out.println("\n=============================");

        //reduce - уменьшение
        //[1,2,3] 1) acc = 1, b = 2; 2) acc = 3, b = 3;
        int sum = Arrays.stream(array).reduce((acc,b) -> acc + b ).getAsInt();
        Arrays.stream(array).reduce(5, Integer::sum);
        System.out.print(sum);
        System.out.println("\n=============================");

        //Supplier
        Stream.generate(() -> 6).limit(6).forEach(System.out::println);

        //Streams for primitives
        String text = "text";
        text.chars()
                .mapToObj(a -> (char)a)
                .filter(s -> s != ' ')
                .forEach(System.out::println);
    }
}
