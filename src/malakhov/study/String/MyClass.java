package malakhov.study.String;

public class MyClass {
    public static void main(String[] args) {
        String str1 = "123";
        String str2 = new String("123");
        char[] array = {'1', '2', '3'};
        byte[] array2 = {23, 21, 74};
        String str3 = new String(array2);
        System.out.println(str1 == str3.intern());

    }
}

