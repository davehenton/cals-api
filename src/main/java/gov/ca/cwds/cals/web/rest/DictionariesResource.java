package gov.ca.cwds.cals.web.rest;

import static gov.ca.cwds.cals.Constants.API.DICTIONARIES;
import static gov.ca.cwds.cals.Constants.DictionaryType.ADDRESS_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.ADDRESS_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.AGE_GROUP_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.AGE_GROUP_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.APPLICANT_RELATIONSHIP_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.APPLICANT_RELATIONSHIP_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.EDUCATION_LEVEL_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.EDUCATION_LEVEL_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.ETHNICITY_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.ETHNICITY_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.GENDER_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.GENDER_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.INCOME_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.INCOME_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.LANGUAGE_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.LANGUAGE_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.NAME_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.NAME_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.PHONE_NUMBER_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.PHONE_NUMBER_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.RACE_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.RACE_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.RESIDENCE_OWNERSHIP_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.RESIDENCE_OWNERSHIP_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.SIBLING_GROUP_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.SIBLING_GROUP_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.STATE_TYPE;
import static gov.ca.cwds.cals.Constants.DictionaryType.STATE_TYPE_PATH;
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

  private ResourceDelegate dictionariesResourceDelegate;

  @Inject
  public DictionariesResource(
      @DictionariesServiceBackendResource ResourceDelegate dictionariesResourceDelegate) {
    this.dictionariesResourceDelegate = dictionariesResourceDelegate;
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
    return dictionariesResourceDelegate.get(AGE_GROUP_TYPE);
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
  @ApiOperation(value = "Returns Languages", response = CollectionDTO.class)
  public Response getDictionaryLanguageTypes() {
    return dictionariesResourceDelegate.get(LANGUAGE_TYPE);
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
  @ApiOperation(value = "Returns Genders", response = CollectionDTO.class)
  public Response getDictionaryGenderTypes() {
    return dictionariesResourceDelegate.get(GENDER_TYPE);
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
    return dictionariesResourceDelegate.get(NAME_TYPE);
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/" + EDUCATION_LEVEL_TYPE_PATH)
  @Timed
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")
    }
  )
  @ApiOperation(value = "Returns Education Level Types", response = CollectionDTO.class)
  public Response getDictionaryEducationLevelType() {
    return dictionariesResourceDelegate.get(EDUCATION_LEVEL_TYPE);
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/" + ETHNICITY_TYPE_PATH)
  @Timed
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")
    }
  )
  @ApiOperation(value = "Returns Ethnicity Types", response = CollectionDTO.class)
  public Response getDictionaryEthnicityType() {
    return dictionariesResourceDelegate.get(ETHNICITY_TYPE);
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/" + RACE_TYPE_PATH)
  @Timed
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")
    }
  )
  @ApiOperation(value = "Returns Races", response = CollectionDTO.class)
  public Response getRaceType() {
    return dictionariesResourceDelegate.get(RACE_TYPE);
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/" + INCOME_TYPE_PATH)
  @Timed
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")
    }
  )
  @ApiOperation(value = "Returns Income Types", response = CollectionDTO.class)
  public Response getIncomeType() {
    return dictionariesResourceDelegate.get(INCOME_TYPE);
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/" + PHONE_NUMBER_TYPE_PATH)
  @Timed
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")
    }
  )
  @ApiOperation(value = "Returns Phone Number Types", response = CollectionDTO.class)
  public Response getPhoneNumberTypes() {
    return dictionariesResourceDelegate.get(PHONE_NUMBER_TYPE);
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/" + ADDRESS_TYPE_PATH)
  @Timed
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")
    }
  )
  @ApiOperation(value = "Returns Address Types", response = CollectionDTO.class)
  public Response getAddressTypes() {
    return dictionariesResourceDelegate.get(ADDRESS_TYPE);
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/" + SIBLING_GROUP_TYPE_PATH)
  @Timed
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")
    }
  )
  @ApiOperation(value = "Returns Sibling Group Types", response = CollectionDTO.class)
  public Response getSiblingGroupTypes() {
    return dictionariesResourceDelegate.get(SIBLING_GROUP_TYPE);
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/" + STATE_TYPE_PATH)
  @Timed
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")
    }
  )
  @ApiOperation(value = "Returns States", response = CollectionDTO.class)
  public Response getStateTypes() {
    return dictionariesResourceDelegate.get(STATE_TYPE);
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/" + RESIDENCE_OWNERSHIP_TYPE_PATH)
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns Residence Ownership Types", response = CollectionDTO.class)
  public Response getResidenceOwnershipTypes() {
    return dictionariesResourceDelegate.get(RESIDENCE_OWNERSHIP_TYPE);
  }

  @UnitOfWork(CALSNS)
  @GET
  @Path("/" + APPLICANT_RELATIONSHIP_TYPE_PATH)
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns Residence Ownership Types", response = CollectionDTO.class)
  public Response getApplicantRelationshipTypes() {
    return dictionariesResourceDelegate.get(APPLICANT_RELATIONSHIP_TYPE);
  }

}
