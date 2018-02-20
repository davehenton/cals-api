package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.service.dto.placementhome.identification.CommonInfoDTO;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(uses = {TrailingSpacesRemovalPostMappingProcessor.class})
@FunctionalInterface
public interface PlacementHomeCommonInfoMapper {

  PlacementHomeCommonInfoMapper INSTANCE = Mappers.getMapper(PlacementHomeCommonInfoMapper.class);

  @Mapping(source = "facltyNm", target = "name")
  @Mapping(source = "prmSubsnm", target = "primaryScp")
  @Mapping(source = "licenseNo", target = "licenseNumber")
  @Mapping(source = "ageFrmNo", target = "ageRange.from")
  @Mapping(source = "ageToNo", target = "ageRange.to")
  @Mapping(source = "facilityType", target = "facType.code")
  @Mapping(source = "oprtdByid", target = "operatedBy.id")
  @Mapping(source = "maxCapNo", target = "capacity.bedsAvailable")
  @Mapping(source = "licCapNo", target = "capacity.placements")
  @Mapping(source = "prmTelNo", target = "primContact.phone.phone")
  @Mapping(source = "prmExtNo", target = "primContact.phone.ext")
  @Mapping(source = "faxNo", target = "primContact.fax")
  @Mapping(source = "licBsnc", target = "subType.id")
  @Mapping(constant = "false", target = "options.onHold")
  @Mapping(constant = "false", target = "options.inactivated")
  @Mapping(source = "atCapInd", target = "options.atCapacity")
  @Mapping(source = "adhmonly", target = "options.adoptionOnly")
  @Mapping(source = "trnhsgFac", target = "options.transitional")
  @Mapping(source = "bckPersnm", target = "backupContact.name")
  @Mapping(source = "bckTelNo", target = "backupContact.phone.phone")
  @Mapping(source = "bckExtNo", target = "backupContact.phone.ext")
  CommonInfoDTO toCommonInfoDTO(PlacementHome placementHome);
}
