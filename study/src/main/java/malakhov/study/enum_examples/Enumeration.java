package malakhov.study.enum_examples;

public class Enumeration {
    public static void main(String[] args) {
        Animal animal = Animal.CAT;

        switch (animal) {
            case CAT -> {
                System.out.println("It`s a cat!");
            }
            case DOG -> {
                System.out.println("It`s a dog!");
                break;
            }
        }
    }

    enum Animal{
        DOG,CAT,FROG
    }
}
