package gov.ca.cwds.cals.exception.mapper;

import gov.ca.cwds.cals.Constants.Validation.Error;
import gov.ca.cwds.cals.exception.BaseExceptionResponse;
import gov.ca.cwds.cals.exception.ExpectedException;
import gov.ca.cwds.cals.exception.IssueDetails;
import gov.ca.cwds.cals.exception.IssueType;
import gov.ca.cwds.logging.LoggingContext.LogParameter;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.MDC;

/**
 * @author CWDS CALS API Team
 */

@Provider
public class ExpectedExceptionMapperImpl implements ExceptionMapper<ExpectedException> {

  @Override
  public Response toResponse(ExpectedException exception) {
    IssueDetails details = new IssueDetails();
    details.setType(IssueType.EXPECTED_EXCEPTION);
    details.setIncidentId(MDC.get(LogParameter.UNIQUE_ID.name()));
    details.setTechnicalMessage(exception.getMessage());
    details.setUserMessage(Error.BASE_MESSAGE);

    Set<IssueDetails> detailsList = new HashSet<>();
    detailsList.add(details);
    BaseExceptionResponse expectedExceptionResponse = new BaseExceptionResponse();
    expectedExceptionResponse.setIssueDetails(detailsList);

    return Response.status(exception.getResponseStatus()).entity(expectedExceptionResponse)
        .type(MediaType.APPLICATION_JSON).build();
  }
}
