package exception.mapper;

import exception.BaseExceptionResponse;
import exception.ExceptionType;
import io.dropwizard.jersey.validation.JerseyViolationException;
import io.dropwizard.jersey.validation.JerseyViolationExceptionMapper;
import io.dropwizard.jersey.validation.ValidationErrorMessage;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * @author CWDS CALS API Team
 */

@Provider
public class CustomJerseyViolationExceptionMapper extends JerseyViolationExceptionMapper {

  @Override
  public Response toResponse(final JerseyViolationException exception) {
    Response response = super.toResponse(exception);
    BaseExceptionResponse constraintViolationsResponse = new BaseExceptionResponse();
    constraintViolationsResponse.setExceptionType(ExceptionType.VALIDATION_ERROR);
    constraintViolationsResponse.setCode("?");
    List<String> userMessages = ((ValidationErrorMessage) response.getEntity()).getErrors();
    constraintViolationsResponse.setUserMessages(userMessages);
    return Response.status(response.getStatus())
        .type(response.getMediaType())
        .entity(constraintViolationsResponse)
        .build();
  }

}


