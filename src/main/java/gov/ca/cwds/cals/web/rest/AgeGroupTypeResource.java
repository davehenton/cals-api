package gov.ca.cwds.cals.web.rest;

import static gov.ca.cwds.cals.Constants.API.AGE_GROUP_TYPES;
import static gov.ca.cwds.cals.Constants.API.DICTIONARIES;
import static gov.ca.cwds.cals.Constants.API.RFA;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.AgeGroupTypesServiceBackendResource;
import gov.ca.cwds.cals.service.dto.AgeGroupTypesDTO;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/** @author CWDS CALS API Team */
@Api(value = AGE_GROUP_TYPES, tags = {DICTIONARIES})
@Path(DICTIONARIES + "/" + RFA + "/"+ AGE_GROUP_TYPES)
@Produces(MediaType.APPLICATION_JSON)
public class AgeGroupTypeResource {

  private ResourceDelegate ageGroupTypesResourceDelegate;

  @Inject
  public AgeGroupTypeResource(@AgeGroupTypesServiceBackendResource ResourceDelegate resourceDelegate) {
    ageGroupTypesResourceDelegate = resourceDelegate;
  }

  @UnitOfWork(CALSNS)
  @GET
  @Timed
  @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")})
  @ApiOperation(value = "Returns Age Group Types", response = AgeGroupTypesDTO.class)
  public Response getAgeGroupTypes() {
    return ageGroupTypesResourceDelegate.get(null);
  }

}
