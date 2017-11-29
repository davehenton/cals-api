package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_APPLICATION_ID;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1C_FORM;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1C_FORM_ID;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_FORMS;
import static gov.ca.cwds.cals.Constants.API.RFA_1C_FORMS;
import static gov.ca.cwds.cals.Constants.RFA;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.RFA1cCollectionServiceBackedResource;
import gov.ca.cwds.cals.inject.RFA1cServiceBackedResource;
import gov.ca.cwds.cals.service.dto.rfa.RFA1cFormDTO;
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
@Path(RFA_1A_FORMS + "/{" + RFA_1A_APPLICATION_ID + "}/" + RFA_1C_FORMS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RFA1cFormsResource {

  private TypedResourceDelegate<
      RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<RFA1cFormDTO>>
      resourceDelegate;

  @Inject
  public RFA1cFormsResource(
      @RFA1cServiceBackedResource
          TypedResourceDelegate<
              RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<RFA1cFormDTO>>
          resourceDelegate,
      @RFA1cCollectionServiceBackedResource
          TypedResourceDelegate<Long, Request> collectionResourceDelegate) {
    this.resourceDelegate = resourceDelegate;
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
  @ApiOperation(value = "Creates and returns RFA 1C Form", response = RFA1cFormDTO.class)
  public Response createRFA1cForm(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long applicationId,
      @ApiParam(required = true, name = RFA_1C_FORM, value = "The RFA-1C Form object")
      @Valid
          RFA1cFormDTO rfa1cForm) {
    return resourceDelegate
        .create(new RFAExternalEntityUpdateParameterObject<>(applicationId, rfa1cForm));
  }

  @UnitOfWork(CALSNS)
  @PUT
  @Path("/{" + RFA_1C_FORM_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Updates and returns RFA 1C Form object", response = RFA1cFormDTO.class)
  public Response updateRFA1cForm(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(
          required = true,
          name = RFA_1A_APPLICATION_ID,
          value = "The RFA-1a Application Id"
      )
          Long applicationId,
      @PathParam(RFA_1C_FORM_ID)
      @ApiParam(
          required = true,
          name = RFA_1C_FORM_ID,
          value = "The RFA-1C Form Id"
      )
          Long rfa1cId,
      @ApiParam(required = true, name = RFA_1C_FORM, value = "The RFA-1C Form object")
      @Valid
          RFA1cFormDTO rfa1cFormDTO) {
    return resourceDelegate.update(
        new RFAExternalEntityGetParameterObject(applicationId, rfa1cId),
        new RFAExternalEntityUpdateParameterObject<>(applicationId, rfa1cFormDTO));
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/{" + RFA_1C_FORM_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns RFA 1C Form by RFA 1A id and RFA 1C id", response = RFA1cFormDTO.class)
  public Response getRFA1CFormById(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(
          required = true,
          name = RFA_1A_APPLICATION_ID,
          value = "The RFA-1A Application Id"
      )
          Long applicationId,
      @PathParam(RFA_1C_FORM_ID)
      @ApiParam(
          required = true,
          name = RFA_1C_FORM_ID,
          value = "The RFA 1C Form Id"
      )
          Long rfa1CFormId) {

    return resourceDelegate
        .get(new RFAExternalEntityGetParameterObject(applicationId, rfa1CFormId));
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
      value = "Returns RFA 1C Form by Application Id",
      response = RFA1cFormDTO.class
  )
  public Response getRFA1cFormByFormRFA1aFormId(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(
          required = true,
          name = RFA_1A_APPLICATION_ID,
          value = "The RFA-1A Application Id"
      )
          Long applicationId) {

    RFAExternalEntityGetParameterObject paramsObj = new RFAExternalEntityGetParameterObject(
        applicationId, null);

    return resourceDelegate.get(paramsObj);
  }

  @UnitOfWork(CALSNS)
  @DELETE
  @Path("/{" + RFA_1C_FORM_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found")
      }
  )
  @ApiOperation(value = "Deletes RFA 1C Form")
  public Response deleteRFA1cForm(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA 1A Form Id")
          Long applicationId,
      @PathParam(RFA_1C_FORM_ID)
      @ApiParam(
          required = true,
          name = RFA_1C_FORM_ID,
          value = "The RFA 1C Form Id"
      )
          Long rfa1cFormId) {
    return resourceDelegate.delete(
        new RFAExternalEntityGetParameterObject(applicationId, rfa1cFormId));
  }
}
