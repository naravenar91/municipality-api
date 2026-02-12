package cl.aravena.domain.service.port.in;

import cl.aravena.domain.service.model.ServiceReport;

public interface GetUserServiceReportUseCase {
    ServiceReport getReportService(Long userId);
}
