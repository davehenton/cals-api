package gov.ca.cwds.cals.web.rest.parameter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildParameterObject implements Serializable {
    public static final long serialVersionUID = 42L;

    private String licenseNumber;
    private String childId;
  private Date after;

    public FacilityChildParameterObject(String licenseNumber, String childId) {
        this.licenseNumber = licenseNumber;
        this.childId = childId;
    }

  public FacilityChildParameterObject(Date after) {
    this.after = after;
  }

    public FacilityChildParameterObject(String licenseNumber) {
        this(licenseNumber, null);
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getChildId() {
        return childId;
    }

  public Date getAfter() {
    return after;
  }
}
