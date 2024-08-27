package malakhov.study.generics;

import java.util.ArrayList;
import java.util.List;

//Параметризация
public class Test1 {
    public static void main(String[] args) {
        ////////////////// Java 5 ////////////////////
        List animals = new ArrayList<>();
        animals.add("cat");
        animals.add("dog");
        animals.add("frog");

        String animal = (String) animals.get(1);
        System.out.println(animal);

        ////////////// С появлением дженериков ////////////////
        List<String> animals2 = new ArrayList<>();
        animals2.add("cat");
        animals2.add("dog");
        animals2.add("frog");

        String animal2 = animals2.get(1);
        System.out.println(animal2);

        ///////////////// Wildcards ////////////////
        List<A> listA = new ArrayList<>();
        List<B> listB = new ArrayList<>();
        method(listA);
        method(listB);

        //--------------------------------------------
        List<Class4> list1 = new ArrayList<>();
        someMethodExtends(list1);
        //-------------------------------------------
        List<Class1> list2 = new ArrayList<>();
        //someMethodSuper(list2);
    }

    class Class0 {
    }

    class Class1 extends Class0 {
    }

    class Class2 extends Class1 {
    }

    class Class3 extends Class2 {
    }

    class Class4 extends Class3 {
    }

    public static void someMethodExtends(List<? extends Class3> list) {
        Class2 test = list.get(0);//Можем получить только объекты супер классов.
    }

    public void someMethodSuper(List<? super Class2> list) {
        list.add(new Class3());//Можем добавлять только объекты потомков.
    }

    public static void someMethod(List<? super Number> list) {
        list.add(new Integer("1"));// Из-за того что мы ограничиваем снизу, мы не знаем какой именно класс предок в итоге будет задействован
        // поэтому добавить в список можно только классы потомки Number.
    }

    static class A {
    }

    static class B extends A {
    }

    public static void method(List<? extends A> list) {
        list.clear();
    }
}
