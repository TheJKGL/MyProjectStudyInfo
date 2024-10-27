package malakhov.study.jackson.bobocode_jackson.dto;

import java.time.LocalDateTime;

public record Message(Long id, String body, LocalDateTime createdAt) {
}
