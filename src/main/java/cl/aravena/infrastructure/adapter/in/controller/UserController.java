package cl.aravena.infrastructure.adapter.in.controller;

import cl.aravena.domain.common.valueobject.Name;
import cl.aravena.domain.municipality.model.Municipality;
import cl.aravena.domain.user.model.User;
import cl.aravena.domain.user.port.in.CreateUserUseCase;
import cl.aravena.domain.user.port.in.GetAllUsersUseCase;
import cl.aravena.domain.user.port.in.GetUserByIdUseCase;
import cl.aravena.infrastructure.adapter.in.dto.request.UserRequest;
import cl.aravena.infrastructure.adapter.in.dto.response.UserResponse;
import cl.aravena.infrastructure.adapter.out.persistence.mapper.WebUserMapper;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;

@Path("/api/user")
public class UserController {

    @Inject
    JsonWebToken jwt;

    private final CreateUserUseCase create;
    private final GetUserByIdUseCase getById;
    private final GetAllUsersUseCase getAll;
    private final WebUserMapper webUserMapper;

    public UserController(CreateUserUseCase create, GetUserByIdUseCase getById, GetAllUsersUseCase getAll, WebUserMapper webUserMapper) {
        this.create = create;
        this.getById = getById;
        this.getAll = getAll;
        this.webUserMapper = webUserMapper;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid UserRequest request){
        User user = User.createNew(request.userName(), new Name(request.name()), request.email(),
                request.address(), request.phone());
        User savedMunicipality = create.save(user);
        return Response.status(Response.Status.CREATED)
                .entity(webUserMapper.toResponse(savedMunicipality))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(Long id){

        // EXTRAER DATOS DEL JSON DE AUTH-API
        String userIdDeAuth = jwt.getClaim("userId");
        String uuidDeAuth = jwt.getClaim("uuid");
        System.out.println("Petición autorizada para el userId: " + userIdDeAuth + ", uuidDeAuth:" + uuidDeAuth);

        User user = getById.getById(id);
        return Response.ok(webUserMapper.toResponse(user)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserResponse> getAll(){
        List<User> municipalities = getAll.getAll();
        return webUserMapper.toResponseList(municipalities);
    }
}
