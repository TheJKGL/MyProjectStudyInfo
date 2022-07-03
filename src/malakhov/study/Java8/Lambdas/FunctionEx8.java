package malakhov.study.Java8.Lambdas;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionEx8 {

    public static void main(String[] args) {

        String[] names = new String[] { //
                "Peter", "Martin", "John", "Peter", //
                "Vijay", "Martin", "Peter", "Arthur" };

        Set<String> set = Arrays.asList(names).stream() //
                .map(Function.identity()).collect(Collectors.toSet());

        set.forEach(System.out::println);
    }
}
