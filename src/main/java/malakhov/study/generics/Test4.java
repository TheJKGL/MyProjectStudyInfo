package malakhov.study.generics;

public class Test4 {

    void demo(Source<String> strs) {
        Source<? extends Object> objects = strs; // !!! Запрещено в Java
        //Чтобы исправить это, вам нужно объявить объекты типа Source<? extends Object>,
        // что в каком-то роде бессмысленно, потому что у переменной такого типа вы можете вызывать только те методы,
        // что и ранее, стало быть, более сложный тип не добавляет смысла. Но компилятор этого не понимает.
    }

    public static void main(String[] args) {

    }
}

//Вариантность на уровне объявления
interface Source<T extends Object> {
    T nextT();
}
