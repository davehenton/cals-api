package gov.ca.cwds.cals.web.rest.rfa;

import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.BeforeClass;
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
    RFA1aForm form = createForm();
    Applicant created = createApplicant(form);
    assertNotNull(created);
    assertNotNull(created.getId());
  }

  @Test
  public void updateApplicant() throws Exception {
    RFA1aForm form = createForm();
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
    Response put = invocation.put(Entity.entity(created, MediaType.APPLICATION_JSON_TYPE));
    // TODO: finish the test
  }

  @Test
  public void getAplicantById() throws Exception {
    RFA1aForm form = createForm();
    Applicant created = createApplicant(form);


  }

  @Test
  public void getAplicantsByFormId() throws Exception {
  }

  @Test
  public void deleteAplicant() throws Exception {
  }

  private RFA1aForm createForm() {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    RFA1aForm rfaForm = invocation.post(null, RFA1aForm.class);
    return rfaForm;
  }

  private Applicant createApplicant(RFA1aForm form) {
    WebTarget target =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + form.getId() + "/" + API.RFA_1A_APPLICANTS);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    return invocation.post(null, Applicant.class);
  }
}
