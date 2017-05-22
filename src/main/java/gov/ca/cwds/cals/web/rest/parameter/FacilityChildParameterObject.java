package gov.ca.cwds.cals.web.rest.parameter;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildParameterObject implements Serializable {
    public static final long serialVersionUID = 42L;

    private String facilityNumber;
    private String childId;

    public FacilityChildParameterObject(String facilityNumber, String childId) {
        this.facilityNumber = facilityNumber;
        this.childId = childId;
    }

    public FacilityChildParameterObject(String facilityNumber) {
        this(facilityNumber, null);
    }

    public String getFacilityNumber() {
        return facilityNumber;
    }

    public String getChildId() {
        return childId;
    }
}
