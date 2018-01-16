package gov.ca.cwds.cals;

import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.IncomeType;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.EmploymentDTO;
import gov.ca.cwds.cals.util.Utils;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author CWDS CALS API Team
 */

public class ApplicantUtilsTest {

  @Test
  public void testEmploymentNull() {
    ApplicantDTO applicantDTO = new ApplicantDTO();
    applicantDTO.setEmployment(null);
    assertEquals(new BigDecimal(0), Utils.Applicant.getAnnualIncome(applicantDTO));
  }

  @Test
  public void testIncomeNull() {
    ApplicantDTO applicantDTO = new ApplicantDTO();
    EmploymentDTO employmentDTO = new EmploymentDTO();
    employmentDTO.setIncome(null);
    applicantDTO.setEmployment(employmentDTO);
    assertEquals(new BigDecimal(0), Utils.Applicant.getAnnualIncome(applicantDTO));
  }

  @Test
  public void testIncomeTypeNull() {
    ApplicantDTO applicantDTO = new ApplicantDTO();
    EmploymentDTO employmentDTO = new EmploymentDTO();
    employmentDTO.setIncome(new BigDecimal(100));
    employmentDTO.setIncomeType(null);
    applicantDTO.setEmployment(employmentDTO);
    assertEquals(new BigDecimal(100), Utils.Applicant.getAnnualIncome(applicantDTO));
  }

  @Test
  public void testIncomeTypeMonthly() {
    ApplicantDTO applicantDTO = new ApplicantDTO();
    EmploymentDTO employmentDTO = new EmploymentDTO();
    employmentDTO.setIncome(new BigDecimal(100));
    IncomeType annualIncomeType = new IncomeType();
    annualIncomeType.setValue("monthly");
    employmentDTO.setIncomeType(annualIncomeType);
    applicantDTO.setEmployment(employmentDTO);
    assertEquals(new BigDecimal(1200), Utils.Applicant.getAnnualIncome(applicantDTO));
  }

  @Test
  public void testIncomeTypeYearly() {
    ApplicantDTO applicantDTO = new ApplicantDTO();
    EmploymentDTO employmentDTO = new EmploymentDTO();
    employmentDTO.setIncome(new BigDecimal(100));
    IncomeType annualIncomeType = new IncomeType();
    annualIncomeType.setValue("yearly");
    employmentDTO.setIncomeType(annualIncomeType);
    applicantDTO.setEmployment(employmentDTO);
    assertEquals(new BigDecimal(100), Utils.Applicant.getAnnualIncome(applicantDTO));
  }

}