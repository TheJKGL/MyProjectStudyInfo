package malakhov.kt_practice.p1_stream_collectors_practice.entity;

public record Employee(
        int id, String name, int age, String gender, String department,
        int yearOfJoining, double salary
) {
}