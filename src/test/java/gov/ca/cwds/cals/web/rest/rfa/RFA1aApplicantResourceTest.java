package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.rfa.RFAHelper.createForm;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDTO;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantResourceTest extends BaseCalsApiIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void createApplicant() throws Exception {
    RFA1aForm form = createForm(clientTestRule);
    Applicant created = createApplicant(form);

    assertNotNull(created);
    assertNotNull(created.getId());

    Applicant found = findApplicant(form, created.getId());

    assertThat(found).isEqualTo(created);
  }

  @Test
  public void updateApplicant() throws Exception {
    RFA1aForm form = createForm(clientTestRule);
    Applicant created = createApplicant(form);

    created.setFirstName("testFirstName");

    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS
                + "/"
                + form.getId()
                + "/"
                + API.RFA_1A_APPLICANTS
                + "/"
                + created.getId());

    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    invocation.put(Entity.entity(created, MediaType.APPLICATION_JSON_TYPE));

    Applicant found = findApplicant(form, created.getId());

    assertThat(found).isEqualTo(created);
  }

  @Test
  public void getAplicantById() throws Exception {
    RFA1aForm form = createForm(clientTestRule);
    Applicant created = createApplicant(form);
    Applicant found = findApplicant(form, created.getId());
    assertThat(found).isEqualTo(created);
  }

  @Test
  public void getAplicantsByFormId() throws Exception {
    RFA1aForm form = createForm(clientTestRule);
    Applicant created = createApplicant(form);
    Applicant created1 = createApplicant(form);
    Applicant created2 = createApplicant(form);
    WebTarget target =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + form.getId() + "/" + API.RFA_1A_APPLICANTS);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    ApplicantsDTO applicantsDTO = invocation.get(ApplicantsDTO.class);
    assertThat(applicantsDTO.getCollection().size()).isEqualTo(3);
  }

  @Test
  public void deleteAplicant() throws Exception {
    RFA1aForm form = createForm(clientTestRule);
    Applicant created = createApplicant(form);
    Applicant found = findApplicant(form, created.getId());

    assertThat(found).isEqualTo(created);

    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS
                + "/"
                + form.getId()
                + "/"
                + API.RFA_1A_APPLICANTS
                + "/"
                + found.getId());
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    invocation.delete();

    Response response = invocation.delete();

    assertThat(response.getStatus()).isEqualTo(Status.NOT_FOUND.getStatusCode());
  }

  private Applicant createApplicant(RFA1aForm form) {
    WebTarget target =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + form.getId() + "/" + API.RFA_1A_APPLICANTS);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    return invocation.post(null, Applicant.class);
  }

  private Applicant findApplicant(RFA1aForm form, Long applicantId) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS
                + "/"
                + form.getId()
                + "/"
                + API.RFA_1A_APPLICANTS
                + "/"
                + applicantId);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    return invocation.get(Applicant.class);
  }
}
