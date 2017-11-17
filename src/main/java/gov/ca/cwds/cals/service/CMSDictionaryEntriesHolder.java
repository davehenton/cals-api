package gov.ca.cwds.cals.service;

import gov.ca.cwds.data.legacy.cms.entity.syscodes.County;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.FacilityType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.LicenseStatus;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.State;

/**
 * @author CWDS CALS API Team
 */

public class CMSDictionaryEntriesHolder {

  private County applicationCounty;
  private LicenseStatus licenseStatus;
  private State stateCode;
  private State payeeStateCode;
  private FacilityType facilityType;

  public County getApplicationCounty() {
    return applicationCounty;
  }

  public void setApplicationCounty(County applicationCounty) {
    this.applicationCounty = applicationCounty;
  }

  public LicenseStatus getLicenseStatus() {
    return licenseStatus;
  }

  public void setLicenseStatus(LicenseStatus licenseStatus) {
    this.licenseStatus = licenseStatus;
  }

  public State getStateCode() {
    return stateCode;
  }

  public void setStateCode(State stateCode) {
    this.stateCode = stateCode;
  }

  public State getPayeeStateCode() {
    return payeeStateCode;
  }

  public void setPayeeStateCode(State payeeStateCode) {
    this.payeeStateCode = payeeStateCode;
  }

  public FacilityType getFacilityType() {
    return facilityType;
  }

  public void setFacilityType(FacilityType facilityType) {
    this.facilityType = facilityType;
  }
}
