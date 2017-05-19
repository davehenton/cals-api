package gov.ca.cwds.cals.web.rest.parameter;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class FacilityInspectionParameterObject implements Serializable {
    private static final long serialVersionUID = -4549680323655263578L;

    private Integer facilityId;

    private String inspectionId;

    public FacilityInspectionParameterObject(Integer facilityId, String inspectionId) {
        this.facilityId = facilityId;
        this.inspectionId = inspectionId;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }
}
