package cl.aravena.infrastructure.adapter.in.exception;

import cl.aravena.infrastructure.adapter.in.dto.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationMapperException implements ExceptionMapper<ConstraintViolationException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ConstraintViolationException ex) {
        // Agrupamos los errores: NombreCampo -> Lista de Mensajes
        Map<String, List<String>> details = ex.getConstraintViolations().stream()
                .collect(Collectors.groupingBy(
                        v -> v.getPropertyPath().toString().substring(v.getPropertyPath().toString().lastIndexOf('.') + 1),
                        Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())
                ));

        var error = new ErrorResponse(
                "ValidationError",
                "One or more fields are invalid",
                LocalDateTime.now(),
                uriInfo.getPath(),
                details
        );

        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}