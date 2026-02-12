package cl.aravena.infrastructure.adapter.in.dto.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "UserMunicipalityRequest", description = "Datos para asociar un usuario a una municipalidad")
public record UserMunicipalityRequest(
        @NotNull
        @Schema(example = "111111111", description = "RUT User")
        String userId,

        @NotNull
        @Schema(example = "1")
        Long muniId,

        @Schema(example = "true")
        boolean isActive
) {
}
