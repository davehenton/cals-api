package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.service.dto.FacilityInspectionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface FacilityInspectionMapper {

    @Mapping(target = "id", ignore = true)
    FacilityInspectionDTO toFacilityInspectionDto(Object object);

}
