package cl.aravena.domain.municipality.model;

import cl.aravena.domain.common.exception.ValidationException;
import cl.aravena.domain.common.valueobject.Name;

import java.time.LocalDateTime;

public record Municipality(Long id,
                           Name name,
                           String address,
                           LocalDateTime createdAt,
                           Long region) {

    public Municipality {
        if(name == null){
            throw new ValidationException("The Name object is required");
        }

        if (region == null) {
            throw new ValidationException("The Region ID is required");
        }
    }
}
