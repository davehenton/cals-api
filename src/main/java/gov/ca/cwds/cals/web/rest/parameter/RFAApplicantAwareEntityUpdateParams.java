package gov.ca.cwds.cals.web.rest.parameter;

import gov.ca.cwds.cals.service.dto.BaseDTO;

/**
 * @author CWDS CALS API Team
 */
public class RFAApplicantAwareEntityUpdateParams<T extends BaseDTO> extends
    RFAExternalEntityUpdateParameterObject<T> {

  private static final long serialVersionUID = 366317258152761909L;

  private Long applicantId;

  public RFAApplicantAwareEntityUpdateParams(Long formId, Long applicantId, T entityDTO) {
    super(formId, entityDTO);
    this.applicantId = applicantId;
  }

  public Long getApplicantId() {
    return applicantId;
  }

  public void setApplicantId(Long applicantId) {
    this.applicantId = applicantId;
  }
}

