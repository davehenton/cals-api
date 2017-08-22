package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_APPLICANT_ID;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_APPLICATION_ID;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_OTHER_ADULT_ID;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1B_FORM;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1B_FORM_ID;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_APPLICANTS;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_FORMS;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_OTHER_ADULTS;
import static gov.ca.cwds.cals.Constants.API.RFA_1B_FORMS;
import static gov.ca.cwds.cals.Constants.RFA;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.RFA1bCollectionServiceBackedResource;
import gov.ca.cwds.cals.inject.RFA1bServiceBackedResource;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.RFA1bFormCollectionDTO;
import gov.ca.cwds.cals.web.rest.parameter.RFAApplicantAwareEntityUpdateParams;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAOtherAdultAwareEntityUpdateParams;
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
@Path(RFA_1A_FORMS + "/{" + RFA_1A_APPLICATION_ID + "}/" + RFA_1B_FORMS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RFA1bFormsResource {

  private TypedResourceDelegate<
      RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<RFA1bFormDTO>>
      resourceDelegate;
  private TypedResourceDelegate<Long, Request> collectionResourceDelegate;

  @Inject
  public RFA1bFormsResource(
      @RFA1bServiceBackedResource
          TypedResourceDelegate<
              RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<RFA1bFormDTO>>
          resourceDelegate,
      @RFA1bCollectionServiceBackedResource
          TypedResourceDelegate<Long, Request> collectionResourceDelegate) {
    this.resourceDelegate = resourceDelegate;
    this.collectionResourceDelegate = collectionResourceDelegate;
  }

  @UnitOfWork(CALSNS)
  @POST
  @Path("/" + RFA_1A_APPLICANTS + "/{" + RFA_1A_APPLICANT_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Creates and returns RFA 1B form for Applicant", response = RFA1bFormDTO.class)
  public Response createRFA1bFormForApplicant(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long applicationId,
      @PathParam(RFA_1A_APPLICANT_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICANT_ID, value = "The Applicant Id")
          Long applicantId,
      @ApiParam(required = true, name = RFA_1B_FORM, value = "The RFA-1B Form object")
      @Valid
          RFA1bFormDTO rfa1bForm) {
    return resourceDelegate.create(
        new RFAApplicantAwareEntityUpdateParams<>(applicationId, applicantId, rfa1bForm));
  }

  @UnitOfWork(CALSNS)
  @POST
  @Path("/" + RFA_1A_OTHER_ADULTS + "/{" + RFA_1A_OTHER_ADULT_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Creates and returns RFA 1B form for OtherAdult", response = RFA1bFormDTO.class)
  public Response createRFA1bFormForOtherAdult(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long applicationId,
      @PathParam(RFA_1A_OTHER_ADULT_ID)
      @ApiParam(required = true, name = RFA_1A_OTHER_ADULT_ID, value = "The OtherAdult Id")
          Long otherAdultId,
      @ApiParam(required = true, name = RFA_1B_FORM, value = "The RFA-1B Form object")
      @Valid
          RFA1bFormDTO rfa1bForm) {
    return resourceDelegate.create(
        new RFAOtherAdultAwareEntityUpdateParams<>(applicationId, otherAdultId, rfa1bForm));
  }

  @UnitOfWork(CALSNS)
  @PUT
  @Path("/{" + RFA_1B_FORM_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Updates and returns RFA 1B Form object", response = RFA1bFormDTO.class)
  public Response updateRFA1bForm(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(
          required = true,
          name = RFA_1A_APPLICATION_ID,
          value = "The RFA-1A Application Id"
      )
          Long applicationId,
      @PathParam(RFA_1B_FORM_ID)
      @ApiParam(
          required = true,
          name = RFA_1B_FORM_ID,
          value = "The RFA-1A Form Id"
      )
          Long rfa1BId,
      @ApiParam(required = true, name = RFA_1B_FORM, value = "The RFA-1B Form object")
      @Valid
          RFA1bFormDTO rfa1bFormDTO) {
    return resourceDelegate.update(new RFAExternalEntityGetParameterObject(applicationId, rfa1BId),
        new RFAExternalEntityUpdateParameterObject<>(applicationId, rfa1bFormDTO));
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/{" + RFA_1B_FORM_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns RFA 1B Form by RFA 1A id and RFA 1B id", response = RFA1bFormDTO.class)
  public Response getRFA1BFormById(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(
          required = true,
          name = RFA_1A_APPLICATION_ID,
          value = "The RFA-1A Application Id"
      )
          Long applicationId,
      @PathParam(RFA_1B_FORM_ID)
      @ApiParam(
          required = true,
          name = RFA_1B_FORM_ID,
          value = "The RFA 1B Form Id"
      )
          Long rfa1BFormId) {

    return resourceDelegate
        .get(new RFAExternalEntityGetParameterObject(applicationId, rfa1BFormId));
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
      value = "Returns RFA 1B Forms by Application Id",
      response = RFA1bFormCollectionDTO.class
  )
  public Response getRFA1bFormsByFormRFA1aFormId(
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
  @Path("/{" + RFA_1B_FORM_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found")
      }
  )
  @ApiOperation(value = "Deletes RFA 1B Form")
  public Response deleteRFA1bForm(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA 1A Form Id")
          Long applicationId,
      @PathParam(RFA_1B_FORM_ID)
      @ApiParam(
          required = true,
          name = RFA_1B_FORM_ID,
          value = "The RFA 1B Form Id"
      )
          Long rfa1BFormId) {
    return resourceDelegate
        .delete(new RFAExternalEntityGetParameterObject(applicationId, rfa1BFormId));
  }
}
