package cl.aravena.application.service;

import cl.aravena.domain.common.exception.DataAlreadyExistsException;
import cl.aravena.domain.common.exception.DataNotFoundException;
import cl.aravena.domain.municipality.model.Municipality;
import cl.aravena.domain.municipality.port.in.CreateMunicipalityUseCase;
import cl.aravena.domain.municipality.port.in.GetAllMunicipalitiesUseCase;
import cl.aravena.domain.municipality.port.in.GetMunicipalityByIdUseCase;
import cl.aravena.domain.municipality.port.in.UpdateMunicipalityUseCase;
import cl.aravena.domain.municipality.port.out.MunicipalityRepository;

import java.util.List;

public class MunicipalityService implements CreateMunicipalityUseCase, UpdateMunicipalityUseCase, GetMunicipalityByIdUseCase, GetAllMunicipalitiesUseCase {

    private final MunicipalityRepository municipalityRepository;

    public MunicipalityService(MunicipalityRepository municipalityRepository) {
        this.municipalityRepository = municipalityRepository;
    }

    @Override
    public Municipality save(Municipality municipality) {
        if(municipalityRepository.findByName(municipality.name().value()).isPresent()){
            throw new DataAlreadyExistsException("Municipality", "id", municipality.name());
        }

        return municipalityRepository.save(municipality);
    }

    @Override
    public List<Municipality> findAll() {
        return municipalityRepository.findAll();
    }

    @Override
    public Municipality findById(Long id) {
        return municipalityRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Municipality Not Found with id: " + id));
    }

    @Override
    public Municipality update(Municipality municipality) {
        if(municipalityRepository.findById(municipality.id()).isEmpty()){
            throw new DataNotFoundException("Municipality Not Found with id: " + municipality.id());
        }
        return municipalityRepository.update(municipality);
    }
}
