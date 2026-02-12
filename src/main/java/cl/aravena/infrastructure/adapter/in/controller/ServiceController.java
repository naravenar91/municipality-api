package cl.aravena.infrastructure.adapter.in.controller;

import cl.aravena.domain.service.model.Service;
import cl.aravena.domain.service.port.in.CreateServiceUseCase;
import cl.aravena.domain.service.port.in.GetAllServicesUseCase;
import cl.aravena.infrastructure.adapter.in.dto.request.ServiceRequest;
import cl.aravena.infrastructure.adapter.in.dto.response.ServiceResponse;
import cl.aravena.infrastructure.adapter.out.persistence.mapper.WebServiceMapper;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/service")
public class ServiceController {

    private final GetAllServicesUseCase getAllService;
    private final CreateServiceUseCase createService;
    private final WebServiceMapper webServiceMapper;

    public ServiceController(GetAllServicesUseCase getAllService, CreateServiceUseCase createService, WebServiceMapper webServiceMapper) {
        this.getAllService = getAllService;
        this.createService = createService;
        this.webServiceMapper = webServiceMapper;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveService(@Valid ServiceRequest request){
        Service service = new Service(request.userId(), request.muniId(), request.isActive(), null);
        Service savedService = createService.create(service);
        return Response.status(Response.Status.CREATED)
                .entity(webServiceMapper.toResponse(savedService))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ServiceResponse> getAllService(){
        List<Service> municipalities = getAllService.getAll();
        return webServiceMapper.toResponseList(municipalities);
    }

    /*
    @GET
    @Path("/services/report/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReportById(Long id){
        ServiceReport municipality = serviceRepo.getReportService(id);
        return Response.ok(webServiceMapper.toRepoResponse(municipality)).build();
    }
     */
}
