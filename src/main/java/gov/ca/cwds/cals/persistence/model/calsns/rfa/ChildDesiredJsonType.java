package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.cals.persistence.hibernate.JsonType;

/**
 * @author CWDS CALS API Team
 */
public class ChildDesiredJsonType extends JsonType {

  public Class<ChildDesired> returnedClass() {
    return ChildDesired.class;
  }

}
