package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.data.legacy.cms.entity.OtherChildrenInPlacementHome;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {LocalDate.class, LocalDateTime.class})
@SuppressWarnings("squid:S1609")
public interface OtherChildrenInPlacementHomeMapper {
  OtherChildrenInPlacementHomeMapper INSTANCE = Mappers.getMapper(OtherChildrenInPlacementHomeMapper.class);

  @Mapping(target = "birthDt", source = "dateOfBirth")
  @Mapping(target = "genderCd", source = "minorChildDTO.gender.cwsShortCode")
  @Mapping(target = "othchldNm", constant = "Undisclosed")
  @Mapping(target = "yrIncAmt", ignore = true)
  @Mapping(target = "lstUpdId", ignore = true)
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now())")
  OtherChildrenInPlacementHome toOtherChild(MinorChildDTO minorChildDTO);

}
