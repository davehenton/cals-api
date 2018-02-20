package gov.ca.cwds.cals.web.rest.placementhome;

import static gov.ca.cwds.cals.Constants.API.PLACEMENTHOMES;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.PlacementHomeServiceBackendResource;
import gov.ca.cwds.cals.service.dto.formsapi.FormsPackageDTO;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
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

  @Inject
  @PlacementHomeServiceBackendResource
  private ResourceDelegate placementHomeResource;


  @UnitOfWork(CMS)
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

    FormsPackageDTO responce = getInprogressPackage(id);
    if (responce != null) {
      return Response.status(Response.Status.OK).entity(responce).build();
    }
    return placementHomeResource.get(id);
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
