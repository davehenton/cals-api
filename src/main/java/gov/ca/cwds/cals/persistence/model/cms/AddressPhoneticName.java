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
@Table(name = "ADR_PHTT")
@IdClass(AddressPhoneticNamePK.class)
public class AddressPhoneticName {

  @Id
  @Column(name = "PHONETC_NM", nullable = false, length = 8)
  private String phonetcNm;

  @Id
  @Column(name = "PRMRY_NMID", nullable = false, length = 10)
  private String prmryNmid;

  @Id
  @Column(name = "PRMRY_NMCD", nullable = false, length = 1)
  private String prmryNmcd;

  @Basic
  @Column(name = "GVR_ENTC", nullable = false)
  private Short gvrEntc;

  @Basic
  @Column(name = "MATCH_CODE", nullable = false, length = 8)
  private String matchCode;

  @Basic
  @Column(name = "LST_UPD_ID", nullable = false, length = 3)
  private String lstUpdId;

  @Basic
  @Column(name = "LST_UPD_TS", nullable = false)
  private LocalDateTime lstUpdTs;

  public String getPhonetcNm() {
    return phonetcNm;
  }

  public void setPhonetcNm(String phonetcNm) {
    this.phonetcNm = phonetcNm;
  }

  public String getPrmryNmid() {
    return prmryNmid;
  }

  public void setPrmryNmid(String prmryNmid) {
    this.prmryNmid = prmryNmid;
  }

  public String getPrmryNmcd() {
    return prmryNmcd;
  }

  public void setPrmryNmcd(String prmryNmcd) {
    this.prmryNmcd = prmryNmcd;
  }

  public Short getGvrEntc() {
    return gvrEntc;
  }

  public void setGvrEntc(Short gvrEntc) {
    this.gvrEntc = gvrEntc;
  }

  public String getMatchCode() {
    return matchCode;
  }

  public void setMatchCode(String matchCode) {
    this.matchCode = matchCode;
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

    AddressPhoneticName that = (AddressPhoneticName) o;

    if (gvrEntc != that.gvrEntc) {
      return false;
    }
    if (phonetcNm != null ? !phonetcNm.equals(that.phonetcNm) : that.phonetcNm != null) {
      return false;
    }
    if (prmryNmid != null ? !prmryNmid.equals(that.prmryNmid) : that.prmryNmid != null) {
      return false;
    }
    if (prmryNmcd != null ? !prmryNmcd.equals(that.prmryNmcd) : that.prmryNmcd != null) {
      return false;
    }
    if (matchCode != null ? !matchCode.equals(that.matchCode) : that.matchCode != null) {
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
    int result = phonetcNm != null ? phonetcNm.hashCode() : 0;
    result = 31 * result + (prmryNmid != null ? prmryNmid.hashCode() : 0);
    result = 31 * result + (prmryNmcd != null ? prmryNmcd.hashCode() : 0);
    result = 31 * result + (int) gvrEntc;
    result = 31 * result + (matchCode != null ? matchCode.hashCode() : 0);
    result = 31 * result + (lstUpdId != null ? lstUpdId.hashCode() : 0);
    result = 31 * result + (lstUpdTs != null ? lstUpdTs.hashCode() : 0);
    return result;
  }
}
