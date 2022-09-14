package malakhov.study.InterfaceWork_Lambdas_DI.test;

public class Test {
    public static void main(String[] args) {
        Device device = new Electronic();
        Device.doDo();
    }
}

interface Device {
    void doIt();
    static void doDo(){

    }
}

class Electronic implements Device {
    @Override
    public void doIt() {
    }



}

abstract class Phone1 extends Electronic {
}

abstract class Phone2 extends Electronic {
    public void doIt(int x) {
    }
}

class Phone3 extends Electronic implements Device {
    public void doStuff() {
    }
}
