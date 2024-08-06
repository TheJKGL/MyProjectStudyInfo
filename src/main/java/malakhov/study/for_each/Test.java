package malakhov.study.for_each;


class JavaCourse{
    String courseName = "Java";
}
public class Test {
    public static void main(String[] args) {
        JavaCourse[] courses = {new JavaCourse(),new JavaCourse()};
        courses[0].courseName = "MegaCourse";
        for(int i = 0; i < courses.length; i++){
            courses[i] = new JavaCourse();
        }
        for(JavaCourse c: courses){
            c = new JavaCourse();
        }
        for(JavaCourse c: courses){
            System.out.println(c.courseName);
        }
        //MegaCourse
        //Java
    }


}









