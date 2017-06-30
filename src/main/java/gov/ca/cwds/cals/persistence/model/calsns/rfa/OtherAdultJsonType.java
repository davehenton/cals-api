package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.cals.persistence.hibernate.JsonType;

/**
 * @author CWDS CALS API Team
 */
public class OtherAdultJsonType extends JsonType {

  public Class<OtherAdult> returnedClass() {
    return OtherAdult.class;
  }
}
