package gov.ca.cwds.cals.persistence.model.cms;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author CWDS CALS API Team
 */
@Entity
@Table(name = "HMNOTE_T")
public class PlacementHomeNotes {

  @Id
  @Column(name = "IDENTIFIER", nullable = false, length = 10)
  private String identifier;

  @Basic
  @Column(name = "RECEIVE_DT", nullable = false)
  private LocalDate receiveDt;

  @Basic
  @Column(name = "REF_LICIND", nullable = false, length = 1)
  private String refLicind;

  @Basic
  @Column(name = "SUBMITR_NM", nullable = false, length = 35)
  private String submitrNm;

  @Basic
  @Column(name = "LST_UPD_ID", nullable = false, length = 3)
  private String lstUpdId;

  @Basic
  @Column(name = "LST_UPD_TS", nullable = false)
  private LocalDateTime lstUpdTs;

  @Basic
  @Column(name = "FKPLC_HM_T", nullable = false, length = 10)
  private String fkplcHmT;

  @Basic
  @Column(name = "COMNT_DSC", nullable = false, length = 254)
  private String comntDsc;

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public LocalDate getReceiveDt() {
    return receiveDt;
  }

  public void setReceiveDt(LocalDate receiveDt) {
    this.receiveDt = receiveDt;
  }

  public String getRefLicind() {
    return refLicind;
  }

  public void setRefLicind(String refLicind) {
    this.refLicind = refLicind;
  }

  public String getSubmitrNm() {
    return submitrNm;
  }

  public void setSubmitrNm(String submitrNm) {
    this.submitrNm = submitrNm;
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

  public String getComntDsc() {
    return comntDsc;
  }

  public void setComntDsc(String comntDsc) {
    this.comntDsc = comntDsc;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PlacementHomeNotes that = (PlacementHomeNotes) o;

    if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) {
      return false;
    }
    if (receiveDt != null ? !receiveDt.equals(that.receiveDt) : that.receiveDt != null) {
      return false;
    }
    if (refLicind != null ? !refLicind.equals(that.refLicind) : that.refLicind != null) {
      return false;
    }
    if (submitrNm != null ? !submitrNm.equals(that.submitrNm) : that.submitrNm != null) {
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
    if (comntDsc != null ? !comntDsc.equals(that.comntDsc) : that.comntDsc != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = identifier != null ? identifier.hashCode() : 0;
    result = 31 * result + (receiveDt != null ? receiveDt.hashCode() : 0);
    result = 31 * result + (refLicind != null ? refLicind.hashCode() : 0);
    result = 31 * result + (submitrNm != null ? submitrNm.hashCode() : 0);
    result = 31 * result + (lstUpdId != null ? lstUpdId.hashCode() : 0);
    result = 31 * result + (lstUpdTs != null ? lstUpdTs.hashCode() : 0);
    result = 31 * result + (fkplcHmT != null ? fkplcHmT.hashCode() : 0);
    result = 31 * result + (comntDsc != null ? comntDsc.hashCode() : 0);
    return result;
  }
}
