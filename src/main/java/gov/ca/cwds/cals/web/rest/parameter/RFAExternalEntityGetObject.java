package gov.ca.cwds.cals.web.rest.parameter;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class RFAExternalEntityGetObject implements Serializable {

  private static final long serialVersionUID = -531872197296633529L;

  private Long formId;
  private Long entityId;

  public RFAExternalEntityGetObject(Long formId, Long entityId) {
    this.formId = formId;
    this.entityId = entityId;
  }

  public Long getFormId() {
    return formId;
  }

  public Long getEntityId() {
    return entityId;
  }

}
