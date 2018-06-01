package gov.ca.cwds.cals.web.rest.rfa.helper;

import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Created by Alexander Serbin on 5/31/2018.
 */
public final class RfaSubmitHelper {

  private RfaHelpersHolder helpersHolder;

  public RfaSubmitHelper(RfaHelpersHolder helpersHolder) {
    this.helpersHolder = helpersHolder;
  }

  public RFA1aFormDTO submitApplication(String pathToPrincipal) throws Exception {
    RFA1aFormDTO form = helpersHolder.getFormAHelper().createRFA1aForm();
    ApplicantDTO applicantDTO = helpersHolder.getApplicantHelper()
        .postApplicant(form.getId(), helpersHolder.getApplicantHelper().getValidApplicant());
    ApplicantDTO secondApplicant = helpersHolder.getApplicantHelper().getValidApplicant();
    secondApplicant.setFirstName("John");
    StateType driverLicenseState = new StateType();
    driverLicenseState.setId("MD");
    driverLicenseState.setValue("Maryland");
    secondApplicant.setDriverLicenseState(driverLicenseState);
    secondApplicant.getEthnicity().setId(2L);
    secondApplicant.getEthnicity().setValue("American Indian");

    secondApplicant = helpersHolder.getApplicantHelper()
        .postApplicant(form.getId(), secondApplicant);
    helpersHolder.getResidenceHelper().putResidence(form.getId(),
        helpersHolder.getResidenceHelper().buildResidenceDTO());

    RFA1bFormDTO rfa1bForm = helpersHolder.getFormBHelper().getRfa1bForm();
    helpersHolder.getFormBHelper().postRfa1bForm(form.getId(), applicantDTO.getId(), rfa1bForm);

    helpersHolder.getOtherAdultHelper().createOtherAdults(form.getId(), secondApplicant);
    helpersHolder.getMinorChildHelper().createMinorChildren(form.getId(), applicantDTO);

    Response response = helpersHolder.getStatusHelper().submitApplication(form.getId(),
        pathToPrincipal);
    assertEquals(Status.OK.getStatusCode(), response.getStatus());

    form = helpersHolder.getFormAHelper().getRFA1aForm(form.getId());

    return form;
  }

}
