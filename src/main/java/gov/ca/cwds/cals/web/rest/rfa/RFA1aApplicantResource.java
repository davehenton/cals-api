package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_FORM_ID;
import static gov.ca.cwds.cals.Constants.API.RFA;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_APPLICANTS;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_FORMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.RFA1aApplicantServiceBackendResource;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aApplicantParameterObject;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author CWDS CALS API Team
 */
@Api(tags = {RFA})
@Path(RFA_1A_FORMS + "/{" + RFA_1A_FORM_ID + "}/" + RFA_1A_APPLICANTS)
@Produces(MediaType.APPLICATION_JSON)
public class RFA1aApplicantResource {

  private ResourceDelegate resourceDelegate;

  @Inject
  public RFA1aApplicantResource(
      @RFA1aApplicantServiceBackendResource ResourceDelegate resourceDelegate) {
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
  @ApiOperation(value = "Creates and returns RFA 1a Applicant", response = Applicant.class)
  public Response createApplicant(
      @PathParam(RFA_1A_FORM_ID)
      @ApiParam(required = true, name = RFA_1A_FORM_ID, value = "The RFA-1a Form Id")
          Long formId) {
    return resourceDelegate.create(new RFA1aApplicantParameterObject(formId, null));
  }
}
