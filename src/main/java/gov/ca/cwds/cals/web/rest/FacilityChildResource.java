package gov.ca.cwds.cals.web.rest;

import static gov.ca.cwds.cals.Constants.API.CHILDREN;
import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.Constants.API.PathParams.CHILD_ID;
import static gov.ca.cwds.cals.Constants.API.PathParams.FACILITY_ID;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FacilityChildCollectionServiceBackedResource;
import gov.ca.cwds.cals.inject.FacilityChildServiceBackedResource;
import gov.ca.cwds.cals.service.builder.FacilityParameterObjectBuilder;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildrenDTO;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
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

/**
 * @author CWDS CALS API Team
 */
@Api(tags = {FACILITIES})
@Path(FACILITIES + "/{" + FACILITY_ID + "}/" + CHILDREN)
@Produces(MediaType.APPLICATION_JSON)
public class FacilityChildResource {

  @Inject
  @FacilityChildServiceBackedResource
  private ResourceDelegate resourceDelegate;

  @Inject
  @FacilityChildCollectionServiceBackedResource
  private ResourceDelegate collectionResourceDelegate;

  @Inject
  private FacilityParameterObjectBuilder facilityParameterObjectBuilder;

  @UnitOfWork(CMS)
  @GET
  @Timed
  @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")})
  @ApiOperation(value = "Returns Children collection by Facility Id", response = FacilityChildrenDTO.class)
  public Response getChildren(@PathParam(FACILITY_ID) @ApiParam(required = true, name = FACILITY_ID,
      value = "Currently it's PLC_HM_T.IDENTIFIER for CWSCMS or lis_fac_file.fac_nbr for LIS") String facilityNumber) {
    return collectionResourceDelegate.get(new FacilityChildParameterObject(
        facilityParameterObjectBuilder.createFacilityParameterObject(facilityNumber)));
  }

  @UnitOfWork(CMS)
  @GET
  @Timed
  @Path("/{" + CHILD_ID + "}")
  @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")})
  @ApiOperation(value = "Returns Child by Facility Id and Child Id", response = FacilityChildDTO.class)
  public Response getChild(@PathParam(FACILITY_ID) @ApiParam(required = true, name = FACILITY_ID,
      value = "Currently it's PLC_HM_T.IDENTIFIER for CWSCMS or lis_fac_file.fac_nbr for LIS") String facilityNumber,
      @PathParam(CHILD_ID) @ApiParam(required = true, name = CHILD_ID, value = "The id of the Client") String childId) {
    return resourceDelegate.get(new FacilityChildParameterObject(
        facilityParameterObjectBuilder.createFacilityParameterObject(facilityNumber), childId));
  }
}
