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
@Table(name = "EM_CNT_T")
public class EmergencyContactDetail {

  @Id
  @Column(name = "IDENTIFIER", nullable = false, length = 10)
  private String identifier;

  @Basic
  @Column(name = "ESTBLSH_CD", nullable = false, length = 1)
  private String estblshCd;

  @Basic
  @Column(name = "ESTBLSH_ID", nullable = false, length = 10)
  private String estblshId;

  @Basic
  @Column(name = "CNTCT_NME", nullable = true, length = 35)
  private String cntctNme;

  @Basic
  @Column(name = "PRI_PH_NO", nullable = true, precision = 0)
  private Integer priPhNo;

  @Basic
  @Column(name = "PRI_PH_EXT", nullable = true)
  private Integer priPhExt;

  @Basic
  @Column(name = "ALT_PH_NO", nullable = true, precision = 0)
  private Integer altPhNo;

  @Basic
  @Column(name = "ALT_PH_EXT", nullable = true)
  private Integer altPhExt;

  @Basic
  @Column(name = "EMAIL_ADDR", nullable = true, length = 50)
  private String emailAddr;

  @Basic
  @Column(name = "STREET_NO", nullable = true, length = 10)
  private String streetNo;

  @Basic
  @Column(name = "STREET_NM", nullable = true, length = 40)
  private String streetNm;

  @Basic
  @Column(name = "CITY_NM", nullable = true, length = 20)
  private String cityNm;

  @Basic
  @Column(name = "STATE_C", nullable = true)
  private Short stateC;

  @Basic
  @Column(name = "ZIP_NO", nullable = true)
  private Integer zipNo;

  @Basic
  @Column(name = "ZIP_SFX_NO", nullable = true)
  private Short zipSfxNo;

  @Basic
  @Column(name = "FRG_ADRT_B", nullable = false, length = 1)
  private String frgAdrtB;

  @Basic
  @Column(name = "LST_UPD_ID", nullable = false, length = 3)
  private String lstUpdId;

  @Basic
  @Column(name = "LST_UPD_TS", nullable = false)
  private LocalDateTime lstUpdTs;

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
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

  public String getCntctNme() {
    return cntctNme;
  }

  public void setCntctNme(String cntctNme) {
    this.cntctNme = cntctNme;
  }

  public Integer getPriPhNo() {
    return priPhNo;
  }

  public void setPriPhNo(Integer priPhNo) {
    this.priPhNo = priPhNo;
  }

  public Integer getPriPhExt() {
    return priPhExt;
  }

  public void setPriPhExt(Integer priPhExt) {
    this.priPhExt = priPhExt;
  }

  public Integer getAltPhNo() {
    return altPhNo;
  }

  public void setAltPhNo(Integer altPhNo) {
    this.altPhNo = altPhNo;
  }

  public Integer getAltPhExt() {
    return altPhExt;
  }

  public void setAltPhExt(Integer altPhExt) {
    this.altPhExt = altPhExt;
  }

  public String getEmailAddr() {
    return emailAddr;
  }

  public void setEmailAddr(String emailAddr) {
    this.emailAddr = emailAddr;
  }

  public String getStreetNo() {
    return streetNo;
  }

  public void setStreetNo(String streetNo) {
    this.streetNo = streetNo;
  }

  public String getStreetNm() {
    return streetNm;
  }

  public void setStreetNm(String streetNm) {
    this.streetNm = streetNm;
  }

  public String getCityNm() {
    return cityNm;
  }

  public void setCityNm(String cityNm) {
    this.cityNm = cityNm;
  }

  public Short getStateC() {
    return stateC;
  }

  public void setStateC(Short stateC) {
    this.stateC = stateC;
  }

  public Integer getZipNo() {
    return zipNo;
  }

  public void setZipNo(Integer zipNo) {
    this.zipNo = zipNo;
  }

  public Short getZipSfxNo() {
    return zipSfxNo;
  }

  public void setZipSfxNo(Short zipSfxNo) {
    this.zipSfxNo = zipSfxNo;
  }

  public String getFrgAdrtB() {
    return frgAdrtB;
  }

  public void setFrgAdrtB(String frgAdrtB) {
    this.frgAdrtB = frgAdrtB;
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

    EmergencyContactDetail that = (EmergencyContactDetail) o;

    if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) {
      return false;
    }
    if (estblshCd != null ? !estblshCd.equals(that.estblshCd) : that.estblshCd != null) {
      return false;
    }
    if (estblshId != null ? !estblshId.equals(that.estblshId) : that.estblshId != null) {
      return false;
    }
    if (cntctNme != null ? !cntctNme.equals(that.cntctNme) : that.cntctNme != null) {
      return false;
    }
    if (priPhNo != null ? !priPhNo.equals(that.priPhNo) : that.priPhNo != null) {
      return false;
    }
    if (priPhExt != null ? !priPhExt.equals(that.priPhExt) : that.priPhExt != null) {
      return false;
    }
    if (altPhNo != null ? !altPhNo.equals(that.altPhNo) : that.altPhNo != null) {
      return false;
    }
    if (altPhExt != null ? !altPhExt.equals(that.altPhExt) : that.altPhExt != null) {
      return false;
    }
    if (emailAddr != null ? !emailAddr.equals(that.emailAddr) : that.emailAddr != null) {
      return false;
    }
    if (streetNo != null ? !streetNo.equals(that.streetNo) : that.streetNo != null) {
      return false;
    }
    if (streetNm != null ? !streetNm.equals(that.streetNm) : that.streetNm != null) {
      return false;
    }
    if (cityNm != null ? !cityNm.equals(that.cityNm) : that.cityNm != null) {
      return false;
    }
    if (stateC != null ? !stateC.equals(that.stateC) : that.stateC != null) {
      return false;
    }
    if (zipNo != null ? !zipNo.equals(that.zipNo) : that.zipNo != null) {
      return false;
    }
    if (zipSfxNo != null ? !zipSfxNo.equals(that.zipSfxNo) : that.zipSfxNo != null) {
      return false;
    }
    if (frgAdrtB != null ? !frgAdrtB.equals(that.frgAdrtB) : that.frgAdrtB != null) {
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
    int result = identifier != null ? identifier.hashCode() : 0;
    result = 31 * result + (estblshCd != null ? estblshCd.hashCode() : 0);
    result = 31 * result + (estblshId != null ? estblshId.hashCode() : 0);
    result = 31 * result + (cntctNme != null ? cntctNme.hashCode() : 0);
    result = 31 * result + (priPhNo != null ? priPhNo.hashCode() : 0);
    result = 31 * result + (priPhExt != null ? priPhExt.hashCode() : 0);
    result = 31 * result + (altPhNo != null ? altPhNo.hashCode() : 0);
    result = 31 * result + (altPhExt != null ? altPhExt.hashCode() : 0);
    result = 31 * result + (emailAddr != null ? emailAddr.hashCode() : 0);
    result = 31 * result + (streetNo != null ? streetNo.hashCode() : 0);
    result = 31 * result + (streetNm != null ? streetNm.hashCode() : 0);
    result = 31 * result + (cityNm != null ? cityNm.hashCode() : 0);
    result = 31 * result + (stateC != null ? stateC.hashCode() : 0);
    result = 31 * result + (zipNo != null ? zipNo.hashCode() : 0);
    result = 31 * result + (zipSfxNo != null ? zipSfxNo.hashCode() : 0);
    result = 31 * result + (frgAdrtB != null ? frgAdrtB.hashCode() : 0);
    result = 31 * result + (lstUpdId != null ? lstUpdId.hashCode() : 0);
    result = 31 * result + (lstUpdTs != null ? lstUpdTs.hashCode() : 0);
    return result;
  }
}
