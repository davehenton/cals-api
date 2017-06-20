package gov.ca.cwds.cals.web.rest.parameter;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.rest.api.Request;
import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantParameterObject implements Request, Serializable {

  private static final long serialVersionUID = 7927793063479450489L;

  private Long formId;
  private Applicant applicant;

  public RFA1aApplicantParameterObject(Long formId, Applicant applicant) {
    this.formId = formId;
    this.applicant = applicant;
  }

  public Long getFormId() {
    return formId;
  }

  public Applicant getApplicant() {
    return applicant;
  }
}
