package gov.ca.cwds.cals.exception.mapper;

import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.exception.ValidationDetails;
import java.io.IOException;
import javax.validation.ConstraintViolation;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.server.model.Invocable;

/**
 * @author CWDS CALS API Team
 */
public interface CustomBeanValidationExceptionSupport {

  /**
   * hibernate validation framework updates user message with prefix that should be removed.
   *
   * @param data constraint violation message
   * @return validation details in case of business validation message or null in case of constraint
   * validation message
   * @see {@link CustomConstraintMessage#calculateMessage(ConstraintViolation, Invocable)}
   */
  default ValidationDetails unmarshallData(String data) {
    String marshalledDetails = StringUtils.removeStart(data, "The request body");
    ValidationDetails details = null;
    try {
      details = (ValidationDetails) Utils.Json.from(marshalledDetails, ValidationDetails.class);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    return details;
  }

}
