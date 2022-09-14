package malakhov.bobocod.PersisteceReflectionDemoApp.entity;



import lombok.Data;
import malakhov.bobocod.PersisteceReflectionDemoApp.annotation.Column;
import malakhov.bobocod.PersisteceReflectionDemoApp.annotation.Id;
import malakhov.bobocod.PersisteceReflectionDemoApp.annotation.Table;

import java.time.LocalDateTime;

@Data
@Table("notes")
public class Note {
    @Id
    private Integer id;

    private String body;

    @Column("person_id")
    private Integer personId;

    @Column("created_at")
    private LocalDateTime createdAt;
}
