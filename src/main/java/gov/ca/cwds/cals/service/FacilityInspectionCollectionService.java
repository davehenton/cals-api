package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.FacilityInspectionsDTO;
import gov.ca.cwds.cals.service.mapper.FacilityInspectionMapper;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class FacilityInspectionCollectionService extends CrudServiceAdapter {

    private FacilityInspectionMapper facilityInspectionMapper;

    @Inject
    public FacilityInspectionCollectionService(FacilityInspectionMapper facilityInspectionMapper) {
        this.facilityInspectionMapper = facilityInspectionMapper;
    }

    @Override
    public Response find(Serializable params) {
        FacilityInspectionsDTO dto = new FacilityInspectionsDTO();
        // TODO Implement retrieving Inspections data
        return dto;
    }
}
