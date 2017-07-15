package gov.ca.cwds.cals.persistence.model.cms;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author CWDS CALS API Team
 */
public class AddressPhoneticNamePK implements Serializable {

  private static final long serialVersionUID = -5529227592027434298L;
  
  @Id
  @Column(name = "PHONETC_NM", nullable = false, length = 8)
  private String phonetcNm;

  @Id
  @Column(name = "PRMRY_NMID", nullable = false, length = 10)
  private String prmryNmid;

  @Id
  @Column(name = "PRMRY_NMCD", nullable = false, length = 1)
  private String prmryNmcd;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AddressPhoneticNamePK that = (AddressPhoneticNamePK) o;

    if (phonetcNm != null ? !phonetcNm.equals(that.phonetcNm) : that.phonetcNm != null) {
      return false;
    }
    if (prmryNmid != null ? !prmryNmid.equals(that.prmryNmid) : that.prmryNmid != null) {
      return false;
    }
    if (prmryNmcd != null ? !prmryNmcd.equals(that.prmryNmcd) : that.prmryNmcd != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = phonetcNm != null ? phonetcNm.hashCode() : 0;
    result = 31 * result + (prmryNmid != null ? prmryNmid.hashCode() : 0);
    result = 31 * result + (prmryNmcd != null ? prmryNmcd.hashCode() : 0);
    return result;
  }
}
