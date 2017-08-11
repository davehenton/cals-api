package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenderType;
import gov.ca.cwds.cals.persistence.model.cms.OtherChildrenInPlacementHome;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import java.time.LocalDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = LocalDate.class)
public interface OtherChildrenInPlacementHomeMapper {


  @Mapping(target = "birthDt", source = "dateOfBirth")
  @Mapping(target = "genderCd", ignore = true)
  @Mapping(target = "othchldNm", constant = "")
  OtherChildrenInPlacementHome toOtherChild(MinorChildDTO minorChildDTO);

  @Mapping(target = "genderCd", source = "cwsShortCode")
  OtherChildrenInPlacementHome toOtherChild(GenderType genderType);

}
