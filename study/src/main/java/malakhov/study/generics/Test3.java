package malakhov.study.generics;

import java.io.Serializable;

public class Test3 {
    public static void main(String[] args) {
        Cell<Job> cell = new Cell<>();
        cell.setT(new Job());
        cell.doJob();
    }
}

class Job implements Serializable{
    void doIt(){
        System.out.println("doit");
    }
}

class Cell<T extends Job & Serializable> {
    T t;

    public T getE(){
        return t;
    }
    public void setT(T t){
        this.t = t;
    }
    public void doJob(){
        t.doIt();
    }
}

