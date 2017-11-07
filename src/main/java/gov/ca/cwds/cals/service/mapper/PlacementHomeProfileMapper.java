package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LanguageType;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHomeProfile;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports={LocalDateTime.class, Utils.class})
public interface PlacementHomeProfileMapper {
  PlacementHomeProfileMapper INSTANCE = Mappers.getMapper(PlacementHomeProfileMapper.class);

  @Mapping(target = "thirdId", expression = "java(Utils.Id.generate())")
  @Mapping(target = "chrctrC", source = "languageType.cwsId")
  @Mapping(target = "chrctrCd", constant = "L")
  @Mapping(target = "lstUpdId", expression = "java(Utils.Id.getStaffPersonId())")
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now())")
  @Mapping(target = "fkplcHmT", source = "placementHomeId")
  PlacementHomeProfile toPlacementHomeProfile(LanguageType languageType, String placementHomeId);
}
