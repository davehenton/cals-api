package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeNotes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {LocalDate.class, LocalDateTime.class, Utils.class})
public interface PlacementHomeNotesMapper {
  @Mapping(target = "identifier", expression = "java(Utils.Id.generate())")
  @Mapping(target = "receiveDt", expression = "java(LocalDate.now())")
  @Mapping(target = "refLicind", constant = "N")
  @Mapping(target = "submitrNm", ignore = true)//todo
  @Mapping(target = "lstUpdId", expression = "java(Utils.Id.getStaffPersonId())")
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now())")
  @Mapping(target = "fkplcHmT", source = "placementHomeId")
  @Mapping(target = "comntDsc", constant = " ")
  PlacementHomeNotes toPlacementHomeNotes(String placementHomeId);
}
