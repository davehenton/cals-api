package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.cms.County;
import gov.ca.cwds.cals.service.dto.DistrictOfficeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface DistrictOfficeMapper {
    DistrictOfficeMapper INSTANCE = Mappers.getMapper(DistrictOfficeMapper.class);

    @Mapping(target = "number", source = "lgcId")
    @Mapping(target = "name", source = "shortDsc")
    DistrictOfficeDTO toDistrictOffice(County county);
}
