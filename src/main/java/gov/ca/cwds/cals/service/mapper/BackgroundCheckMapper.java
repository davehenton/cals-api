package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.persistence.model.cms.BackgroundCheck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {LocalDateTime.class, LocalDate.class, Utils.class})
public interface BackgroundCheckMapper {
  @Mapping(target = "identifier", expression = "java(Utils.Id.generate())")
  @Mapping(target = "rcpntCd", constant = "P")
  @Mapping(target = "rcpntId", source = "placementHomeId")
  @Mapping(target = "bkgrchkc", constant = "-1") // TODO: 8/17/2017
  @Mapping(target = "bkgrchkDt", expression = "java(LocalDate.now())") // TODO: 8/17/2017
  @Mapping(target = "lstUpdId", expression = "java(Utils.Id.getStaffPersonId())")
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now())")
  @Mapping(target = "fkcoltrlT", ignore = true)  // TODO: 8/17/2017
  BackgroundCheck toBackgroundCheck(String placementHomeId);
}
