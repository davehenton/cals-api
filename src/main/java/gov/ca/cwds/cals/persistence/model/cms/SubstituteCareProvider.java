package gov.ca.cwds.cals.persistence.model.cms;

import gov.ca.cwds.data.persistence.PersistentObject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team
 */
@Entity
@Table(name = "SB_PVDRT")
@SuppressWarnings({"squid:S3437"}) //LocalDate is serializable
public class SubstituteCareProvider implements PersistentObject {

  private static final long serialVersionUID = -3252555869369790341L;

  @Id
  @Column(name = "IDENTIFIER", nullable = false, length = 10)
  private String identifier;

  @Basic
  @Column(name = "ADD_TEL_NO", nullable = false, precision = 0)
  private int addTelNo;

  @Basic
  @Column(name = "ADD_EXT_NO", nullable = false)
  private int addExtNo;

  @Basic
  @Column(name = "BIRTH_DT", nullable = true)
  private LocalDate birthDt;

  @Basic
  @Column(name = "CA_DLIC_NO", nullable = false, length = 8)
  private String caDlicNo;

  @Basic
  @Column(name = "CITY_NM", nullable = false, length = 20)
  private String cityNm;

  @Basic
  @Column(name = "EMPLYR_NM", nullable = false, length = 35)
  private String emplyrNm;

  @Basic
  @Column(name = "FIRST_NM", nullable = false, length = 20)
  private String firstNm;

  @Basic
  @Column(name = "FRG_ADRT_B", nullable = false, length = 1)
  private String frgAdrtB;

  @Basic
  @Column(name = "GENDER_IND", nullable = false, length = 1)
  private String genderInd;

  @Basic
  @Column(name = "IND_TRBC", nullable = false)
  private short indTrbc;

  @Basic
  @Column(name = "LAST_NM", nullable = false, length = 25)
  private String lastNm;

  @Basic
  @Column(name = "MID_INI_NM", nullable = false, length = 1)
  private String midIniNm;

  @Basic
  @Column(name = "NMPRFX_DSC", nullable = false, length = 6)
  private String nmprfxDsc;

  @Basic
  @Column(name = "SS_NO", nullable = false, length = 9)
  private String ssNo;

  @Basic
  @Column(name = "STATE_C", nullable = false)
  private short stateC;

  @Basic
  @Column(name = "STREET_NM", nullable = false, length = 40)
  private String streetNm;

  @Basic
  @Column(name = "STREET_NO", nullable = false, length = 10)
  private String streetNo;

  @Basic
  @Column(name = "SUFX_TLDSC", nullable = false, length = 4)
  private String sufxTldsc;

  @Basic
  @Column(name = "ZIP_NO", nullable = false)
  private int zipNo;

  @Basic
  @Column(name = "LST_UPD_ID", nullable = false, length = 3)
  private String lstUpdId;

  @Basic
  @Column(name = "LST_UPD_TS", nullable = false)
  private LocalDateTime lstUpdTs;

  @Basic
  @Column(name = "ZIP_SFX_NO", nullable = false)
  private short zipSfxNo;

  @Basic
  @Column(name = "EDUCATION", nullable = false)
  private short education;

  @Basic
  @Column(name = "EMPL_STAT", nullable = false)
  private short emplStat;

  @Basic
  @Column(name = "PRIM_INC", nullable = false)
  private short primInc;

  @Basic
  @Column(name = "SEC_INC", nullable = false)
  private short secInc;

  @Basic
  @Column(name = "YR_INC_AMT", nullable = false, precision = 2)
  private BigDecimal yrIncAmt;

  @Basic
  @Column(name = "HISP_CD", nullable = false, length = 1)
  private String hispCd;

  @Basic
  @Column(name = "MRTL_STC", nullable = false)
  private short mrtlStc;

  @Basic
  @Column(name = "LISOWNIND", nullable = false, length = 1)
  private String lisownind;

  @Basic
  @Column(name = "LIS_PER_ID", nullable = true, length = 10)
  private String lisPerId;

  @Basic
  @Column(name = "EMAIL_ADDR", nullable = true, length = 50)
  private String emailAddr;

  @Basic
  @Column(name = "ETH_UD_CD", nullable = true, length = 1)
  private String ethUdCd;

  @Basic
  @Column(name = "HISP_UD_CD", nullable = true, length = 1)
  private String hispUdCd;

  @Basic
  @Column(name = "RESOST_IND", nullable = true, length = 1)
  private String resostInd;

  @Basic
  @Column(name = "PASSBC_CD", nullable = true, length = 1)
  private String passbcCd;

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public int getAddTelNo() {
    return addTelNo;
  }

  public void setAddTelNo(int addTelNo) {
    this.addTelNo = addTelNo;
  }

  public int getAddExtNo() {
    return addExtNo;
  }

  public void setAddExtNo(int addExtNo) {
    this.addExtNo = addExtNo;
  }

  public LocalDate getBirthDt() {
    return birthDt;
  }

  public void setBirthDt(LocalDate birthDt) {
    this.birthDt = birthDt;
  }

  public String getCaDlicNo() {
    return caDlicNo;
  }

  public void setCaDlicNo(String caDlicNo) {
    this.caDlicNo = caDlicNo;
  }

  public String getCityNm() {
    return cityNm;
  }

  public void setCityNm(String cityNm) {
    this.cityNm = cityNm;
  }

  public String getEmplyrNm() {
    return emplyrNm;
  }

  public void setEmplyrNm(String emplyrNm) {
    this.emplyrNm = emplyrNm;
  }

  public String getFirstNm() {
    return firstNm;
  }

  public void setFirstNm(String firstNm) {
    this.firstNm = firstNm;
  }

  public String getFrgAdrtB() {
    return frgAdrtB;
  }

  public void setFrgAdrtB(String frgAdrtB) {
    this.frgAdrtB = frgAdrtB;
  }

  public String getGenderInd() {
    return genderInd;
  }

  public void setGenderInd(String genderInd) {
    this.genderInd = genderInd;
  }

  public short getIndTrbc() {
    return indTrbc;
  }

  public void setIndTrbc(short indTrbc) {
    this.indTrbc = indTrbc;
  }

  public String getLastNm() {
    return lastNm;
  }

  public void setLastNm(String lastNm) {
    this.lastNm = lastNm;
  }

  public String getMidIniNm() {
    return midIniNm;
  }

  public void setMidIniNm(String midIniNm) {
    this.midIniNm = midIniNm;
  }

  public String getNmprfxDsc() {
    return nmprfxDsc;
  }

  public void setNmprfxDsc(String nmprfxDsc) {
    this.nmprfxDsc = nmprfxDsc;
  }

  public String getSsNo() {
    return ssNo;
  }

  public void setSsNo(String ssNo) {
    this.ssNo = ssNo;
  }

  public short getStateC() {
    return stateC;
  }

  public void setStateC(short stateC) {
    this.stateC = stateC;
  }

  public String getStreetNm() {
    return streetNm;
  }

  public void setStreetNm(String streetNm) {
    this.streetNm = streetNm;
  }

  public String getStreetNo() {
    return streetNo;
  }

  public void setStreetNo(String streetNo) {
    this.streetNo = streetNo;
  }

  public String getSufxTldsc() {
    return sufxTldsc;
  }

  public void setSufxTldsc(String sufxTldsc) {
    this.sufxTldsc = sufxTldsc;
  }

  public int getZipNo() {
    return zipNo;
  }

  public void setZipNo(int zipNo) {
    this.zipNo = zipNo;
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

  public short getZipSfxNo() {
    return zipSfxNo;
  }

  public void setZipSfxNo(short zipSfxNo) {
    this.zipSfxNo = zipSfxNo;
  }

  public short getEducation() {
    return education;
  }

  public void setEducation(short education) {
    this.education = education;
  }

  public short getEmplStat() {
    return emplStat;
  }

  public void setEmplStat(short emplStat) {
    this.emplStat = emplStat;
  }

  public short getPrimInc() {
    return primInc;
  }

  public void setPrimInc(short primInc) {
    this.primInc = primInc;
  }

  public short getSecInc() {
    return secInc;
  }

  public void setSecInc(short secInc) {
    this.secInc = secInc;
  }

  public BigDecimal getYrIncAmt() {
    return yrIncAmt;
  }

  public void setYrIncAmt(BigDecimal yrIncAmt) {
    this.yrIncAmt = yrIncAmt;
  }

  public String getHispCd() {
    return hispCd;
  }

  public void setHispCd(String hispCd) {
    this.hispCd = hispCd;
  }

  public short getMrtlStc() {
    return mrtlStc;
  }

  public void setMrtlStc(short mrtlStc) {
    this.mrtlStc = mrtlStc;
  }

  public String getLisownind() {
    return lisownind;
  }

  public void setLisownind(String lisownind) {
    this.lisownind = lisownind;
  }

  public String getLisPerId() {
    return lisPerId;
  }

  public void setLisPerId(String lisPerId) {
    this.lisPerId = lisPerId;
  }

  public String getEmailAddr() {
    return emailAddr;
  }

  public void setEmailAddr(String emailAddr) {
    this.emailAddr = emailAddr;
  }

  public String getEthUdCd() {
    return ethUdCd;
  }

  public void setEthUdCd(String ethUdCd) {
    this.ethUdCd = ethUdCd;
  }

  public String getHispUdCd() {
    return hispUdCd;
  }

  public void setHispUdCd(String hispUdCd) {
    this.hispUdCd = hispUdCd;
  }

  public String getResostInd() {
    return resostInd;
  }

  public void setResostInd(String resostInd) {
    this.resostInd = resostInd;
  }

  public String getPassbcCd() {
    return passbcCd;
  }

  public void setPassbcCd(String passbcCd) {
    this.passbcCd = passbcCd;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public Serializable getPrimaryKey() {
    return getIdentifier();
  }
}
