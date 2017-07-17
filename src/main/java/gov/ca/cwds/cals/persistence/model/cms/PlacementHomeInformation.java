package gov.ca.cwds.cals.persistence.model.cms;

import java.sql.Date;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team
 */
@Entity
@Table(name = "HM_SCP_T")
@IdClass(PlacementHomeInformationPK.class)
public class PlacementHomeInformation {

  @Id
  @Column(name = "THIRD_ID", nullable = false, length = 10)
  private String thirdId;

  @Basic
  @Column(name = "START_DT", nullable = false)
  private Date startDt;

  @Basic
  @Column(name = "END_DT", nullable = true)
  private Date endDt;

  @Basic
  @Column(name = "LICNSEE_CD", nullable = false, length = 1)
  private String licnseeCd;

  @Basic
  @Column(name = "CRPRVDR_CD", nullable = false, length = 1)
  private String crprvdrCd;

  @Basic
  @Column(name = "LST_UPD_ID", nullable = false, length = 3)
  private String lstUpdId;

  @Basic
  @Column(name = "LST_UPD_TS", nullable = false)
  private LocalDateTime lstUpdTs;

  @Id
  @Column(name = "FKSB_PVDRT", nullable = false, length = 10)
  private String fksbPvdrt;

  @Id
  @Column(name = "FKPLC_HM_T", nullable = false, length = 10)
  private String fkplcHmT;

  @Basic
  @Column(name = "PRPRVDR_CD", nullable = false, length = 1)
  private String prprvdrCd;

  @Basic
  @Column(name = "CDS_PRSN", nullable = false, length = 2)
  private String cdsPrsn;

  @Basic
  @Column(name = "SCPRVD_IND", nullable = false, length = 1)
  private String scprvdInd;

  public String getThirdId() {
    return thirdId;
  }

  public void setThirdId(String thirdId) {
    this.thirdId = thirdId;
  }

  public Date getStartDt() {
    return startDt;
  }

  public void setStartDt(Date startDt) {
    this.startDt = startDt;
  }

  public Date getEndDt() {
    return endDt;
  }

  public void setEndDt(Date endDt) {
    this.endDt = endDt;
  }

  public String getLicnseeCd() {
    return licnseeCd;
  }

  public void setLicnseeCd(String licnseeCd) {
    this.licnseeCd = licnseeCd;
  }

  public String getCrprvdrCd() {
    return crprvdrCd;
  }

  public void setCrprvdrCd(String crprvdrCd) {
    this.crprvdrCd = crprvdrCd;
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

  public String getFksbPvdrt() {
    return fksbPvdrt;
  }

  public void setFksbPvdrt(String fksbPvdrt) {
    this.fksbPvdrt = fksbPvdrt;
  }

  public String getFkplcHmT() {
    return fkplcHmT;
  }

  public void setFkplcHmT(String fkplcHmT) {
    this.fkplcHmT = fkplcHmT;
  }

  public String getPrprvdrCd() {
    return prprvdrCd;
  }

  public void setPrprvdrCd(String prprvdrCd) {
    this.prprvdrCd = prprvdrCd;
  }

  public String getCdsPrsn() {
    return cdsPrsn;
  }

  public void setCdsPrsn(String cdsPrsn) {
    this.cdsPrsn = cdsPrsn;
  }

  public String getScprvdInd() {
    return scprvdInd;
  }

  public void setScprvdInd(String scprvdInd) {
    this.scprvdInd = scprvdInd;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}
