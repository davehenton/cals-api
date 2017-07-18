package gov.ca.cwds.cals.web.rest.rfa.changed;

import static gov.ca.cwds.cals.Constants.API.RFA_1A_FORMS;
import static org.junit.Assert.assertTrue;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.RecordChangeOperation;
import gov.ca.cwds.cals.service.dto.changed.ChangedRFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS TPT-2
 */
public class ChangedRFA1aFormsResourceTest extends BaseCalsApiIntegrationTest {

  static final String PATH_CHANGED_RFA_1A_FORMS = "changed-" + RFA_1A_FORMS;
  static final String PATH_PARAM_DATE_AFTER = "dateAfter";

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void getChangedRFA1aFormsTest() throws Exception {
    CollectionDTO<ChangedRFA1aFormDTO> rfaForms = getChangedRFA1aFormsAfter("1970-01-01 00:00:00");
    assertTrue(rfaForms.getCollection().size() == 2);
    assertTrue(RecordChangeOperation.I == rfaForms.getCollection().iterator().next()
        .getRecordChangeOperation());

    rfaForms = getChangedRFA1aFormsAfter("2017-07-18 10:01:00");
    assertTrue(rfaForms.getCollection().size() == 1);
    assertTrue(2 == rfaForms.getCollection().iterator().next().getDTO().getId());

    rfaForms = getChangedRFA1aFormsAfter("2222-01-01 00:00:00");
    assertTrue(rfaForms.getCollection().size() == 0);
  }

  private CollectionDTO<ChangedRFA1aFormDTO> getChangedRFA1aFormsAfter(String dateTime) {
    WebTarget target = clientTestRule.target(PATH_CHANGED_RFA_1A_FORMS + "/" + dateTime);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    return invocation.get(new GenericType<CollectionDTO<ChangedRFA1aFormDTO>>() {
    });
  }
}
