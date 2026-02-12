package cl.aravena.domain.service.port.in;

import cl.aravena.domain.service.model.Service;

public interface CreateServiceUseCase {
    Service create(Service service);
}
