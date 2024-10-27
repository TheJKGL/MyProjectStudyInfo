package malakhov.study.jackson;

public class Main {
    public static void main(String[] args) {
        String person = "{\"name\": \"Sriram\", \"age\": 25}";
        Person sriram = JsonParser.toJSON(person, Person.class);
        System.out.println( sriram);

        //System.out.println("Name : " + sriram.getName() + " , age: " + sriram.getAge());
    }
}