package cl.aravena.infrastructure.adapter.out.persistence.mapper;

import cl.aravena.domain.common.valueobject.Name;
import cl.aravena.domain.user.model.User;
import cl.aravena.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface JpaUserMapper {


    @Mapping(target = "name", source = "name.value")
    @Mapping(target = "userServiceId", ignore = true)
    UserEntity toEntity(User user);

    @InheritInverseConfiguration(name = "toEntity")
    User toDomain(UserEntity user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name.value")
    void updateEntityFromDomain(User domain, @MappingTarget UserEntity entity);

    default Name map(String value) {
        return value != null ? new Name(value) : null;
    }
}