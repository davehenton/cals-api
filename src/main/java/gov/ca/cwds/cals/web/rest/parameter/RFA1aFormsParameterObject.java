package gov.ca.cwds.cals.web.rest.parameter;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aFormsParameterObject implements Serializable {
  public static final long serialVersionUID = 42L;

  private Long formId;
  private boolean expanded;

  public RFA1aFormsParameterObject(Long formId) {
    this.formId = formId;
  }

  public RFA1aFormsParameterObject(Long formId, boolean expanded) {
    this.formId = formId;
    this.expanded = expanded;
  }

  public Long getFormId() {
    return formId;
  }

  public boolean isExpanded() {
    return expanded;
  }
}