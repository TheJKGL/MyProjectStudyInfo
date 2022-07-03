package malakhov.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<String> valuse = new ArrayList<>();
        valuse.stream().filter(a->null!=a && !"".equals(a)).skip(2).limit(2);
    }
}
