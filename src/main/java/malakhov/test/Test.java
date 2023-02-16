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

        List<String> list2 = Arrays.asList();
        String opt =  list2.stream().min(Comparator.naturalOrder()).orElse(null);
        System.out.println(opt);

    }
}
