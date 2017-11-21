package gov.ca.cwds.cals.service.dto.rfa.lic198b;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.dto.rfa.PersonNameDTO;
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.validation.Valid;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RespondingStateDTO extends BaseDTO {

  private static final long serialVersionUID = -4962920585140931371L;

  @ApiModelProperty("Reports")
  @Valid
  private List<ReportDTO> reports;

  @ApiModelProperty("Contact name")
  @Valid
  private PersonNameDTO contactName;

  @ApiModelProperty("Agency")
  private String agency;

  @ApiModelProperty("Phone")
  @Valid
  private PhoneDTO phone;

  @ApiModelProperty("Email")
  private String email;


  public List<ReportDTO> getReports() {
    return reports;
  }

  public void setReports(List<ReportDTO> reports) {
    this.reports = reports;
  }

  public PersonNameDTO getContactName() {
    return contactName;
  }

  public void setContactName(PersonNameDTO contactName) {
    this.contactName = contactName;
  }

  public String getAgency() {
    return agency;
  }

  public void setAgency(String agency) {
    this.agency = agency;
  }

  public PhoneDTO getPhone() {
    return phone;
  }

  public void setPhone(PhoneDTO phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
