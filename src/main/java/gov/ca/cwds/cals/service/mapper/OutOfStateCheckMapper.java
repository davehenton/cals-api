package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.data.legacy.cms.entity.OtherAdultsInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OutOfStateCheck;
import gov.ca.cwds.data.legacy.cms.entity.SubstituteCareProvider;
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
  @Mapping(target = "stateC", source = "state.cwsId")
  @Mapping(target = "regmntInd", ignore = true)
  @Mapping(target = "requestDt", ignore = true)
  @Mapping(target = "receiveDt", ignore = true)
  @Mapping(target = "statusDt", ignore = true)
  @Mapping(target = "statusCd", ignore = true)
  @Mapping(target = "lstUpdId", ignore = true)
  @Mapping(target = "lstUpdTs", ignore = true)
  @Mapping(target = "fkcoltrlT", ignore = true)
  void toOutOfStateCheck(@MappingTarget OutOfStateCheck outOfStateCheck, Object stub,
      StateType state);

  @InheritConfiguration
  @Mapping(target = "rcpntId", source = "substituteCareProvider.identifier")
  @Mapping(target = "rcpntCd", constant = "S")
  OutOfStateCheck toOutOfStateCheck(SubstituteCareProvider substituteCareProvider,
      StateType state);

  @InheritConfiguration
  @Mapping(target = "rcpntId", source = "otherAdultsInPlacementHome.identifier")
  @Mapping(target = "rcpntCd", constant = "O")
  OutOfStateCheck toOutOfStateCheck(OtherAdultsInPlacementHome otherAdultsInPlacementHome,
      StateType state);

}
