package malakhov.study;

public class OperatorPlusPlus {

    public static void main(String[] args) {
        int a = 0;
        for(int i = 0;i < 100;i++){
            a=a++;
        }
        System.out.println(a);

        //The same;
        int b = 0;
        increment(b);
        System.out.println(b);
    }

    static int increment(int i){
        int __temp = i;
        i = i +1;
        __temp = i;
        return __temp;
    }
}
