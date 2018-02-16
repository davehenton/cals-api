package gov.ca.cwds.cals.service.mapper;


import gov.ca.cwds.cals.service.dto.placementhome.identification.EmergencyContactDTO;
import gov.ca.cwds.data.legacy.cms.entity.EmergencyContactDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {TrailingSpacesRemovalPostMappingProcessor.class})
@FunctionalInterface
public interface EmergencyContactMapper {

  EmergencyContactMapper INSTANCE = Mappers.getMapper(EmergencyContactMapper.class);


  @Mapping(source = "cntctNme", target = "name")
  @Mapping(source = "priPhNo", target = "phone.phone")
  @Mapping(source = "priPhExt", target = "phone.ext")
  @Mapping(source = "altPhNo", target = "altPhone.phone")
  @Mapping(source = "altPhExt", target = "altPhone.ext")
  @Mapping(source = "emailAddr", target = "email")
  @Mapping(source = "streetNo", target = "streetNo")
  @Mapping(source = "streetNm", target = "streetName")
  @Mapping(source = "cityNm", target = "city")
  @Mapping(target = "state.id", source = "stateC")
  @Mapping(source = "zipNo", target = "zip")
  @Mapping(source = "zipSfxNo", target = "zipExt")
    //frgAdrtB - foreign address indicator
  EmergencyContactDTO toEmergencyContactDTO(EmergencyContactDetail emergencyContactDetail);
}
