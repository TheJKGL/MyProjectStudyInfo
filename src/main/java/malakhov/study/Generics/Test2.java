package malakhov.study.Generics;

public class Test2 {

    static <E> E cast(Object item){
        return (E) item;
    }

    public static void main(String[] args) {
        String str = new String("Java");
        int i = 1;
        char j = 3;
        System.out.println(str.substring(i,j));// i - включительно, j - не включительно
        Object o1 = 10;
        int a = 10;
        Integer integer = 10;
        Integer i1 = cast(o1);
        Integer i2 = cast(a);
        Integer i3 = cast(10);
        Integer i4 = cast(integer);
        System.out.println("" + i1 + i2 + i3 + i4);
    }
}


