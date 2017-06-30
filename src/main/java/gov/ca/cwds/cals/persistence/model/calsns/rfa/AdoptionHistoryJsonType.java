package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.cals.persistence.hibernate.JsonType;

/**
 * @author CWDS CALS API Team.
 */
public class AdoptionHistoryJsonType extends JsonType {

  @Override
  public Class<AdoptionHistory> returnedClass() {
    return AdoptionHistory.class;
  }
}
