package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.domain.Facility;
import gov.ca.cwds.cals.service.dto.FacilityDTO;

public class FacilityMapperImpl implements FacilityMapper {

    @Override
    public FacilityDTO facilityToFacilityDTO(Facility facility) {
        FacilityDTO facilityDTO = new FacilityDTO();
        facilityDTO.setId(125454l);
        return facilityDTO;
    }

}
