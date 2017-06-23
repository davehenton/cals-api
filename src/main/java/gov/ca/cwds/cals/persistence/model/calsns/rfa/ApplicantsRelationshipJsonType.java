package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.cals.persistence.hibernate.JsonType;

/**
 * @author CWDS CALS API Team
 */

public class ApplicantsRelationshipJsonType extends JsonType {

  public static final ApplicantsRelationshipJsonType INSTANCE = new ApplicantsRelationshipJsonType();

  public Class<ApplicantsRelationship> returnedClass() {
    return ApplicantsRelationship.class;
  }

}



