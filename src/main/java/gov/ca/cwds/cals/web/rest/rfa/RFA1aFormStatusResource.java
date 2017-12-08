package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_APPLICATION_ID;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_FORMS;
import static gov.ca.cwds.cals.Constants.API.STATUS;
import static gov.ca.cwds.cals.Constants.RFA;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.rfa.RFAApplicationStatusDTO;
import gov.ca.cwds.cals.service.rfa.RFA1aFormService;
import gov.ca.cwds.drools.DroolsException;
import gov.ca.cwds.rest.exception.BusinessValidationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * @author CWDS CALS API Team
 */
@Api(tags = {RFA})
@Path(RFA_1A_FORMS + "/{" + RFA_1A_APPLICATION_ID + "}/" + STATUS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RFA1aFormStatusResource {

  private final RFA1aFormService service;

  @Inject
  public RFA1aFormStatusResource(RFA1aFormService service) {
    this.service = service;
  }

  @POST
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "Application status has been changed successfully"),
          @ApiResponse(code = 304, message = "Application status is the same as previous"),
          @ApiResponse(code = 400, message = "Application status can not be changed to requested one"),
          @ApiResponse(code = 422, message = "Validation exception fired"),
      }
  )
  @ApiOperation(value = "Changes application status")
  public Response changeApplicationStatus(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId,
      @ApiParam(
          required = true,
          name = STATUS,
          value = "New Application status"
      )
          RFAApplicationStatusDTO status) throws BusinessValidationException, DroolsException {
    service.setApplicationStatus(formId, status);
    return Response.status(Status.OK).build();
  }

  @GET
  @Timed
  @ApiOperation(value = "Get RFA application status", response = RFAApplicationStatusDTO.class)
  public Response getApplicationStatus(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId) {
    RFAApplicationStatusDTO status = service.getApplicationStatus(formId);
    return Response.ok(status).status(Status.OK).build();
  }

}
