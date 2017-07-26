package gov.ca.cwds.cals.persistence.model.fas;

import gov.ca.cwds.data.persistence.PersistentObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(
    name = LpaInformation.FIND_ASSIGNED_WORKER_BY_LPA_CODE,
    query = "SELECT l FROM LpaInformation l WHERE l.lpaCode = :" +
        LpaInformation.PARAM_LPA_CODE
)
@Entity
@Table(name = "lpa_information")
@SuppressWarnings("squid:S3437") //LocalDate is serializable
public class LpaInformation implements PersistentObject {
  private static final long serialVersionUID = -3566583736496919788L;

  public static final String FIND_ASSIGNED_WORKER_BY_LPA_CODE = "findAssignedWorkerByLPACode";
  public static final String PARAM_LPA_CODE = "lpaCode";

  private String docUnid;
  private String locAddressCode;
  private String lpacodesave;
  private String deleteFlag;
  private String editors;
  private String fullLpaName;
  private String dontSaveFlag;
  private String responseDescription;
  private String auditHistory;
  private String originalunidkey;
  private LocalDateTime dtEdited;
  private String editorName;
  private String editHistory;
  private LocalDateTime dtCreated;
  private String creatorName;
  private String doEmailAddress;
  private String lpaDoPhone;
  private String lpaDoZip;
  private String lpaDoCity;
  private String lpaDoStreet;
  private String lpaDoName;
  private String lpaSupEmailAddr;
  private String lpaSupTelephone;
  private String lpaSup;
  private String lpaEmailAddress;
  private String lpaTelephone;
  private String lpaName;
  private String lpaCode;
  private String lpaRgnlMgr;
  private String lpaLocCode;
  private String accessLevel;
  private String programCode;
  private String keydbname;
  private String dbtitle;
  private String dbfilename;
  private String dbpathname;
  private String dbservername;
  private String noexport;
  private LocalDateTime dtModified;

  @Basic
  @Column(name = "doc_unid")
  public String getDocUnid() {
    return docUnid;
  }

  public void setDocUnid(String docUnid) {
    this.docUnid = docUnid;
  }

  @Basic
  @Column(name = "loc_address_code")
  public String getLocAddressCode() {
    return locAddressCode;
  }

  public void setLocAddressCode(String locAddressCode) {
    this.locAddressCode = locAddressCode;
  }

  @Basic
  @Column(name = "lpacodesave")
  public String getLpacodesave() {
    return lpacodesave;
  }

  public void setLpacodesave(String lpacodesave) {
    this.lpacodesave = lpacodesave;
  }

  @Basic
  @Column(name = "delete_flag")
  public String getDeleteFlag() {
    return deleteFlag;
  }

  public void setDeleteFlag(String deleteFlag) {
    this.deleteFlag = deleteFlag;
  }

  @Basic
  @Column(name = "editors")
  public String getEditors() {
    return editors;
  }

  public void setEditors(String editors) {
    this.editors = editors;
  }

  @Basic
  @Column(name = "full_lpa_name")
  public String getFullLpaName() {
    return fullLpaName;
  }

  public void setFullLpaName(String fullLpaName) {
    this.fullLpaName = fullLpaName;
  }

  @Basic
  @Column(name = "dont_save_flag")
  public String getDontSaveFlag() {
    return dontSaveFlag;
  }

  public void setDontSaveFlag(String dontSaveFlag) {
    this.dontSaveFlag = dontSaveFlag;
  }

  @Basic
  @Column(name = "response_description")
  public String getResponseDescription() {
    return responseDescription;
  }

  public void setResponseDescription(String responseDescription) {
    this.responseDescription = responseDescription;
  }

  @Basic
  @Column(name = "audit_history")
  public String getAuditHistory() {
    return auditHistory;
  }

  public void setAuditHistory(String auditHistory) {
    this.auditHistory = auditHistory;
  }

  @Basic
  @Column(name = "originalunidkey")
  public String getOriginalunidkey() {
    return originalunidkey;
  }

  public void setOriginalunidkey(String originalunidkey) {
    this.originalunidkey = originalunidkey;
  }

  @Basic
  @Column(name = "dt_edited")
  public LocalDateTime getDtEdited() {
    return dtEdited;
  }

  public void setDtEdited(LocalDateTime dtEdited) {
    this.dtEdited = dtEdited;
  }

  @Basic
  @Column(name = "editor_name")
  public String getEditorName() {
    return editorName;
  }

  public void setEditorName(String editorName) {
    this.editorName = editorName;
  }

  @Basic
  @Column(name = "edit_history")
  public String getEditHistory() {
    return editHistory;
  }

  public void setEditHistory(String editHistory) {
    this.editHistory = editHistory;
  }

  @Basic
  @Column(name = "dt_created")
  public LocalDateTime getDtCreated() {
    return dtCreated;
  }

  public void setDtCreated(LocalDateTime dtCreated) {
    this.dtCreated = dtCreated;
  }

  @Basic
  @Column(name = "creator_name")
  public String getCreatorName() {
    return creatorName;
  }

  public void setCreatorName(String creatorName) {
    this.creatorName = creatorName;
  }

  @Basic
  @Column(name = "do_email_address")
  public String getDoEmailAddress() {
    return doEmailAddress;
  }

  public void setDoEmailAddress(String doEmailAddress) {
    this.doEmailAddress = doEmailAddress;
  }

  @Basic
  @Column(name = "lpa_do_phone")
  public String getLpaDoPhone() {
    return lpaDoPhone;
  }

  public void setLpaDoPhone(String lpaDoPhone) {
    this.lpaDoPhone = lpaDoPhone;
  }

  @Basic
  @Column(name = "lpa_do_zip")
  public String getLpaDoZip() {
    return lpaDoZip;
  }

  public void setLpaDoZip(String lpaDoZip) {
    this.lpaDoZip = lpaDoZip;
  }

  @Basic
  @Column(name = "lpa_do_city")
  public String getLpaDoCity() {
    return lpaDoCity;
  }

  public void setLpaDoCity(String lpaDoCity) {
    this.lpaDoCity = lpaDoCity;
  }

  @Basic
  @Column(name = "lpa_do_street")
  public String getLpaDoStreet() {
    return lpaDoStreet;
  }

  public void setLpaDoStreet(String lpaDoStreet) {
    this.lpaDoStreet = lpaDoStreet;
  }

  @Basic
  @Column(name = "lpa_do_name")
  public String getLpaDoName() {
    return lpaDoName;
  }

  public void setLpaDoName(String lpaDoName) {
    this.lpaDoName = lpaDoName;
  }

  @Basic
  @Column(name = "lpa_sup_email_addr")
  public String getLpaSupEmailAddr() {
    return lpaSupEmailAddr;
  }

  public void setLpaSupEmailAddr(String lpaSupEmailAddr) {
    this.lpaSupEmailAddr = lpaSupEmailAddr;
  }

  @Basic
  @Column(name = "lpa_sup_telephone")
  public String getLpaSupTelephone() {
    return lpaSupTelephone;
  }

  public void setLpaSupTelephone(String lpaSupTelephone) {
    this.lpaSupTelephone = lpaSupTelephone;
  }

  @Basic
  @Column(name = "lpa_sup")
  public String getLpaSup() {
    return lpaSup;
  }

  public void setLpaSup(String lpaSup) {
    this.lpaSup = lpaSup;
  }

  @Basic
  @Column(name = "lpa_email_address")
  public String getLpaEmailAddress() {
    return lpaEmailAddress;
  }

  public void setLpaEmailAddress(String lpaEmailAddress) {
    this.lpaEmailAddress = lpaEmailAddress;
  }

  @Basic
  @Column(name = "lpa_telephone")
  public String getLpaTelephone() {
    return lpaTelephone;
  }

  public void setLpaTelephone(String lpaTelephone) {
    this.lpaTelephone = lpaTelephone;
  }

  @Basic
  @Column(name = "lpa_name")
  public String getLpaName() {
    return lpaName;
  }

  public void setLpaName(String lpaName) {
    this.lpaName = lpaName;
  }

  @Id
  @Column(name = "lpa_code")
  public String getLpaCode() {
    return lpaCode;
  }

  public void setLpaCode(String lpaCode) {
    this.lpaCode = lpaCode;
  }

  @Basic
  @Column(name = "lpa_rgnl_mgr")
  public String getLpaRgnlMgr() {
    return lpaRgnlMgr;
  }

  public void setLpaRgnlMgr(String lpaRgnlMgr) {
    this.lpaRgnlMgr = lpaRgnlMgr;
  }

  @Basic
  @Column(name = "lpa_loc_code")
  public String getLpaLocCode() {
    return lpaLocCode;
  }

  public void setLpaLocCode(String lpaLocCode) {
    this.lpaLocCode = lpaLocCode;
  }

  @Basic
  @Column(name = "access_level")
  public String getAccessLevel() {
    return accessLevel;
  }

  public void setAccessLevel(String accessLevel) {
    this.accessLevel = accessLevel;
  }

  @Basic
  @Column(name = "program_code")
  public String getProgramCode() {
    return programCode;
  }

  public void setProgramCode(String programCode) {
    this.programCode = programCode;
  }

  @Basic
  @Column(name = "keydbname")
  public String getKeydbname() {
    return keydbname;
  }

  public void setKeydbname(String keydbname) {
    this.keydbname = keydbname;
  }

  @Basic
  @Column(name = "dbtitle")
  public String getDbtitle() {
    return dbtitle;
  }

  public void setDbtitle(String dbtitle) {
    this.dbtitle = dbtitle;
  }

  @Basic
  @Column(name = "dbfilename")
  public String getDbfilename() {
    return dbfilename;
  }

  public void setDbfilename(String dbfilename) {
    this.dbfilename = dbfilename;
  }

  @Basic
  @Column(name = "dbpathname")
  public String getDbpathname() {
    return dbpathname;
  }

  public void setDbpathname(String dbpathname) {
    this.dbpathname = dbpathname;
  }

  @Basic
  @Column(name = "dbservername")
  public String getDbservername() {
    return dbservername;
  }

  public void setDbservername(String dbservername) {
    this.dbservername = dbservername;
  }

  @Basic
  @Column(name = "noexport")
  public String getNoexport() {
    return noexport;
  }

  public void setNoexport(String noexport) {
    this.noexport = noexport;
  }

  @Basic
  @Column(name = "dt_modified")
  public LocalDateTime getDtModified() {
    return dtModified;
  }

  public void setDtModified(LocalDateTime dtModified) {
    this.dtModified = dtModified;
  }

  @Transient
  @Override
  public Serializable getPrimaryKey() {
    return lpaCode;
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
