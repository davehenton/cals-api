package gov.ca.cwds.cals.exception.mapper;

import gov.ca.cwds.cals.exception.BaseExceptionResponse;
import gov.ca.cwds.cals.exception.BusinessValidationException;
import gov.ca.cwds.cals.exception.IssueDetails;
import gov.ca.cwds.cals.exception.IssueType;
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
    Set<IssueDetails> detailsList = exception.getValidationDetailsList();
    for (IssueDetails details : detailsList) {
      details.setType(IssueType.BUSINESS_VALIDATION);
    }
    BaseExceptionResponse manualValidationResponse = new BaseExceptionResponse();
    manualValidationResponse.setIssueDetails(detailsList);

    return Response.status(422)
        .type(MediaType.APPLICATION_JSON)
        .entity(manualValidationResponse)
        .build();
  }
}
