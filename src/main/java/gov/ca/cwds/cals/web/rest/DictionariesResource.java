package gov.ca.cwds.cals.web.rest;

import static gov.ca.cwds.cals.Constants.API.DICTIONARIES;
import static gov.ca.cwds.cals.Constants.DictionaryType.AGE_GROUP_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.AGE_GROUP_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.GENDER_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.GENDER_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.LANGUAGE_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.LANGUAGE_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.NAME_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.NAME_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.DictionariesServiceBackendResource;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
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
  @Path("/" + AGE_GROUP_TYPE_PATH)
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns Age Group Types", response = CollectionDTO.class)
  public Response getDictionaryAgeGroupTypes() {
    return dictionariesResourceDeledate.get(AGE_GROUP_TYPE);
  }


  @UnitOfWork(CALSNS)
  @GET
  @Path("/" + LANGUAGE_TYPE_PATH)
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns Language Types", response = CollectionDTO.class)
  public Response getDictionaryLanguageTypes() {
    return dictionariesResourceDeledate.get(LANGUAGE_TYPE);
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/" + GENDER_TYPE_PATH)
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns Gender Types", response = CollectionDTO.class)
  public Response getDictionaryGenderTypes() {
    return dictionariesResourceDeledate.get(GENDER_TYPE);
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/" + NAME_TYPE_PATH)
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns Name Types", response = CollectionDTO.class)
  public Response getDictionaryNameTypes() {
    return dictionariesResourceDeledate.get(NAME_TYPE);
  }

}
