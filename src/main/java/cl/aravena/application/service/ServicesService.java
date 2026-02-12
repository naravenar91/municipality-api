package cl.aravena.application.service;

import cl.aravena.domain.common.exception.DataAlreadyExistsException;
import cl.aravena.domain.common.exception.DataNotFoundException;
import cl.aravena.domain.municipality.model.Municipality;
import cl.aravena.domain.municipality.port.out.MunicipalityRepository;
import cl.aravena.domain.service.model.Service;
import cl.aravena.domain.service.port.in.CreateServiceUseCase;
import cl.aravena.domain.service.port.in.GetAllServicesUseCase;
import cl.aravena.domain.service.port.in.GetByIdUserAndIdMuniUseCase;
import cl.aravena.domain.service.port.out.ServicesRepository;
import cl.aravena.domain.user.model.User;
import cl.aravena.domain.user.port.out.UserRepository;
import cl.aravena.infrastructure.adapter.in.dto.response.ServiceReportResponse;

import java.util.List;
import java.util.Optional;

public class ServicesService implements CreateServiceUseCase, GetAllServicesUseCase, GetByIdUserAndIdMuniUseCase {

    private final ServicesRepository servicesRepository;
    private final UserRepository userRepository;
    private final MunicipalityRepository municipalityRepository;

    public ServicesService(ServicesRepository servicesRepository, UserRepository userRepository, MunicipalityRepository municipalityRepository) {
        this.servicesRepository = servicesRepository;
        this.userRepository = userRepository;
        this.municipalityRepository = municipalityRepository;
    }

    @Override
    public Service create(Service service) {

        if(servicesRepository.getByIdUserAndIdMuni(service.userId(), service.muniId()).isPresent()) {
            throw new DataAlreadyExistsException("Service","userId - muniId", service.userId() + ", muniId" + service.muniId());
        }

        return servicesRepository.create(service);
    }

    @Override
    public List<Service> getAll() {
        return servicesRepository.getAll();
    }

    @Override
    public Service getByIdUserAndIdMuni(Long userId, Long muniId) {
        return servicesRepository.getByIdUserAndIdMuni(userId, muniId).orElseThrow(
                () -> new DataNotFoundException(String.format("User Not Found with userId: %s and muniId: %s ", userId, muniId))
        );
    }

    public ServiceReportResponse getReportByUserId(Long userId) {
        // 1. Obtener el usuario
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        // 2. Obtener todos los servicios del usuario
        // (Asumiendo que implementaste un método findByUserId en tu adapter)
        List<Service> services = servicesRepository.findByUserId(userId);

        // 3. Obtener las municipalidades asociadas a esos servicios
        //List<Long> muniIds = services.stream().map(Service::muniId).toList();
        //List<Municipality> municipalities = municipalityRepository.findAllByIds(muniIds);

        // 4. Mapear a la respuesta
        ServiceReportResponse.UserResponse userRes = new ServiceReportResponse.UserResponse(
                user.userName(), user.name().value(), user.email(),
                user.address(), user.phone(), user.isActive(), user.createdAt()
        );

        return new ServiceReportResponse(userRes, null, services);
    }
}
