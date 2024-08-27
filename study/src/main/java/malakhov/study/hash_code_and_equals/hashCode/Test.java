package malakhov.study.hash_code_and_equals.hashCode;

public class Test {
    public static void main(String[] args) {
        ClassA classA = new ClassA(1, "123");
        ClassB classB = new ClassB(1, "123");

        System.out.println(classA.hashCode());
        System.out.println(classB.hashCode());
    }
}
