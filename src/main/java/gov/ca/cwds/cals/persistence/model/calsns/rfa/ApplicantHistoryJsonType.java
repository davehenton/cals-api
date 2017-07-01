package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.cals.persistence.hibernate.JsonType;

/**
 * @author CWDS CALS API Team
 */
public class ApplicantHistoryJsonType extends JsonType {

  public Class<ApplicantsHistory> returnedClass() {
    return ApplicantsHistory.class;
  }

}
