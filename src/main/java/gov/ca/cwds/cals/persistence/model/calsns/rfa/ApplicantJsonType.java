package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.cals.persistence.hibernate.JsonType;

/**
 * @author CWDS CALS API Team
 */
public class ApplicantJsonType extends JsonType {
  public Class<Applicant> returnedClass() {
    return Applicant.class;
  }
}
