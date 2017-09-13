package gov.ca.cwds.cals.exception;

import java.util.Set;

/**
 * @author CWDS CALS API Team
 */

public class BusinessValidationException extends RuntimeException {

  private final Set<IssueDetails> validationDetailsList;

  public BusinessValidationException(Set<IssueDetails> validationDetailsList) {
    this.validationDetailsList = validationDetailsList;
  }

  public Set<IssueDetails> getValidationDetailsList() {
    return validationDetailsList;
  }

}
