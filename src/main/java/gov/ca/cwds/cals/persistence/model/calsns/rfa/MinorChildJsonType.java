package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.cals.persistence.hibernate.JsonType;

/**
 * @author CWDS CALS API Team
 */
public class MinorChildJsonType extends JsonType {

  public Class<MinorChild> returnedClass() {
    return MinorChild.class;
  }
}
