package gov.ca.cwds.cals.web.rest.packet;

import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_APPLICATION_ID;
import static gov.ca.cwds.cals.Constants.API.RFA_PACKET;
import static gov.ca.cwds.cals.Constants.API.SUMMARY;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.RFA1aAdoptionHistoryServiceBackedResource;
import gov.ca.cwds.cals.service.dto.packet.PacketDTO;
import gov.ca.cwds.rest.resources.TypedResourceDelegate;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author CWDS TPT-2
 */
@Api(tags = {RFA_PACKET})
@Path("/{" + RFA_1A_APPLICATION_ID + "}/" + SUMMARY)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RFAPacketResource {

  private TypedResourceDelegate<Long, PacketDTO> resourceDelegate;

  @Inject
  public RFAPacketResource(
      @RFA1aAdoptionHistoryServiceBackedResource
          TypedResourceDelegate<Long, PacketDTO> resourceDelegate) {
    this.resourceDelegate = resourceDelegate;
  }

  @UnitOfWork(CALSNS)
  @GET
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(
      value = "Returns RFA Packet summary by RFA-1A Form Id",
      response = PacketDTO.class
  )
  public Response getRFAPacketSummary(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId) {
    return resourceDelegate.get(formId);
  }
}
