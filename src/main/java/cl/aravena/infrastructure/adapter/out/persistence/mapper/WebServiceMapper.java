package cl.aravena.infrastructure.adapter.out.persistence.mapper;

import cl.aravena.domain.service.model.Service; // Asegúrate que el path sea correcto
import cl.aravena.domain.service.model.ServiceReport;
import cl.aravena.infrastructure.adapter.in.dto.response.ServiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface WebServiceMapper {

    // Convierte el modelo de Dominio (Service) al DTO de salida (Response)
    ServiceResponse toResponse(Service domain);

    //ServiceReport toRepoResponse(ServiceReport domain);

    // Convierte listas completas (útil para GET /services)
    List<ServiceResponse> toResponseList(List<Service> domainList);
}