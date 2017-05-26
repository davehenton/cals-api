package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.cms.County;
import gov.ca.cwds.cals.service.dto.DistrictOfficeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(uses = TrailingSpacesRemovalPostMappingProcessor.class)
public interface DistrictOfficeMapper {

    //This is standard mapstruct approach that is why it's false positive
    @SuppressWarnings({"squid:S1214"})
    DistrictOfficeMapper INSTANCE = Mappers.getMapper(DistrictOfficeMapper.class);

    @Mapping(target = "number", source = "lgcId")
    @Mapping(target = "name", source = "shortDsc")
    DistrictOfficeDTO toDistrictOffice(County county);
}
