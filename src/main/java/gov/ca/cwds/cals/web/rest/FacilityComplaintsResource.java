package gov.ca.cwds.cals.web.rest;

import com.codahale.metrics.annotation.Timed;
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

import static gov.ca.cwds.cals.Constants.API.COMPLAINTS;
import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.Constants.API.PATH_PARAMS.COMPLAINT_ID;
import static gov.ca.cwds.cals.Constants.API.PATH_PARAMS.FACILITY_ID;
import static io.dropwizard.testing.FixtureHelpers.fixture;

/**
 * @author CWDS CALS API Team
 */

@Api(tags = {FACILITIES, COMPLAINTS})
@Path(FACILITIES + "/{"+ FACILITY_ID + "}/" + COMPLAINTS)
public class FacilityComplaintsResource {


    private ResourceDelegate resourceDelegate;

    public FacilityComplaintsResource() {
    }


    @GET
    @Timed
    @Produces(MediaType.TEXT_PLAIN)
    @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 404, message = "Not found")})
    @ApiOperation(value = "Returns Facility by id ", response = String.class)
    public String get(@PathParam(FACILITY_ID)  @ApiParam(required = true, name = FACILITY_ID,
            value = "The id of the Facility") Integer facilityId) {

        return fixture("fixtures/complaints-response.json");
    }


    @GET
    @Timed
    @Path("/{" + COMPLAINT_ID + "}")
    @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 404, message = "Not found")})
    @ApiOperation(value = "Returns Facility by id ", response = String.class)
    public String get(@PathParam(FACILITY_ID)  @ApiParam(required = true, name = FACILITY_ID,
            value = "The id of the Facility") Integer facilityId,
            @PathParam(COMPLAINT_ID)  @ApiParam(required = true, name = COMPLAINT_ID,
            value = "The id of the Complaint") Integer complaintId ) {

        return fixture("fixtures/complaint-response.json");
    }


}

