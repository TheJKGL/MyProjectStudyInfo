package malakhov.study.Polimorphism;

public class Test {
    public static void main(String[] args) {
        GeneralInterface generaInterface = new Implementation();
        System.out.println(generaInterface.getClass());
    }
}
