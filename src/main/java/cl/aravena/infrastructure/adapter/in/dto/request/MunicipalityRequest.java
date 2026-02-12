package cl.aravena.infrastructure.adapter.in.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Request body for creating or updating a municipality")
public record MunicipalityRequest(
        @Schema(example = "2", description = "Unique identifier of the municipality")
        Long id,

        @NotBlank(message = "The name is required")
        @Size(min = 3, message = "The name must be at least 3 characters long")
        @Schema(example = "Concepción", description = "Name of the municipality")
        String name,

        String address,

        @Column(name = "created_at")
        LocalDateTime createdAt,

        @NotNull(message = "Region ID is required")
        @Schema(example = "8", description = "Region ID associated with the municipality")
        Long region
) {
}
