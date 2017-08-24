package gov.ca.cwds.cals.exception.mapper;

import gov.ca.cwds.cals.exception.BaseExceptionResponse;
import gov.ca.cwds.cals.exception.BusinessValidationException;
import gov.ca.cwds.cals.exception.ExceptionType;
import gov.ca.cwds.cals.exception.ValidationDetails;
import java.util.Set;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author CWDS CALS API Team
 */

public class BusinessValidationExceptionMapper implements
    ExceptionMapper<BusinessValidationException> {

  @Override
  public Response toResponse(BusinessValidationException exception) {
    Set<ValidationDetails> detailsList = exception.getValidationDetailsList();
    for (ValidationDetails details : detailsList) {
      details.setType(ExceptionType.BUSINESS_VALIDATION);
    }
    BaseExceptionResponse manualValidationResponse = new BaseExceptionResponse();
    manualValidationResponse.setValidationDetails(detailsList);

    return Response.status(422)
        .type(MediaType.APPLICATION_JSON)
        .entity(manualValidationResponse)
        .build();
  }
}
