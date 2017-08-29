package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.cms.OtherAdultsInPlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.OutOfStateCheck;
import gov.ca.cwds.cals.persistence.model.cms.SubstituteCareProvider;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface OutOfStateCheckMapper {
  OutOfStateCheckMapper INSTANCE = Mappers.getMapper(OutOfStateCheckMapper.class);

  @Mapping(target = "identifier", ignore = true)
  @Mapping(target = "stateC", constant = "0")
  @Mapping(target = "regmntInd", ignore = true)
  @Mapping(target = "requestDt", ignore = true)
  @Mapping(target = "receiveDt", ignore = true)
  @Mapping(target = "statusDt", ignore = true)
  @Mapping(target = "statusCd", ignore = true)
  @Mapping(target = "lstUpdId", ignore = true)
  @Mapping(target = "lstUpdTs", ignore = true)
  @Mapping(target = "fkcoltrlT", ignore = true)
  void toOutOfStateCheck(@MappingTarget OutOfStateCheck outOfStateCheck, Object stub);

  @InheritConfiguration
  @Mapping(target = "rcpntId", source = "substituteCareProvider.identifier")
  @Mapping(target = "rcpntCd", constant = "S")
  OutOfStateCheck toOutOfStateCheck(SubstituteCareProvider substituteCareProvider);

  @InheritConfiguration
  @Mapping(target = "rcpntId", source = "otherAdultsInPlacementHome.identifier")
  @Mapping(target = "rcpntCd", constant = "O")
  OutOfStateCheck toOutOfStateCheck(OtherAdultsInPlacementHome otherAdultsInPlacementHome);

}
