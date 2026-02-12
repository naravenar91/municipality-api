package cl.aravena.domain.service.model;

import cl.aravena.domain.municipality.model.Municipality;
import cl.aravena.domain.user.model.User;

import java.util.List;

public record ServiceReport(
        User user,
        List<Municipality> municipalities,
        List<Service> services
) {}