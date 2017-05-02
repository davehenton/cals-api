package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.cals.service.dto.FacilityDTO;

public class FacilityMapperImpl implements FacilityMapper {

    @Override
    public FacilityDTO facilityToFacilityDTO(LisFacFile lisFacFile) {
        FacilityDTO facilityDTO = new FacilityDTO();
        facilityDTO.setId(lisFacFile.getFacNbr().longValue());
        return facilityDTO;
    }

}
