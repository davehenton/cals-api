package gov.ca.cwds.cals.web.rest.rfa.changed;

import static gov.ca.cwds.cals.Constants.API.RFA_1A_FORMS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.RecordChangeOperation;
import gov.ca.cwds.cals.service.dto.changed.ChangedRFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import java.util.Optional;
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
    int numberOfChangedAfter19700101 = rfaForms.getCollection().size();
    assertTrue(numberOfChangedAfter19700101 >= 2);
    ChangedRFA1aFormDTO changedRFA1aFormDTO = rfaForms.getCollection().iterator().next();
    assertTrue(RecordChangeOperation.I == changedRFA1aFormDTO.getRecordChangeOperation());

    String after = "2017-07-18 10:01:00";
    rfaForms = getChangedRFA1aFormsAfter(after);
    int numberOfChangedAfter20170718 = rfaForms.getCollection().size();
    assertEquals(numberOfChangedAfter20170718, numberOfChangedAfter19700101 - 1);

    Optional<RFA1aFormDTO> optional = rfaForms.getCollection().stream().map(ChangedRFA1aFormDTO::getDTO)
        .filter(rfa1aForm -> rfa1aForm.getId() == 2).findFirst();
    assertTrue(optional.isPresent());
    RFA1aFormDTO rfa1aFormDTO = optional.get();
    assertTrue(rfa1aFormDTO.getApplicants().size() > 0);
    assertTrue(0 > toLocalDateTime(after).compareTo(rfa1aFormDTO.getApplicants().get(0).getUpdateDateTime()));

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
