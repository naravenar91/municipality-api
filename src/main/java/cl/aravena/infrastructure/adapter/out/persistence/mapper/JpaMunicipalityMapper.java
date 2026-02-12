package cl.aravena.infrastructure.adapter.out.persistence.mapper;

import cl.aravena.domain.common.valueobject.Name;
import cl.aravena.domain.municipality.model.Municipality;
import cl.aravena.infrastructure.adapter.out.persistence.entity.MunicipalityEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface JpaMunicipalityMapper {

    // De Dominio a Entidad
    @Mapping(target = "name", source = "name.value") // Extrae el String del Value Object
    MunicipalityEntity toEntity(Municipality domain);

    // De Entidad a Dominio
    @InheritInverseConfiguration(name = "toEntity")
    Municipality toDomain(MunicipalityEntity entity);

    // Para actualizaciones: Mapea los datos del dominio sobre una entidad existente
    @Mapping(target = "id", ignore = true) // El ID no debe cambiarse en un update
    @Mapping(target = "name", source = "name.value")
    void updateEntityFromDomain(Municipality domain, @MappingTarget MunicipalityEntity entity);

    // Método de ayuda para que MapStruct sepa cómo crear un Value Object Name desde un String
    default Name map(String value) {
        return value != null ? new Name(value) : null;
    }
}