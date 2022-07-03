package malakhov.test;


import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test2<E> {

    private E[] elements;

    @SuppressWarnings("unchecked")
    E[] arr = (E[])new Object[12];

    @SuppressWarnings("unchecked")
    public Test2(Class<E> clazz, int capacity) {
        elements = (E[]) Array.newInstance(clazz, capacity);
    }

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        List<Starosta> list2 = new ArrayList<>();
        paintAllBuildings(list2);

        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        fill(strings,"1");

        Box<Student> box = new Box<>();
        box.setT(new Starosta());
        System.out.println(box.getT());
    }

    public static <T> void fill(List<T> list, T val) {
        for (int i = 0; i < list.size(); i++)
            list.set(i, val);
    }

    public static <T, G> List<G> fromArrayToList(T[] a, Function<T, G> mapperFunction) {
        return Arrays.stream(a)
                .map(mapperFunction)
                .collect(Collectors.toList());
    }

    public static void paintAllBuildings(List<? extends Student> buildings) {
        buildings.forEach(Student::paint);
    }

//    public <T> T[] getArray(int size) {
//        T[] genericArray = new T[size]; // suppose this is allowed
//        return genericArray;
//    }
}
class Student{
    void paint(){
    }
}

class Starosta extends Student{
}

    class Box<T>{
        T t;

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }



