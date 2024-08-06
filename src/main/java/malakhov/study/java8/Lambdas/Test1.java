package malakhov.study.java8.Lambdas;

import java.util.ArrayList;
import java.util.Comparator;

public class Test1 {

    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("Сергей",38));
        persons.add(new Person("Даша",7));
        persons.add(new Person("Глаша",3));
        persons.add(new Person("Саша",6));
        persons.add(new Person("Анна",18));

        //1 вариант (императивный код)
        /*for(Person p : persons){
            System.out.println(p);
        }*/

        //2 вариант (декларативный подход)
        /*persons.stream().forEach(
                (Person p ) -> { System.out.println(p); });*/
        //persons.stream().forEach(System.out::println);

        //сортировка
        persons.stream().
                filter(p -> p.getAge() >= 18).
                //sorted((p1,p2) -> p1 .getName().compareTo(p2.getName())).
                sorted(Comparator.comparing(Person::getName)).
                forEach(System.out::println);

        //средний возраст потока массива
        double averageAge2 = persons.stream().
                filter(p -> p.getAge() >= 18).
                mapToInt(Person::getAge).//преобразовываем набор объектов в набор возрастов.
                average().getAsDouble();
        System.out.println(averageAge2);
    }
}

class Person {
    private int age;
    private String name;

    public Person(String name,int age ) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
