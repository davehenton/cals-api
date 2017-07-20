package gov.ca.cwds.cals.web.rest.rfa.changed;

import static gov.ca.cwds.cals.Constants.RFA;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;
import static gov.ca.cwds.cals.web.rest.rfa.changed.ChangedRFA1aFormsResourceTest.PATH_CHANGED_RFA_1A_FORMS;
import static gov.ca.cwds.cals.web.rest.rfa.changed.ChangedRFA1aFormsResourceTest.PATH_PARAM_DATE_AFTER;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.service.dto.changed.ChangedRFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.RFA1aFormCollectionDTO;
import gov.ca.cwds.cals.service.rfa.RFA1aFormsCollectionService;
import gov.ca.cwds.rest.api.Response;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author CWDS TPT-2
 */
@Api(tags = {RFA})
@Path(PATH_CHANGED_RFA_1A_FORMS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChangedRFA1aFormsResource {

  @Inject
  private RFA1aFormsCollectionService rfa1aFormsCollectionService;

  @UnitOfWork(CALSNS)
  @GET
  @Path("/{" + PATH_PARAM_DATE_AFTER + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(
      value = "Returns all RFA 1A Forms that were changed after given time",
      response = RFA1aFormCollectionDTO.class
  )
  public Response getChangedApplicationForms(
      @PathParam(PATH_PARAM_DATE_AFTER)
      @ApiParam(required = true, name = PATH_PARAM_DATE_AFTER, value = "date/time")
          String dateAfter
  ) {
    List<ChangedRFA1aFormDTO> changedRFA1aFormDTOList = rfa1aFormsCollectionService
        .streamChangedRFA1aForms(BaseCalsApiIntegrationTest.toLocalDateTime(dateAfter))
        .collect(Collectors.toList());
    return new CollectionDTO<>(changedRFA1aFormDTOList);
  }
}
