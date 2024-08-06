package malakhov.study.java8.Stream;

import java.util.ArrayList;
import java.util.List;

public class TestMap {
    public static void main(String[] args) {
        List<Dad> list = new ArrayList<>();
        list.add(new Dad());
        list.add(new Son());
        list.forEach(a->a.set(5));
    }
}

class Dad {
    int age;
    public void set(int age) {
        this.age = age;
    }
}

class Son extends Dad {

}