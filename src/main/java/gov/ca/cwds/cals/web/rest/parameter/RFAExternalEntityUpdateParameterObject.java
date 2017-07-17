package gov.ca.cwds.cals.web.rest.parameter;

import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.rest.api.Request;
import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class RFAExternalEntityUpdateParameterObject<T extends BaseDTO> implements Request,
    Serializable {

  private static final long serialVersionUID = -531872197296633529L;

  private Long formId;
  private T entityDTO;

  public RFAExternalEntityUpdateParameterObject(Long formId, T entityDTO) {
    this.formId = formId;
    this.entityDTO = entityDTO;
  }

  public Long getFormId() {
    return formId;
  }

  public T getEntityDTO() {
    return entityDTO;
  }

}

