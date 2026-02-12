package cl.aravena.domain.service.model;

import java.time.LocalDateTime;

public record Service(Long userId,
                      Long muniId,
                      boolean isActive,
                      LocalDateTime createdAt) {
}
