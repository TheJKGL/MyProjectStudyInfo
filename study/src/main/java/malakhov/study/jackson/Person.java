package malakhov.study.jackson;

public class Person {

    private String name;
    private int age;
    private Adress adress;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {

    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    static class Adress {
        private String name;
    }
}