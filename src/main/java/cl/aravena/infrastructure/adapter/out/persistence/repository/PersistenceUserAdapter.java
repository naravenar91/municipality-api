package cl.aravena.infrastructure.adapter.out.persistence.repository;

import cl.aravena.domain.user.model.User;
import cl.aravena.domain.user.port.out.UserRepository;
import cl.aravena.infrastructure.adapter.out.persistence.entity.UserEntity;
import cl.aravena.infrastructure.adapter.out.persistence.mapper.JpaUserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PersistenceUserAdapter implements UserRepository {

    private final JpaRepositoryUser jpaRepositoryUser;
    private final JpaUserMapper mapper;

    public PersistenceUserAdapter(JpaRepositoryUser jpaRepositoryUser, JpaUserMapper mapper) {
        this.jpaRepositoryUser = jpaRepositoryUser;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public User create(User user) {
        UserEntity entity = mapper.toEntity(user);
        jpaRepositoryUser.persist(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepositoryUser.findByIdOptional(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return jpaRepositoryUser.listAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
