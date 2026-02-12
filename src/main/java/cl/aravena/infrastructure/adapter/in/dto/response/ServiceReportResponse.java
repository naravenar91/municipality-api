package cl.aravena.infrastructure.adapter.in.dto.response;

import cl.aravena.domain.municipality.model.Municipality;
import cl.aravena.domain.service.model.Service;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

public record ServiceReportResponse(
        UserResponse user,
        List<Municipality> municipality,
        List<Service> services
) {
    public record UserResponse(
            String userName,
            String name,
            String email,
            String address,
            String phone,
            boolean isActive,
            @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
            java.time.LocalDateTime createdAt
    ) {}
}