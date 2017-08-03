package gov.ca.cwds.cals.web.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cals.JerseyGuiceRule;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApplicationResourceTest {

    private static final String APP_NAME = "my app";

    @ClassRule
    public static JerseyGuiceRule rule = new JerseyGuiceRule();

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new ApplicationResource(APP_NAME))
            .build();

    @Test
    public void testApplicationGet() throws IOException {
        String response = resources.client().target("/application").request().get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
        Map<String,String> applicationProperties = mapper.readValue(response, typeRef);
        assertNotNull(applicationProperties);
        assertEquals(APP_NAME, applicationProperties.get("Application"));
        assertNotNull(applicationProperties.get("Version"));
        assertNotNull(applicationProperties.get("BuildNumber"));
    }
}