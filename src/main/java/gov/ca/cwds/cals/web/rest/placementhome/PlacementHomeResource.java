package gov.ca.cwds.cals.web.rest.placementhome;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FacilityChildServiceBackedResource;
import gov.ca.cwds.cals.inject.PlacementHomeServiceBackendResource;
import gov.ca.cwds.cals.service.dto.formsapi.FormInstanceDTO;
import gov.ca.cwds.cals.service.dto.formsapi.FormsPackageDTO;
import gov.ca.cwds.cals.service.dto.placementhome.identification.EmergencyContactDTO;
import gov.ca.cwds.cals.service.mapper.EmergencyContactMapper;
import gov.ca.cwds.cms.data.access.dao.EmergencyContactDetailDao;
import gov.ca.cwds.cms.data.access.service.PlacementHomeService;
import gov.ca.cwds.data.legacy.cms.entity.EmergencyContactDetail;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
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

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gov.ca.cwds.cals.Constants.API.PLACEMENTHOMES;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;


@Api(
        value = PLACEMENTHOMES,
        tags = {PLACEMENTHOMES}
)
@Path(PLACEMENTHOMES)
@Produces(MediaType.APPLICATION_JSON)
public class PlacementHomeResource {


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
    return placementHomeResource.get(id);
  }



}
