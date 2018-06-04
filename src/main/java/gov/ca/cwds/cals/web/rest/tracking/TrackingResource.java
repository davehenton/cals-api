package gov.ca.cwds.cals.web.rest.tracking;

import static gov.ca.cwds.cals.Constants.API.PathParams.TRACKING_ID;
import static gov.ca.cwds.cals.Constants.API.TRACKINGS;
import static gov.ca.cwds.cals.Constants.TRACKING;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.TrackingServiceBackedResource;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import gov.ca.cwds.rest.resources.TypedResourceDelegate;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Api for Tracking.
 *
 * @author CWDS TPT-2 Team
 */
@Api(tags = {TRACKING})
@Path(TRACKINGS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrackingResource {

  private TypedResourceDelegate<Long, Tracking> trackingService;

  @Inject
  public TrackingResource(
      @TrackingServiceBackedResource TypedResourceDelegate<Long, Tracking> trackingService) {
    this.trackingService = trackingService;
  }

  // POST
  @UnitOfWork(CALSNS)
  @POST
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 201, message = "Created"),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Creates and returns Tracking", response = Tracking.class)
  public Response createTracking(
      @ApiParam(name = "Tracking", value = "The Tracking object")
      @Valid
          Tracking tracking) {
    return trackingService.create(tracking);
  }

  // PUT
  @UnitOfWork(CALSNS)
  @PUT
  @Path("/{" + TRACKING_ID + "}")
  @Produces(MediaType.APPLICATION_JSON)
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 400, message = "Bad request"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Updates Tracking", response = Tracking.class)
  public Response updateTracking(
      @PathParam(TRACKING_ID)
      @ApiParam(required = true, name = TRACKING_ID, value = "The Tracking Id")
          Long trackingId,
      @ApiParam(name = "Tracking", value = "The Tracking object")
      @Valid
          Tracking tracking) {
    return trackingService.update(trackingId, tracking);
  }


  // GET
  @UnitOfWork(CALSNS)
  @GET
  @Path("/{" + TRACKING_ID + "}")
  @Produces({MediaType.APPLICATION_JSON})
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns Tracking by Id", response = Tracking.class)
  public Response getTracking(
      @PathParam(TRACKING_ID)
      @ApiParam(required = true, name = TRACKING_ID, value = "The Tracking Id")
          Long trackingId) {
    return trackingService.get(trackingId);
  }

  // DELETE
  @UnitOfWork(CALSNS)
  @DELETE
  @Path("/{" + TRACKING_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found")
      }
  )
  @ApiOperation(value = "Deletes Tracking")
  public Response deleteTracking(
      @PathParam(TRACKING_ID)
      @ApiParam(required = true, name = TRACKING_ID, value = "The Tracking Id")
          Long trackingId
  ) {
    return trackingService.delete(trackingId);
  }


}
