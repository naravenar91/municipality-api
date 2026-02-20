package cl.aravena.infrastructure.adapter.in.security;
/*
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import jakarta.inject.Inject;

@Provider
@Priority(Priorities.AUTHORIZATION)
 */
public class GlobalSecurityFilter /*implements ContainerRequestFilter */{

    /*@Inject
    JsonWebToken jwt;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath();
        String method = requestContext.getMethod();

        // Ejemplo de lógica centralizada:
        if (path.startsWith("api/user") && !jwt.getGroups().contains("ADMIN")) {
            requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN)
                            .entity("Acceso denegado: Se requiere rol ADMIN")
                            .build()
            );
        }
    }*/
}