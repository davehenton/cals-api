package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeNotes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {LocalDate.class, LocalDateTime.class, Utils.class})
public interface PlacementHomeNotesMapper {
  PlacementHomeNotesMapper INSTANCE = Mappers.getMapper(PlacementHomeNotesMapper.class);

  @Mapping(target = "identifier", expression = "java(Utils.Id.generate())")
  @Mapping(target = "receiveDt", expression = "java(LocalDate.now())")
  @Mapping(target = "refLicind", constant = "N")
  @Mapping(target = "submitrNm", constant = " ")// TODO: 8/17/2017 mapping is required
  @Mapping(target = "lstUpdId", expression = "java(Utils.Id.getStaffPersonId())")
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now())")
  @Mapping(target = "fkplcHmT", source = "placementHomeId")
  @Mapping(target = "comntDsc", constant = " ")
  PlacementHomeNotes toPlacementHomeNotes(String placementHomeId);
}
