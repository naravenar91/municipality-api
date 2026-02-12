package cl.aravena.infrastructure.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ErrorResponse(
        String code,
        String message,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp,
        String path,
        Object details
) {}