package gov.ca.cwds.cals.persistence.dao.cms;

import gov.ca.cwds.cals.persistence.model.cms.County;
import gov.ca.cwds.cals.persistence.model.cms.LicenseStatus;

/**
 * @author CWDS CALS API Team
 */

public class DictionaryEntriesHolder {

  private County applicationCounty;
  private LicenseStatus licenseStatus;

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
}
