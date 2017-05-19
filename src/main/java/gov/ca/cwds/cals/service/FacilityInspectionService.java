package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.FacilityInspectionDTO;
import gov.ca.cwds.cals.service.mapper.FacilityInspectionMapper;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class FacilityInspectionService extends CrudServiceAdapter {

    private FacilityInspectionMapper facilityInspectionMapper;

    @Inject
    public FacilityInspectionService(FacilityInspectionMapper facilityInspectionMapper) {
        this.facilityInspectionMapper = facilityInspectionMapper;
    }

    @Override
    public Response find(Serializable params) {
        FacilityInspectionDTO dto =  facilityInspectionMapper.toFacilityInspectionDto(null);
        return dto;
    }

}
