package malakhov.study.generics;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class PecsExample {
    static class Class0 implements Comparable<Class0> {
        @Override
        public int compareTo(Class0 o) {
            return 0;
        }
    }

    static class Class1 extends Class0 {
    }

    static class Class2 extends Class1 {
    }

    static class Class3 extends Class2 {
    }

    static class Class4 extends Class3 {
    }

    //CS
    private static void methodWithSuper(List<? super Class2> list) {
        list.add(new Class2());
        list.add(new Class3());
        list.add(new Class4());
        //list.add(new Class1()); //Compilation error
        //Object class2 =  list.get(0);
    }

    //PE
    static void methodWithExtend(List<? extends Class2> list) {
        Class2 class2 = list.get(0);
        Class1 class1 = list.get(1);
        //Class4 class4 = list.get(0); //Compilation error
    }

    public static void main(String[] args) {
        List<Class2> list = new ArrayList<>();
        list.add(new Class4());
        list.add(new Class3());
        //list.add(new Class2());

        //PECS
        methodWithExtend(list);
        methodWithSuper(list);

        // Example 1
        List<?> list1 = new ArrayList<>();
        list1.add(null);

        // Example 2 from LinkedList toArray()
        Class0[] class0 = new Class0[2];
        arrayCreation(class0);

        comparing((c) -> new Class1());
    }

    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
            Function<? super T, ? extends U> keyExtractor)
    {
        Objects.requireNonNull(keyExtractor);
        return (Comparator<T> & Serializable)
                (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
    }

    private static <T> void arrayCreation(T[] type) {
        @SuppressWarnings(value = "unchecked")
        T[] array = (T[]) Array.newInstance(type.getClass().componentType(), type.length);
        System.out.println(array.getClass().componentType());
    }

    static class Chooser<T> {

        //T[] objects;
        // - Массивы коварианты, то есть Sub extends Super то массив Sub[] будет подтипом Super[].
        //   Информация о типе массива доступна во время выполения
        // - Списки инварианты
        List<T> objects;

        public Chooser(Collection<T> objects) {
            this.objects = new ArrayList<>(objects);
        }

        public T choose() {
            Random rnd = ThreadLocalRandom.current();
            return objects.get(rnd.nextInt(objects.size()));
        }
     }
}
