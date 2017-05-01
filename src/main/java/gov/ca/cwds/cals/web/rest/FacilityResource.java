package gov.ca.cwds.cals.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FacilityServiceBackendResource;
import gov.ca.cwds.cals.service.dto.Facility;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static gov.ca.cwds.cals.Constants.API.FACILITY;

/**
 *
 *  A resource provides get API
 *
 *  @author CALS API Team
 *
 */

@Api(value = FACILITY, tags = FACILITY)
@Path(FACILITY)
@Produces(MediaType.APPLICATION_JSON)
public class FacilityResource {

    private ResourceDelegate resourceDelegate;

    @Inject
    public FacilityResource(@FacilityServiceBackendResource ResourceDelegate resourceDelegate) {
        this.resourceDelegate = resourceDelegate;
    }

    @GET
    @Timed
    @Path("/{id}")
    @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 406, message = "Accept Header not supported")})
    @ApiOperation(value = "Returns Facility by id ", response = Facility.class)
    public Response get(@PathParam("id")  @ApiParam(required = true, name = "id",
            value = "The id of the Facility to find") String id) {
        return resourceDelegate.get(id);
    }

}
