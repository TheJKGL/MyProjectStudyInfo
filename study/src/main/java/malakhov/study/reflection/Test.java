package malakhov.study.reflection;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws Exception {
        String value = "Old value";
        System.out.println(value);

        //Получаем поле value в классе String
        Field field = value.getClass().getDeclaredField("value");
        //Разрешаем изменять его
        field.setAccessible(true);
        //Устанавливаем новое значение
        field.set(value, "JavaRush".getBytes());

        System.out.println(value);

        /* Вывод:
         *
         * Exception in thread "main" java.lang.reflect.InaccessibleObjectException: Unable to make field private final
         *  byte[] java.lang.String.value accessible: module java.base does not "opens java.lang" to unnamed module @548c4f57
         */
    }
}
