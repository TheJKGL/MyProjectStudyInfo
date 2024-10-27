package malakhov.study.annotations.example2.simple;

public class MyClass {
    @MyCustomAnnotation(value = "custom value", count = 42)
    public void myAnnotatedMethod() {
        // Annotated method implementation
    }
}