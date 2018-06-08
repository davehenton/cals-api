package gov.ca.cwds.cals.web.rest;

import static gov.ca.cwds.cals.Constants.API.COMPLAINTS;
import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.Constants.API.PathParams.COMPLAINT_ID;
import static gov.ca.cwds.cals.Constants.API.PathParams.FACILITY_ID;
import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.authorizer.FacilityReadStaticAuthorizer;
import gov.ca.cwds.cals.service.ComplaintsService;
import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import gov.ca.cwds.cals.service.dto.ComplaintsDTO;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * Resource for handling facility complaints functionality.
 * @author CWDS CALS API Team
 */

@Api(tags = {FACILITIES})
@Path(FACILITIES + "/{" + FACILITY_ID + "}/" + COMPLAINTS)
@Produces(MediaType.APPLICATION_JSON)
public class FacilityComplaintResource {

  @Inject
  private ComplaintsService complaintService;

  @UnitOfWork(FAS)
  @GET
  @Timed
  @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not founMappd")})
  @ApiOperation(value = "Returns Complaints collection by Facility Id",
      response = ComplaintsDTO.class)
  @RequiresPermissions(FacilityReadStaticAuthorizer.FACILITY_READ_PERMISSION)
  public Response getFacilityComplaintsByFacilityId(
      @PathParam(FACILITY_ID) @ApiParam(required = true, name = FACILITY_ID,
          value = "The id of the Facility") String facilityId) {
    ComplaintsDTO complaintsDto = new ComplaintsDTO();
    Set<ComplaintDTO> complaints = complaintService.getComplaintsByFacilityId(facilityId);
    complaintsDto.setComplaints(complaints);
    return DefaultResponseHandler.get(complaintsDto);
  }

  @UnitOfWork(FAS)
  @GET
  @Timed
  @Path("/{" + COMPLAINT_ID + "}")
  @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found")})
  @ApiOperation(value = "Returns Complaint by Facility Id and Complaint Id",
      response = ComplaintDTO.class)
  @RequiresPermissions(FacilityReadStaticAuthorizer.FACILITY_READ_PERMISSION)
  public Response getFacilityComplaintByFacilityIdAndComplaintId(
      @PathParam(FACILITY_ID) @ApiParam(required = true, name = FACILITY_ID,
          value = "The id of the Facility") String facilityId,
      @PathParam(COMPLAINT_ID) @ApiParam(required = true, name = COMPLAINT_ID,
          value = "The id of the Complaint") String complaintId) {
    return DefaultResponseHandler
        .get(complaintService.getComplaintByFacilityIdAndComplaintId(facilityId, complaintId));
  }

}
