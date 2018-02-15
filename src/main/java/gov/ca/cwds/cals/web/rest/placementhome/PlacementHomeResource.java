package gov.ca.cwds.cals.web.rest.placementhome;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cals.service.dto.formsapi.FormInstanceDTO;
import gov.ca.cwds.cals.service.dto.formsapi.FormsPackageDTO;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static gov.ca.cwds.cals.Constants.API.PLACEMENTHOMES;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;


@Api(
        value = PLACEMENTHOMES,
        tags = {PLACEMENTHOMES}
)
@Path(PLACEMENTHOMES)
@Produces(MediaType.APPLICATION_JSON)
public class PlacementHomeResource {

  private static final Long PACKAGE_ID = 123L;
  private static final String PH_ADDRESS = "PH_page_ID_Address";
  private static final String PH_EMERGENCY_CONTACT = "PH_page_ID_Emergency_Contact";
  private static final String PH_END_DATE = "PH_page_ID_End_Date";
  private static final String PH_COMMON_INFO = "PH_page_ID_common_info";
  private static final String SCHEMA_VERSION = "1";
  private static final Random random = new Random();

  private static final String ADDRESS_EXAMPLE = "{\n" +
          "  \"street_no\": \"ABCDEFGHIJKLMNOPQRSTUVWX\",\n" +
          "  \"street_name\": \"ABCDEFGHIJKLMNOPQRSTUVWXYZAB\",\n" +
          "  \"city\": \"ABCDEFGHIJKLMNOPQRSTUVWXYZAB\",\n" +
          "  \"state\": {\n" +
          "    \"id\": \"AB\",\n" +
          "    \"value\": \"ABCDEFGHIJKLMNOPQR\"\n" +
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
          "  \"name\": \"ABCDEFGHIJKLMNOPQRSTUVWX\",\n" +
          "  \"primary_scp\": \"ABCDEFGHIJKLMNOPQRSTUVWXYZAB\",\n" +
          "  \"fac_type\": {\n" +
          "    \"id\": 677,\n" +
          "    \"value\": \"ABCDEFGHIJKLMNO\"\n" +
          "  },\n" +
          "  \"operated_by\": {\n" +
          "    \"id\": 936,\n" +
          "    \"value\": \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\"\n" +
          "  },\n" +
          "  \"sub_type\": {\n" +
          "    \"id\": 870,\n" +
          "    \"value\": \"ABCDEF\"\n" +
          "  },\n" +
          "  \"license_number\": \"ABCDEFGHIJKLMNOP\",\n" +
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
          "  \"name\": \"ABCDEFGHIJKLMNOPQRSTUVWX\",\n" +
          "  \"phone\": {\n" +
          "    \"phone\": \"9995555555\",\n" +
          "    \"ext\": 677\n" +
          "  },\n" +
          "  \"alt_phone\": {\n" +
          "    \"phone\": \"9995555555\",\n" +
          "    \"ext\": 736\n" +
          "  },\n" +
          "  \"email\": \"TRTWETW@MAIL.COM\",\n" +
          "  \"street_no\": \"ABCDEFGHIJKLMNO\",\n" +
          "  \"street_name\": \"ABCDEFGHIJK\",\n" +
          "  \"city\": \"ABCDEF\",\n" +
          "  \"state\": {\n" +
          "    \"id\": \"AB\",\n" +
          "    \"value\": \"ABCDEFGHIJKLMNOPQRSTUVWX\"\n" +
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
    FormsPackageDTO responce = getMockedPackage(id);
    return Response.status(responce == null ? Response.Status.NOT_FOUND : Response.Status.OK).entity(responce).build();
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

}
