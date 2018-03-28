package gov.ca.cwds.cals.web.rest.parameter;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class FacilityParameterObject implements Serializable {

  public static final long serialVersionUID = 42L;

  private boolean expanded;
  private String facilityId;
  private String unitOfWork;

  public boolean isExpanded() {
    return expanded;
  }

  public void setExpanded(boolean expanded) {
    this.expanded = expanded;
  }

  public String getFacilityId() {
    return facilityId;
  }

  public String getUnitOfWork() {
    return unitOfWork;
  }

  public void setFacilityId(String facilityId) {
    this.facilityId = facilityId;
  }

  public void setUnitOfWork(String unitOfWork) {
    this.unitOfWork = unitOfWork;
  }
}
