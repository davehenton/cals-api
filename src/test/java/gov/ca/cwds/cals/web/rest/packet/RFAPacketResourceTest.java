package gov.ca.cwds.cals.web.rest.packet;

import static gov.ca.cwds.cals.Constants.API.SUMMARY;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.dto.packet.PacketDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.web.rest.rfa.BaseRFAIntegrationTest;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS TPT-2
 */
public class RFAPacketResourceTest extends BaseRFAIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void testPacketApi() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicantDTO = applicantHelper
        .postApplicant(form.getId(), applicantHelper.getValidApplicant());
    ApplicantDTO secondApplicant = applicantHelper.getValidApplicant();
    secondApplicant.setFirstName("John");
    StateType driverLicenseState = new StateType();
    driverLicenseState.setId("MD");
    driverLicenseState.setValue("Maryland");
    secondApplicant.setDriverLicenseState(driverLicenseState);
    secondApplicant.getEthnicity().setId(2L);
    secondApplicant.getEthnicity().setValue("American Indian");

    secondApplicant = applicantHelper.postApplicant(form.getId(), secondApplicant);
    residenceHelper.putResidence(form.getId(), residenceHelper.buildResidenceDTO());

    RFA1bFormDTO rfa1bForm = formBHelper.getRfa1bForm();
    formBHelper.postRfa1bForm(form.getId(), applicantDTO.getId(), rfa1bForm);

    otherAdultHelper.createOtherAdults(form.getId(), secondApplicant);
    minorChildHelper.createMinorChildren(form.getId(), applicantDTO);

    WebTarget target = clientTestRule.target(API.RFA_PACKET + "/" + form.getId() + "/" + SUMMARY);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);

    PacketDTO packetDTO = invocation.get(PacketDTO.class);
    assertNotNull(packetDTO);
  }
}