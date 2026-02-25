package cl.aravena.infrastructure.adapter.out.rest;

import cl.aravena.infrastructure.adapter.in.dto.request.LoginRequest;
import cl.aravena.infrastructure.adapter.in.dto.response.AuthenticationResponse;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

// El nombre "auth-service" debe coincidir con el del yaml
@RegisterRestClient(baseUri = "stork://auth-service")
@Path("/api/auth") //El path que tiene tu API de Spring Boot
public interface AuthClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    AuthenticationResponse getAuthData();

    @POST
    @Path("/login") // Coincide con @PostMapping("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    AuthenticationResponse login(LoginRequest request);

    @GET // Coincide con @GetMapping sin path adicional
    @Produces(MediaType.APPLICATION_JSON)
    List<AuthenticationResponse> getAll();
}
