package malakhov.study;

interface AbleToEat{
    void eat();
}

class Animal implements AbleToEat{
    @Override
    public void eat() {
        System.out.println("Eat");
    }
}

public class Anonymous_Class {
    private int age = 15;
    public static void main(String[] args) {
        //Без анонимного класа.
        AbleToEat ableToEat0 = new Animal();
        ableToEat0.eat();

        //С анонимным класом.
        AbleToEat ableToEat = new AbleToEat() {
            int a = new Anonymous_Class().age;
            @Override
            public void eat() {
                System.out.println();
            }
        };
        ableToEat.eat();
    }

    public void method(){
        class InnerClass{
            int a = age;
        }
    }
}

