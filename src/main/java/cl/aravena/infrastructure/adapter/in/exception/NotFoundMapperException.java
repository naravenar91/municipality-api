package cl.aravena.infrastructure.adapter.in.exception;

import cl.aravena.domain.common.exception.DataNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.Map;

@Provider
public class NotFoundMapperException implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(DataNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(Map.of(
                        "error", exception.getMessage(),
                        "code", 404
                ))
                .build();
    }
}
