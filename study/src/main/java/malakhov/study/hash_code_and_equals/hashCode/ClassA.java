package malakhov.study.hash_code_and_equals.hashCode;

import java.util.Objects;

public class ClassA {
    private int param1;
    private String param2;

    public ClassA(int param1, String param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassA classA = (ClassA) o;

        if (param1 != classA.param1) return false;
        return Objects.equals(param2, classA.param2);
    }


}
