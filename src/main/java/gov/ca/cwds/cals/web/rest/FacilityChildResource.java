package gov.ca.cwds.cals.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FacilityChildCollectionServiceBackendResource;
import gov.ca.cwds.cals.inject.FacilityChildServiceBackendResource;
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

import static gov.ca.cwds.cals.Constants.API.FACILITY_CHILD;

/**
 * @author CWDS CALS API Team
 */
@Api(value = FACILITY_CHILD, tags = FACILITY_CHILD)
@Path(FACILITY_CHILD)
@Produces(MediaType.APPLICATION_JSON)
public class FacilityChildResource {
    private ResourceDelegate resourceDelegate;
    private ResourceDelegate collectionResourceDelegate;

    @Inject
    public FacilityChildResource(@FacilityChildServiceBackendResource ResourceDelegate resourceDelegate,
                                 @FacilityChildCollectionServiceBackendResource ResourceDelegate collectionResourceDelegate) {
        this.resourceDelegate = resourceDelegate;
        this.collectionResourceDelegate = collectionResourceDelegate;
    }

    @UnitOfWork(value = "cms")
    @GET
    @Timed
    @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 406, message = "Accept Header not supported")})
    @ApiOperation(value = "Returns Children", response = FacilityDTO.class)
    public Response getChildren(@PathParam("facility_id")  @ApiParam(required = true, name = "facility_id",
            value = "The license number of the Placement Home") String facility_license_number) {
        return collectionResourceDelegate.get(facility_license_number);
    }

    @UnitOfWork(value = "cms")
    @GET
    @Timed
    @Path("/{child_id}")
    @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 406, message = "Accept Header not supported")})
    @ApiOperation(value = "Returns Child", response = FacilityDTO.class)
    public Response getChild(@PathParam("facility_id")  @ApiParam(required = true, name = "facility_id", value = "The license number of the Placement Home") String facility_license_number,
            @PathParam("child_id") @ApiParam(required = true, name = "child_id", value = "The id of the Client") String child_id) {
        return resourceDelegate.get(facility_license_number + ',' + child_id);
    }
}
