package malakhov.bobocod.PersisteceReflectionDemoApp.entity;



import lombok.Data;
import malakhov.bobocod.PersisteceReflectionDemoApp.annotation.Column;
import malakhov.bobocod.PersisteceReflectionDemoApp.annotation.Id;
import malakhov.bobocod.PersisteceReflectionDemoApp.annotation.Table;

@Data
@Table("persons")
public class Person {
    @Id
    private Integer id;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;
}
