package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameType;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;

/**
 * @author CWDS CALS API Team.
 */
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TypedPersonNameDTO extends PersonNameDTO {

  @CheckReferentialIntegrity
  private NameType nameType;

  public NameType getNameType() {
    return nameType;
  }

  public void setNameType(NameType nameType) {
    this.nameType = nameType;
  }

}
