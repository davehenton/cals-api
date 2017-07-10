package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_APPLICATION_ID;
import static gov.ca.cwds.cals.Constants.API.QueryParams.EXPANDED;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_FORMS;
import static gov.ca.cwds.cals.Constants.RFA;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.RFA1aFormCollectionServiceBackedResource;
import gov.ca.cwds.cals.inject.RFA1aFormServiceBackedResource;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.RFA1aFormCollectionDTO;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aFormsParameterObject;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/** @author CWDS CALS API Team */
@Api(tags = {RFA})
@Path(RFA_1A_FORMS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RFA1aFormsResource {

  private ResourceDelegate resourceDelegate;
  private ResourceDelegate collectionResourceDelegate;

  @Inject
  public RFA1aFormsResource(
      @RFA1aFormServiceBackedResource ResourceDelegate resourceDelegate,
      @RFA1aFormCollectionServiceBackedResource ResourceDelegate collectionResourceDelegate) {
    this.resourceDelegate = resourceDelegate;
    this.collectionResourceDelegate = collectionResourceDelegate;
  }

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
  @ApiOperation(value = "Creates and returns RFA 1A Form", response = RFA1aFormDTO.class)
  public Response createApplicationForm(
      @ApiParam(name = "application", value = "The RFA-1A Application object") @Valid
          RFA1aFormDTO application) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    // TODO: remove this temporary fix to support older versions of CALS DS
    if (application == null) {
      application = new RFA1aFormDTO();
      application.setInitialApplication(true);
    }
    return resourceDelegate.create(application);
  }

  @UnitOfWork(CALSNS)
  @PUT
  @Path("/{" + RFA_1A_APPLICATION_ID + "}")
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
    return resourceDelegate.update(formId, formDTO);
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/{" + RFA_1A_APPLICATION_ID + "}")
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
          boolean expanded) {
    return resourceDelegate.get(new RFA1aFormsParameterObject(formId, expanded));
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
      @ApiParam(name = EXPANDED, value = "Use 'true' to get forms with all parts of form included")
          boolean expanded) {
    return collectionResourceDelegate.get(expanded);
  }
}
