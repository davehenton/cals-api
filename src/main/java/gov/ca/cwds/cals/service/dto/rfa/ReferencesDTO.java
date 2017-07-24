package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NamePrefixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameSuffixType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.CheckReferentialIntegrity;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.validation.Valid;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReferencesDTO extends BaseDTO implements RequestResponse {

  private static final long serialVersionUID = 5956216012241314291L;

  private List<Reference> references;

  public List<Reference> getReferences() {
    return references;
  }

  public void setReferences(List<Reference> references) {
    this.references = references;
  }

  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  public static class Reference extends BaseDTO {

    private static final long serialVersionUID = -6715371392246671568L;

    @ApiModelProperty(value = "Prefix")
    @CheckReferentialIntegrity
    private NamePrefixType namePrefix;

    private String firstName;

    private String middleName;

    private String lastName;

    @ApiModelProperty(value = "Suffix")
    @CheckReferentialIntegrity
    private NameSuffixType nameSuffix;

    @Valid
    private RFAAddressDTO mailingAddress;

    private String phoneNumber;

    private String email;

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

    public RFAAddressDTO getMailingAddress() {
      return mailingAddress;
    }

    public void setMailingAddress(RFAAddressDTO mailingAddress) {
      this.mailingAddress = mailingAddress;
    }

    public String getPhoneNumber() {
      return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public NameSuffixType getNameSuffix() {
      return nameSuffix;
    }

    public void setNameSuffix(NameSuffixType nameSuffix) {
      this.nameSuffix = nameSuffix;
    }
  }
}
