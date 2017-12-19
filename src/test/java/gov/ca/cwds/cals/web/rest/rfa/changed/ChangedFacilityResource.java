package gov.ca.cwds.cals.web.rest.rfa.changed;

import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.web.rest.rfa.changed.ChangedFacilityResourceTest.DATE_AFTER;
import static gov.ca.cwds.cals.web.rest.rfa.changed.ChangedFacilityResourceTest.LIS_DATE_AFTER;
import static gov.ca.cwds.cals.web.rest.rfa.changed.ChangedFacilityResourceTest.PATH_CHANGED_FACILITY;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.service.ChangedFacilityService;
import gov.ca.cwds.cals.service.dto.changed.ChangedFacilityDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.rest.api.Response;
import io.swagger.annotations.Api;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang.time.DateUtils;

/**
 * @author CWDS TPT-2
 */
@Api(tags = {FACILITIES})
@Path(PATH_CHANGED_FACILITY)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChangedFacilityResource {


  @Inject
  private ChangedFacilityService changedFacilityService;

  @GET
  public CollectionDTO<ChangedFacilityDTO> getChangedFacilities(
      @QueryParam(DATE_AFTER)
          String stringDateAfter,
      @QueryParam(LIS_DATE_AFTER)
          String stringLisDateAfter
  ) throws ParseException {
    String[] patterns = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
    Date dateAfter = DateUtils.parseDate(stringDateAfter, patterns);
    Date lisDateAfter = DateUtils.parseDate(stringLisDateAfter, patterns);

    List<ChangedFacilityDTO> changedFacilityDTOList = changedFacilityService
        .changedFacilitiesStream(
            dateAfter, lisDateAfter).collect(Collectors.toList());
    return new CollectionDTO<>(changedFacilityDTOList);
  }
}
