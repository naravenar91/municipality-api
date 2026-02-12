package cl.aravena.infrastructure.adapter.in.dto.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Schema(name = "UserRequest", description = "Information for registering a new resident")
public record UserRequest(
        //Long id,
        //String userUuid,
        @NotBlank
        @Schema(example = "111111111", description = "RUT User")
        String userName,

        @NotBlank
        @Schema(example = "Juanito")
        String name,

        @Email
        @Schema(example = "emailuser@test.com")
        String email,

        @Schema(example = "house 123")
        String address,

        @Schema(example = "+56911111111")
        String phone

        //boolean isActive,
        //LocalDateTime createdAt
    ) {
}
