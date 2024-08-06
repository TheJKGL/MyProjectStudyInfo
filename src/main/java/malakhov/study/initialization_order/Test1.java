package malakhov.study.initialization_order;

public class Test1 {
    public class Child extends Parent {
        int x = 20;

        Child() {
            super();
            System.out.println("Child constr");
            show();
        }

        void show() {
            System.out.println("Child Show " + x + "  ");
        }
    }

    class Parent {
        int x = 10;

        Parent() {
            System.out.println("Parent constr");
            show();
        }

        void show() {
            System.out.println("Parent Show " + x + "  ");
        }
    }

    private void run() {
        new Child();
    }

    public static void main(String s[]) {
        Test1 order = new Test1();
        order.run();

    }
}
//output:
        /*Base constr
        Child Show 0 // x еще не инициализировался поэтому 0.
        Child constr
        Child Show 20*/
