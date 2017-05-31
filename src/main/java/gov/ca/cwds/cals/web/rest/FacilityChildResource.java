package gov.ca.cwds.cals.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FacilityChildCollectionServiceBackendResource;
import gov.ca.cwds.cals.inject.FacilityChildServiceBackendResource;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildrenDTO;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
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
import static gov.ca.cwds.cals.Constants.API.CHILDREN;
import static gov.ca.cwds.cals.Constants.API.PathParams.CHILD_ID;
import static gov.ca.cwds.cals.Constants.API.PathParams.FACILITY_ID;

/**
 * @author CWDS CALS API Team
 */
@Api(tags = {FACILITIES})
@Path(FACILITIES + "/{"+ FACILITY_ID + "}/" + CHILDREN)
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
    @ApiOperation(value = "Returns Children collection by Facility Id", response = FacilityChildrenDTO.class)
    public Response getChildren(@PathParam(FACILITY_ID)  @ApiParam(required = true, name = FACILITY_ID,
            value = "The license number of the Placement Home") String facility_license_number) {
        return collectionResourceDelegate.get(new FacilityChildParameterObject(facility_license_number));
    }

    @UnitOfWork(value = "cms")
    @GET
    @Timed
    @Path("/{" + CHILD_ID + "}")
    @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 406, message = "Accept Header not supported")})
    @ApiOperation(value = "Returns Child by Facility Id and Child Id", response = FacilityChildDTO.class)
    public Response getChild(@PathParam(FACILITY_ID)  @ApiParam(required = true, name = FACILITY_ID, value = "The license number of the Placement Home") String facility_license_number,
            @PathParam(CHILD_ID) @ApiParam(required = true, name = CHILD_ID, value = "The id of the Client") String childId) {
        return resourceDelegate.get(new FacilityChildParameterObject(facility_license_number, childId));
    }
}
