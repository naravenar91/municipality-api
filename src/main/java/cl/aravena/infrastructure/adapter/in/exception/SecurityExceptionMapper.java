package cl.aravena.infrastructure.adapter.in.exception;

import io.quarkus.security.ForbiddenException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@jakarta.annotation.Priority(1)
public class SecurityExceptionMapper implements ExceptionMapper<ForbiddenException> {

    @Override
    public Response toResponse(ForbiddenException exception) {
        return Response.status(Response.Status.FORBIDDEN)
                .entity(new ApiError("AUTH_003", "You do not have permission to access this resource"))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}