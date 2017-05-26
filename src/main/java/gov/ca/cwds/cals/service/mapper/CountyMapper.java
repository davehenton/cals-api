package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.cms.County;
import gov.ca.cwds.cals.service.dto.CountyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS CALS API Team
 */
@Mapper(uses = TrailingSpacesRemovalPostMappingProcessor.class)
public interface CountyMapper {

    @Mapping(target = "code", source = "lgcId")
    @Mapping(target = "description", source = "shortDsc")
    CountyDTO toCountyDTO(County county);

}
