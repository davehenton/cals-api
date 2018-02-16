package gov.ca.cwds.cals.web.rest.placementhome;

import static gov.ca.cwds.cals.Constants.API.PLACEMENTHOMES;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.formsapi.FormInstanceDTO;
import gov.ca.cwds.cals.service.dto.formsapi.FormsPackageDTO;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.net.URI;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Api(
        value = PLACEMENTHOMES,
        tags = {PLACEMENTHOMES}
)
@Path(PLACEMENTHOMES)
@Produces(MediaType.APPLICATION_JSON)
public class PlacementHomeResource {

  @Inject
  @Named("formsAPI.uri")
  private String formsApiURI;

  private static final Logger LOG = LoggerFactory.getLogger(PlacementHomeResource.class);
  
  @Inject
  private Client client;

  private static final Long PACKAGE_ID = 123L;
  private static final String PH_ADDRESS = "PH_page_ID_Address";
  private static final String PH_EMERGENCY_CONTACT = "PH_page_ID_Emergency_Contact";
  private static final String PH_END_DATE = "PH_page_ID_End_Date";
  private static final String PH_COMMON_INFO = "PH_page_ID_common_info";
  private static final String SCHEMA_VERSION = "1";
  private static final SecureRandom random = new SecureRandom();

  private static final String ADDRESS_EXAMPLE = "{\n" +
          "  \"street_no\": \"123\",\n" +
          "  \"street_name\": \"Maple POC St\",\n" +
          "  \"city\": \"Sacramento\",\n" +
          "  \"state\": {\n" +
          "    \"id\": \"AB\",\n" +
          "    \"value\": \"California\"\n" +
          "  },\n" +
          "  \"zip\": \"95833\",\n" +
          "  \"zip_ext\": \"1234\",\n" +
          "  \"geo_region\": \"ABCDEFGHIJKLMNO\",\n" +
          "  \"county\": {\n" +
          "    \"id\": 15,\n" +
          "    \"value\": \"ABCDEFGHIJK\"\n" +
          "  },\n" +
          "  \"foreign_country\": {\n" +
          "    \"id\": 749,\n" +
          "    \"value\": \"ABCDEFGH\"\n" +
          "  },\n" +
          "  \"foreign_zip\": \"1234567890\",\n" +
          "  \"foreign_addr_desc\": \"ABCDEFGHIJKLM\",\n" +
          "  \"comment\": \"ABCDEF\"\n" +
          "}";
  private static final String COMMON_INFO_EXAMPLE = "{\n" +
          "  \"name\": \"John Johjson\",\n" +
          "  \"primary_scp\": \"Mary SCP Allison\",\n" +
          "  \"fac_type\": {\n" +
          "    \"id\": 677,\n" +
          "    \"value\": \"TBD\"\n" +
          "  },\n" +
          "  \"operated_by\": {\n" +
          "    \"id\": 936,\n" +
          "    \"value\": \"John Johjson\"\n" +
          "  },\n" +
          "  \"sub_type\": {\n" +
          "    \"id\": 870,\n" +
          "    \"value\": \"ABCDEF\"\n" +
          "  },\n" +
          "  \"license_number\": \"N3234342\",\n" +
          "  \"age_range\": {\n" +
          "    \"from\": 9,\n" +
          "    \"to\": 21\n" +
          "  },\n" +
          "  \"capacity\": {\n" +
          "    \"placements\": 553,\n" +
          "    \"beds_available\": 404,\n" +
          "    \"adj_capacity\": 260\n" +
          "  },\n" +
          "  \"prim_contact\": {\n" +
          "    \"phone\": {\n" +
          "      \"phone\": \"8887777777\",\n" +
          "      \"ext\": 583\n" +
          "    },\n" +
          "    \"fax\": \"7776665544\"\n" +
          "  },\n" +
          "  \"backup_contact\": {\n" +
          "    \"name\": \"ABCDEFGHIJKLMNOPQRSTUVWX\",\n" +
          "    \"phone\": {\n" +
          "      \"phone\": \"0009998877\",\n" +
          "      \"ext\": 23\n" +
          "    }\n" +
          "  },\n" +
          "  \"options\": {\n" +
          "    \"transitional\": true,\n" +
          "    \"on_hold\": true,\n" +
          "    \"inactivated\": false,\n" +
          "    \"at_capacity\": true,\n" +
          "    \"adoption_only\": false\n" +
          "  }\n" +
          "}";

  private static final String EMERGENCY_CONTACT_EXAMPLE = "{\n" +
          "  \"name\": \"Mary Allison\",\n" +
          "  \"phone\": {\n" +
          "    \"phone\": \"9995555555\",\n" +
          "    \"ext\": 677\n" +
          "  },\n" +
          "  \"alt_phone\": {\n" +
          "    \"phone\": \"9995555555\",\n" +
          "    \"ext\": 736\n" +
          "  },\n" +
          "  \"email\": \"TRTWETW@MAIL.COM\",\n" +
          "  \"street_no\": \"879\",\n" +
          "  \"street_name\": \"Alstead\",\n" +
          "  \"city\": \"Sacramento\",\n" +
          "  \"state\": {\n" +
          "    \"id\": \"AB\",\n" +
          "    \"value\": \"California\"\n" +
          "  },\n" +
          "  \"zip\": \"99999\",\n" +
          "  \"zip_ext\": \"1234\",\n" +
          "  \"foreign_country\": {\n" +
          "    \"id\": 320,\n" +
          "    \"value\": \"ABCDEFGHIJKLMNOPQRSTUV\"\n" +
          "  },\n" +
          "  \"foreign_zip\": \"1234567890\",\n" +
          "  \"foreign_addr_desc\": \"ABCDEFGHIJKLMNOPQRST\"\n" +
          "}";

  private static final String END_DATE_EXAMPLE = "{\n" +
          "  \"end_date\": \"2019-07-14\",\n" +
          "  \"reason_type\": {\n" +
          "    \"id\": 188,\n" +
          "    \"value\": \"ABCDEFGHIJKLMNOPQRSTUV\"\n" +
          "  },\n" +
          "  \"comments\": \"ABCDEFGHIJKLMNO\"\n" +
          "}";


  @UnitOfWork(CALSNS)
  @GET
  @Path("/{id}")
  @Timed
  @ApiResponses(
          value = {
                  @ApiResponse(code = 401, message = "Not Authorized"),
                  @ApiResponse(code = 404, message = "Not found"),
          }
  )
  @ApiOperation(value = "Returns PlacementHome in Package representation", response = FormsPackageDTO.class)
  public Response getPlacementhome(
          @PathParam("id")
          @ApiParam(required = true, name = "id", value = "The id of the Placementhome to find", example = "AaQshqm0Mb") final String id
  ) {

    FormsPackageDTO packageDTO = getInprogressPackage(id);
    if (packageDTO == null) {
      packageDTO = getMockedPackage(id);
    }

    FormsPackageDTO responce = getMockedPackage(id);
    return Response.status(responce == null ? Response.Status.NOT_FOUND : Response.Status.OK).entity(responce).build();
  }


  @POST
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 201, message = "Created"),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Saves and returns Forms Package for Placement Home", response = FormsPackageDTO.class)
  public Response saveFormsPackage(
      @ApiParam(name = "formsPackage", value = "The FormsPackageDTO object") @Valid
          FormsPackageDTO formsPackageDTO) {
    if (formsPackageDTO.getId() == null){
      return postFormsPackage(formsPackageDTO);
    } else {
      return putFormsPackage(formsPackageDTO);
    }
  }




  public FormsPackageDTO getMockedPackage(String id) {
    FormsPackageDTO result = new FormsPackageDTO();
    result.setId(PACKAGE_ID);
    result.setExternalEntityId(id);
    result.setDescription("Some Description");
    result.setFormInstances(getformInstances());
    return result;
  }


  private static List<FormInstanceDTO> getformInstances() {
    List<FormInstanceDTO> formInstanceDTOList = new ArrayList<>();
    formInstanceDTOList.add(getFormInstance(PH_ADDRESS));
    formInstanceDTOList.add(getFormInstance(PH_COMMON_INFO));
    formInstanceDTOList.add(getFormInstance(PH_EMERGENCY_CONTACT));
    formInstanceDTOList.add(getFormInstance(PH_END_DATE));

    return formInstanceDTOList;
  }

  private static FormInstanceDTO getFormInstance(String formName) {

    FormInstanceDTO formInstance = new FormInstanceDTO();
    formInstance.setName(formName);
    formInstance.setSchemaVersion(SCHEMA_VERSION);
    formInstance.setPackageId(PACKAGE_ID);
    formInstance.setFormId(String.valueOf(random.nextInt()));
    formInstance.setContent(getContent(formName));
    return formInstance;
  }

  private static JsonNode getContent(String formName) {
    ObjectMapper mapper = new ObjectMapper();
    String json;
    switch (formName) {
      case PH_ADDRESS:
        json = ADDRESS_EXAMPLE;
        break;
      case PH_COMMON_INFO:
        json = COMMON_INFO_EXAMPLE;
        break;
      case PH_EMERGENCY_CONTACT:
        json = EMERGENCY_CONTACT_EXAMPLE;
        break;
      case PH_END_DATE:
        json = END_DATE_EXAMPLE;
        break;
      default:
        return null;
    }
    try {
      return mapper.readTree(json);
    } catch (IOException e) {
      return null;
    }
  }


  private FormsPackageDTO getInprogressPackage(String id) {
    URI uri = UriBuilder.fromUri(formsApiURI)
        .path("/forms/packages")
        .queryParam("extId", id)
        .build();
    Response response = client.target(uri).request().get();
    FormsPackageDTO packageDTO = null;
    if (response.getStatus() == 200) {
      packageDTO = response.readEntity(FormsPackageDTO.class);
    } else if (response.getStatus() == 500) {
      throw new RuntimeException(response.getStatusInfo().getReasonPhrase());
    } else {
      LOG.error("Problems during formsPackage retrieving ", new Exception(response.getStatus() + " " + response.getStatusInfo().getReasonPhrase()));
    }
    return packageDTO;
  }


  private Response postFormsPackage(FormsPackageDTO packageDTO) {
    UriBuilder uriBuilder = UriBuilder.fromUri(formsApiURI);
    uriBuilder.path("/forms/packages");
    URI uri = uriBuilder.build();
    return client.target(uri).request()
        .post(Entity.entity(packageDTO, MediaType.APPLICATION_JSON_TYPE));
  }

  private Response putFormsPackage(FormsPackageDTO packageDTO) {
    assert (null == packageDTO.getId());
    UriBuilder uriBuilder = UriBuilder.fromUri(formsApiURI);
    uriBuilder.path("/forms/packages");
    uriBuilder.path("/" + packageDTO.getId());
    URI uri = uriBuilder.build();
    return client.target(uri).request()
        .put(Entity.entity(packageDTO, MediaType.APPLICATION_JSON_TYPE));
  }

}
