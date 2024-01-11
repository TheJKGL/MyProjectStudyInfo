package malakhov.study;

public class NestedClasses {
    public static void main(String[] args){
        NestedClasses test = new NestedClasses();
        //Test.Student2 student2 = test.new Student2();
        Student2 student2 = test.new Student2();
        Student3 student3 = new Student3();
    }

    public void method(){
        class Student4{

        }
        Student4 student4 = new Student4();
    }

    class Student2{

    }

    static class Student3{

    }
}





