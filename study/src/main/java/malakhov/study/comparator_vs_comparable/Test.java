package malakhov.study.comparator_vs_comparable;

public class Test implements Comparable<Test>{
    public Test() {
    }

    @Override
    public int compareTo(Test o) {
        return 0;
    }
}
