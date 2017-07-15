package gov.ca.cwds.cals.persistence.model.cms;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import org.joda.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 */
@Entity
@Table(name = "EXTINF_T")
@IdClass(ExternalInterfacePK.class)
public class ExternalInterface {

  @Id
  @Column(name = "SEQ_NO", nullable = false)
  private Integer seqNo;

  @Id
  @Column(name = "SUBMTL_TS", nullable = false)
  private LocalDateTime submtlTs;

  @Basic
  @Column(name = "TABLE_NAME", nullable = false, length = 8)
  private String tableName;

  @Basic
  @Column(name = "OPER_TYPE", nullable = false, length = 1)
  private String operType;

  @Basic
  @Column(name = "PRM_KEY_1", nullable = false, length = 10)
  private String prmKey1;

  @Basic
  @Column(name = "PRM_KEY_2", nullable = false, length = 10)
  private String prmKey2;

  @Basic
  @Column(name = "PRM_KEY_3", nullable = false, length = 10)
  private String prmKey3;

  @Basic
  @Column(name = "PRM_KEY_4", nullable = false, length = 10)
  private String prmKey4;

  @Basic
  @Column(name = "PRM_KEY_5", nullable = false, length = 10)
  private String prmKey5;

  @Basic
  @Column(name = "PRM_KEY_6", nullable = false, length = 10)
  private String prmKey6;

  @Basic
  @Column(name = "PRM_KEY_7", nullable = false, length = 10)
  private String prmKey7;

  @Basic
  @Column(name = "PRM_KEY_8", nullable = false, length = 10)
  private String prmKey8;

  @Basic
  @Column(name = "START_DT", nullable = false, length = 10)
  private String startDt;

  @Basic
  @Column(name = "AST_UNT_CD", nullable = false, length = 1)
  private String astUntCd;

  @Basic
  @Column(name = "PERSON_NO", nullable = false, length = 2)
  private String personNo;

  @Basic
  @Column(name = "SERIAL_NO", nullable = false, length = 7)
  private String serialNo;

  @Basic
  @Column(name = "AID_TPC", nullable = false)
  private Short aidTpc;

  @Basic
  @Column(name = "GVR_ENTC", nullable = false)
  private Short gvrEntc;

  @Basic
  @Column(name = "LICENSE_NO", nullable = false, length = 9)
  private String licenseNo;

  @Basic
  @Column(name = "RESPONS_DT", nullable = false, length = 10)
  private String responsDt;

  @Basic
  @Column(name = "RECEIVE_DT", nullable = false, length = 10)
  private String receiveDt;

  @Basic
  @Column(name = "RAP_ID", nullable = false, length = 10)
  private String rapId;

  @Basic
  @Column(name = "FBI_IND", nullable = false, length = 1)
  private String fbiInd;

  @Basic
  @Column(name = "CRSP_TPC", nullable = false)
  private Short crspTpc;

  @Basic
  @Column(name = "OTHER_DATA", nullable = false, length = 254)
  private String otherData;

  @Id
  @Column(name = "L_USERID", nullable = false, length = 8)
  private String lUserid;

  public Integer getSeqNo() {
    return seqNo;
  }

  public void setSeqNo(Integer seqNo) {
    this.seqNo = seqNo;
  }

  public LocalDateTime getSubmtlTs() {
    return submtlTs;
  }

  public void setSubmtlTs(LocalDateTime submtlTs) {
    this.submtlTs = submtlTs;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getOperType() {
    return operType;
  }

  public void setOperType(String operType) {
    this.operType = operType;
  }

  public String getPrmKey1() {
    return prmKey1;
  }

  public void setPrmKey1(String prmKey1) {
    this.prmKey1 = prmKey1;
  }

  public String getPrmKey2() {
    return prmKey2;
  }

  public void setPrmKey2(String prmKey2) {
    this.prmKey2 = prmKey2;
  }

  public String getPrmKey3() {
    return prmKey3;
  }

  public void setPrmKey3(String prmKey3) {
    this.prmKey3 = prmKey3;
  }

  public String getPrmKey4() {
    return prmKey4;
  }

  public void setPrmKey4(String prmKey4) {
    this.prmKey4 = prmKey4;
  }

  public String getPrmKey5() {
    return prmKey5;
  }

  public void setPrmKey5(String prmKey5) {
    this.prmKey5 = prmKey5;
  }

  public String getPrmKey6() {
    return prmKey6;
  }

  public void setPrmKey6(String prmKey6) {
    this.prmKey6 = prmKey6;
  }

  public String getPrmKey7() {
    return prmKey7;
  }

  public void setPrmKey7(String prmKey7) {
    this.prmKey7 = prmKey7;
  }

  public String getPrmKey8() {
    return prmKey8;
  }

  public void setPrmKey8(String prmKey8) {
    this.prmKey8 = prmKey8;
  }

  public String getStartDt() {
    return startDt;
  }

  public void setStartDt(String startDt) {
    this.startDt = startDt;
  }

  public String getAstUntCd() {
    return astUntCd;
  }

  public void setAstUntCd(String astUntCd) {
    this.astUntCd = astUntCd;
  }

  public String getPersonNo() {
    return personNo;
  }

  public void setPersonNo(String personNo) {
    this.personNo = personNo;
  }

  public String getSerialNo() {
    return serialNo;
  }

  public void setSerialNo(String serialNo) {
    this.serialNo = serialNo;
  }

  public Short getAidTpc() {
    return aidTpc;
  }

  public void setAidTpc(Short aidTpc) {
    this.aidTpc = aidTpc;
  }

  public Short getGvrEntc() {
    return gvrEntc;
  }

  public void setGvrEntc(Short gvrEntc) {
    this.gvrEntc = gvrEntc;
  }

  public String getLicenseNo() {
    return licenseNo;
  }

  public void setLicenseNo(String licenseNo) {
    this.licenseNo = licenseNo;
  }

  public String getResponsDt() {
    return responsDt;
  }

  public void setResponsDt(String responsDt) {
    this.responsDt = responsDt;
  }

  public String getReceiveDt() {
    return receiveDt;
  }

  public void setReceiveDt(String receiveDt) {
    this.receiveDt = receiveDt;
  }

  public String getRapId() {
    return rapId;
  }

  public void setRapId(String rapId) {
    this.rapId = rapId;
  }

  public String getFbiInd() {
    return fbiInd;
  }

  public void setFbiInd(String fbiInd) {
    this.fbiInd = fbiInd;
  }

  public Short getCrspTpc() {
    return crspTpc;
  }

  public void setCrspTpc(Short crspTpc) {
    this.crspTpc = crspTpc;
  }

  public String getOtherData() {
    return otherData;
  }

  public void setOtherData(String otherData) {
    this.otherData = otherData;
  }

  public String getlUserid() {
    return lUserid;
  }

  public void setlUserid(String lUserid) {
    this.lUserid = lUserid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ExternalInterface that = (ExternalInterface) o;

    if (seqNo != that.seqNo) {
      return false;
    }
    if (aidTpc != that.aidTpc) {
      return false;
    }
    if (gvrEntc != that.gvrEntc) {
      return false;
    }
    if (crspTpc != that.crspTpc) {
      return false;
    }
    if (submtlTs != null ? !submtlTs.equals(that.submtlTs) : that.submtlTs != null) {
      return false;
    }
    if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) {
      return false;
    }
    if (operType != null ? !operType.equals(that.operType) : that.operType != null) {
      return false;
    }
    if (prmKey1 != null ? !prmKey1.equals(that.prmKey1) : that.prmKey1 != null) {
      return false;
    }
    if (prmKey2 != null ? !prmKey2.equals(that.prmKey2) : that.prmKey2 != null) {
      return false;
    }
    if (prmKey3 != null ? !prmKey3.equals(that.prmKey3) : that.prmKey3 != null) {
      return false;
    }
    if (prmKey4 != null ? !prmKey4.equals(that.prmKey4) : that.prmKey4 != null) {
      return false;
    }
    if (prmKey5 != null ? !prmKey5.equals(that.prmKey5) : that.prmKey5 != null) {
      return false;
    }
    if (prmKey6 != null ? !prmKey6.equals(that.prmKey6) : that.prmKey6 != null) {
      return false;
    }
    if (prmKey7 != null ? !prmKey7.equals(that.prmKey7) : that.prmKey7 != null) {
      return false;
    }
    if (prmKey8 != null ? !prmKey8.equals(that.prmKey8) : that.prmKey8 != null) {
      return false;
    }
    if (startDt != null ? !startDt.equals(that.startDt) : that.startDt != null) {
      return false;
    }
    if (astUntCd != null ? !astUntCd.equals(that.astUntCd) : that.astUntCd != null) {
      return false;
    }
    if (personNo != null ? !personNo.equals(that.personNo) : that.personNo != null) {
      return false;
    }
    if (serialNo != null ? !serialNo.equals(that.serialNo) : that.serialNo != null) {
      return false;
    }
    if (licenseNo != null ? !licenseNo.equals(that.licenseNo) : that.licenseNo != null) {
      return false;
    }
    if (responsDt != null ? !responsDt.equals(that.responsDt) : that.responsDt != null) {
      return false;
    }
    if (receiveDt != null ? !receiveDt.equals(that.receiveDt) : that.receiveDt != null) {
      return false;
    }
    if (rapId != null ? !rapId.equals(that.rapId) : that.rapId != null) {
      return false;
    }
    if (fbiInd != null ? !fbiInd.equals(that.fbiInd) : that.fbiInd != null) {
      return false;
    }
    if (otherData != null ? !otherData.equals(that.otherData) : that.otherData != null) {
      return false;
    }
    if (lUserid != null ? !lUserid.equals(that.lUserid) : that.lUserid != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = seqNo;
    result = 31 * result + (submtlTs != null ? submtlTs.hashCode() : 0);
    result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
    result = 31 * result + (operType != null ? operType.hashCode() : 0);
    result = 31 * result + (prmKey1 != null ? prmKey1.hashCode() : 0);
    result = 31 * result + (prmKey2 != null ? prmKey2.hashCode() : 0);
    result = 31 * result + (prmKey3 != null ? prmKey3.hashCode() : 0);
    result = 31 * result + (prmKey4 != null ? prmKey4.hashCode() : 0);
    result = 31 * result + (prmKey5 != null ? prmKey5.hashCode() : 0);
    result = 31 * result + (prmKey6 != null ? prmKey6.hashCode() : 0);
    result = 31 * result + (prmKey7 != null ? prmKey7.hashCode() : 0);
    result = 31 * result + (prmKey8 != null ? prmKey8.hashCode() : 0);
    result = 31 * result + (startDt != null ? startDt.hashCode() : 0);
    result = 31 * result + (astUntCd != null ? astUntCd.hashCode() : 0);
    result = 31 * result + (personNo != null ? personNo.hashCode() : 0);
    result = 31 * result + (serialNo != null ? serialNo.hashCode() : 0);
    result = 31 * result + (int) aidTpc;
    result = 31 * result + (int) gvrEntc;
    result = 31 * result + (licenseNo != null ? licenseNo.hashCode() : 0);
    result = 31 * result + (responsDt != null ? responsDt.hashCode() : 0);
    result = 31 * result + (receiveDt != null ? receiveDt.hashCode() : 0);
    result = 31 * result + (rapId != null ? rapId.hashCode() : 0);
    result = 31 * result + (fbiInd != null ? fbiInd.hashCode() : 0);
    result = 31 * result + (int) crspTpc;
    result = 31 * result + (otherData != null ? otherData.hashCode() : 0);
    result = 31 * result + (lUserid != null ? lUserid.hashCode() : 0);
    return result;
  }
}
