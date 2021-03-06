package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.service.dto.DistrictOfficeDTO;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.County;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS CALS API Team
 */
@FunctionalInterface
@Mapper(uses = TrailingSpacesRemovalPostMappingProcessor.class)
public interface DistrictOfficeMapper {

  @Mapping(target = "number", source = "logicalId")
  @Mapping(target = "name", source = "shortDescription")
  DistrictOfficeDTO toDistrictOffice(County county);
}
