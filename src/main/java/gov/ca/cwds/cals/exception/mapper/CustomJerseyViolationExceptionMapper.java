package gov.ca.cwds.cals.exception.mapper;


import gov.ca.cwds.cals.exception.BaseExceptionResponse;
import gov.ca.cwds.cals.exception.ExceptionType;
import io.dropwizard.jersey.validation.JerseyViolationException;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author CWDS CALS API Team
 */

@Provider
public class CustomJerseyViolationExceptionMapper implements
    ExceptionMapper<JerseyViolationException> {

  @Override
  public Response toResponse(final JerseyViolationException exception) {
    BaseExceptionResponse constraintViolationsResponse = new BaseExceptionResponse();
    constraintViolationsResponse.setExceptionType(ExceptionType.VALIDATION_ERROR);
    constraintViolationsResponse.setCode("?");
    List<String> userMessages =
        exception
            .getConstraintViolations()
            .stream()
            .map(v -> CustomConstraintMessage.getMessage(v, exception.getInvocable()))
            .collect(Collectors.toList());
    constraintViolationsResponse.setUserMessages(userMessages);
    int status =
        CustomConstraintMessage.determineStatus(
            exception.getConstraintViolations(), exception.getInvocable());
    return Response.status(status)
        .type(MediaType.APPLICATION_JSON_TYPE)
        .entity(constraintViolationsResponse)
        .build();
  }

}


