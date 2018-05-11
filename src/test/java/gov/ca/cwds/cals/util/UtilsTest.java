package gov.ca.cwds.cals.util;

import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.util.Utils.PlacementHome;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * @author CWDS TPT-2 Team
 */
public class UtilsTest {

  private ApplicantDTO applicant1;
  private ApplicantDTO applicant2;
  private ApplicantDTO applicant3;

  private List<ApplicantDTO> applicants;
  
  @Before
  public void setup() {
    applicant1 = new ApplicantDTO();
    applicant1.setFirstName("John");
    applicant1.setLastName("Smith");

    applicant2 = new ApplicantDTO();
    applicant2.setFirstName("Anna");
    applicant2.setLastName("Dow");

    applicant3 = new ApplicantDTO();
    applicant3.setFirstName("Some");
    applicant3.setLastName("Guy");

    applicants = new ArrayList<>();
  }


  @Test
  public void composeFacilityName_FullDifferentNames() {
    // Different Last names 3 applicants
    applicants.add(applicant1);
    applicants.add(applicant2);
    applicants.add(applicant3);
    assertEquals("Smith, John & Dow, Anna",
        PlacementHome.composeFacilityName(applicants));
  }

  @Test
  public void composeFacilityName_SameLastName() {
    // Same Last names 2 applicants
    applicants.add(applicant1);
    applicants.add(applicant2);
    applicant2.setLastName("Smith");
    assertEquals("Smith, John & Anna",
        PlacementHome.composeFacilityName(applicants));
  }

  @Test
  public void composeFacilityName_FirstApplicantDoesNotHaveLastName() {
    // Same Last names 2 applicants
    applicants.add(applicant1);
    applicants.add(applicant2);
    applicant1.setLastName(null);
    assertEquals("John & Dow, Anna",
        PlacementHome.composeFacilityName(applicants));
  }

  @Test
  public void composeFacilityName_SecondApplicantDoesNotHaveLastName() {
    // Same Last names 2 applicants
    applicants.add(applicant1);
    applicants.add(applicant2);
    applicant2.setLastName(null);
    assertEquals("Smith, John & Anna",
        PlacementHome.composeFacilityName(applicants));
  }

  @Test
  public void composeFacilityName_OneApplicantFullName() {
    // One applicant full name
    applicants.add(applicant1);
    assertEquals("Smith, John",
        PlacementHome.composeFacilityName(applicants));
  }

  @Test
  public void composeFacilityName_OneApplicantLastNameOnly() {
    // One applicant Last name only
    applicants.add(applicant1);
    applicant1.setFirstName(null);
    assertEquals("Smith",
        PlacementHome.composeFacilityName(applicants));
  }

  @Test
  public void composeFacilityName_OneApplicantFirstNameOnly() {
    // One applicant First name onli
    applicants.add(applicant1);
    applicant1.setLastName(null);
    assertEquals("John",
        PlacementHome.composeFacilityName(applicants));
  }
}