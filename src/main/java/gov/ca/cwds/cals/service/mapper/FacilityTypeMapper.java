package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.FacilityType;
import gov.ca.cwds.cals.service.dto.FacilityTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(uses = TrailingSpacesRemovalPostMappingProcessor.class)
public interface FacilityTypeMapper {

  FacilityTypeMapper INSTANCE = Mappers.getMapper(FacilityTypeMapper.class);

  @Mapping(target = "code", source = "id")
  @Mapping(target = "description", source = "value")
  FacilityTypeDTO toFacilityTypeDTO(FacilityType facilityType);
}
