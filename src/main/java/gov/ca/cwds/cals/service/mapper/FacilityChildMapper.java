package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface FacilityChildMapper {
    FacilityChildMapper INSTANCE = Mappers.getMapper(FacilityChildMapper.class);

    FacilityChildDTO toFacilityChildDTO(LisFacFile lisFacFile);
}
