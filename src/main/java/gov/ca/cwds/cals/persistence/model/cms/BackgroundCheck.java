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
@Table(name = "BKGRCHKT")
public class BackgroundCheck {

  @Id
  @Column(name = "IDENTIFIER", nullable = false, length = 10)
  private String identifier;

  @Basic
  @Column(name = "RCPNT_CD", nullable = true, length = 1)
  private String rcpntCd;

  @Basic
  @Column(name = "RCPNT_ID", nullable = true, length = 10)
  private String rcpntId;

  @Basic
  @Column(name = "BKGRCHKC", nullable = false)
  private short bkgrchkc;

  @Basic
  @Column(name = "BKGRCHK_DT", nullable = false)
  private LocalDate bkgrchkDt;

  @Basic
  @Column(name = "LST_UPD_ID", nullable = false, length = 3)
  private String lstUpdId;

  @Basic
  @Column(name = "LST_UPD_TS", nullable = false)
  private LocalDateTime lstUpdTs;

  @Basic
  @Column(name = "FKCOLTRL_T", nullable = true, length = 10)
  private String fkcoltrlT;

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getRcpntCd() {
    return rcpntCd;
  }

  public void setRcpntCd(String rcpntCd) {
    this.rcpntCd = rcpntCd;
  }

  public String getRcpntId() {
    return rcpntId;
  }

  public void setRcpntId(String rcpntId) {
    this.rcpntId = rcpntId;
  }

  public short getBkgrchkc() {
    return bkgrchkc;
  }

  public void setBkgrchkc(short bkgrchkc) {
    this.bkgrchkc = bkgrchkc;
  }

  public LocalDate getBkgrchkDt() {
    return bkgrchkDt;
  }

  public void setBkgrchkDt(LocalDate bkgrchkDt) {
    this.bkgrchkDt = bkgrchkDt;
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

  public String getFkcoltrlT() {
    return fkcoltrlT;
  }

  public void setFkcoltrlT(String fkcoltrlT) {
    this.fkcoltrlT = fkcoltrlT;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BackgroundCheck that = (BackgroundCheck) o;

    if (bkgrchkc != that.bkgrchkc) {
      return false;
    }
    if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) {
      return false;
    }
    if (rcpntCd != null ? !rcpntCd.equals(that.rcpntCd) : that.rcpntCd != null) {
      return false;
    }
    if (rcpntId != null ? !rcpntId.equals(that.rcpntId) : that.rcpntId != null) {
      return false;
    }
    if (bkgrchkDt != null ? !bkgrchkDt.equals(that.bkgrchkDt) : that.bkgrchkDt != null) {
      return false;
    }
    if (lstUpdId != null ? !lstUpdId.equals(that.lstUpdId) : that.lstUpdId != null) {
      return false;
    }
    if (lstUpdTs != null ? !lstUpdTs.equals(that.lstUpdTs) : that.lstUpdTs != null) {
      return false;
    }
    if (fkcoltrlT != null ? !fkcoltrlT.equals(that.fkcoltrlT) : that.fkcoltrlT != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = identifier != null ? identifier.hashCode() : 0;
    result = 31 * result + (rcpntCd != null ? rcpntCd.hashCode() : 0);
    result = 31 * result + (rcpntId != null ? rcpntId.hashCode() : 0);
    result = 31 * result + (int) bkgrchkc;
    result = 31 * result + (bkgrchkDt != null ? bkgrchkDt.hashCode() : 0);
    result = 31 * result + (lstUpdId != null ? lstUpdId.hashCode() : 0);
    result = 31 * result + (lstUpdTs != null ? lstUpdTs.hashCode() : 0);
    result = 31 * result + (fkcoltrlT != null ? fkcoltrlT.hashCode() : 0);
    return result;
  }
}
