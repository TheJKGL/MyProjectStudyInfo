package malakhov.study.clone;

public class CloneTest {
    static class Person implements Cloneable {
        private String name;
        private int age;
        private Car car;

        public Person(String name, int age, Car car) {
            this.name = name;
            this.age = age;
            this.car = car;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", car=" + car +
                    '}';
        }

        @Override
        public Person clone() throws CloneNotSupportedException {
            //Глубокое клонирование (Для него все внутр. классы тоже должны переопределять метод clone().)
            Person person = (Person) super.clone();
            person.car = car.clone();
            return person;
            //Поверхсное клонирование, работает только для классов встроеных в java(String,Integer, Double) и примитивов.
            //return (Person) super.clone();
        }
    }

    static class Car implements Cloneable{
        private String color;

        public Car(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "color='" + color + '\'' +
                    '}';
        }

        @Override
        public Car clone() {
            try {
                Car clone = (Car) super.clone();
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Car car = new Car("Green");
        Person person = new Person("Mike", 25, car);

        Person clone = person.clone();
        System.out.println(person);
        System.out.println(clone);
        clone.name = "Ivan";
        clone.car.color = "red";
        System.out.println(person);
        System.out.println(clone);
    }
}
