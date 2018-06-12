package gov.ca.cwds.cals.service.dto;

import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;

import io.dropwizard.jackson.Jackson;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

/**
 * Created by Alexander Serbin on 6/11/2018.
 */
public class AssignedWorkerDtoTest {

  @Test
  public void checkJsonAdheresFrontendContract() throws IOException {
    AssignedWorkerDto assignedWorkerDto = new AssignedWorkerDto();
    assignedWorkerDto.setId("some id");
    assignedWorkerDto.setEmail("email@gmail.com");
    assignedWorkerDto.setFullName("Mo Nasir");
    PersonPhoneDTO primaryPhone = new PersonPhoneDTO();
    primaryPhone.setNumber("1111111111");
    primaryPhone.setRelation("primary");
    PersonPhoneDTO alternatePhone = new PersonPhoneDTO();
    alternatePhone.setNumber("2222222222");
    alternatePhone.setRelation("alternate");
    ArrayList<PersonPhoneDTO> phones = new ArrayList<>(2);
    phones.add(primaryPhone);
    phones.add(alternatePhone);
    assignedWorkerDto.setPhones(phones);
    String actualResult = Jackson.newObjectMapper().writeValueAsString(assignedWorkerDto);
    String fixture = fixture("fixtures/dto/assigned-worker.json");
    assertEqualsResponse(fixture, actualResult);
  }

}