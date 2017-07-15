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
@Table(name = "SCP_PHTT")
@IdClass(SubCareProviderPhoneticNamePK.class)
public class SubCareProviderPhoneticName {

  @Id
  @Column(name = "PHONETC_NM", nullable = false, length = 8)
  private String phonetcNm;

  @Id
  @Column(name = "FKSB_PVDRT", nullable = false, length = 10)
  private String fksbPvdrt;

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

  public String getFksbPvdrt() {
    return fksbPvdrt;
  }

  public void setFksbPvdrt(String fksbPvdrt) {
    this.fksbPvdrt = fksbPvdrt;
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

    SubCareProviderPhoneticName that = (SubCareProviderPhoneticName) o;

    if (phonetcNm != null ? !phonetcNm.equals(that.phonetcNm) : that.phonetcNm != null) {
      return false;
    }
    if (fksbPvdrt != null ? !fksbPvdrt.equals(that.fksbPvdrt) : that.fksbPvdrt != null) {
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
    result = 31 * result + (fksbPvdrt != null ? fksbPvdrt.hashCode() : 0);
    result = 31 * result + (lstUpdId != null ? lstUpdId.hashCode() : 0);
    result = 31 * result + (lstUpdTs != null ? lstUpdTs.hashCode() : 0);
    return result;
  }
}
