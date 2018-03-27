package gov.ca.cwds.cals.web.rest.parameter;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildParameterObject implements Serializable {

  public static final long serialVersionUID = 42L;

  private String childId;
  private FacilityParameterObject facilityParameterObject;

  public FacilityChildParameterObject(FacilityParameterObject facilityParameterObject, String childId) {
    this.facilityParameterObject = facilityParameterObject;
    this.childId = childId;
  }

  public FacilityChildParameterObject(FacilityParameterObject facilityParameterObject) {
    this(facilityParameterObject, null);
  }

  public String getFacilityId() {
    return facilityParameterObject.getFacilityId();
  }

  public String getUnitOfWork() {
    return facilityParameterObject.getUnitOfWork();
  }

  public String getChildId() {
    return childId;
  }
}
