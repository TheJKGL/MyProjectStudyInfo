package malakhov.study.annotations.example2.processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Arrays;
import java.util.Set;

@SupportedAnnotationTypes("MyAnnotationWithElements")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MyElementProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(MyAnnotationWithElements.class)) {
            MyAnnotationWithElements annotation = element.getAnnotation(MyAnnotationWithElements.class);

            int intValue = annotation.intValue();
            String stringValue = annotation.stringValue();
            Class<?> classValue = annotation.classValue();
            MyEnum enumValue = annotation.enumValue();
            String[] arrayValue = annotation.arrayValue();

            System.out.println("Element: " + element.getSimpleName());
            System.out.println("int value: " + intValue);
            System.out.println("String value: " + stringValue);
            System.out.println("Class value: " + classValue);
            System.out.println("Enum value: " + enumValue);
            System.out.println("Array value: " + Arrays.toString(arrayValue));
        }
        return false;
    }
}