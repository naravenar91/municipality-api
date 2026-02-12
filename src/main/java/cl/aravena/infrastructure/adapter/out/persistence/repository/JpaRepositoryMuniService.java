package cl.aravena.infrastructure.adapter.out.persistence.repository;

import cl.aravena.infrastructure.adapter.out.persistence.entity.UserMunicipalityEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JpaRepositoryMuniService implements PanacheRepository<UserMunicipalityEntity> {
    Optional<UserMunicipalityEntity> getByIdUserAndIdMuni(Long userId, Long muniId){
        return find("id.userId = ?1 and id.muniId = ?2", userId, muniId).firstResultOptional();
    }

    public List<UserMunicipalityEntity> findByUserId(Long userId) {
        return list("id.userId", userId);
    }
}