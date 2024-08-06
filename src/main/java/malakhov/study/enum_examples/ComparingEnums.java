package malakhov.study.enum_examples;

public class ComparingEnums {
    public static void main(String[] args) {
        enum Color { BLACK, WHITE };
        Color nothing = null;

        System.out.println(nothing == Color.BLACK);// runs fine
        System.out.println(nothing.equals(Color.BLACK));// выбрасывает NullPointerException
    }
}
