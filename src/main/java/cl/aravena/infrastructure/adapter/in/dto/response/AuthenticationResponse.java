package cl.aravena.infrastructure.adapter.in.dto.response;

public record AuthenticationResponse(
        String uuid,
        String userId,
        boolean isActive,
        String role,
        String token
) {}