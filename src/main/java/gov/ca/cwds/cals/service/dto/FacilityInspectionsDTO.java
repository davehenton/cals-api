package gov.ca.cwds.cals.service.dto;

import gov.ca.cwds.rest.api.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 *
 * Represent Collection of Facility Inspections {@link FacilityInspectionDTO}
 */
public class FacilityInspectionsDTO implements Response {

    private List<FacilityInspectionDTO> inspections = new ArrayList<>();


    public List<FacilityInspectionDTO> getInspections() {
        return inspections;
    }

    public void setInspections(List<FacilityInspectionDTO> inspections) {
        this.inspections = new ArrayList<>(inspections);
    }
}
