package cl.aravena.infrastructure.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "UserResponse", description = "Detailed user response")
public record UserResponse(
        Long id,
        String userUuid,
        String userName,
        @Schema(example = "Juanito")
        String name,
        @Schema(example = "emailuser@test.com")
        String email,
        @Schema(example = "housse 123")
        String address,
        @Schema(example = "+56911111111")
        String phone,
        @Schema(example = "true")
        boolean isActive,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createdAt
) {
}