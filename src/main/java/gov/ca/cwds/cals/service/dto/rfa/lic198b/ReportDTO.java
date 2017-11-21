package gov.ca.cwds.cals.service.dto.rfa.lic198b;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.dto.rfa.PersonNameDTO;
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.validation.Valid;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S2160", "squid:S3437"}) //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReportDTO extends BaseDTO {

  private static final long serialVersionUID = 7255857406884293822L;

  @ApiModelProperty("Report date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate reportDate;

  @ApiModelProperty("Report number")
  private String reportNumber;

  @ApiModelProperty("Local contact")
  @Valid
  private PersonNameDTO localContact;

  @ApiModelProperty("Phone")
  @Valid
  private PhoneDTO phone;

  public LocalDate getReportDate() {
    return reportDate;
  }

  public void setReportDate(LocalDate reportDate) {
    this.reportDate = reportDate;
  }

  public String getReportNumber() {
    return reportNumber;
  }

  public void setReportNumber(String reportNumber) {
    this.reportNumber = reportNumber;
  }

  public PersonNameDTO getLocalContact() {
    return localContact;
  }

  public void setLocalContact(PersonNameDTO localContact) {
    this.localContact = localContact;
  }

  public PhoneDTO getPhone() {
    return phone;
  }

  public void setPhone(PhoneDTO phone) {
    this.phone = phone;
  }
}
