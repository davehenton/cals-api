package gov.ca.cwds.cals.persistence.model.cms;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author CWDS CALS API Team
 */
public class SubCareProviderPhoneticNamePK implements Serializable {

  @Column(name = "PHONETC_NM", nullable = false, length = 8)
  @Id
  private String phonetcNm;

  @Column(name = "FKSB_PVDRT", nullable = false, length = 10)
  @Id
  private String fksbPvdrt;

  public String getPhonetcNm() {
    return phonetcNm;
  }

  public void setPhonetcNm(String phonetcNm) {
    this.phonetcNm = phonetcNm;
  }

  public String getFksbPvdrt() {
    return fksbPvdrt;
  }

  public void setFksbPvdrt(String fksbPvdrt) {
    this.fksbPvdrt = fksbPvdrt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SubCareProviderPhoneticNamePK that = (SubCareProviderPhoneticNamePK) o;

    if (phonetcNm != null ? !phonetcNm.equals(that.phonetcNm) : that.phonetcNm != null) {
      return false;
    }
    if (fksbPvdrt != null ? !fksbPvdrt.equals(that.fksbPvdrt) : that.fksbPvdrt != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = phonetcNm != null ? phonetcNm.hashCode() : 0;
    result = 31 * result + (fksbPvdrt != null ? fksbPvdrt.hashCode() : 0);
    return result;
  }
}
