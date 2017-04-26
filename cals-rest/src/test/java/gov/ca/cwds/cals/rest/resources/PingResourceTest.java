package gov.ca.cwds.cals.rest.resources;

import gov.ca.cwds.cals.rest.JerseyGuiceRule;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PingResourceTest {

    @ClassRule
    public static JerseyGuiceRule rule = new JerseyGuiceRule();

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PingResource())
            .build();

    @Test
    public void testPing() {
        assertEquals("pong", resources.target("/ping").request().get(String.class));
    }

}