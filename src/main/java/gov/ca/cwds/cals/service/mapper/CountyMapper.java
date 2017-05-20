package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.cms.County;
import gov.ca.cwds.cals.service.dto.CountyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface CountyMapper {

    @Mapping(target = "code", source = "sysId")
    @Mapping(target = "description", source = "shortDsc")
    @Mapping(target = "lisCode", source = "lgcId")
    CountyDTO toCountyDTO(County county);

}
