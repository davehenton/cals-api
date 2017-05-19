package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.fas.FacilityType;
import gov.ca.cwds.cals.service.dto.FacilityTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface FacilityTypeMapper {
    FacilityTypeMapper INSTANCE = Mappers.getMapper(FacilityTypeMapper.class);

    List<FacilityTypeDTO> toFacilityTypeDTO(List<FacilityType> facilityTypes);

    @Mapping(target = "code", source = "tblFacTypeCode")
    @Mapping(target = "description", source = "tblFacTypeDesc")
    FacilityTypeDTO toFacilityTypeDTO(FacilityType facilityType);
}
