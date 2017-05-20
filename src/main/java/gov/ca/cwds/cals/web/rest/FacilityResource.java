package gov.ca.cwds.cals.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FacilityServiceBackendResource;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import io.dropwizard.hibernate.UnitOfWork;
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

import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.Constants.API.PATH_PARAMS.FACILITY_ID;

/**
 *  @author CALS API Team
 *
 */

@Api(value = FACILITIES, tags = FACILITIES)
@Path(FACILITIES)
@Produces(MediaType.APPLICATION_JSON)
public class FacilityResource {

    private ResourceDelegate resourceDelegate;

    @Inject
    public FacilityResource(@FacilityServiceBackendResource ResourceDelegate resourceDelegate) {
        this.resourceDelegate = resourceDelegate;
    }

    @GET
    @Timed
    @Path("/{" + FACILITY_ID + "}")
    @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 406, message = "Accept Header not supported")})
    @ApiOperation(value = "Returns Facility by id ", response = FacilityDTO.class)
    public Response getFacilityById(@PathParam(FACILITY_ID) @ApiParam(required = true, name = FACILITY_ID,
            value = "The id of the Facility to find") Integer facilityId) {
        return resourceDelegate.get(facilityId);
    }

}
