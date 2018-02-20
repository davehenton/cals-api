package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.service.dto.placementhome.identification.EndDateDTO;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {TrailingSpacesRemovalPostMappingProcessor.class})
@FunctionalInterface
public interface PlacementHomeEndDateMapper {
  PlacementHomeEndDateMapper INSTANCE = Mappers.getMapper(PlacementHomeEndDateMapper.class);

  @Mapping(source = "endDt", target = "endDate")
  @Mapping(source = "endComdsc", target = "comments")
  @Mapping(source = "phEndc", target = "reasonType.id")
  EndDateDTO toEndDateDTO(PlacementHome placementHome);

}
