package gov.ca.cwds.cals.service.mapper;


import gov.ca.cwds.cals.service.dto.placementhome.identification.AddressDTO;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {TrailingSpacesRemovalPostMappingProcessor.class})
@FunctionalInterface
public interface PlacementHomeAddressMapper {

  PlacementHomeAddressMapper INSTANCE = Mappers.getMapper(PlacementHomeAddressMapper.class);

  @Mapping(source = "streetNo", target = "streetNo")
  @Mapping(source = "streetNm", target = "streetName")
  @Mapping(source = "cityNm", target = "city")
  @Mapping(source = "stateCode", target = "state.id")
  @Mapping(source = "zipNo", target = "zip")
  @Mapping(source = "zipSfxNo", target = "zipExt")
  @Mapping(source = "geoRgntcd", target = "geoRegion")
  @Mapping(source = "gvrEntc", target = "county.id")
  @Mapping(source = "addrDsc", target = "comment")
  AddressDTO toAddressDTO(PlacementHome placementHome);
}
