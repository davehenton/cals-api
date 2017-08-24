package gov.ca.cwds.cals.exception.mapper;

import gov.ca.cwds.cals.exception.BaseExceptionResponse;
import gov.ca.cwds.cals.exception.ExceptionType;
import gov.ca.cwds.cals.exception.ValidationDetails;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author CWDS CALS API Team
 */
public class ValidationExceptionMapperImpl implements
    ExceptionMapper<ConstraintViolationException> {

  @Override
  public Response toResponse(ConstraintViolationException exception) {
    Set<ValidationDetails> validationDetailsList = new HashSet<>();
    Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
    for (ConstraintViolation<?> v : constraintViolations) {
      String message = v.getMessage();
      ValidationDetails details = null;
      details = new ValidationDetails();
      details.setUserMessage(message);
      details.setType(ExceptionType.CONSTRAINT_VALIDATION);
      validationDetailsList.add(details);
    }

    BaseExceptionResponse constraintViolationsResponse = new BaseExceptionResponse();
    constraintViolationsResponse.setValidationDetails(validationDetailsList);
    return Response.status(422)
        .type(MediaType.APPLICATION_JSON_TYPE)
        .entity(constraintViolationsResponse)
        .build();
  }
}
