package gov.ca.cwds.cals.persistence.model.cms;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author CWDS CALS API Team
 */
public class PlacementHomeProfilePK implements Serializable {

  private static final long serialVersionUID = -1338578310179366105L;

  @Column(name = "THIRD_ID", nullable = false, length = 10)
  @Id
  private String thirdId;

  @Column(name = "FKPLC_HM_T", nullable = false, length = 10)
  @Id
  private String fkplcHmT;

  public String getThirdId() {
    return thirdId;
  }

  public void setThirdId(String thirdId) {
    this.thirdId = thirdId;
  }

  public String getFkplcHmT() {
    return fkplcHmT;
  }

  public void setFkplcHmT(String fkplcHmT) {
    this.fkplcHmT = fkplcHmT;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PlacementHomeProfilePK that = (PlacementHomeProfilePK) o;

    if (thirdId != null ? !thirdId.equals(that.thirdId) : that.thirdId != null) {
      return false;
    }
    if (fkplcHmT != null ? !fkplcHmT.equals(that.fkplcHmT) : that.fkplcHmT != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = thirdId != null ? thirdId.hashCode() : 0;
    result = 31 * result + (fkplcHmT != null ? fkplcHmT.hashCode() : 0);
    return result;
  }
}
