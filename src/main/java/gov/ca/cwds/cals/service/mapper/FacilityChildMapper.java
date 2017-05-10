package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.cms.OutOfHomePlacement;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface FacilityChildMapper {
    FacilityChildMapper INSTANCE = Mappers.getMapper(FacilityChildMapper.class);

    @Mapping(source = "startDt", target = "dateOfPlacement")
    FacilityChildDTO toFacilityChildDTO(OutOfHomePlacement outOfHomePlacement);
}
