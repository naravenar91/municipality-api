package cl.aravena.infrastructure.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record MunicipalityResponse(
        Long id,
        String name,
        String address,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createdAt,
        Long region
) {
}