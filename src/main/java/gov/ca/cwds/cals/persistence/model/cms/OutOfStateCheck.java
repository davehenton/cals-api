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
@Table(name = "OST_CHKT")
public class OutOfStateCheck {

  @Id
  @Column(name = "IDENTIFIER", nullable = false, length = 10)
  private String identifier;

  @Basic
  @Column(name = "RCPNT_ID", nullable = true, length = 10)
  private String rcpntId;

  @Basic
  @Column(name = "RCPNT_CD", nullable = true, length = 1)
  private String rcpntCd;

  @Basic
  @Column(name = "STATE_C", nullable = false)
  private Short stateC;

  @Basic
  @Column(name = "REGMNT_IND", nullable = true, length = 1)
  private String regmntInd;

  @Basic
  @Column(name = "REQUEST_DT", nullable = true)
  private LocalDate requestDt;

  @Basic
  @Column(name = "RECEIVE_DT", nullable = true)
  private LocalDate receiveDt;

  @Basic
  @Column(name = "STATUS_DT", nullable = true)
  private LocalDate statusDt;

  @Basic
  @Column(name = "STATUS_CD", nullable = true, length = 1)
  private String statusCd;

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

  public String getRcpntId() {
    return rcpntId;
  }

  public void setRcpntId(String rcpntId) {
    this.rcpntId = rcpntId;
  }

  public String getRcpntCd() {
    return rcpntCd;
  }

  public void setRcpntCd(String rcpntCd) {
    this.rcpntCd = rcpntCd;
  }

  public Short getStateC() {
    return stateC;
  }

  public void setStateC(Short stateC) {
    this.stateC = stateC;
  }

  public String getRegmntInd() {
    return regmntInd;
  }

  public void setRegmntInd(String regmntInd) {
    this.regmntInd = regmntInd;
  }

  public LocalDate getRequestDt() {
    return requestDt;
  }

  public void setRequestDt(LocalDate requestDt) {
    this.requestDt = requestDt;
  }

  public LocalDate getReceiveDt() {
    return receiveDt;
  }

  public void setReceiveDt(LocalDate receiveDt) {
    this.receiveDt = receiveDt;
  }

  public LocalDate getStatusDt() {
    return statusDt;
  }

  public void setStatusDt(LocalDate statusDt) {
    this.statusDt = statusDt;
  }

  public String getStatusCd() {
    return statusCd;
  }

  public void setStatusCd(String statusCd) {
    this.statusCd = statusCd;
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

    OutOfStateCheck that = (OutOfStateCheck) o;

    if (stateC != that.stateC) {
      return false;
    }
    if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) {
      return false;
    }
    if (rcpntId != null ? !rcpntId.equals(that.rcpntId) : that.rcpntId != null) {
      return false;
    }
    if (rcpntCd != null ? !rcpntCd.equals(that.rcpntCd) : that.rcpntCd != null) {
      return false;
    }
    if (regmntInd != null ? !regmntInd.equals(that.regmntInd) : that.regmntInd != null) {
      return false;
    }
    if (requestDt != null ? !requestDt.equals(that.requestDt) : that.requestDt != null) {
      return false;
    }
    if (receiveDt != null ? !receiveDt.equals(that.receiveDt) : that.receiveDt != null) {
      return false;
    }
    if (statusDt != null ? !statusDt.equals(that.statusDt) : that.statusDt != null) {
      return false;
    }
    if (statusCd != null ? !statusCd.equals(that.statusCd) : that.statusCd != null) {
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
    result = 31 * result + (rcpntId != null ? rcpntId.hashCode() : 0);
    result = 31 * result + (rcpntCd != null ? rcpntCd.hashCode() : 0);
    result = 31 * result + (int) stateC;
    result = 31 * result + (regmntInd != null ? regmntInd.hashCode() : 0);
    result = 31 * result + (requestDt != null ? requestDt.hashCode() : 0);
    result = 31 * result + (receiveDt != null ? receiveDt.hashCode() : 0);
    result = 31 * result + (statusDt != null ? statusDt.hashCode() : 0);
    result = 31 * result + (statusCd != null ? statusCd.hashCode() : 0);
    result = 31 * result + (lstUpdId != null ? lstUpdId.hashCode() : 0);
    result = 31 * result + (lstUpdTs != null ? lstUpdTs.hashCode() : 0);
    result = 31 * result + (fkcoltrlT != null ? fkcoltrlT.hashCode() : 0);
    return result;
  }
}
