package cl.aravena.infrastructure.adapter.out.persistence.mapper;

import cl.aravena.domain.common.exception.BusinessRuleException;
import cl.aravena.domain.common.exception.DataNotFoundException;
import cl.aravena.domain.common.exception.DomainException;
import cl.aravena.domain.common.exception.ValidationException;
import cl.aravena.infrastructure.adapter.in.dto.response.ErrorResponse;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class DomainExceptionMapper implements ExceptionMapper<DomainException> {

    @Context
    UriInfo uriInfo; // Para obtener la ruta que falló

    @Override
    public Response toResponse(DomainException ex) {
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        if (ex instanceof DataNotFoundException) {
            status = Response.Status.NOT_FOUND; // 404
        } else if (ex instanceof ValidationException) {
            status = Response.Status.BAD_REQUEST; // 400
        } else if (ex instanceof BusinessRuleException) {
            status = Response.Status.fromStatusCode(422); // 422
        }

        ErrorResponse error = new ErrorResponse(
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                LocalDateTime.now(),
                uriInfo.getPath(),
                null
        );

        return Response.status(status).entity(error).build();
    }
}