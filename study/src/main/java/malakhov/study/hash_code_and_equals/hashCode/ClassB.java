package malakhov.study.hash_code_and_equals.hashCode;

import java.util.Objects;

public class ClassB {
    private int param1;
    private String param2;

    public ClassB(int param1, String param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassB classB = (ClassB) o;
        return param1 == classB.param1 && Objects.equals(param2, classB.param2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(param1, param2);
    }
}
