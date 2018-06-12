package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_APPLICATION_ID;
import static gov.ca.cwds.cals.Constants.API.PathParams.TRACKING_ID;
import static gov.ca.cwds.cals.Constants.API.QueryParams.EXPANDED;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_FORMS;
import static gov.ca.cwds.cals.Constants.RFA;
import static gov.ca.cwds.cals.Constants.TRACKING;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.RFA1aFormCollectionServiceBackedResource;
import gov.ca.cwds.cals.inject.RFA1aFormServiceBackedResource;
import gov.ca.cwds.cals.inject.RFA1aTrackingServiceBackedResource;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.RFA1aFormCollectionDTO;
import gov.ca.cwds.cals.service.dto.tracking.TrackingDTO;
import gov.ca.cwds.cals.service.rfa.RFA1aPDFGenerationService;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aFormsParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.TrackingParameterObject;
import gov.ca.cwds.rest.api.ApiException;
import gov.ca.cwds.rest.api.Request;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
@Api(tags = {RFA})
@Path(RFA_1A_FORMS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RFA1aFormsResource {

  private static final Logger LOG = LoggerFactory.getLogger(RFA1aFormsResource.class);

  private static final String APPLICATION_PDF = "application/pdf";
  private TypedResourceDelegate<RFA1aFormsParameterObject, RFA1aFormDTO> resourceDelegate;
  private TypedResourceDelegate<Boolean, Request> collectionResourceDelegate;
  private TypedResourceDelegate<TrackingParameterObject, TrackingDTO> rfa1aTrackingResourceDelegate;

  @Inject
  private RFA1aPDFGenerationService pdfGenerationService;

  @Inject
  public RFA1aFormsResource(
      @RFA1aFormServiceBackedResource
          TypedResourceDelegate<RFA1aFormsParameterObject, RFA1aFormDTO> resourceDelegate,
      @RFA1aFormCollectionServiceBackedResource
          TypedResourceDelegate<Boolean, Request> collectionResourceDelegate,
      @RFA1aTrackingServiceBackedResource
          TypedResourceDelegate<TrackingParameterObject, TrackingDTO> rfa1aTrackingResourceDelegate
  ) {
    this.resourceDelegate = resourceDelegate;
    this.collectionResourceDelegate = collectionResourceDelegate;
    this.rfa1aTrackingResourceDelegate = rfa1aTrackingResourceDelegate;
  }

  //@UnitOfWork(CALSNS)
  @POST
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 201, message = "Created"),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Creates and returns RFA 1A Form", response = RFA1aFormDTO.class)
  public Response createApplicationForm(
      @ApiParam(name = "application", value = "The RFA-1A Application object") @Valid
          RFA1aFormDTO application) {
    // TODO: remove this temporary fix to support older versions of CALS DS
    if (application == null) {
      application = new RFA1aFormDTO();
      application.setInitialApplication(Boolean.TRUE);
    }
    return resourceDelegate.create(application);
  }

  //@UnitOfWork(CALSNS)
  @PUT
  @Path("/{" + RFA_1A_APPLICATION_ID + "}")
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
  @ApiOperation(value = "Updates RFA 1A Form", response = RFA1aFormDTO.class)
  public Response updateApplicationForm(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId,
      @ApiParam(name = "application", value = "The RFA-1A Application object")
      @Valid
          RFA1aFormDTO formDTO) {
    return resourceDelegate.update(new RFA1aFormsParameterObject(formId), formDTO);
  }

  //@UnitOfWork(CALSNS)
  @GET
  @Path("/{" + RFA_1A_APPLICATION_ID + "}")
  @Produces({MediaType.APPLICATION_JSON, APPLICATION_PDF})
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns RFA 1A Form by Id", response = RFA1aFormDTO.class)
  public Response getApplicationForm(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId,
      @QueryParam(EXPANDED)
      @ApiParam(name = EXPANDED, value = "Use 'true' to get form with all parts of form included")
          boolean expanded, @Context HttpHeaders headers) {

    String accept = headers.getHeaderString(HttpHeaders.ACCEPT);
    long startTime = System.currentTimeMillis();
    Response response = null;
    if (APPLICATION_PDF.equals(accept)) {
      String documentId = pdfGenerationService.generatePDF(formId);
      response = pdfGenerationService.getFormPdf(documentId);
    } else {
      RFA1aFormsParameterObject params = new RFA1aFormsParameterObject(formId, expanded);
      response = resourceDelegate.get(params);
    }
    LOG.info("Get RFA1a form total time: {}", (System.currentTimeMillis() - startTime));
    return response;
  }

  @GET
  @Path("{" + RFA_1A_APPLICATION_ID + "}/documentId")
  @Produces({MediaType.TEXT_PLAIN})
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(
      value = "Returns RFA 1A generated document (PDF) Id by application Id",
      response = String.class)
  public Response getGeneratedDocumentId(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId) {
    long startTime = System.currentTimeMillis();
    String docId = pdfGenerationService.generatePDF(formId);
    LOG.info("Get RFA1a form generation time: {}", (System.currentTimeMillis() - startTime));
    if (docId != null && !docId.isEmpty()) {
      return Response.status(Status.OK).entity(docId).build();
    }
    return Response.status(Status.NOT_FOUND).build();
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
      value = "Returns all available RFA 1A Forms",
      response = RFA1aFormCollectionDTO.class
  )
  public Response getAllApplicationForms(
      @QueryParam(EXPANDED)
      @ApiParam(
          name = EXPANDED,
          value = "Use 'true' to get forms with all parts of form included")
          boolean expanded) {
    return collectionResourceDelegate.get(expanded);
  }

  @DELETE
  @Timed
  @Path("/{" + RFA_1A_APPLICATION_ID + "}")
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found")
      }
  )
  @ApiOperation(value = "Delete RFA 1A Form by Id")
  public Response deleteForm(@PathParam(RFA_1A_APPLICATION_ID)
  @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
      Long formId){
    return resourceDelegate.delete(new RFA1aFormsParameterObject(formId));
  }

  @POST
  @Timed
  @Path("{" + RFA_1A_APPLICATION_ID + "}/tracking")
  @ApiResponses(
      value = {
          @ApiResponse(code = 201, message = "Created"),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Creates tracking for RFA 1A Form", response = TrackingDTO.class)
  @UnitOfWork(CALSNS)
  public Response createTracking(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId) {
    TrackingDTO tracking = new TrackingDTO();
    tracking.setRfa1aId(formId);
    return rfa1aTrackingResourceDelegate.create(tracking);
  }

  @GET
  @Path("/{" + RFA_1A_APPLICATION_ID + "}/" + TRACKING + "/{" + TRACKING_ID + "}")
  @Produces({MediaType.APPLICATION_JSON})
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns tracking for RFA 1A Form", response = TrackingDTO.class)
  @UnitOfWork(CALSNS)
  public Response getTracking(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId,
      @PathParam(TRACKING_ID)
      @ApiParam(name = TRACKING_ID, value = "Tracking id")
          Long trackingId) {
    TrackingParameterObject searchParams = new TrackingParameterObject(formId, trackingId);
    return rfa1aTrackingResourceDelegate.get(searchParams);
  }


  /**
   * Update Tracking REST API endpoint.
   *
   * @param formId Form Id
   * @param trackingId Tracking Id
   * @param tracking Tracking Object
   * @return Result of update
   */
  @PUT
  @Timed
  @Path("{" + RFA_1A_APPLICATION_ID + "}/" + TRACKING + "/" + "{" + TRACKING_ID + "}")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "Updated successfully"),
          @ApiResponse(code = 400, message = "Bad request"),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Update tracking for RFA 1A Form", response = Tracking.class)
  @UnitOfWork(CALSNS)
  public Response updateTracking(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId,
      @PathParam(TRACKING_ID)
      @ApiParam(required = true, name = TRACKING_ID, value = "The Tracking Id")
          Long trackingId,
      @ApiParam(name = "tracking", value = "The Tracking object")
      @Valid
          TrackingDTO tracking) {
    Response response;
    try {
      response = rfa1aTrackingResourceDelegate
          .update(new TrackingParameterObject(formId, trackingId), tracking);
    } catch (ApiException e) {
      LOG.warn(e.getMessage(), e);
      response = Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
    return response;
  }

  /**
   * Delete Tracking REST API endpoint.
   *
   * @param formId Form Id
   * @param trackingId Tracking Id
   * @return Deleted Tracking
   */
  @DELETE
  @Timed
  @Path("{" + RFA_1A_APPLICATION_ID + "}/" + TRACKING + "/" + "{" + TRACKING_ID + "}")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "Deleted successfully"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Delete tracking for RFA 1A Form", response = Tracking.class)
  @UnitOfWork(CALSNS)
  public Response deleteTracking(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId,
      @PathParam(TRACKING_ID)
      @ApiParam(required = true, name = TRACKING_ID, value = "The Tracking Id")
          Long trackingId) {
    return rfa1aTrackingResourceDelegate.delete(new TrackingParameterObject(formId, trackingId));
  }
}
