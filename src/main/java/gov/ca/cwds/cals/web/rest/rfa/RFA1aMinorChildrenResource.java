package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_APPLICATION_ID;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_MINOR_CHILD;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_MINOR_CHILD_ID;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_FORMS;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_MINOR_CHILDREN;
import static gov.ca.cwds.cals.Constants.RFA;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.RFA1aMinorChildrenCollectionServiceBackedResource;
import gov.ca.cwds.cals.inject.RFA1aMinorChildrenServiceBackedResource;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.MinorChildrenCollectionDTO;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author CWDS CALS API Team
 */
@Api(tags = {RFA})
@Path(RFA_1A_FORMS + "/{" + RFA_1A_APPLICATION_ID + "}/" + RFA_1A_MINOR_CHILDREN)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RFA1aMinorChildrenResource {

  private TypedResourceDelegate<RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<MinorChildDTO>>
      resourceDelegate;
  private TypedResourceDelegate<Long, Request> collectionResourceDelegate;

  @Inject
  public RFA1aMinorChildrenResource(
      @RFA1aMinorChildrenServiceBackedResource
          TypedResourceDelegate<
              RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<MinorChildDTO>>
          resourceDelegate,
      @RFA1aMinorChildrenCollectionServiceBackedResource
          TypedResourceDelegate<Long, Request> collectionResourceDelegate) {
    this.resourceDelegate = resourceDelegate;
    this.collectionResourceDelegate = collectionResourceDelegate;
  }

  @UnitOfWork(CALSNS)
  @POST
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Creates and returns RFA 1A MinorChild object", response = MinorChildDTO.class)
  public Response createMinorChild(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long applicationId,
      @ApiParam(required = true, name = RFA_1A_MINOR_CHILD, value = "The RFA-1A MinorChild object")
      @Valid
          MinorChildDTO minorChild) {
    return resourceDelegate.create(
        new RFAExternalEntityUpdateParameterObject<>(applicationId, minorChild));
  }

  @UnitOfWork(CALSNS)
  @PUT
  @Path("/{" + RFA_1A_MINOR_CHILD_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Update and returns RFA 1A MinorChild object", response = MinorChildDTO.class)
  public Response updateMinorChild(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(
          required = true,
          name = RFA_1A_APPLICATION_ID,
          value = "The RFA-1A Application Id"
      )
          Long applicationId,
      @PathParam(RFA_1A_MINOR_CHILD_ID)
      @ApiParam(
          required = true,
          name = RFA_1A_MINOR_CHILD_ID,
          value = "The RFA-1A MinorChild Id"
      )
          Long minorChildId,
      @ApiParam(required = true, name = RFA_1A_MINOR_CHILD, value = "The RFA-1A MinorChild object")
      @Valid
          MinorChildDTO minorChild) {
    return resourceDelegate.update(
        new RFAExternalEntityGetParameterObject(applicationId, minorChildId),
        new RFAExternalEntityUpdateParameterObject<>(applicationId, minorChild));
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/{" + RFA_1A_MINOR_CHILD_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns RFA 1A Form's MinorChild by Id", response = MinorChildDTO.class)
  public Response getMinorChildById(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(
          required = true,
          name = RFA_1A_APPLICATION_ID,
          value = "The RFA-1A Application Id"
      )
          Long applicationId,
      @PathParam(RFA_1A_MINOR_CHILD_ID)
      @ApiParam(
          required = true,
          name = RFA_1A_MINOR_CHILD_ID,
          value = "The RFA-1A MinorChild Id"
      )
          Long minorChildId) {

    return resourceDelegate
        .get(new RFAExternalEntityGetParameterObject(applicationId, minorChildId));
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
      value = "Returns RFA 1A Form's MinorChild by Application Id",
      response = MinorChildrenCollectionDTO.class
  )
  public Response getMinorChildByFormId(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(
          required = true,
          name = RFA_1A_APPLICATION_ID,
          value = "The RFA-1A Application Id"
      )
          Long applicationId) {
    return collectionResourceDelegate.get(applicationId);
  }

  @UnitOfWork(CALSNS)
  @DELETE
  @Path("/{" + RFA_1A_MINOR_CHILD_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found")
      }
  )
  @ApiOperation(value = "Delete RFA 1A Form's MinorChild by Id")
  public Response deleteMinorChild(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long applicationId,
      @PathParam(RFA_1A_MINOR_CHILD_ID)
      @ApiParam(
          required = true,
          name = RFA_1A_MINOR_CHILD_ID,
          value = "The RFA-1A MinorChild Id"
      )
          Long minorChildId) {
    return resourceDelegate.delete(
        new RFAExternalEntityGetParameterObject(applicationId, minorChildId));
  }
}
