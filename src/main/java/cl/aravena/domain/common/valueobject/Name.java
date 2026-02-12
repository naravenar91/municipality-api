package cl.aravena.domain.common.valueobject;

import cl.aravena.domain.common.exception.ValidationException;

public record Name(String value) {
    public Name {
        if(value == null || value.isBlank()){
            throw new ValidationException("The name is required");
        }
    }
}
