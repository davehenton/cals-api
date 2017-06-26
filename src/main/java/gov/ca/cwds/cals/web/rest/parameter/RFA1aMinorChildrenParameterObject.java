package gov.ca.cwds.cals.web.rest.parameter;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.MinorChild;
import gov.ca.cwds.rest.api.Request;
import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aMinorChildrenParameterObject implements Request, Serializable {

  private static final long serialVersionUID = 6694171109010473069L;

  private Long applicationId;
  private Long minorChildId;
  private MinorChild minorChild;

  public RFA1aMinorChildrenParameterObject(Long applicationId) {
    this.applicationId = applicationId;
    this.minorChild = null;
    this.minorChildId = null;
  }

  public RFA1aMinorChildrenParameterObject(Long applicationId, MinorChild minorChild) {
    this.applicationId = applicationId;
    this.minorChild = minorChild;
    this.minorChildId = null;
  }

  public RFA1aMinorChildrenParameterObject(Long applicationId, Long minorChildId) {
    this.applicationId = applicationId;
    this.minorChild = null;
    this.minorChildId = minorChildId;
  }

  public Long getApplicationId() {
    return applicationId;
  }

  public Long getMinorChildId() {
    return minorChildId;
  }

  public MinorChild getMinorChild() {
    return minorChild;
  }
}
