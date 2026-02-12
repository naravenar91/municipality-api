package cl.aravena.domain.user.port.in;

import cl.aravena.domain.user.model.User;

import java.util.Optional;

public interface GetUserByIdUseCase {
    User getById(Long id);
}
