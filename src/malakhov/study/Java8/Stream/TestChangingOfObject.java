package malakhov.study.Java8.Stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TestChangingOfObject {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        List<Item> myList = new ArrayList<>();
        myList.add(new Item(1));
        myList.add(new Item(2));
        myList.add(new Item(3));
        myClass.setList(myList);

        //List<Item> myList2 = myClass.getList().stream().filter(a -> a.getNumber() == 3).peek(a -> a.setNumber(123)).toList();
        //System.out.println(myList);
        //System.out.println(myList2);
    }
}

class MyClass {
    private List<Item> list;

    public void setList(List<Item> list) {
        this.list = list;
    }

    public List<Item> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "list=" + list +
                '}';
    }
}

class Item {
    private int number;

    public Item(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Item{" +
                "number=" + number +
                '}';
    }
}
