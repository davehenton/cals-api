package gov.ca.cwds.cals.web.rest.tracking;

import static gov.ca.cwds.cals.Constants.API.PathParams.TRACKING_TEMPLATE_ID;
import static gov.ca.cwds.cals.Constants.API.TRACKING_TEMPLATES;
import static gov.ca.cwds.cals.Constants.TRACKING;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.TrackingTemplateServiceBackedResource;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate;
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
 * REST Api for Tracking Template.
 *
 * @author CWDS TPT-2 Team
 */
@Api(tags = {TRACKING})
@Path(TRACKING_TEMPLATES)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrackingTemplateResource {

  private TypedResourceDelegate<Long, TrackingTemplate> trackingTemplateService;

  @Inject
  public TrackingTemplateResource(
      @TrackingTemplateServiceBackedResource
          TypedResourceDelegate<Long, TrackingTemplate> trackingTemplateService) {
    this.trackingTemplateService = trackingTemplateService;
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
  @ApiOperation(value = "Creates and returns TrackingTemplate", response = TrackingTemplate.class)
  public Response createTrackingTemplate(
      @ApiParam(name = "Tracking", value = "The TrackingTemplate object")
      @Valid
          TrackingTemplate trackingTemplate) {
    return trackingTemplateService.create(trackingTemplate);
  }

  // PUT
  @UnitOfWork(CALSNS)
  @PUT
  @Path("/{" + TRACKING_TEMPLATE_ID + "}")
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
  @ApiOperation(value = "Updates Tracking Template", response = TrackingTemplate.class)
  public Response updateTrackingTemplate(
      @PathParam(TRACKING_TEMPLATE_ID)
      @ApiParam(required = true, name = TRACKING_TEMPLATE_ID, value = "The TrackingTemplate Id")
          Long trackingTemplateId,
      @ApiParam(name = "TrackingTemplate", value = "The TrackingTemplate object")
      @Valid
          TrackingTemplate trackingTemplate) {
    return trackingTemplateService.update(trackingTemplateId, trackingTemplate);
  }


  // GET
  @UnitOfWork(CALSNS)
  @GET
  @Path("/{" + TRACKING_TEMPLATE_ID + "}")
  @Produces({MediaType.APPLICATION_JSON})
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns Tracking Tempalte by Id", response = TrackingTemplate.class)
  public Response getTrackingTemplate(
      @PathParam(TRACKING_TEMPLATE_ID)
      @ApiParam(required = true, name = TRACKING_TEMPLATE_ID, value = "The Tracking Template Id")
          Long trackingTemplateId) {
    return trackingTemplateService.get(trackingTemplateId);
  }

  // DELETE
  @UnitOfWork(CALSNS)
  @DELETE
  @Path("/{" + TRACKING_TEMPLATE_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found")
      }
  )
  @ApiOperation(value = "Deletes Tracking Template")
  public Response deleteTrackingTemplate(
      @PathParam(TRACKING_TEMPLATE_ID)
      @ApiParam(required = true, name = TRACKING_TEMPLATE_ID, value = "The Tracking Template Id")
          Long trackingTemplateId) {
    return trackingTemplateService.delete(trackingTemplateId);
  }

}
