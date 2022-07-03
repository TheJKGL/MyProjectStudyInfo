package malakhov.study.exceptions;

public class TryCatch {

    public static void main(String[] args) throws java.lang.Exception {

        TryCatch tryCatch = new TryCatch();
        System.out.println(tryCatch.test());
    }

    public void func(int a){
        func(a++);
    }

    public int test() {
        try {
            func(2);
        } finally {
            return 2;
        }
        //return 3;
    }
}
