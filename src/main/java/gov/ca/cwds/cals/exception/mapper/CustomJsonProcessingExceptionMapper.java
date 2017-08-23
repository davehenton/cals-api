package gov.ca.cwds.cals.exception.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import gov.ca.cwds.cals.Constants.ErrorMessages;
import gov.ca.cwds.cals.exception.BaseExceptionResponse;
import gov.ca.cwds.cals.exception.ExceptionType;
import gov.ca.cwds.cals.exception.ValidationDetails;
import io.dropwizard.jersey.errors.ErrorMessage;
import io.dropwizard.jersey.jackson.JsonProcessingExceptionMapper;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.HashSet;
import java.util.Set;

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

    ValidationDetails details = new ValidationDetails();
    details.setType(ExceptionType.JSON_PROCESSING_EXCEPTION);
    details.setIncidentId(MDC.get("uniqueId"));
    details.setUserMessage(ErrorMessages.BASE_ERROR_MESSAGE);
    details.setTechnicalMessage(
        StringUtils.join(new Object[] {errorMessage.getMessage(), errorMessage.getDetails()}, ". "));

    Set<ValidationDetails> detailsList = new HashSet<>();
    detailsList.add(details);
    BaseExceptionResponse jsonProcessingExceptionResponse = new BaseExceptionResponse();
    jsonProcessingExceptionResponse.setValidationDetails(detailsList);

    return Response.status(response.getStatus())
        .type(response.getMediaType())
        .entity(jsonProcessingExceptionResponse)
        .build();
  }

}


