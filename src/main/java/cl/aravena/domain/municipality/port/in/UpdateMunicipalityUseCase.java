package cl.aravena.domain.municipality.port.in;

import cl.aravena.domain.municipality.model.Municipality;

public interface UpdateMunicipalityUseCase {
    Municipality update(Municipality municipality);
}
