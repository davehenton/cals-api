package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.cals.persistence.hibernate.JsonType;

/**
 * @author CWDS CALS API Team.
 */
public class ResidenceJsonType extends JsonType {

  public static final ResidenceJsonType INSTANCE = new ResidenceJsonType();

  @Override
  public Class<Residence> returnedClass() {
    return Residence.class;
  }
}
