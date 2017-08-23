package gov.ca.cwds.cals.exception.mapper;


import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.exception.BaseExceptionResponse;
import gov.ca.cwds.cals.exception.ExceptionType;
import gov.ca.cwds.cals.exception.ValidationDetails;
import io.dropwizard.jersey.validation.JerseyViolationException;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.server.model.Invocable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
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

  private static final Logger LOG = LoggerFactory.getLogger(CustomJerseyViolationExceptionMapper.class);


  @Override
  public Response toResponse(final JerseyViolationException exception) {
    Set<ValidationDetails> validationDetailsList = new HashSet<>();
    Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
    for (ConstraintViolation<?> v : constraintViolations) {
      String message = CustomConstraintMessage.getMessage(v, exception.getInvocable()).trim();
      ValidationDetails details = unmarshallData(message);
      if (details != null) {
        details.setType(ExceptionType.BUSINESS_VALIDATION);
      } else {
        details = new ValidationDetails();
        details.setUserMessage(message);
        details.setType(ExceptionType.CONSTRAINT_VALIDATION);
      }
      validationDetailsList.add(details);
    }

    BaseExceptionResponse constraintViolationsResponse = new BaseExceptionResponse();
    constraintViolationsResponse.setValidationDetails(validationDetailsList);

    int status = CustomConstraintMessage.determineStatus(
        exception.getConstraintViolations(), exception.getInvocable());
    return Response.status(status)
        .type(MediaType.APPLICATION_JSON_TYPE)
        .entity(constraintViolationsResponse)
        .build();
  }

  /**
   * hibernate validation framework updates user message with prefix that should be removed.
   * @see {@link CustomConstraintMessage#calculateMessage(ConstraintViolation, Invocable)}
   *
   * @param data constraint violation message
   * @return validation details in case of business validation message
   *    or null in case of constraint validation message
   */
  private ValidationDetails unmarshallData(String data) {
    String marshalledDetails = StringUtils.removeStart(data, "The request body");
    ValidationDetails details = null;
    try {
      details = (ValidationDetails) Utils.Json.from(marshalledDetails, ValidationDetails.class);
    } catch (IOException e) {
      LOG.debug("Cannot unmarshall validation details", e);
    }
    return details;
  }

}


