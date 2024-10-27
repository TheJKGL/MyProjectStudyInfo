package malakhov.study.annotations.example2.processor;

import malakhov.study.annotations.example2.simple.MyClass;

@MyAnnotationWithElements(intValue = 10, stringValue = "custom", classValue = MyClass.class, enumValue = MyEnum.CUSTOM, arrayValue = {"a", "b"})
public class AnnotatedClass {
    // Class implementation
}