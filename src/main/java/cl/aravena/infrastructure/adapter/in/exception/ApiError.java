package cl.aravena.infrastructure.adapter.in.exception;

public record ApiError(String code,
                       String message)
{}