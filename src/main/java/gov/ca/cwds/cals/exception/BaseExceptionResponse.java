package gov.ca.cwds.cals.exception;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serializable;
import java.util.Set;

/**
 * @author CWDS CALS API Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaseExceptionResponse implements Serializable {

  private Set<ValidationDetails> validationDetails;

  public Set<ValidationDetails> getValidationDetails() {
    return validationDetails;
  }

  public void setValidationDetails(Set<ValidationDetails> validationDetails) {
    this.validationDetails = validationDetails;
  }

}
