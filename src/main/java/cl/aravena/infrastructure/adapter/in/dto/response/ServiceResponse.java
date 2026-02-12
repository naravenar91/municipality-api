package cl.aravena.infrastructure.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(name = "ServiceResponse", description = "Respuesta de la asociación usuario-municipio")
public record ServiceResponse(
        @Schema(example = "111111111")
        Long userId,

        @Schema(example = "1")
        Long muniId,

        @Schema(example = "true")
        boolean isActive,

        @Schema(example = "11-02-2026 19:56:04")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createdAt
) {
}