package cl.aravena.infrastructure.adapter.in.dto.request;

public record LoginRequest(
        String userName,
        String password
) {}