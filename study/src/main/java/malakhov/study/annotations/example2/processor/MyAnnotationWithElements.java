package malakhov.study.annotations.example2.processor;

public @interface MyAnnotationWithElements {
    int intValue() default 42;
    String stringValue() default "default";
    Class<?> classValue();
    MyEnum enumValue() default MyEnum.DEFAULT;
    String[] arrayValue();
}