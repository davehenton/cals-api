package gov.ca.cwds.cals.rest.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import gov.ca.cwds.rest.api.ApiException;
import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static gov.ca.cwds.cals.Constants.API.RESOURCE_APPLICATION;

@Api(value = RESOURCE_APPLICATION/*, hidden = true*/)
@Path(RESOURCE_APPLICATION)
@Produces(MediaType.APPLICATION_JSON)
public class ApplicationResource {

    private String applicationName;
    private String version;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Constructor
     *
     * @param applicationName The name of the application
     * @param version The version of the API
     */
    @Inject
    public ApplicationResource(@Named("app.name") String applicationName,
            @Named("app.version") String version) {
        this.applicationName = applicationName;
        this.version = version;
    }

    /**
     * Get the name of the application.
     *
     * @return the application data
     */
    @GET
    public String get() {
        ImmutableMap<String, String> map = ImmutableMap.<String, String>builder()
                .put("Application", applicationName).put("Version", version).build();
        try {
            return MAPPER.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new ApiException("Unable to parse application data", e);
        }
    }
}
