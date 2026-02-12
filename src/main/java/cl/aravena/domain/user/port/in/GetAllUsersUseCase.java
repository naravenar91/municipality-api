package cl.aravena.domain.user.port.in;

import cl.aravena.domain.user.model.User;

import java.util.List;

public interface GetAllUsersUseCase {
    List<User> getAll();
}
