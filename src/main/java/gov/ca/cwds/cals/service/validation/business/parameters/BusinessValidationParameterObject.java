package gov.ca.cwds.cals.service.validation.business.parameters;

import gov.ca.cwds.cals.service.dto.BaseDTO;
import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class BusinessValidationParameterObject<T extends BaseDTO> implements Serializable {

  private static final long serialVersionUID = 5657114831453018513L;

  private Long formId;
  private T entityDTO;

  public BusinessValidationParameterObject(Long formId, T entityDTO) {
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

