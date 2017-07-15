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
@Table(name = "SBPVD_UC")
public class SubstituteCareProviderUc {

  @Id
  @Column(name = "PKSB_PVDRT", nullable = false, length = 10)
  private String pksbPvdrt;

  @Basic
  @Column(name = "CA_DLIC_NO", nullable = false, length = 8)
  private String caDlicNo;

  @Basic
  @Column(name = "FIRST_NM", nullable = false, length = 20)
  private String firstNm;

  @Basic
  @Column(name = "LAST_NM", nullable = false, length = 25)
  private String lastNm;

  @Basic
  @Column(name = "LST_UPD_ID", nullable = false, length = 3)
  private String lstUpdId;

  @Basic
  @Column(name = "LST_UPD_TS", nullable = false)
  private LocalDateTime lstUpdTs;

  public String getPksbPvdrt() {
    return pksbPvdrt;
  }

  public void setPksbPvdrt(String pksbPvdrt) {
    this.pksbPvdrt = pksbPvdrt;
  }

  public String getCaDlicNo() {
    return caDlicNo;
  }

  public void setCaDlicNo(String caDlicNo) {
    this.caDlicNo = caDlicNo;
  }

  public String getFirstNm() {
    return firstNm;
  }

  public void setFirstNm(String firstNm) {
    this.firstNm = firstNm;
  }

  public String getLastNm() {
    return lastNm;
  }

  public void setLastNm(String lastNm) {
    this.lastNm = lastNm;
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

    SubstituteCareProviderUc that = (SubstituteCareProviderUc) o;

    if (pksbPvdrt != null ? !pksbPvdrt.equals(that.pksbPvdrt) : that.pksbPvdrt != null) {
      return false;
    }
    if (caDlicNo != null ? !caDlicNo.equals(that.caDlicNo) : that.caDlicNo != null) {
      return false;
    }
    if (firstNm != null ? !firstNm.equals(that.firstNm) : that.firstNm != null) {
      return false;
    }
    if (lastNm != null ? !lastNm.equals(that.lastNm) : that.lastNm != null) {
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
    int result = pksbPvdrt != null ? pksbPvdrt.hashCode() : 0;
    result = 31 * result + (caDlicNo != null ? caDlicNo.hashCode() : 0);
    result = 31 * result + (firstNm != null ? firstNm.hashCode() : 0);
    result = 31 * result + (lastNm != null ? lastNm.hashCode() : 0);
    result = 31 * result + (lstUpdId != null ? lstUpdId.hashCode() : 0);
    result = 31 * result + (lstUpdTs != null ? lstUpdTs.hashCode() : 0);
    return result;
  }
}
