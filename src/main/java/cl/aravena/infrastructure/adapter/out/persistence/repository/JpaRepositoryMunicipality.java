package cl.aravena.infrastructure.adapter.out.persistence.repository;

import cl.aravena.infrastructure.adapter.out.persistence.entity.MunicipalityEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JpaRepositoryMunicipality implements PanacheRepository<MunicipalityEntity> {
}