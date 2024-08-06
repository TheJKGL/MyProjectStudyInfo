package malakhov.study.abstract_and_interface.example;

/**
 * Created by Администратор on 26.01.2016.
 */
public interface Operation {
    int calculate(int first, int second);

    static void printOperation(Operation op, int a, int b){
        System.out.println(op.calculate(a,b));
    }
}
