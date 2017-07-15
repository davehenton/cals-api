package gov.ca.cwds.cals.persistence.model.cms;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author CWDS CALS API Team
 */
@Entity
@Table(name = "HM_PRFT")
@IdClass(PlacementHomeProfilePK.class)
public class PlacementHomeProfile {

  @Id
  @Column(name = "THIRD_ID", nullable = false, length = 10)
  private String thirdId;

  @Basic
  @Column(name = "CHRCTR_C", nullable = false)
  private short chrctrC;

  @Basic
  @Column(name = "CHRCTR_CD", nullable = false, length = 1)
  private String chrctrCd;

  @Basic
  @Column(name = "LST_UPD_ID", nullable = false, length = 3)
  private String lstUpdId;

  @Basic
  @Column(name = "LST_UPD_TS", nullable = false)
  private LocalDateTime lstUpdTs;

  @Id
  @Column(name = "FKPLC_HM_T", nullable = false, length = 10)
  private String fkplcHmT;

  public String getThirdId() {
    return thirdId;
  }

  public void setThirdId(String thirdId) {
    this.thirdId = thirdId;
  }

  public short getChrctrC() {
    return chrctrC;
  }

  public void setChrctrC(short chrctrC) {
    this.chrctrC = chrctrC;
  }

  public String getChrctrCd() {
    return chrctrCd;
  }

  public void setChrctrCd(String chrctrCd) {
    this.chrctrCd = chrctrCd;
  }

  public String getLstUpdId() {
    return lstUpdId;
  }

  public void setLstUpdId(String lstUpdId) {
    this.lstUpdId = lstUpdId;
  }

  public LocalDateTime getLstUpdTs() {
    return lstUpdTs;
  }

  public void setLstUpdTs(LocalDateTime lstUpdTs) {
    this.lstUpdTs = lstUpdTs;
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

    PlacementHomeProfile that = (PlacementHomeProfile) o;

    if (chrctrC != that.chrctrC) {
      return false;
    }
    if (thirdId != null ? !thirdId.equals(that.thirdId) : that.thirdId != null) {
      return false;
    }
    if (chrctrCd != null ? !chrctrCd.equals(that.chrctrCd) : that.chrctrCd != null) {
      return false;
    }
    if (lstUpdId != null ? !lstUpdId.equals(that.lstUpdId) : that.lstUpdId != null) {
      return false;
    }
    if (lstUpdTs != null ? !lstUpdTs.equals(that.lstUpdTs) : that.lstUpdTs != null) {
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
    result = 31 * result + (int) chrctrC;
    result = 31 * result + (chrctrCd != null ? chrctrCd.hashCode() : 0);
    result = 31 * result + (lstUpdId != null ? lstUpdId.hashCode() : 0);
    result = 31 * result + (lstUpdTs != null ? lstUpdTs.hashCode() : 0);
    result = 31 * result + (fkplcHmT != null ? fkplcHmT.hashCode() : 0);
    return result;
  }
}
