package cl.aravena.infrastructure.adapter.out.persistence.repository;

import cl.aravena.domain.common.exception.DataNotFoundException;
import cl.aravena.domain.municipality.model.Municipality;
import cl.aravena.domain.municipality.port.out.MunicipalityRepository;
import cl.aravena.domain.service.model.Service;
import cl.aravena.domain.service.model.ServiceReport;
import cl.aravena.domain.service.port.out.ServicesRepository;
import cl.aravena.domain.user.model.User;
import cl.aravena.infrastructure.adapter.out.persistence.entity.MunicipalityEntity;
import cl.aravena.infrastructure.adapter.out.persistence.entity.UserMunicipalityEntity;
import cl.aravena.infrastructure.adapter.out.persistence.mapper.JpaMunicipalityMapper;
import cl.aravena.infrastructure.adapter.out.persistence.mapper.JpaServiceMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PersistenceMunicipalityAdapter implements MunicipalityRepository, ServicesRepository {

    private final JpaRepositoryMunicipality jpaRepositoryMunicipality;
    //private final JpaRepositoryUser jpaRepositoryUser;
    private final JpaRepositoryMuniService jpaRepositoryMuniService;

    private final JpaMunicipalityMapper municipalityMapper;
    private final JpaServiceMapper serviceMapper;

    public PersistenceMunicipalityAdapter(JpaRepositoryMunicipality jpaRepositoryMunicipality, JpaRepositoryMuniService jpaRepositoryMuniService, JpaMunicipalityMapper municipalityMapper, JpaServiceMapper serviceMapper) {
        this.jpaRepositoryMunicipality = jpaRepositoryMunicipality;
        this.jpaRepositoryMuniService = jpaRepositoryMuniService;
        this.municipalityMapper = municipalityMapper;
        this.serviceMapper = serviceMapper;
    }


    @Override
    @Transactional
    public Municipality save(Municipality municipality) {
        MunicipalityEntity entity = municipalityMapper.toEntity(municipality);
        jpaRepositoryMunicipality.persist(entity);
        return municipalityMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public Municipality update(Municipality municipality) {
        MunicipalityEntity entity = jpaRepositoryMunicipality.findByIdOptional(municipality.id())
                .orElseThrow(() -> new DataNotFoundException("Municipality Not Found with id:" + municipality.id()));
        municipalityMapper.updateEntityFromDomain(municipality, entity);
        return municipalityMapper.toDomain(entity);
    }

    @Override
    public Optional<Municipality> findById(Long id) {
        return jpaRepositoryMunicipality.findByIdOptional(id)
                .map(municipalityMapper::toDomain);
    }

    @Override
    public List<Municipality> findAll() {
        return jpaRepositoryMunicipality.listAll().stream()
                .map(municipalityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Municipality> findByName(String name) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Service create(Service service) {
        UserMunicipalityEntity entity = serviceMapper.toEntity(service);
        jpaRepositoryMuniService.persist(entity);
        return serviceMapper.toDomain(entity);
    }

    @Override
    public List<Service> findByUserId(Long idUser) {
        return List.of();
    }

    @Override
    public List<Service> getAll() {
        return jpaRepositoryMuniService.listAll().stream()
                .map(serviceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Service> getByIdUserAndIdMuni(Long userId, Long muniId) {
        return jpaRepositoryMuniService.getByIdUserAndIdMuni(userId, muniId)
                .map(serviceMapper::toDomain);
    }

    /*
    @Override
    public ServiceReport getReportService(Long userId) {
        User user = jpaRepositoryUser.findById(userId);
        List<Service> services = serviceRepo.findAllByUserId(userId); // Tendrás que añadir este método al Port

        List<Long> muniIds = services.stream().map(Service::muniId).toList();
        List<Municipality> munis = muniRepo.findAllByIds(muniIds); // Tendrás que añadir este método al Port

        return new ServiceReport(user, munis, services);
    }
     */
}
