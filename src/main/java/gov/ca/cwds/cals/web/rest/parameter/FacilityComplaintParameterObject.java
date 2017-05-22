package gov.ca.cwds.cals.web.rest.parameter;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */

public class FacilityComplaintParameterObject implements Serializable {
    public static final long serialVersionUID = 42L;

    private Integer facilityId;

    private String complaintId;

    public FacilityComplaintParameterObject(Integer facilityId, String complaintId) {
        this.facilityId = facilityId;
        this.complaintId = complaintId;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

}
