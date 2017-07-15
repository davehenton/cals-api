package gov.ca.cwds.cals.persistence.model.cms;

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
@Table(name = "PH_CNT_T")
public class PhoneContactDetail {

  @Id
  @Column(name = "THIRD_ID", nullable = false, length = 10)
  private String thirdId;

  @Basic
  @Column(name = "PHONE_NO", nullable = false, precision = 0)
  private int phoneNo;

  @Basic
  @Column(name = "PHEXT_NO", nullable = true)
  private Integer phextNo;

  @Basic
  @Column(name = "PHN_TYP_CD", nullable = false, length = 1)
  private String phnTypCd;

  @Basic
  @Column(name = "ESTBLSH_CD", nullable = false, length = 1)
  private String estblshCd;

  @Basic
  @Column(name = "ESTBLSH_ID", nullable = false, length = 10)
  private String estblshId;

  @Basic
  @Column(name = "LST_UPD_ID", nullable = false, length = 3)
  private String lstUpdId;

  @Basic
  @Column(name = "LST_UPD_TS", nullable = false)
  private LocalDateTime lstUpdTs;

  public String getThirdId() {
    return thirdId;
  }

  public void setThirdId(String thirdId) {
    this.thirdId = thirdId;
  }

  public int getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(int phoneNo) {
    this.phoneNo = phoneNo;
  }

  public Integer getPhextNo() {
    return phextNo;
  }

  public void setPhextNo(Integer phextNo) {
    this.phextNo = phextNo;
  }

  public String getPhnTypCd() {
    return phnTypCd;
  }

  public void setPhnTypCd(String phnTypCd) {
    this.phnTypCd = phnTypCd;
  }

  public String getEstblshCd() {
    return estblshCd;
  }

  public void setEstblshCd(String estblshCd) {
    this.estblshCd = estblshCd;
  }

  public String getEstblshId() {
    return estblshId;
  }

  public void setEstblshId(String estblshId) {
    this.estblshId = estblshId;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PhoneContactDetail that = (PhoneContactDetail) o;

    if (phoneNo != that.phoneNo) {
      return false;
    }
    if (thirdId != null ? !thirdId.equals(that.thirdId) : that.thirdId != null) {
      return false;
    }
    if (phextNo != null ? !phextNo.equals(that.phextNo) : that.phextNo != null) {
      return false;
    }
    if (phnTypCd != null ? !phnTypCd.equals(that.phnTypCd) : that.phnTypCd != null) {
      return false;
    }
    if (estblshCd != null ? !estblshCd.equals(that.estblshCd) : that.estblshCd != null) {
      return false;
    }
    if (estblshId != null ? !estblshId.equals(that.estblshId) : that.estblshId != null) {
      return false;
    }
    if (lstUpdId != null ? !lstUpdId.equals(that.lstUpdId) : that.lstUpdId != null) {
      return false;
    }
    if (lstUpdTs != null ? !lstUpdTs.equals(that.lstUpdTs) : that.lstUpdTs != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = thirdId != null ? thirdId.hashCode() : 0;
    result = 31 * result + phoneNo;
    result = 31 * result + (phextNo != null ? phextNo.hashCode() : 0);
    result = 31 * result + (phnTypCd != null ? phnTypCd.hashCode() : 0);
    result = 31 * result + (estblshCd != null ? estblshCd.hashCode() : 0);
    result = 31 * result + (estblshId != null ? estblshId.hashCode() : 0);
    result = 31 * result + (lstUpdId != null ? lstUpdId.hashCode() : 0);
    result = 31 * result + (lstUpdTs != null ? lstUpdTs.hashCode() : 0);
    return result;
  }
}
