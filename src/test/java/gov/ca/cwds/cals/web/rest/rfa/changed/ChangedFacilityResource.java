package gov.ca.cwds.cals.web.rest.rfa.changed;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.ChangedFacilityService;
import gov.ca.cwds.cals.service.dto.changed.ChangedFacilityDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import org.apache.commons.lang.time.DateUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static gov.ca.cwds.cals.web.rest.rfa.changed.ChangedFacilityResourceTest.*;

/**
 * @author CWDS TPT-2
 */
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
    List<ChangedFacilityDTO> changedFacilityDTOList = changedFacilityService
            .changedFacilitiesStream(
                    getDateFromString(stringDateAfter), getDateFromString(stringLisDateAfter)).collect(Collectors.toList());
    return new CollectionDTO<>(changedFacilityDTOList);
  }

  @GET
  @Path("/" + PATH_INITIAL)
  public CollectionDTO<ChangedFacilityDTO> initialLLoadedFacilities(
          @QueryParam(LIS_DATE_AFTER)
                  String stringLisDateAfter
  ) throws ParseException {
    List<ChangedFacilityDTO> changedFacilityDTOList = changedFacilityService
            .changedFacilitiesStream(
                    null, getDateFromString(stringLisDateAfter)).collect(Collectors.toList());
    return new CollectionDTO<>(changedFacilityDTOList);
  }

  private Date getDateFromString(@QueryParam(LIS_DATE_AFTER) String stringLisDateAfter) throws ParseException {
    String[] patterns = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
    return DateUtils.parseDate(stringLisDateAfter, patterns);
  }

}
