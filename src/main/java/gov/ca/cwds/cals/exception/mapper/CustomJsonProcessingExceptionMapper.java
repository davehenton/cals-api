package gov.ca.cwds.cals.exception.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import gov.ca.cwds.cals.Constants.ErrorMessages;
import gov.ca.cwds.cals.exception.BaseExceptionResponse;
import gov.ca.cwds.cals.exception.ExceptionType;
import io.dropwizard.jersey.errors.ErrorMessage;
import io.dropwizard.jersey.jackson.JsonProcessingExceptionMapper;
import javax.ws.rs.core.Response;
import org.slf4j.MDC;

/**
 * @author CWDS CALS API Team
 */

public class CustomJsonProcessingExceptionMapper extends JsonProcessingExceptionMapper {

  public CustomJsonProcessingExceptionMapper(boolean showDetails) {
    super(showDetails);
  }

  @Override
  public Response toResponse(JsonProcessingException exception) {
    Response response = super.toResponse(exception);
    if (!(response.getEntity() instanceof ErrorMessage)) {
      throw new IllegalStateException("ErrorMessage class is expected here");
    }
    ErrorMessage errorMessage = (ErrorMessage) response.getEntity();
    BaseExceptionResponse jsonProcessingExceptionResponse =
        new BaseExceptionResponse();
    jsonProcessingExceptionResponse.setExceptionType(ExceptionType.JSON_PROCESSING_EXCEPTION);
    jsonProcessingExceptionResponse.setIncidentId(MDC.get("uniqueId"));
    jsonProcessingExceptionResponse.addUserMessage(ErrorMessages.BASE_ERROR_MESSAGE);
    jsonProcessingExceptionResponse.addTechnicalMessage(errorMessage.getMessage());
    jsonProcessingExceptionResponse.addTechnicalMessage(errorMessage.getDetails());
    return Response.status(response.getStatus())
        .type(response.getMediaType())
        .entity(jsonProcessingExceptionResponse)
        .build();
  }

}


