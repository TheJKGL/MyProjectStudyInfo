package malakhov.study.Java8.Lambdas;

interface Executable {
    void execute(int x);
}

class Runner {
    public void run(Executable e) {
        e.execute(10);
    }
}

class ExecutableImplementation implements Executable {

    @Override
    public void execute(int x) {
        System.out.println("Hello");
    }
}

//Three examples of interface method implementation.
public class Lambdas {
    public static void main(String[] args) {
        Runner runner = new Runner();

        //1.Create a class and override the method.
        runner.run(new ExecutableImplementation());

        //2.Create an anonymous class and redefine the method immediately.

        runner.run(new Executable() {
            @Override
            public void execute(int x) {
                System.out.println("Hello");
            }
        });

        //3.Use the lambda expression.
        final int a = 1;//Передаваемая переменная в лямбда должна быть константой.
        // У лямбда выражения нет своей области видимости.
        runner.run((int x) -> {
            System.out.println("Hello" + a);
        });
    }
}
