package malakhov.study.java8.stream_collectors.entity;

public record Employee(
        int id, String name, int age, String gender, String department,
        int yearOfJoining, double salary
) {
}