package malakhov.study.java8.Lambdas;

import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
        numbers.forEach(s -> System.out.print(s + " "));// TODO: <-
        System.out.println("\n====================================================");

        //Тоже ничего сложного. Метод перебирает коллекцию, и удаляет те элементы, которые соответствуют filter.
        List <Integer> numbers2 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
        numbers2.removeIf(s -> s < 5);// TODO: <-
        numbers2.forEach(s -> System.out.print(s + " "));
        System.out.println("\n====================================================");

        //Метод forEach работает не только для классов, реализующих интерфейс Collection, но и для Map.
        Map<String, String> books = new HashMap<>();
        books.put("Война и мир", "Лев Толстой");
        books.put("Преступление и наказание", "Федор Достоевский");
        books.forEach((a,b) -> System.out.println("Название книги: " + a + ". Автор: " + b));// TODO: <-
        System.out.println("\n====================================================");

        //Для указанного ключа key этот метод устанавливает в качестве value результат выполнения функции remappingFunction.
        Map <String, String> books2 = new HashMap<>();
        books2.put("Война и мир", "Лев Толстой");
        books2.put("Философия Java", "Брюс Эккель");
        books2.forEach((a,b) -> System.out.println("Название книги: " + a + ". Автор: " + b));

        books2.compute("Философия Java", (a,b) -> b+", крутой чувак");// TODO: <-
        System.out.println("_______________________");
        books2.forEach((a,b) -> System.out.println("Название книги: " + a + ". Автор: " + b));
        System.out.println("\n====================================================");

        //Тот же принцип, что и у Map.compute(), но все вычисления будут выполнены только в случае, если элемент с ключом key уже существует.
        Map <String, String> books3 = new HashMap<>();
        books3.put("Братья Карамазовы", "Федор Достоевский");

        books3.computeIfPresent("Евгений Онегин", (a,b) -> b="Александр Пушкин");// TODO: <-
        System.out.println("_________________");
        books3.forEach((a,b) -> System.out.println("Название книги: " + a + ". Автор: " + b));
        books3.computeIfPresent("Братья Карамазовы", (a,b) -> b="Александр Пушкин");// TODO: <-
        System.out.println("_________________");
        books3.forEach((a,b) -> System.out.println("Название книги: " + a + ". Автор: " + b));
    }
}
