package cl.aravena.domain.municipality.port.out;

import cl.aravena.domain.municipality.model.Municipality;

import java.util.List;
import java.util.Optional;

public interface MunicipalityRepository {
    Municipality save(Municipality municipality);
    Municipality update(Municipality municipality);
    Optional<Municipality> findById(Long id);
    List<Municipality> findAll();
    Optional<Municipality> findByName(String name);
    //List<Municipality> findAllByIds(List<Long> ids);
}
