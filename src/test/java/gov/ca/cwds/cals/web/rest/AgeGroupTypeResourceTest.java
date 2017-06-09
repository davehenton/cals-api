package gov.ca.cwds.cals.web.rest;

import static gov.ca.cwds.cals.Constants.API.AGE_GROUP_TYPES;
import static gov.ca.cwds.cals.Constants.API.RFA;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.AgeGroupTypesDTO;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.junit.BeforeClass;
import org.junit.Test;

/** @author CWDS CALS API Team */
public class AgeGroupTypeResourceTest extends BaseCalsApiIntegrationTest {

  private static final String FIXTURES_AGE_GROUP_TYPES_RESPONSE_JSON = "fixtures/age-group-types-response.json";

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void getAgeGroupTypesTest() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.DICTIONARIES + "/" + RFA  + "/" + AGE_GROUP_TYPES);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    AgeGroupTypesDTO ageGroupTypesDTO = invocation.get(AgeGroupTypesDTO.class);
    assertNotNull(ageGroupTypesDTO);
    String fixture = fixture(FIXTURES_AGE_GROUP_TYPES_RESPONSE_JSON);
    assertEqualsResponse(fixture, ageGroupTypesDTO);
  }
}
