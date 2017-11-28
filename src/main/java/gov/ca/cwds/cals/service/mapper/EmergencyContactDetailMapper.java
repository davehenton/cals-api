package gov.ca.cwds.cals.service.mapper;

import static gov.ca.cwds.cals.Constants.N;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.data.legacy.cms.entity.EmergencyContactDetail;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {LocalDateTime.class, Utils.class, Constants.class})
public interface EmergencyContactDetailMapper {
  EmergencyContactDetailMapper INSTANCE = Mappers.getMapper(EmergencyContactDetailMapper.class);

  @Mapping(target = "identifier", expression = "java(Utils.StaffPerson.generate())")
  @Mapping(target = "estblshCd", constant = "P")
  @Mapping(target = "estblshId", source = "placementHomeId")
  @Mapping(target = "cntctNme", ignore = true) // TODO: 8/17/2017 mapping required
  @Mapping(target = "priPhNo", ignore = true) // TODO: 8/17/2017 mapping required
  @Mapping(target = "priPhExt", ignore = true) // TODO: 8/17/2017 mapping required
  @Mapping(target = "altPhNo", ignore = true) // TODO: 8/17/2017 mapping required
  @Mapping(target = "altPhExt", ignore = true) // TODO: 8/17/2017 mapping required
  @Mapping(target = "emailAddr", ignore = true) // TODO: 8/17/2017 mapping required
  @Mapping(target = "streetNo", ignore = true) // TODO: 8/17/2017 mapping required
  @Mapping(target = "streetNm", ignore = true) // TODO: 8/17/2017 mapping required
  @Mapping(target = "cityNm", ignore = true) // TODO: 8/17/2017 mapping required
  @Mapping(target = "stateC", ignore = true) // TODO: 8/17/2017 mapping required
  @Mapping(target = "zipNo", ignore = true) // TODO: 8/17/2017 mapping required
  @Mapping(target = "zipSfxNo", ignore = true) // TODO: 8/17/2017 mapping required
  @Mapping(target = "frgAdrtB", constant = N)
  @Mapping(target = "lstUpdId", expression = "java(Utils.StaffPerson.getStaffPersonId())")
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now())")
  EmergencyContactDetail toEmergencyContactDetail(String placementHomeId);
}
