package cl.aravena.domain.service.port.out;

import cl.aravena.domain.service.model.Service;
import cl.aravena.domain.service.model.ServiceReport;

import java.util.List;
import java.util.Optional;

public interface ServicesRepository {
    Service create(Service service);
    List<Service> findByUserId(Long idUser);
    List<Service> getAll();
    Optional<Service> getByIdUserAndIdMuni(Long userId, Long muniId);
    //ServiceReport getReportService(Long userId);
    //List<Service> findAllByUserId(Long userId);
}
