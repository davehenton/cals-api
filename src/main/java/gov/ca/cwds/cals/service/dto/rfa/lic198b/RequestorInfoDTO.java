package gov.ca.cwds.cals.service.dto.rfa.lic198b;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.dto.rfa.PersonNameDTO;
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestorInfoDTO extends BaseDTO {
  private static final long serialVersionUID = -6078937533831831021L;

  @ApiModelProperty("Name")
  @Valid
  private PersonNameDTO name;

  @ApiModelProperty("Phone")
  @Valid
  private PhoneDTO phone;

  @ApiModelProperty("Fax")
  @Valid
  private PhoneDTO fax;

  @ApiModelProperty("Email")
  private String email;

  public PersonNameDTO getName() {
    return name;
  }

  public void setName(PersonNameDTO name) {
    this.name = name;
  }

  public PhoneDTO getPhone() {
    return phone;
  }

  public void setPhone(PhoneDTO phone) {
    this.phone = phone;
  }

  public PhoneDTO getFax() {
    return fax;
  }

  public void setFax(PhoneDTO fax) {
    this.fax = fax;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
}
