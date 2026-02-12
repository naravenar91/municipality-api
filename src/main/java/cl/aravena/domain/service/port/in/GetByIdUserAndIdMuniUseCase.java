package cl.aravena.domain.service.port.in;

import cl.aravena.domain.service.model.Service;

import java.util.Optional;

public interface GetByIdUserAndIdMuniUseCase {
    Service getByIdUserAndIdMuni(Long userId, Long muniId);
}
