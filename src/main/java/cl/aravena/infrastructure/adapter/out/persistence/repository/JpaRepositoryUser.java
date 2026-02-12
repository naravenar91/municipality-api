package cl.aravena.infrastructure.adapter.out.persistence.repository;

import cl.aravena.infrastructure.adapter.out.persistence.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JpaRepositoryUser implements PanacheRepository<UserEntity> {
}