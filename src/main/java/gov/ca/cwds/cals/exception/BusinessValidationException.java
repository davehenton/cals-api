package gov.ca.cwds.cals.exception;

import java.util.List;

/**
 * @author CWDS CALS API Team
 */

public class BusinessValidationException extends Exception {

  private final List<String> validationMessages;

  public BusinessValidationException(List<String> validationMessages) {
    this.validationMessages = validationMessages;
  }

  public List<String> getValidationMessages() {
    return validationMessages;
  }

}
