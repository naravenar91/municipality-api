package cl.aravena.infrastructure.adapter.out.persistence.mapper;

import cl.aravena.domain.municipality.model.Municipality;
import cl.aravena.infrastructure.adapter.in.dto.response.MunicipalityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface WebMunicipalityMapper {
    @Mapping(target = "name", source = "name.value")
    MunicipalityResponse toResponse(Municipality domain);

    List<MunicipalityResponse> toResponseList(List<Municipality> domainList);
}