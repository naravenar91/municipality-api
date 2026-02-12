package cl.aravena.domain.municipality.port.in;

import cl.aravena.domain.municipality.model.Municipality;

public interface CreateMunicipalityUseCase {
    Municipality save(Municipality municipality);
}
