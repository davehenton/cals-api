package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.cals.service.dto.CountyDTO;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.County;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(uses = TrailingSpacesRemovalPostMappingProcessor.class)
public interface CountyMapper {
    CountyMapper INSTANCE = Mappers.getMapper(CountyMapper.class);

    @Mapping(target = "code", source = "logicalId")
    @Mapping(target = "description", source = "shortDescription")
    CountyDTO toCountyDTO(County county);

    @Mapping(target = "code", source = "tblCoNbr")
    @Mapping(target = "description", source = "tblCoDesc")
    CountyDTO toCountyDTO(LisTableFile lisTableFile);
}
