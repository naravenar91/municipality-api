package cl.aravena.domain.user.port.in;

import cl.aravena.domain.user.model.User;

public interface CreateUserUseCase {
    User save(User user);
}
