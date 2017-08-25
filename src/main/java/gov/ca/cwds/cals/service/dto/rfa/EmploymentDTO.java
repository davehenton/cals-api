package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.IncomeType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;


/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S2160")//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EmploymentDTO extends BaseDTO {

  private static final long serialVersionUID = 4050127780486627644L;

  private String employerName;

  private String occupation;

  private Float income;

  @CheckReferentialIntegrity(enrich = true)
  private IncomeType incomeType;

  private PhysicalAddressDTO physicalAddress;

  public String getEmployerName() {
    return employerName;
  }

  public void setEmployerName(String employerName) {
    this.employerName = employerName;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public Float getIncome() {
    return income;
  }

  public void setIncome(Float income) {
    this.income = income;
  }

  public IncomeType getIncomeType() {
    return incomeType;
  }

  public void setIncomeType(IncomeType incomeType) {
    this.incomeType = incomeType;
  }

  public PhysicalAddressDTO getPhysicalAddress() {
    return physicalAddress;
  }

  public void setPhysicalAddress(PhysicalAddressDTO physicalAddress) {
    this.physicalAddress = physicalAddress;
  }

}
