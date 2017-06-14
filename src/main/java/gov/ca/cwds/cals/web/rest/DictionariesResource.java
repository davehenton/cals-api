package gov.ca.cwds.cals.web.rest;

import static gov.ca.cwds.cals.Constants.API.DICTIONARIES;
import static gov.ca.cwds.cals.Constants.API.PathParams.DICTIONARY_TYPE;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.DictionariesServiceBackendResource;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/** @author CWDS CALS API Team */
@Api(
  value = DICTIONARIES,
  tags = {DICTIONARIES}
)
@Path(DICTIONARIES)
@Produces(MediaType.APPLICATION_JSON)
public class DictionariesResource {

  private ResourceDelegate dictionariesResourceDeledate;

  @Inject
  public DictionariesResource(
      @DictionariesServiceBackendResource ResourceDelegate dictionariesResourceDeledate) {
    this.dictionariesResourceDeledate = dictionariesResourceDeledate;
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/{" + DICTIONARY_TYPE + "}")
  @Timed
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")
    }
  )
  @ApiOperation(value = "Returns Dictionary by Type", response = CollectionDTO.class)
  public Response getDictionaryByType(
      @PathParam(DICTIONARY_TYPE)
          @ApiParam(
            required = true,
            name = DICTIONARY_TYPE,
            value = "The Type of Dictionary",
            allowableValues = "age-group-type, language-type"
          )
          String type) {
    return dictionariesResourceDeledate.get(type);
  }
}
