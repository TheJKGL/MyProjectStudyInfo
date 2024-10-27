package malakhov.study.сoncurrency.producer_consumer.kt_practice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Imitates an item produced by {@link Producer} and consumed by {@link Consumer}.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SyncItem {

    private String description;
    private int quantity;
    private LocalDateTime createdAt;
}
