package cl.aravena.domain.service.port.in;

import cl.aravena.domain.service.model.Service;

import java.util.List;

public interface GetAllServicesUseCase {
    List<Service> getAll();
}
