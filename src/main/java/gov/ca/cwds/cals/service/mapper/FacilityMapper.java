package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.cals.service.dto.FacilityDTO;

public interface FacilityMapper {

    FacilityDTO facilityToFacilityDTO(LisFacFile lisFacFile);

}
