package gov.ca.cwds.cals.web.rest.rfa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aFormsResourceFilterTest extends BaseRFAIntegrationTest {

  private static final String PRINCIPAL_STAFF_ID_0X6_JSON = "security/principal-staffId-0X6.json";
  private static final String PRINCIPAL_STAFF_ID_0X5_JSON = "security/principal-staffId-0X5.json";


  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void getAllFilteredAndOrderedFormsTest() throws Exception {
    ApplicantDTO applicant = applicantHelper.getApplicant();

    // Create form for 0X5 staffID
    RFA1aFormDTO rfaFormCreate = formAHelper.createRFA1aForm(PRINCIPAL_STAFF_ID_0X5_JSON);

    // Create forms for 0X6 staffID
    RFA1aFormDTO rfaFormCreate1 = formAHelper.createRFA1aForm(PRINCIPAL_STAFF_ID_0X6_JSON);
    applicant.setLastName("Ggg");
    applicant.setFirstName("Hhh");
    applicantHelper.postApplicant(rfaFormCreate1.getId(), applicant);
    applicant.setLastName("Ggg");
    applicant.setFirstName("Iii");
    applicantHelper.postApplicant(rfaFormCreate1.getId(), applicant);

    RFA1aFormDTO rfaFormCreate2 = formAHelper.createRFA1aForm(PRINCIPAL_STAFF_ID_0X6_JSON);
    applicant.setLastName("Ddd");
    applicant.setFirstName("Eee");
    applicantHelper.postApplicant(rfaFormCreate2.getId(), applicant);
    applicant.setLastName("Ddd");
    applicant.setFirstName("Fff");
    applicantHelper.postApplicant(rfaFormCreate2.getId(), applicant);

    RFA1aFormDTO rfaFormCreate3 = formAHelper.createRFA1aForm(PRINCIPAL_STAFF_ID_0X6_JSON);
    applicant.setLastName("Aaa");
    applicant.setFirstName("Bbb");
    applicantHelper.postApplicant(rfaFormCreate3.getId(), applicant);
    applicant.setLastName("Aaa");
    applicant.setFirstName("Ccc");
    applicantHelper.postApplicant(rfaFormCreate3.getId(), applicant);

    assertNotEquals(rfaFormCreate1, rfaFormCreate2);
    assertNotEquals(rfaFormCreate2, rfaFormCreate3);
    assertNotEquals(rfaFormCreate3, rfaFormCreate1);

    CollectionDTO<RFA1aFormDTO> rfaForms = formAHelper.getRFA1aForms(PRINCIPAL_STAFF_ID_0X6_JSON);
    Collection<RFA1aFormDTO> formsDTOs = rfaForms.getCollection();
    assertTrue(formsDTOs.size() == 3);

    List<RFA1aFormDTO> list = new ArrayList<>(formsDTOs);

    assertEquals("Aaa, Bbb & Ccc", list.get(0).getFacilityName());
    assertEquals("Ddd, Eee & Fff", list.get(1).getFacilityName());
    assertEquals("Ggg, Hhh & Iii", list.get(2).getFacilityName());

    assertEquals(list.get(0).getId(), rfaFormCreate3.getId());
    assertEquals(list.get(1).getId(), rfaFormCreate2.getId());
    assertEquals(list.get(2).getId(), rfaFormCreate1.getId());
  }


  @Test
  public void checkFacilityNameAfterApplicantNameUpdateAndApplicantDeletedFormsTest()
      throws Exception {
    
    ApplicantDTO applicant = applicantHelper.getApplicant();

    // Create form for 0X6 staffID
    RFA1aFormDTO rfaFormCreate = formAHelper.createRFA1aForm(PRINCIPAL_STAFF_ID_0X5_JSON);
    applicant.setLastName("Aaa");
    applicant.setFirstName("Bbb");
    applicantHelper.postApplicant(rfaFormCreate.getId(), applicant);
    applicant.setLastName("Aaa");
    applicant.setFirstName("Ccc");
    applicantHelper.postApplicant(rfaFormCreate.getId(), applicant);

    RFA1aFormDTO rfaForm = formAHelper.getRFA1aForm(rfaFormCreate.getId());

    assertEquals("Aaa, Bbb & Ccc", rfaForm.getFacilityName());

    ApplicantDTO applicantDTO = rfaForm.getApplicants().get(0);
    applicantDTO.setFirstName("Vvv");

    applicantHelper.updateApplicant(rfaForm.getId(), applicantDTO);

    rfaForm = formAHelper.getRFA1aForm(rfaFormCreate.getId());
    assertEquals("Aaa, Vvv & Ccc", rfaForm.getFacilityName());

    ApplicantDTO applicantDTO2 = rfaForm.getApplicants().get(1);
    applicantHelper.deleteApplicant(rfaForm.getId(), applicantDTO2.getId());

    rfaForm = formAHelper.getRFA1aForm(rfaFormCreate.getId());
    assertEquals("Aaa, Vvv", rfaForm.getFacilityName());
  }

}
