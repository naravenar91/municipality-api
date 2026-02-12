package cl.aravena.infrastructure.adapter.in.config;

import cl.aravena.application.service.MunicipalityService;
import cl.aravena.application.service.ServicesService;
import cl.aravena.application.service.UserService;
import cl.aravena.domain.municipality.port.out.MunicipalityRepository;
import cl.aravena.domain.service.port.out.ServicesRepository;
import cl.aravena.domain.user.port.out.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

public class BeanConfig {

    @Produces
    @ApplicationScoped
    public MunicipalityService municipalityService(MunicipalityRepository repository) {
        return new MunicipalityService(repository);
    }

    @Produces
    @ApplicationScoped
    public UserService userService(UserRepository userRepository){
        return new UserService(userRepository);
    }

    @Produces
    @ApplicationScoped
    public ServicesService userService(ServicesRepository servicesRepository, UserRepository userRepository, MunicipalityRepository municipalityRepository){
        return new ServicesService(servicesRepository, userRepository, municipalityRepository);
    }
}
