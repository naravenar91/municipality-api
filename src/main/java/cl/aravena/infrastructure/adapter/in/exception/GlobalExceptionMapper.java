package cl.aravena.infrastructure.adapter.in.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionMapper.class);

    @Override
    public Response toResponse(Throwable exception) {
        // Logueamos el error real para poder debuguear en consola
        log.error("Unhandled Exception: ", exception);

        // Devolvemos un JSON genérico al cliente
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ApiError("ERR_500", "An unexpected error has occurred on the server"))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}