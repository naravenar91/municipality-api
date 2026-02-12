package cl.aravena.infrastructure.adapter.in.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Request body for creating a municipality")
public record ServiceRequest(
        @NotNull(message = "User ID is required")
        @Positive(message = "User ID must be positive")
        @Schema(example = "1", description = "ID User")
        Long userId,

        @NotNull(message = "Municipality ID is required")
        @Positive(message = "Municipality ID must be positive")
        @Schema(example = "2", description = "ID Municipality")
        Long muniId,

        boolean isActive

        //@Column(name = "created_at")
        //LocalDateTime createdAt
) {
}