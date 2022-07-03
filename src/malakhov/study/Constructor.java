package malakhov.study;

public class Constructor {
    class A{
        A(){
            System.out.println(("Внутри конструктора А."));
        }
    }

    class B extends A{
        B(){
            System.out.println(("Внутри конструктора B."));
        }
    }

    class C extends  B{
        C(){
            System.out.println(("Внутри конструктора C."));
        }
    }


    //========================================================

    public class One {
        // Будет создан конструктор по умолчанию
    }

    public class Two {
        // Единственный конструктор класса Two.
        // Выражение new Two() ошибочно!
        public Two(int x) {
        }
    }

    public class Three extends Two {
        public Three() {
            super(1);// выражение super требуется
        }
        public Three(int x) {
            super(x); // выражение super требуется
        }
    }

    //===========================================================

    public class Vector {
        private int vx, vy;
        public Vector(int x, int y) {
            super();
            vx=x;
            vy=y;
            System.out.println(vx);//2
            System.out.println(vy);//2
        }

        public Vector(int x1, int y1, int x2, int y2) {
            this(x2-x1, y2-y1);
            new Vector(x2-x1, y2-y1);

        }
    }

    public static void main(String[] args) {
        Constructor constructor = new Constructor();
        constructor.run();

    }

    void run(){
        Vector vector = new Vector(1,2,3,4);
    }
}
