package cl.aravena.infrastructure.adapter.in.controller;

import cl.aravena.domain.common.valueobject.Name;
import cl.aravena.domain.user.model.User;
import cl.aravena.domain.user.port.in.CreateUserUseCase;
import cl.aravena.domain.user.port.in.GetAllUsersUseCase;
import cl.aravena.domain.user.port.in.GetUserByIdUseCase;
import cl.aravena.infrastructure.adapter.in.dto.request.UserRequest;
import cl.aravena.infrastructure.adapter.in.dto.response.UserResponse;
import cl.aravena.infrastructure.adapter.out.persistence.mapper.WebUserMapper;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/api/user")
//@RolesAllowed({"ADMIN", "ROLE_ADMIN"})
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

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
    @PermitAll
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

        System.out.println("********************************************************************************");
        System.out.println("Grupos detectados: " + jwt.getGroups());
        System.out.println("Claims: " + jwt.getClaimNames());
        System.out.println("********************************************************************************");

        // EXTRAER DATOS DEL JSON DE AUTH-API
        String userIdDeAuth = jwt.getSubject(); // Esto mapea automáticamente al "sub" del token
        String uuidDeAuth = jwt.getClaim("uuid"); // Este sí es un claim personalizado

        System.out.println("Petición autorizada para el userId: " + userIdDeAuth + ", uuidDeAuth:" + uuidDeAuth);
        log.info("UserController.getById id:{}, userId: {}, uuidDeAuth:{}", id, userIdDeAuth, uuidDeAuth);

        User user = getById.getById(id);
        return Response.ok(webUserMapper.toResponse(user)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserResponse> getAll(){
        List<User> municipalities = getAll.getAll();
        log.info("***** UserController.getAll ***** municipalities:{}", municipalities);
        return webUserMapper.toResponseList(municipalities);
    }
}
