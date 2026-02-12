package cl.aravena.infrastructure.adapter.in.controller;

import cl.aravena.domain.common.valueobject.Name;
import cl.aravena.domain.municipality.model.Municipality;
import cl.aravena.domain.municipality.port.in.CreateMunicipalityUseCase;
import cl.aravena.domain.municipality.port.in.GetAllMunicipalitiesUseCase;
import cl.aravena.domain.municipality.port.in.GetMunicipalityByIdUseCase;
import cl.aravena.domain.municipality.port.in.UpdateMunicipalityUseCase;
import cl.aravena.domain.service.model.Service;
import cl.aravena.domain.service.model.ServiceReport;
import cl.aravena.domain.service.port.in.CreateServiceUseCase;
import cl.aravena.domain.service.port.in.GetAllServicesUseCase;
import cl.aravena.domain.service.port.in.GetUserServiceReportUseCase;
import cl.aravena.infrastructure.adapter.in.dto.request.MunicipalityRequest;
import cl.aravena.infrastructure.adapter.in.dto.request.ServiceRequest;
import cl.aravena.infrastructure.adapter.in.dto.response.MunicipalityResponse;
import cl.aravena.infrastructure.adapter.in.dto.response.ServiceResponse;
import cl.aravena.infrastructure.adapter.out.persistence.mapper.WebMunicipalityMapper;
import cl.aravena.infrastructure.adapter.out.persistence.mapper.WebServiceMapper;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/api/municipality")
@Tag(name = "Municipality", description = "Operations related to municipalities")
public class MunicipalityController {

    private final CreateMunicipalityUseCase create;
    private final UpdateMunicipalityUseCase update;
    private final GetMunicipalityByIdUseCase getById;
    private final GetAllMunicipalitiesUseCase getAll;
    private final WebMunicipalityMapper webMunicipalityMapper;

    public MunicipalityController(CreateMunicipalityUseCase create, UpdateMunicipalityUseCase update, GetMunicipalityByIdUseCase getById, GetAllMunicipalitiesUseCase getAll, WebMunicipalityMapper webMunicipalityMapper) {
        this.create = create;
        this.update = update;
        this.getById = getById;
        this.getAll = getAll;
        this.webMunicipalityMapper = webMunicipalityMapper;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid MunicipalityRequest request){
        Municipality municipality = new Municipality(null, new Name(request.name()), request.address(), request.createdAt() , request.region());
        Municipality savedMunicipality = create.save(municipality);
        return Response.status(Response.Status.CREATED)
                .entity(webMunicipalityMapper.toResponse(savedMunicipality))
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update an existing municipality")
    public Response update(
            @RequestBody(
                    description = "Municipality data to update",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MunicipalityRequest.class),
                            example = "{\"id\": 2, \"name\": \"Concepción\", \"region\": 8}"
                    )
            )
            @Valid MunicipalityRequest request){
        Municipality municipality = new Municipality(request.id(), new Name(request.name()), request.address(), request.createdAt() , request.region());
        Municipality savedMunicipality = update.update(municipality);
        return Response.status(Response.Status.CREATED)
                .entity(webMunicipalityMapper.toResponse(savedMunicipality))
                .build();
    }

    @Operation(summary = "Get a municipality by ID", description = "Returns a specific municipality based on its unique identifier")
    @APIResponse(responseCode = "200", description = "Municipality found")
    @APIResponse(responseCode = "404", description = "Municipality not found")
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(Long id){
        Municipality municipality = getById.findById(id);
        return Response.ok(webMunicipalityMapper.toResponse(municipality)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MunicipalityResponse> getAll(){
        List<Municipality> municipalities = getAll.findAll();
        return webMunicipalityMapper.toResponseList(municipalities);
    }
}