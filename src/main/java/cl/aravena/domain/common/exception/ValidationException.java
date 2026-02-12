package cl.aravena.domain.common.exception;

public class ValidationException extends DomainException {
    public ValidationException(String message) {
        super(message);
    }
}