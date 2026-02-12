package cl.aravena.infrastructure.adapter.out.persistence.mapper;

import cl.aravena.domain.service.model.Service;
import cl.aravena.infrastructure.adapter.out.persistence.entity.MunicipalityEntity;
import cl.aravena.infrastructure.adapter.out.persistence.entity.UserEntity;
import cl.aravena.infrastructure.adapter.out.persistence.entity.UserMunicipalityEntity;
import cl.aravena.infrastructure.adapter.out.persistence.repository.JpaRepositoryMunicipality;
import cl.aravena.infrastructure.adapter.out.persistence.repository.JpaRepositoryUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public abstract class JpaServiceMapper {
    @jakarta.inject.Inject
    JpaRepositoryUser userRepository;

    @jakarta.inject.Inject
    JpaRepositoryMunicipality muniRepository;

    @Mapping(target = "id.userId", source = "userId")
    @Mapping(target = "id.muniId", source = "muniId")
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "municipality", source = "muniId")
    @Mapping(target = "isActive", source = "isActive")
    public abstract UserMunicipalityEntity toEntity(Service domain);

    // MapStruct usará estos métodos automáticamente al ver las firmas
    public UserEntity mapUser(Long id) {
        return id == null ? null : userRepository.findById(id);
    }

    public MunicipalityEntity mapMuni(Long id) {
        return id == null ? null : muniRepository.findById(id);
    }

    @Mapping(target = "userId", source = "id.userId")
    @Mapping(target = "muniId", source = "id.muniId")
    public abstract Service toDomain(UserMunicipalityEntity entity);
}
