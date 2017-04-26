package gov.ca.cwds.cals.rest.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static gov.ca.cwds.cals.Constants.PING;

/**
 *  Ping resource
 *
 */

@Api(value = PING, tags = PING)
@Path(PING)
@Produces(MediaType.TEXT_PLAIN)
public class PingResource {

    /**
     *
     * @return "Pong" string
     */
    @GET
    @ApiOperation(value = "The simplest rest GET to check application is up and running", response = String.class)
    public String ping() {
        return "pong";
    }
}
