package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.domain.Facility;
import gov.ca.cwds.cals.service.dto.FacilityDTO;

public interface FacilityMapper {

    FacilityDTO facilityToFacilityDTO(Facility facility);

}
