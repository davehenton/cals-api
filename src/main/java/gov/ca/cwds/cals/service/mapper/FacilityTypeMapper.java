package gov.ca.cwds.cals.service.mapper;

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

    //This is standard mapstruct approach that is why it's false positive
    @SuppressWarnings({"squid:S1214"})
    FacilityTypeMapper INSTANCE = Mappers.getMapper(FacilityTypeMapper.class);

    List<FacilityTypeDTO> toFacilityTypeDTO(List<gov.ca.cwds.cals.model.lis.FacilityType> facilityTypes);

    @Mapping(target = "code", source = "tblFacTypeCode")
    @Mapping(target = "description", source = "tblFacTypeDesc")
    FacilityTypeDTO toFacilityTypeDTO(gov.ca.cwds.cals.model.lis.FacilityType facilityType);

    @Mapping(target = "code", source = "lgcId")
    @Mapping(target = "description", source = "shortDsc")
    FacilityTypeDTO toFacilityTypeDTO(gov.ca.cwds.cals.model.cms.FacilityType facilityType);

}
