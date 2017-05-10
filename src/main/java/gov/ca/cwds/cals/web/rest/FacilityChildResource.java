package gov.ca.cwds.cals.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FacilityChildServiceBackendResource;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static gov.ca.cwds.cals.Constants.API.FACILITY_CHILD;

/**
 * @author CWDS CALS API Team
 */
@Api(value = FACILITY_CHILD, tags = FACILITY_CHILD)
@Path(FACILITY_CHILD)
@Produces(MediaType.APPLICATION_JSON)
public class FacilityChildResource {
    private CollectionResourceDelegate resourceDelegate;

    @Inject
    public FacilityChildResource(@FacilityChildServiceBackendResource CollectionResourceDelegate resourceDelegate) {
        this.resourceDelegate = resourceDelegate;
    }

    @UnitOfWork(value = "cms")
    @GET
    @Timed
    @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 406, message = "Accept Header not supported")})
    @ApiOperation(value = "Returns Children list by Facility id ", response = FacilityDTO.class)
    public Response get(@PathParam("facility_id")  @ApiParam(required = true, name = "facility_id",
            value = "The id of the Facility") Integer facility_id) {
        return resourceDelegate.get(facility_id);
    }
}
