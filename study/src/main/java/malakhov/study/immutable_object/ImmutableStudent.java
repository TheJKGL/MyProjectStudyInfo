package malakhov.study.immutable_object;

public class ImmutableStudent {
    private final String name;
    private final Age age;

    public ImmutableStudent(String name, Age age) {
        this.name = name;
        this.age = new Age(age.getYear());
    }

    public String getName() {
        return name;
    }

    public Age getAge() {
        return new Age(this.age.getYear());
    }

    public ImmutableStudent setName(String name) {
        return new ImmutableStudent(name, this.age);
    }

    public ImmutableStudent setAge(Age age) {
        return new ImmutableStudent(this.name, age);
    }
}
