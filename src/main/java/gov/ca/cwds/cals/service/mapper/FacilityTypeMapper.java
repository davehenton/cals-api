package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.lis.FacilityType;
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

    List<FacilityTypeDTO> toFacilityTypeDTO(List<gov.ca.cwds.cals.model.fas.FacilityType> facilityTypes);

    @Mapping(target = "code", source = "tblFacTypeCode")
    @Mapping(target = "description", source = "tblFacTypeDesc")
    FacilityTypeDTO toFacilityTypeDTO(gov.ca.cwds.cals.model.fas.FacilityType facilityType);

    @Mapping(target = "code", source = "lgcId")
    @Mapping(target = "description", source = "shortDsc")
    FacilityTypeDTO toFacilityTypeDTO(gov.ca.cwds.cals.model.cms.FacilityType facilityType);

}
