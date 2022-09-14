package malakhov.study.StaticImport.t2;

import static java.lang.Math.sqrt;
import static malakhov.study.StaticImport.t1.A.print;

public class Main {
    public static void main(String[] args) {
        print();

        //Тоже самое что и Math:
        System.out.println(sqrt(4));
    }
}
