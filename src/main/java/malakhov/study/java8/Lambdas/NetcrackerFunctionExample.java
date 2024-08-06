package malakhov.study.java8.Lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class NetcrackerFunctionExample {
    public static void main(String[] args) {
        Function<List<ClassA>, ClassB> creator = (list) -> {
            ClassB classB = new ClassB();
            if (list.size() > 0) {
                classB.setClassA(list.iterator().next());
            }
            return  classB;
        };

        List<ClassA> list = Arrays.asList(new ClassA(1));
        ClassB classB =  creator.apply(list);
        System.out.println(classB);
    }
}

class ClassA {
    private int a;

    public ClassA(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "ClassA{" +
                "a=" + a +
                '}';
    }
}

class ClassB {
    private ClassA classA;

    public ClassA getClassA() {
        return classA;
    }

    public void setClassA(ClassA classA) {
        this.classA = classA;
    }

    @Override
    public String toString() {
        return "ClassB{" +
                "classA=" + classA +
                '}';
    }
}
