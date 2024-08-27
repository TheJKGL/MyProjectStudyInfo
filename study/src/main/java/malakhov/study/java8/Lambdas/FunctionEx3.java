package malakhov.study.java8.Lambdas;

import java.util.*;
import java.util.function.Function;

public class FunctionEx3 {

    public static void main(String[] args) {

        Function<String, Integer> func = text -> text.length();

        List<String> list = Arrays.asList("Java", "C#", "Python");

        Map<String, Integer> map = listToMap(func, list);

        // @see: Map.forEach(BiConsumer).
        map.forEach((t,r) -> System.out.println(t + " : " + r));
    }

    public static <T, R> Map<T,R> listToMap(Function<T,R> mapper, List<T> list) {
        Map<T, R> result = new HashMap<>();

        for(T t: list)  {
            R r = mapper.apply(t);
            result.put(t, r);
        }
        return result;
    }
}
