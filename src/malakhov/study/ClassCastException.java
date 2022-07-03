package malakhov.study;

class BoxPrinter {
    private Object val;

    public BoxPrinter(Object arg) {
        val = arg;
    }

    public Object getValue() {
        return val;
    }
}

public class ClassCastException {
    public static void main(String[] args) {
        BoxPrinter value1 = new BoxPrinter(new Integer(10));
        Integer intValue1 = (Integer) value1.getValue();
        System.out.println(intValue1);
        BoxPrinter value2 = new BoxPrinter("Hello world");
        Integer intValue2 = (Integer) value2.getValue();
        System.out.println("" + intValue2);
        //10 RuntimeException
    }
}
