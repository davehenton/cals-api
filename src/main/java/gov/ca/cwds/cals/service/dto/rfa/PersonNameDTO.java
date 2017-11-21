package gov.ca.cwds.cals.service.dto.rfa;

import static gov.ca.cwds.cals.Constants.Validation.Pattern.ALFANUMERICAL_PATTERN;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.Constants.Validation.Constraint;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NamePrefixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameSuffixType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PersonNameDTO extends BaseDTO {

  private static final long serialVersionUID = -8654631136147609963L;

  @ApiModelProperty(value = "Prefix")
  @CheckReferentialIntegrity(enrich = true)
  private NamePrefixType namePrefix;

  @Size(max = 20, message = Constraint.MAX_LENGTH_MESSAGE)
  @Pattern(regexp = ALFANUMERICAL_PATTERN, message = Constraint.ALPHANUMERIC_MESSAGE)
  private String firstName;

  @Size(max = 20, message = Constraint.MAX_LENGTH_MESSAGE)
  @Pattern(regexp = ALFANUMERICAL_PATTERN, message = Constraint.ALPHANUMERIC_MESSAGE)
  private String middleName;

  @Size(max = 25, message = Constraint.MAX_LENGTH_MESSAGE)
  @Pattern(regexp = ALFANUMERICAL_PATTERN, message = Constraint.ALPHANUMERIC_MESSAGE)
  private String lastName;

  @ApiModelProperty(value = "Suffix")
  @CheckReferentialIntegrity(enrich = true)
  private NameSuffixType nameSuffix;

  public NamePrefixType getNamePrefix() {
    return namePrefix;
  }

  public void setNamePrefix(
      NamePrefixType namePrefix) {
    this.namePrefix = namePrefix;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public NameSuffixType getNameSuffix() {
    return nameSuffix;
  }

  public void setNameSuffix(NameSuffixType nameSuffix) {
    this.nameSuffix = nameSuffix;
  }
}
