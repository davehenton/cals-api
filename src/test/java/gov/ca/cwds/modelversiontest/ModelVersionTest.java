package gov.ca.cwds.modelversiontest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jonpeterson.jackson.module.versioning.VersioningModule;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Created by TPT2 on 1/2/2018.
 */
public class ModelVersionTest {
  @Test
  public void test() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new VersioningModule());

    PersonV3 person = objectMapper.readValue(IOUtils.toString(getClass().getResourceAsStream("/modelversion/person.json"), "UTF-8"), PersonV3.class);
    assert person.getFirstName().equals("John");

    person = objectMapper.readValue(IOUtils.toString(getClass().getResourceAsStream("/modelversion/personV1.json"), "UTF-8"), PersonV3.class);
    assert person.getFirstName().equals("First");

    person = objectMapper.readValue(IOUtils.toString(getClass().getResourceAsStream("/modelversion/personV2.json"), "UTF-8"), PersonV3.class);
    assert person.getFirstName().equals("First");

    String personSerialized = objectMapper.writeValueAsString(person);
    ObjectMapper testObjectMapper = new ObjectMapper();
    Map map = testObjectMapper.readValue(personSerialized, Map.class);
    assert map.get("modelVersion").equals(PersonHistory.CURRENT_VERSION);


  }
}
