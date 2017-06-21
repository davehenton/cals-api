package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_FORM_ID;
import static gov.ca.cwds.cals.Constants.API.RESIDENCE;
import static gov.ca.cwds.cals.Constants.API.RFA;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_FORMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import gov.ca.cwds.cals.inject.ResidenceServiceBackedResource;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Residence;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author CWDS CALS API Team.
 */
@Api(tags = {RFA})
@Path(RFA_1A_FORMS + "{" + RFA_1A_FORM_ID + "}" + RESIDENCE)
@Produces(MediaType.APPLICATION_JSON)
public class ResidenceResource {
  private ResourceDelegate resourceDelegate;

  public ResidenceResource(@ResidenceServiceBackedResource ResourceDelegate resourceDelegate) {
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
  @ApiOperation(value = "Returns Residence by RFA-1a Form Id", response = Residence.class)
  public Response getResidence(
      @PathParam(RFA_1A_FORM_ID)
      @ApiParam(required = true, name = RFA_1A_FORM_ID, value = "The RFA-1a Form Id")
          Long formId) {
    return resourceDelegate.get(formId);
  }

  @UnitOfWork(CALSNS)
  @PUT
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Update Residence in RFA 1a Form", response = Residence.class)
  public Response updateResidence(
      @PathParam(RFA_1A_FORM_ID)
      @ApiParam(required = true, name = RFA_1A_FORM_ID, value = "The RFA-1a Form Id")
          Long formId) {
    return resourceDelegate.create(null);
  }

}
