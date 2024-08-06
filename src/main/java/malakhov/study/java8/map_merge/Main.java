package malakhov.study.java8.map_merge;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static Map<String, Employee> map1 = new HashMap<>();
    private static Map<String, Employee> map2 = new HashMap<>();

    public static void main(String[] args) {
        Employee employee1 = new Employee(1L, "Henry");
        map1.put(employee1.getName(), employee1);
        Employee employee2 = new Employee(22L, "Annie");
        map1.put(employee2.getName(), employee2);
        Employee employee3 = new Employee(8L, "John");
        map1.put(employee3.getName(), employee3);

        Employee employee4 = new Employee(2L, "George");
        map2.put(employee4.getName(), employee4);
        Employee employee5 = new Employee(3L, "Henry");
        map2.put(employee5.getName(), employee5);

        Map<String, Employee> map3 = new HashMap<>(map1);

        // ----------------------  Map.merge() --------------------------
        //map3.merge(key, value, (v1, v2) -> new Employee(v1.getId(),v2.getName())
        map2.forEach(
                (key, value) -> map3.merge(key, value, (v1, v2) -> new Employee(v1.getId(),v2.getName())));

        for (String name: map3.keySet()) {
            String key = name;
            String value = map3.get(key).toString();
            System.out.println(key + " " + value);
        }
        //Also, we notice that the Employee object of the last entry has the id from the map1,
        // and value is picked from map2.

        // ------------------------- Stream.concat() ----------------------------
        Stream<Map.Entry<String, Employee>> combined = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream());
        Map<String, Employee> result = combined
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (value1, value2) -> new Employee(value2.getId(), value1.getName())));
        for (String name: result.keySet()) {
            String key = name;
            String value = map3.get(key).toString();
            System.out.println(key + " " + value);
        }
        //As we see, the duplicate entries with the key “Henry” were merged into a new key-value pair
        // where the id of the new Employee was picked from the map2 and the value from map1.

        // ------------------------------ Stream.of() ----------------------------------
        Map<String, Employee> map33 = Stream.of(map1, map2)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> new Employee(v1.getId(), v2.getName())));

        // -------------------------------- Simple Streaming -------------------------------------
        Map<String, Employee> map333 = map2.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> new Employee(v1.getId(), v2.getName()),
                        () -> new HashMap<>(map1)));

        //-------------------------------- StreamEx -------------------------------------------
        //In addition to solutions that are provided by the JDK, we can also use the popular StreamEx library.
//        Map<String, Employee> map3 = EntryStream.of(map1)
//                .append(EntryStream.of(map2))
//                .toMap((e1, e2) -> e1);
    }
}
