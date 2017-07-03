package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.cals.persistence.hibernate.JsonType;

/**
 * @author CWDS CALS API Team
 */

public class ApplicantsRelationshipJsonType extends JsonType {

  public Class<ApplicantsRelationship> returnedClass() {
    return ApplicantsRelationship.class;
  }

}



