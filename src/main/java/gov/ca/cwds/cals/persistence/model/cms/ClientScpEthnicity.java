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
@Table(name = "CLSCP_ET")
public class ClientScpEthnicity {

  @Id
  @Column(name = "IDENTIFIER", nullable = false, length = 10)
  private String identifier;

  @Basic
  @Column(name = "ETHNCTYC", nullable = false)
  private Short ethnctyc;

  @Basic
  @Column(name = "ESTBLSH_ID", nullable = false, length = 10)
  private String estblshId;

  @Basic
  @Column(name = "ESTBLSH_CD", nullable = false, length = 1)
  private String estblshCd;

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

  public Short getEthnctyc() {
    return ethnctyc;
  }

  public void setEthnctyc(Short ethnctyc) {
    this.ethnctyc = ethnctyc;
  }

  public String getEstblshId() {
    return estblshId;
  }

  public void setEstblshId(String estblshId) {
    this.estblshId = estblshId;
  }

  public String getEstblshCd() {
    return estblshCd;
  }

  public void setEstblshCd(String estblshCd) {
    this.estblshCd = estblshCd;
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

    ClientScpEthnicity that = (ClientScpEthnicity) o;

    if (ethnctyc != that.ethnctyc) {
      return false;
    }
    if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) {
      return false;
    }
    if (estblshId != null ? !estblshId.equals(that.estblshId) : that.estblshId != null) {
      return false;
    }
    if (estblshCd != null ? !estblshCd.equals(that.estblshCd) : that.estblshCd != null) {
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
    result = 31 * result + (int) ethnctyc;
    result = 31 * result + (estblshId != null ? estblshId.hashCode() : 0);
    result = 31 * result + (estblshCd != null ? estblshCd.hashCode() : 0);
    result = 31 * result + (lstUpdId != null ? lstUpdId.hashCode() : 0);
    result = 31 * result + (lstUpdTs != null ? lstUpdTs.hashCode() : 0);
    return result;
  }
}
