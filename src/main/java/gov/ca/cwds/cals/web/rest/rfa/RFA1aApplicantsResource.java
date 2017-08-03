package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_APPLICANT;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_APPLICANT_ID;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_APPLICATION_ID;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_APPLICANTS;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_FORMS;
import static gov.ca.cwds.cals.Constants.RFA;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;
import static gov.ca.cwds.cals.service.validation.business.configuration.ValidationConfigurationRegistry.APPLICANTS_DUPLICATE_NAME_POST;
import static gov.ca.cwds.cals.service.validation.business.configuration.ValidationConfigurationRegistry.APPLICANTS_DUPLICATE_NAME_PUT;
import static gov.ca.cwds.cals.service.validation.business.configuration.ValidationConfigurationRegistry.APPLICANT_VALIDATION;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.RFA1aApplicantServiceBackedResource;
import gov.ca.cwds.cals.inject.RFA1aApplicantsCollectionServiceBackedResource;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.ApplicantCollectionDTO;
import gov.ca.cwds.cals.service.validation.business.BusinessValidationFieldConstraint;
import gov.ca.cwds.cals.service.validation.business.BusinessValidationParamsConstraint;
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
@Path(RFA_1A_FORMS + "/{" + RFA_1A_APPLICATION_ID + "}/" + RFA_1A_APPLICANTS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RFA1aApplicantsResource {

  private TypedResourceDelegate<RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<ApplicantDTO>>
      resourceDelegate;
  private TypedResourceDelegate<Long, Request> collectionResourceDelegate;

  @Inject
  public RFA1aApplicantsResource(
      @RFA1aApplicantServiceBackedResource TypedResourceDelegate<
          RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<ApplicantDTO>> resourceDelegate,
      @RFA1aApplicantsCollectionServiceBackedResource
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
  @ApiOperation(value = "Creates and returns RFA 1A Applicant", response = ApplicantDTO.class)
  @BusinessValidationParamsConstraint(
      configuration = APPLICANTS_DUPLICATE_NAME_POST
  )
  public Response createApplicant(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId,
      @ApiParam(required = true, name = RFA_1A_APPLICANT, value = "The RFA-1A Applicant object")
      @Valid
      @BusinessValidationFieldConstraint(configuration = APPLICANT_VALIDATION)
          ApplicantDTO applicant) {
    return resourceDelegate.create(new RFAExternalEntityUpdateParameterObject<>(formId, applicant));
  }

  @UnitOfWork(CALSNS)
  @PUT
  @Path("/{" + RFA_1A_APPLICANT_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Update and returns RFA 1A Applicant", response = ApplicantDTO.class)
  @BusinessValidationParamsConstraint(
      configuration = APPLICANTS_DUPLICATE_NAME_PUT
  )
  public Response updateApplicant(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId,
      @PathParam(RFA_1A_APPLICANT_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICANT_ID, value = "The RFA-1A Applicant Id")
          Long applicantId,
      @ApiParam(required = true, name = RFA_1A_APPLICANT, value = "The RFA-1A Applicant object")
      @Valid
      @BusinessValidationFieldConstraint(configuration = APPLICANT_VALIDATION)
          ApplicantDTO applicant) {
    return resourceDelegate.update(
        new RFAExternalEntityGetParameterObject(formId, applicantId),
        new RFAExternalEntityUpdateParameterObject<>(formId, applicant));
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/{" + RFA_1A_APPLICANT_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns RFA 1A Form's Applicant by Id", response = ApplicantDTO.class)
  public Response getApplicantById(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId,
      @PathParam(RFA_1A_APPLICANT_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICANT_ID, value = "The RFA-1A Applicant Id")
          Long applicantId) {

    return resourceDelegate.get(
        new RFAExternalEntityGetParameterObject(formId, applicantId));
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
      value = "Returns RFA 1A Form's Applicants by Form Id",
      response = ApplicantCollectionDTO.class
  )
  public Response getApplicantsByFormId(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId) {
    return collectionResourceDelegate
        .get(formId);
  }

  @UnitOfWork(CALSNS)
  @DELETE
  @Path("/{" + RFA_1A_APPLICANT_ID + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found")
      }
  )
  @ApiOperation(value = "Delete RFA 1A Form's Applicant by Id")
  public Response deleteApplicant(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId,
      @PathParam(RFA_1A_APPLICANT_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICANT_ID, value = "The RFA-1A Applicant Id")
          Long applicantId) {
    return resourceDelegate.delete(
        new RFAExternalEntityGetParameterObject(formId, applicantId));
  }
}
