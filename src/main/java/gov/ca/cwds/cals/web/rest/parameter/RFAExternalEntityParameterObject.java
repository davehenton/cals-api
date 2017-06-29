package gov.ca.cwds.cals.web.rest.parameter;

import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.rest.api.Request;
import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S00119")
public class RFAExternalEntityParameterObject<EntityDTO extends BaseDTO> implements Request,
    Serializable {

  private static final long serialVersionUID = -531872197296633529L;

  private Long formId;
  private Long applicantId;
  private EntityDTO entityDTO;

  public RFAExternalEntityParameterObject(Long formId) {
    this.formId = formId;
  }

  public RFAExternalEntityParameterObject(Long formId, EntityDTO entityDTO) {
    this.formId = formId;
    this.entityDTO = entityDTO;
  }

  public RFAExternalEntityParameterObject(Long formId, Long applicantId) {
    this.formId = formId;
    this.applicantId = applicantId;
  }

  public Long getFormId() {
    return formId;
  }

  public EntityDTO getEntityDTO() {
    return entityDTO;
  }

  public Long getEntityId() {
    return applicantId;
  }
}

