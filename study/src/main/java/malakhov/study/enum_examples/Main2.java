package malakhov.study.enum_examples;

public class Main2 {
    enum CoffeeSize{BIG,HUGE,OVER};
    public static void main(String[] args) {
        CoffeeSize cs = CoffeeSize.BIG;
        System.out.println(cs.HUGE);
    }
}
