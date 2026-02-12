package cl.aravena.domain.municipality.port.in;

import cl.aravena.domain.municipality.model.Municipality;

import java.util.List;

public interface GetAllMunicipalitiesUseCase {
    List<Municipality> findAll();
}
