package gov.ca.cwds.cals.exception;

import java.util.Set;

/**
 * @author CWDS CALS API Team
 */

public class BusinessValidationException extends RuntimeException {

  private final Set<ValidationDetails> validationDetailsList;

  public BusinessValidationException(Set<ValidationDetails> validationDetailsList) {
    this.validationDetailsList = validationDetailsList;
  }

  public Set<ValidationDetails> getValidationDetailsList() {
    return validationDetailsList;
  }

}
