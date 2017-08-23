package gov.ca.cwds.cals.web.rest.parameter;

import gov.ca.cwds.cals.service.dto.BaseDTO;

/**
 * @author CWDS CALS API Team
 */
public class RFAOtherAdultAwareEntityUpdateParams<T extends BaseDTO> extends
    RFAExternalEntityUpdateParameterObject<T> {


  private static final long serialVersionUID = 5541082917782701209L;

  private Long otherAdultId;

  public RFAOtherAdultAwareEntityUpdateParams(Long formId, Long otherAdultId, T entityDTO) {
    super(formId, entityDTO);
    this.otherAdultId = otherAdultId;
  }

  public Long getOtherAdultId() {
    return otherAdultId;
  }

  public void setOtherAdultId(Long otherAdultId) {
    this.otherAdultId = otherAdultId;
  }
}

