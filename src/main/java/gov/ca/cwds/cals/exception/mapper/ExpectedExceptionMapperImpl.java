package gov.ca.cwds.cals.exception.mapper;

import gov.ca.cwds.cals.Constants.ErrorMessages;
import gov.ca.cwds.cals.exception.BaseExceptionResponse;
import gov.ca.cwds.cals.exception.ExceptionType;
import gov.ca.cwds.cals.exception.ExpectedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import gov.ca.cwds.cals.exception.ValidationDetails;
import org.slf4j.MDC;

import java.util.HashSet;
import java.util.Set;

/**
 * @author CWDS CALS API Team
 */

@Provider
public class ExpectedExceptionMapperImpl implements ExceptionMapper<ExpectedException> {

  @Override
  public Response toResponse(ExpectedException exception) {
    ValidationDetails details = new ValidationDetails();
    details.setType(ExceptionType.EXPECTED_EXCEPTION);
    details.setIncidentId(MDC.get("uniqueId"));
    details.setTechnicalMessage(exception.getCalsExceptionInfo().getMessage());
    details.setUserMessage(ErrorMessages.BASE_ERROR_MESSAGE);
    details.setCode(exception.getCalsExceptionInfo().getCode());

    Set<ValidationDetails> detailsList = new HashSet<>();
    detailsList.add(details);
    BaseExceptionResponse expectedExceptionResponse = new BaseExceptionResponse();
    expectedExceptionResponse.setValidationDetails(detailsList);

    return Response.status(exception.getResponseStatus()).entity(expectedExceptionResponse)
        .type(MediaType.APPLICATION_JSON).build();
  }
}
