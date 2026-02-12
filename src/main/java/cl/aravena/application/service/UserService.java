package cl.aravena.application.service;

import cl.aravena.domain.common.exception.DataNotFoundException;
import cl.aravena.domain.user.model.User;
import cl.aravena.domain.user.port.in.CreateUserUseCase;
import cl.aravena.domain.user.port.in.GetAllUsersUseCase;
import cl.aravena.domain.user.port.in.GetUserByIdUseCase;
import cl.aravena.domain.user.port.out.UserRepository;

import java.util.List;

public class UserService implements CreateUserUseCase, GetUserByIdUseCase, GetAllUsersUseCase {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.create(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("User Not Found with id: " + id));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
