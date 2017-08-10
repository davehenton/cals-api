package gov.ca.cwds.cals.service;

import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;

/**
 * @author CWDS CALS API Team
 */

public class CalsNsDictionaryEntriesHolder {

  private CountyType applicationCounty;
  private StateType stateCode;

  public CountyType getApplicationCounty() {
    return applicationCounty;
  }

  public void setApplicationCounty(
      CountyType applicationCounty) {
    this.applicationCounty = applicationCounty;
  }

  public StateType getStateCode() {
    return stateCode;
  }

  public void setStateCode(StateType stateCode) {
    this.stateCode = stateCode;
  }

}
