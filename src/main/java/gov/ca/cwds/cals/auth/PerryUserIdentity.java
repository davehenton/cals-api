package gov.ca.cwds.cals.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.security.realm.PerryAccount;

/**
 * @author CWDS CALS API Team
 */
public class PerryUserIdentity extends PerryAccount {

  @JsonProperty
  private String staffId;

  public String getStaffId() {
    return staffId;
  }

  public void setStaffId(String staffId) {
    this.staffId = staffId;
  }
}