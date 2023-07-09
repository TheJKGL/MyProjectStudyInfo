package malakhov.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {
    boolean testes;
    public static void main(String[] args) {
        //List<String> valuse = new ArrayList<>();
        //valuse.stream().filter(a->null!=a && !"".equals(a)).skip(2).limit(2);


        /*Set<Student> set = new HashSet<>();
        set.add(new Student());
        Map<Integer, Set<Student>> map = new HashMap<>();
        map.put(1, set);

        Set<Student> result = map.get(111);
        System.out.println(result);*/

        /*List<String> list = new ArrayList<>( Arrays.asList("alex", "brian", "charles") );

        list.add("baz");*/

        /*List<String> list2 = Arrays.asList();
        String opt =  list2.stream().min(Comparator.naturalOrder()).orElse(null);
        System.out.println(opt);*/

        //System.out.println(printNumber());

        System.out.println(System.getProperty("sun.arch"));
        System.out.println("JVM Bit size: " + System.getProperty("os.arch"));
        int var1;
        int _123;
        int $;
        int abc;
        $ = 1;
        System.out.println($);

        int x = 5*4%3;
        System.out.println("-------");
        String line1 = "123";
        String line2 = new String("123");
        System.out.println(line1 == "123");
        System.out.println(line1 == line2);

        switch (2){
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
            default:
                System.out.println(0);
        }

    }

    public boolean isTestes() {
        return testes;
    }

    public void setTestes(boolean val) {
        testes = val;
    }

    static long printNumber() { return (1 == 1) ? null : 1L; }
}
