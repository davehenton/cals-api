package gov.ca.cwds.cals.rest.resources;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PingResourceTest {

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PingResource())
            .build();

    @Test
    public void testGetPerson() {
        assertEquals("pong", resources.target("/ping").request().get(String.class));
    }

}