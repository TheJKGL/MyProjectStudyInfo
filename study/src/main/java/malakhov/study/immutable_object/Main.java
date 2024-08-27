package malakhov.study.immutable_object;

public class Main {

    public static void main(String[] args) {

        // создаем какой то, любой объект класса Age, он mutable
        Age age = new Age();
        age.setYear(10);

        //содаем IMMUTABLE объект класса ImmutableStudent
        ImmutableStudent immutableStudent = new ImmutableStudent("Andrew", age);
        System.out.println("Name : " + immutableStudent.getName() + " | " + "Age : " + immutableStudent.getAge().getYear());

        // пытаемся изменить поля
        immutableStudent.setAge(new Age(20));
        immutableStudent.setName("Ivan");

        //вывод не изменился и ЭТО ХОРОШО
        System.out.println("Name : " + immutableStudent.getName() + " | " + "Age : " + immutableStudent.getAge().getYear());

        //еще пытаемся изменить поля
        age.setYear(30);

        //вывод не изменился и ЭТО ОТЛИЧНО
        System.out.println("Name : " + immutableStudent.getName() + " | " + "Age : " + immutableStudent.getAge().getYear());

        //упорно пытаемся что то сломать
        immutableStudent.getAge().setYear(50);
        immutableStudent.setName("Petro");

        //вывод не изменился, что то менять бесполезно
        System.out.println("Name : " + immutableStudent.getName() + " | " + "Age : " + immutableStudent.getAge().getYear());

    }
}
