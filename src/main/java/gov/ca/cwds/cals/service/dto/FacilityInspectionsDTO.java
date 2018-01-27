package gov.ca.cwds.cals.service.dto;

import gov.ca.cwds.dto.BaseDTO;
import gov.ca.cwds.rest.api.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 *
 * Represent Collection of Facility Inspections {@link FacilityInspectionDTO}
 */
@SuppressWarnings("squid:S2160") //reflection equals hashcode is used in superclass
public class FacilityInspectionsDTO extends BaseDTO implements Response {

    private static final long serialVersionUID = 9143585256908266652L;

    private ArrayList<FacilityInspectionDTO> inspections;


    public List<FacilityInspectionDTO> getInspections() {
        return inspections;
    }

    public void setInspections(List<FacilityInspectionDTO> inspections) {
        this.inspections = new ArrayList<>(inspections);
    }
}
