package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.API.LIC_198B_FORM;
import static gov.ca.cwds.cals.Constants.API.LIC_198B_FORMS;
import static gov.ca.cwds.cals.Constants.API.PathParams.LIC_198B_FORM_ID;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_APPLICANT_ID;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_APPLICATION_ID;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_OTHER_ADULT_ID;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1B_FORM;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_APPLICANTS;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_FORMS;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_OTHER_ADULTS;
import static gov.ca.cwds.cals.Constants.RFA;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.LIC198bCollectionServiceBackedResource;
import gov.ca.cwds.cals.inject.LIC198bServiceBackedResource;
import gov.ca.cwds.cals.service.dto.rfa.collection.LIC198bFormCollectionDTO;
import gov.ca.cwds.cals.service.dto.rfa.lic198b.LIC198bFormDTO;
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
@Path(RFA_1A_FORMS + "/{" + RFA_1A_APPLICATION_ID + "}/" + LIC_198B_FORMS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LIC198bFormsResource {


  private TypedResourceDelegate<
      RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<LIC198bFormDTO>>
      resourceDelegate;
  private TypedResourceDelegate<Long, Request> collectionResourceDelegate;

  @Inject
  public LIC198bFormsResource(
      @LIC198bServiceBackedResource
          TypedResourceDelegate<
              RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<LIC198bFormDTO>>
          resourceDelegate,
      @LIC198bCollectionServiceBackedResource
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
  @ApiOperation(value = "Creates and returns LIC-198b form for Applicant", response = LIC198bFormDTO.class)
  public Response createLIC198bFormForApplicant(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long applicationId,
      @PathParam(RFA_1A_APPLICANT_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICANT_ID, value = "The Applicant Id")
          Long applicantId,
      @ApiParam(required = true, name = RFA_1B_FORM, value = "The LIC-198b Form object")
      @Valid
          LIC198bFormDTO lic198bForm) {
    return resourceDelegate.create(
        new RFAApplicantAwareEntityUpdateParams<>(applicationId, applicantId, lic198bForm));
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
  @ApiOperation(value = "Creates and returns LIC-198b form for OtherAdult", response = LIC198bFormDTO.class)
  public Response createLIC198bFormForOtherAdult(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long applicationId,
      @PathParam(RFA_1A_OTHER_ADULT_ID)
      @ApiParam(required = true, name = RFA_1A_OTHER_ADULT_ID, value = "The OtherAdult Id")
          Long otherAdultId,
      @ApiParam(required = true, name = RFA_1B_FORM, value = "The LIC-198b Form object")
      @Valid
          LIC198bFormDTO lic198bForm) {
    return resourceDelegate.create(
        new RFAOtherAdultAwareEntityUpdateParams<>(applicationId, otherAdultId, lic198bForm));
  }

  @UnitOfWork(CALSNS)
  @PUT
  @Path("/{" + LIC_198B_FORM_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Updates and returns LIC-198b Form object", response = LIC198bFormDTO.class)
  public Response updateLIC198bForm(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(
          required = true,
          name = RFA_1A_APPLICATION_ID,
          value = "The RFA-1A Application Id"
      )
          Long applicationId,
      @PathParam(LIC_198B_FORM_ID)
      @ApiParam(
          required = true,
          name = LIC_198B_FORM_ID,
          value = "The LIC-198b Form Id"
      )
          Long lic198BId,
      @ApiParam(required = true, name = LIC_198B_FORM, value = "The LIC-198b Form object")
      @Valid
          LIC198bFormDTO lic198bFormDTO) {
    return resourceDelegate
        .update(new RFAExternalEntityGetParameterObject(applicationId, lic198BId),
            new RFAExternalEntityUpdateParameterObject<>(applicationId, lic198bFormDTO));
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/{" + LIC_198B_FORM_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns LIC-198b Form by RFA-1A id and LIC-198b id", response = LIC198bFormDTO.class)
  public Response getLIC198bFormById(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(
          required = true,
          name = RFA_1A_APPLICATION_ID,
          value = "The RFA-1A Application Id"
      )
          Long applicationId,
      @PathParam(LIC_198B_FORM_ID)
      @ApiParam(
          required = true,
          name = LIC_198B_FORM_ID,
          value = "The LIC-198b Form Id"
      )
          Long lic198bFormId) {

    return resourceDelegate
        .get(new RFAExternalEntityGetParameterObject(applicationId, lic198bFormId));
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
      value = "Returns LIC-198b Forms by Application Id",
      response = LIC198bFormCollectionDTO.class
  )
  public Response getLIC198bFormsByFormRFA1aFormId(
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
  @Path("/{" + LIC_198B_FORM_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found")
      }
  )
  @ApiOperation(value = "Deletes LIC-198b Form")
  public Response deleteLIC198bForm(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA 1A Form Id")
          Long applicationId,
      @PathParam(LIC_198B_FORM_ID)
      @ApiParam(
          required = true,
          name = LIC_198B_FORM_ID,
          value = "The LIC-198b Form Id"
      )
          Long lic198bFormId) {
    return resourceDelegate
        .delete(new RFAExternalEntityGetParameterObject(applicationId, lic198bFormId));
  }
}
