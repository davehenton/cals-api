package gov.ca.cwds.cals.web.rest.parameter;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */

public class FacilityComplaintParameterObject implements Serializable {

  public static final long serialVersionUID = 42L;

  private String facilityId;

  private String complaintId;

  public FacilityComplaintParameterObject(String facilityId, String complaintId) {
    this.facilityId = facilityId;
    this.complaintId = complaintId;
  }

  public String getFacilityId() {
    return facilityId;
  }

  public String getComplaintId() {
    return complaintId;
  }
}
