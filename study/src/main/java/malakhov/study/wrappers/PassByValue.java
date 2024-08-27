package malakhov.study.wrappers;

public class PassByValue {
    public static void main(String[] args) {
        Integer a = 6;
        change(a);
        System.out.println("A = " + a);
    }
    public static void change(Integer x) {
        x = 10;
    }
}
