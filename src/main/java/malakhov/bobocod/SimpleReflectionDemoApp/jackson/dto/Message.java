package malakhov.bobocod.SimpleReflectionDemoApp.jackson.dto;

import java.time.LocalDateTime;

public record Message(Long id, String body, LocalDateTime createdAt) {
}
