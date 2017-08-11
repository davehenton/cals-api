package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenderType;
import gov.ca.cwds.cals.persistence.model.cms.OtherChildrenInPlacementHome;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {LocalDate.class, LocalDateTime.class})
public interface OtherChildrenInPlacementHomeMapper {


  @Mapping(target = "birthDt", source = "dateOfBirth")
  @Mapping(target = "genderCd", ignore = true)
  @Mapping(target = "othchldNm", constant = "")
  @Mapping(target = "yrIncAmt", ignore = true)
  @Mapping(target = "lstUpdId", ignore = true)
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now())")
  OtherChildrenInPlacementHome toOtherChild(MinorChildDTO minorChildDTO);

  @Mapping(target = "genderCd", source = "cwsShortCode")
  void toOtherChild(@MappingTarget OtherChildrenInPlacementHome otherChild, GenderType genderType);

}
