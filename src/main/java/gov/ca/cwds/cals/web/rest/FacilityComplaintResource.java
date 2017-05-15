package gov.ca.cwds.cals.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.ComplaintServiceBackedResource;
import gov.ca.cwds.cals.inject.ComplaintsCollectionServiceBackedResource;
import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import gov.ca.cwds.cals.service.dto.ComplaintsDTO;
import gov.ca.cwds.cals.web.rest.parameter.FacilityComplaintParameterObject;
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

import static gov.ca.cwds.cals.Constants.API.COMPLAINTS;
import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.Constants.API.PATH_PARAMS.COMPLAINT_ID;
import static gov.ca.cwds.cals.Constants.API.PATH_PARAMS.FACILITY_ID;

/**
 * @author CWDS CALS API Team
 */

@Api(tags = {FACILITIES, COMPLAINTS})
@Path(FACILITIES + "/{"+ FACILITY_ID + "}/" + COMPLAINTS)
@Produces(MediaType.APPLICATION_JSON)
public class FacilityComplaintResource {

    private ResourceDelegate resourceCollectionDelegate;
    private ResourceDelegate resourceEntityDelegate;

    @Inject
    public FacilityComplaintResource(
            @ComplaintsCollectionServiceBackedResource ResourceDelegate resourceCollectionDelegate,
            @ComplaintServiceBackedResource ResourceDelegate resourceEntityDelegate
    ) {
       this.resourceCollectionDelegate = resourceCollectionDelegate;
       this.resourceEntityDelegate = resourceEntityDelegate;
    }

    @UnitOfWork(value = "fas")
    @GET
    @Timed
    @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 404, message = "Not found")})
    @ApiOperation(value = "Returns Facility by id ", response = ComplaintsDTO.class)
    public Response getFacilityComplaintsByFacilityId(
            @PathParam(FACILITY_ID) @ApiParam(required = true, name = FACILITY_ID,
                    value = "The id of the Facility") Integer facilityId) {

        return resourceCollectionDelegate.get(facilityId);
    }

    @UnitOfWork(value = "fas")
    @GET
    @Timed
    @Path("/{" + COMPLAINT_ID + "}")
    @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 404, message = "Not found")})
    @ApiOperation(value = "Returns Facility by id ", response = ComplaintDTO.class)
    public Response getFacilityComplaintByFacilityIdAndComplaintId(
            @PathParam(FACILITY_ID) @ApiParam(required = true, name = FACILITY_ID,
                    value = "The id of the Facility") Integer facilityId,
            @PathParam(COMPLAINT_ID) @ApiParam(required = true, name = COMPLAINT_ID,
                    value = "The id of the Complaint") String complaintId) {

        return resourceEntityDelegate.get(new FacilityComplaintParameterObject(facilityId, complaintId));
    }

}

