package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.cals.persistence.hibernate.JsonType;

/**
 * @author CWDS CALS API Team.
 */
public class ApplicationJsonType extends JsonType {

  public static final ApplicationJsonType INSTANCE = new ApplicationJsonType();

  public Class<Application> returnedClass() {
    return Application.class;
  }
}
