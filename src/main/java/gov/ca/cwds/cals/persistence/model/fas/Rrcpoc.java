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
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static gov.ca.cwds.cals.persistence.model.fas.Rrcpoc.NQ_FIND_BY_FACILITY_NUMBER;
import static gov.ca.cwds.cals.persistence.model.fas.Rrcpoc.NQ_FIND_BY_FACILITY_NUMBER_AND_INSPECTION_ID;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(
    name = NQ_FIND_BY_FACILITY_NUMBER,
    query = "SELECT pocs FROM Rrcpoc pocs WHERE pocs.facilityNumberText = :facilityNumberText"
)
@NamedQuery(
    name = NQ_FIND_BY_FACILITY_NUMBER_AND_INSPECTION_ID,
    query = "SELECT pocs FROM Rrcpoc pocs WHERE pocs.facilityNumberText = :facilityNumberText " +
        "AND pocs.createDateTime = :inspectionId"
)
@Entity
@Table(name = "rrcpoc")
@SuppressWarnings("squid:S3437") //LocalDate is serializable
public class Rrcpoc implements PersistentObject {
  private static final long serialVersionUID = -6022589439025838467L;

  public static final String NQ_FIND_BY_FACILITY_NUMBER = "Rrcpoc.findByFacilityNumber";
  public static final String NQ_FIND_BY_FACILITY_NUMBER_AND_INSPECTION_ID =
      "Rrcpoc.findByFacilityNumberAndInspectionId";

  private String visitDate;
  private String responseDescription;
  private String dontSaveFlag;
  private String auditHistory;
  private String originalunidkey;
  private ZonedDateTime dtEdited;
  private String editorName;
  private String editHistory;
  private LocalDateTime dtCreated;
  private String creatorName;
  private String pocComments3;
  private LocalDateTime pocDatecleared3;
  private String cpocCorrectionPlan3;
  private String cpocSectionviolated3;
  private LocalDateTime cpocPocDate3;
  private String pocCommentsCont3Cont;
  private String cpocCorrectionPlan3Cont;
  private String pocComments2;
  private LocalDateTime pocDatecleared2;
  private String cpocCorrectionPlan2;
  private String cpocSectionviolated2;
  private LocalDateTime cpocPocDate2;
  private String pocCommentsCont1;
  private String cpocCorrectionPlan2Cont;
  private String pocComments1;
  private LocalDateTime pocDatecleared1;
  private String cpocCorrectionPlan1;
  private String cpocSectionviolated1;
  private LocalDateTime cpocPocDate1;
  private String pocCommentsCont;
  private String cpocCorrectionPlanCont;
  private String pocComments;
  private LocalDateTime pocDatecleared;
  private String cpocCorrectionPlan;
  private String cpocSectionviolated;
  private LocalDateTime cpocPocDate;
  private LocalDateTime cpocVisitDate;
  private String facilityNumberText;
  private String facilityName;
  private String doZip;
  private String doState;
  private String doCity;
  private String doAddress;
  private String doName;
  private String reportTitle;
  private String reportname;
  private String linkedPages;
  private String timeCreated;
  private String createDate;
  private String createdBy;
  private String facilityNumNew;
  private String orgtoamdUnid;
  private String originalForm;
  private String amendPrinted;
  private BigInteger timesAmended;
  private BigInteger amendFlag;
  private String proof;
  private String programCode;
  private String doNumber;
  private String createDateTime;
  private BigInteger linkCount;
  private String showLinkedPages;
  private String fasLpaCode;
  private BigInteger facilityNumber;
  private String authornames;
  private String lpaCode;
  private String deleteFlag;
  private String signature;
  private String formAbrievation;
  private String controlNumber;
  private String continuesectionthree;
  private String continuesectiontwo;
  private String continuesectionone;
  private String cleared4;
  private String cleared3;
  private String cleared2;
  private String cleared1;
  private String howcleared4;
  private String howcleared3;
  private String howcleared2;
  private String howcleared1;
  private String choice;
  private String whichpoc4;
  private String whichpoc3;
  private String whichpoc2;
  private String whichpoc1;
  private BigInteger cpocPocClearcnt;
  private BigInteger cpocPocCount;
  private String clearedFlag;
  private String formNumber;
  private String formName;
  private LocalDateTime dtModified;

  @Basic
  @Column(name = "visit_date")
  public String getVisitDate() {
    return visitDate;
  }

  public void setVisitDate(String visitDate) {
    this.visitDate = visitDate;
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
  @Column(name = "dont_save_flag")
  public String getDontSaveFlag() {
    return dontSaveFlag;
  }

  public void setDontSaveFlag(String dontSaveFlag) {
    this.dontSaveFlag = dontSaveFlag;
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
  public ZonedDateTime getDtEdited() {
    return dtEdited;
  }

  public void setDtEdited(ZonedDateTime dtEdited) {
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
  @Column(name = "poc_comments_3")
  public String getPocComments3() {
    return pocComments3;
  }

  public void setPocComments3(String pocComments3) {
    this.pocComments3 = pocComments3;
  }

  @Basic
  @Column(name = "poc_datecleared_3")
  public LocalDateTime getPocDatecleared3() {
    return pocDatecleared3;
  }

  public void setPocDatecleared3(LocalDateTime pocDatecleared3) {
    this.pocDatecleared3 = pocDatecleared3;
  }

  @Basic
  @Column(name = "cpoc_correction_plan_3")
  public String getCpocCorrectionPlan3() {
    return cpocCorrectionPlan3;
  }

  public void setCpocCorrectionPlan3(String cpocCorrectionPlan3) {
    this.cpocCorrectionPlan3 = cpocCorrectionPlan3;
  }

  @Basic
  @Column(name = "cpoc_sectionviolated_3")
  public String getCpocSectionviolated3() {
    return cpocSectionviolated3;
  }

  public void setCpocSectionviolated3(String cpocSectionviolated3) {
    this.cpocSectionviolated3 = cpocSectionviolated3;
  }

  @Basic
  @Column(name = "cpoc_poc_date_3")
  public LocalDateTime getCpocPocDate3() {
    return cpocPocDate3;
  }

  public void setCpocPocDate3(LocalDateTime cpocPocDate3) {
    this.cpocPocDate3 = cpocPocDate3;
  }

  @Basic
  @Column(name = "poc_comments_cont_3_cont")
  public String getPocCommentsCont3Cont() {
    return pocCommentsCont3Cont;
  }

  public void setPocCommentsCont3Cont(String pocCommentsCont3Cont) {
    this.pocCommentsCont3Cont = pocCommentsCont3Cont;
  }

  @Basic
  @Column(name = "cpoc_correction_plan_3_cont")
  public String getCpocCorrectionPlan3Cont() {
    return cpocCorrectionPlan3Cont;
  }

  public void setCpocCorrectionPlan3Cont(String cpocCorrectionPlan3Cont) {
    this.cpocCorrectionPlan3Cont = cpocCorrectionPlan3Cont;
  }

  @Basic
  @Column(name = "poc_comments_2")
  public String getPocComments2() {
    return pocComments2;
  }

  public void setPocComments2(String pocComments2) {
    this.pocComments2 = pocComments2;
  }

  @Basic
  @Column(name = "poc_datecleared_2")
  public LocalDateTime getPocDatecleared2() {
    return pocDatecleared2;
  }

  public void setPocDatecleared2(LocalDateTime pocDatecleared2) {
    this.pocDatecleared2 = pocDatecleared2;
  }

  @Basic
  @Column(name = "cpoc_correction_plan_2")
  public String getCpocCorrectionPlan2() {
    return cpocCorrectionPlan2;
  }

  public void setCpocCorrectionPlan2(String cpocCorrectionPlan2) {
    this.cpocCorrectionPlan2 = cpocCorrectionPlan2;
  }

  @Basic
  @Column(name = "cpoc_sectionviolated_2")
  public String getCpocSectionviolated2() {
    return cpocSectionviolated2;
  }

  public void setCpocSectionviolated2(String cpocSectionviolated2) {
    this.cpocSectionviolated2 = cpocSectionviolated2;
  }

  @Basic
  @Column(name = "cpoc_poc_date_2")
  public LocalDateTime getCpocPocDate2() {
    return cpocPocDate2;
  }

  public void setCpocPocDate2(LocalDateTime cpocPocDate2) {
    this.cpocPocDate2 = cpocPocDate2;
  }

  @Basic
  @Column(name = "poc_comments_cont_1")
  public String getPocCommentsCont1() {
    return pocCommentsCont1;
  }

  public void setPocCommentsCont1(String pocCommentsCont1) {
    this.pocCommentsCont1 = pocCommentsCont1;
  }

  @Basic
  @Column(name = "cpoc_correction_plan_2_cont")
  public String getCpocCorrectionPlan2Cont() {
    return cpocCorrectionPlan2Cont;
  }

  public void setCpocCorrectionPlan2Cont(String cpocCorrectionPlan2Cont) {
    this.cpocCorrectionPlan2Cont = cpocCorrectionPlan2Cont;
  }

  @Basic
  @Column(name = "poc_comments_1")
  public String getPocComments1() {
    return pocComments1;
  }

  public void setPocComments1(String pocComments1) {
    this.pocComments1 = pocComments1;
  }

  @Basic
  @Column(name = "poc_datecleared_1")
  public LocalDateTime getPocDatecleared1() {
    return pocDatecleared1;
  }

  public void setPocDatecleared1(LocalDateTime pocDatecleared1) {
    this.pocDatecleared1 = pocDatecleared1;
  }

  @Basic
  @Column(name = "cpoc_correction_plan_1")
  public String getCpocCorrectionPlan1() {
    return cpocCorrectionPlan1;
  }

  public void setCpocCorrectionPlan1(String cpocCorrectionPlan1) {
    this.cpocCorrectionPlan1 = cpocCorrectionPlan1;
  }

  @Basic
  @Column(name = "cpoc_sectionviolated_1")
  public String getCpocSectionviolated1() {
    return cpocSectionviolated1;
  }

  public void setCpocSectionviolated1(String cpocSectionviolated1) {
    this.cpocSectionviolated1 = cpocSectionviolated1;
  }

  @Basic
  @Column(name = "cpoc_poc_date_1")
  public LocalDateTime getCpocPocDate1() {
    return cpocPocDate1;
  }

  public void setCpocPocDate1(LocalDateTime cpocPocDate1) {
    this.cpocPocDate1 = cpocPocDate1;
  }

  @Basic
  @Column(name = "poc_comments_cont")
  public String getPocCommentsCont() {
    return pocCommentsCont;
  }

  public void setPocCommentsCont(String pocCommentsCont) {
    this.pocCommentsCont = pocCommentsCont;
  }

  @Basic
  @Column(name = "cpoc_correction_plan_cont")
  public String getCpocCorrectionPlanCont() {
    return cpocCorrectionPlanCont;
  }

  public void setCpocCorrectionPlanCont(String cpocCorrectionPlanCont) {
    this.cpocCorrectionPlanCont = cpocCorrectionPlanCont;
  }

  @Basic
  @Column(name = "poc_comments")
  public String getPocComments() {
    return pocComments;
  }

  public void setPocComments(String pocComments) {
    this.pocComments = pocComments;
  }

  @Basic
  @Column(name = "poc_datecleared")
  public LocalDateTime getPocDatecleared() {
    return pocDatecleared;
  }

  public void setPocDatecleared(LocalDateTime pocDatecleared) {
    this.pocDatecleared = pocDatecleared;
  }

  @Basic
  @Column(name = "cpoc_correction_plan")
  public String getCpocCorrectionPlan() {
    return cpocCorrectionPlan;
  }

  public void setCpocCorrectionPlan(String cpocCorrectionPlan) {
    this.cpocCorrectionPlan = cpocCorrectionPlan;
  }

  @Basic
  @Column(name = "cpoc_sectionviolated")
  public String getCpocSectionviolated() {
    return cpocSectionviolated;
  }

  public void setCpocSectionviolated(String cpocSectionviolated) {
    this.cpocSectionviolated = cpocSectionviolated;
  }

  @Basic
  @Column(name = "cpoc_poc_date")
  public LocalDateTime getCpocPocDate() {
    return cpocPocDate;
  }

  public void setCpocPocDate(LocalDateTime cpocPocDate) {
    this.cpocPocDate = cpocPocDate;
  }

  @Basic
  @Column(name = "cpoc_visit_date")
  public LocalDateTime getCpocVisitDate() {
    return cpocVisitDate;
  }

  public void setCpocVisitDate(LocalDateTime cpocVisitDate) {
    this.cpocVisitDate = cpocVisitDate;
  }

  @Basic
  @Column(name = "facility_number_text")
  public String getFacilityNumberText() {
    return facilityNumberText;
  }

  public void setFacilityNumberText(String facilityNumberText) {
    this.facilityNumberText = facilityNumberText;
  }

  @Basic
  @Column(name = "facility_name")
  public String getFacilityName() {
    return facilityName;
  }

  public void setFacilityName(String facilityName) {
    this.facilityName = facilityName;
  }

  @Basic
  @Column(name = "do_zip")
  public String getDoZip() {
    return doZip;
  }

  public void setDoZip(String doZip) {
    this.doZip = doZip;
  }

  @Basic
  @Column(name = "do_state")
  public String getDoState() {
    return doState;
  }

  public void setDoState(String doState) {
    this.doState = doState;
  }

  @Basic
  @Column(name = "do_city")
  public String getDoCity() {
    return doCity;
  }

  public void setDoCity(String doCity) {
    this.doCity = doCity;
  }

  @Basic
  @Column(name = "do_address")
  public String getDoAddress() {
    return doAddress;
  }

  public void setDoAddress(String doAddress) {
    this.doAddress = doAddress;
  }

  @Basic
  @Column(name = "do_name")
  public String getDoName() {
    return doName;
  }

  public void setDoName(String doName) {
    this.doName = doName;
  }

  @Basic
  @Column(name = "report_title")
  public String getReportTitle() {
    return reportTitle;
  }

  public void setReportTitle(String reportTitle) {
    this.reportTitle = reportTitle;
  }

  @Basic
  @Column(name = "reportname")
  public String getReportname() {
    return reportname;
  }

  public void setReportname(String reportname) {
    this.reportname = reportname;
  }

  @Basic
  @Column(name = "linked_pages")
  public String getLinkedPages() {
    return linkedPages;
  }

  public void setLinkedPages(String linkedPages) {
    this.linkedPages = linkedPages;
  }

  @Basic
  @Column(name = "time_created")
  public String getTimeCreated() {
    return timeCreated;
  }

  public void setTimeCreated(String timeCreated) {
    this.timeCreated = timeCreated;
  }

  @Basic
  @Column(name = "create_date")
  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  @Basic
  @Column(name = "created_by")
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  @Basic
  @Column(name = "facility_num_new")
  public String getFacilityNumNew() {
    return facilityNumNew;
  }

  public void setFacilityNumNew(String facilityNumNew) {
    this.facilityNumNew = facilityNumNew;
  }

  @Basic
  @Column(name = "orgtoamd_unid")
  public String getOrgtoamdUnid() {
    return orgtoamdUnid;
  }

  public void setOrgtoamdUnid(String orgtoamdUnid) {
    this.orgtoamdUnid = orgtoamdUnid;
  }

  @Basic
  @Column(name = "original_form")
  public String getOriginalForm() {
    return originalForm;
  }

  public void setOriginalForm(String originalForm) {
    this.originalForm = originalForm;
  }

  @Basic
  @Column(name = "amend_printed")
  public String getAmendPrinted() {
    return amendPrinted;
  }

  public void setAmendPrinted(String amendPrinted) {
    this.amendPrinted = amendPrinted;
  }

  @Basic
  @Column(name = "times_amended")
  public BigInteger getTimesAmended() {
    return timesAmended;
  }

  public void setTimesAmended(BigInteger timesAmended) {
    this.timesAmended = timesAmended;
  }

  @Basic
  @Column(name = "amend_flag")
  public BigInteger getAmendFlag() {
    return amendFlag;
  }

  public void setAmendFlag(BigInteger amendFlag) {
    this.amendFlag = amendFlag;
  }

  @Basic
  @Column(name = "proof")
  public String getProof() {
    return proof;
  }

  public void setProof(String proof) {
    this.proof = proof;
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
  @Column(name = "do_number")
  public String getDoNumber() {
    return doNumber;
  }

  public void setDoNumber(String doNumber) {
    this.doNumber = doNumber;
  }

  @Id
  @Column(name = "create_date_time")
  public String getCreateDateTime() {
    return createDateTime;
  }

  public void setCreateDateTime(String createDateTime) {
    this.createDateTime = createDateTime;
  }

  @Basic
  @Column(name = "link_count")
  public BigInteger getLinkCount() {
    return linkCount;
  }

  public void setLinkCount(BigInteger linkCount) {
    this.linkCount = linkCount;
  }

  @Basic
  @Column(name = "show_linked_pages")
  public String getShowLinkedPages() {
    return showLinkedPages;
  }

  public void setShowLinkedPages(String showLinkedPages) {
    this.showLinkedPages = showLinkedPages;
  }

  @Basic
  @Column(name = "fas_lpa_code")
  public String getFasLpaCode() {
    return fasLpaCode;
  }

  public void setFasLpaCode(String fasLpaCode) {
    this.fasLpaCode = fasLpaCode;
  }

  @Basic
  @Column(name = "facility_number")
  public BigInteger getFacilityNumber() {
    return facilityNumber;
  }

  public void setFacilityNumber(BigInteger facilityNumber) {
    this.facilityNumber = facilityNumber;
  }

  @Basic
  @Column(name = "authornames")
  public String getAuthornames() {
    return authornames;
  }

  public void setAuthornames(String authornames) {
    this.authornames = authornames;
  }

  @Basic
  @Column(name = "lpa_code")
  public String getLpaCode() {
    return lpaCode;
  }

  public void setLpaCode(String lpaCode) {
    this.lpaCode = lpaCode;
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
  @Column(name = "signature")
  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  @Basic
  @Column(name = "form_abrievation")
  public String getFormAbrievation() {
    return formAbrievation;
  }

  public void setFormAbrievation(String formAbrievation) {
    this.formAbrievation = formAbrievation;
  }

  @Basic
  @Column(name = "control_number")
  public String getControlNumber() {
    return controlNumber;
  }

  public void setControlNumber(String controlNumber) {
    this.controlNumber = controlNumber;
  }

  @Basic
  @Column(name = "continuesectionthree")
  public String getContinuesectionthree() {
    return continuesectionthree;
  }

  public void setContinuesectionthree(String continuesectionthree) {
    this.continuesectionthree = continuesectionthree;
  }

  @Basic
  @Column(name = "continuesectiontwo")
  public String getContinuesectiontwo() {
    return continuesectiontwo;
  }

  public void setContinuesectiontwo(String continuesectiontwo) {
    this.continuesectiontwo = continuesectiontwo;
  }

  @Basic
  @Column(name = "continuesectionone")
  public String getContinuesectionone() {
    return continuesectionone;
  }

  public void setContinuesectionone(String continuesectionone) {
    this.continuesectionone = continuesectionone;
  }

  @Basic
  @Column(name = "cleared_4")
  public String getCleared4() {
    return cleared4;
  }

  public void setCleared4(String cleared4) {
    this.cleared4 = cleared4;
  }

  @Basic
  @Column(name = "cleared_3")
  public String getCleared3() {
    return cleared3;
  }

  public void setCleared3(String cleared3) {
    this.cleared3 = cleared3;
  }

  @Basic
  @Column(name = "cleared_2")
  public String getCleared2() {
    return cleared2;
  }

  public void setCleared2(String cleared2) {
    this.cleared2 = cleared2;
  }

  @Basic
  @Column(name = "cleared_1")
  public String getCleared1() {
    return cleared1;
  }

  public void setCleared1(String cleared1) {
    this.cleared1 = cleared1;
  }

  @Basic
  @Column(name = "howcleared_4")
  public String getHowcleared4() {
    return howcleared4;
  }

  public void setHowcleared4(String howcleared4) {
    this.howcleared4 = howcleared4;
  }

  @Basic
  @Column(name = "howcleared_3")
  public String getHowcleared3() {
    return howcleared3;
  }

  public void setHowcleared3(String howcleared3) {
    this.howcleared3 = howcleared3;
  }

  @Basic
  @Column(name = "howcleared_2")
  public String getHowcleared2() {
    return howcleared2;
  }

  public void setHowcleared2(String howcleared2) {
    this.howcleared2 = howcleared2;
  }

  @Basic
  @Column(name = "howcleared_1")
  public String getHowcleared1() {
    return howcleared1;
  }

  public void setHowcleared1(String howcleared1) {
    this.howcleared1 = howcleared1;
  }

  @Basic
  @Column(name = "choice")
  public String getChoice() {
    return choice;
  }

  public void setChoice(String choice) {
    this.choice = choice;
  }

  @Basic
  @Column(name = "whichpoc_4")
  public String getWhichpoc4() {
    return whichpoc4;
  }

  public void setWhichpoc4(String whichpoc4) {
    this.whichpoc4 = whichpoc4;
  }

  @Basic
  @Column(name = "whichpoc_3")
  public String getWhichpoc3() {
    return whichpoc3;
  }

  public void setWhichpoc3(String whichpoc3) {
    this.whichpoc3 = whichpoc3;
  }

  @Basic
  @Column(name = "whichpoc_2")
  public String getWhichpoc2() {
    return whichpoc2;
  }

  public void setWhichpoc2(String whichpoc2) {
    this.whichpoc2 = whichpoc2;
  }

  @Basic
  @Column(name = "whichpoc_1")
  public String getWhichpoc1() {
    return whichpoc1;
  }

  public void setWhichpoc1(String whichpoc1) {
    this.whichpoc1 = whichpoc1;
  }

  @Basic
  @Column(name = "cpoc_poc_clearcnt")
  public BigInteger getCpocPocClearcnt() {
    return cpocPocClearcnt;
  }

  public void setCpocPocClearcnt(BigInteger cpocPocClearcnt) {
    this.cpocPocClearcnt = cpocPocClearcnt;
  }

  @Basic
  @Column(name = "cpoc_poc_count")
  public BigInteger getCpocPocCount() {
    return cpocPocCount;
  }

  public void setCpocPocCount(BigInteger cpocPocCount) {
    this.cpocPocCount = cpocPocCount;
  }

  @Basic
  @Column(name = "cleared_flag")
  public String getClearedFlag() {
    return clearedFlag;
  }

  public void setClearedFlag(String clearedFlag) {
    this.clearedFlag = clearedFlag;
  }

  @Basic
  @Column(name = "form_number")
  public String getFormNumber() {
    return formNumber;
  }

  public void setFormNumber(String formNumber) {
    this.formNumber = formNumber;
  }

  @Basic
  @Column(name = "form_name")
  public String getFormName() {
    return formName;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  @Basic
  @Column(name = "dt_modified")
  public LocalDateTime getDtModified() {
    return dtModified;
  }

  public void setDtModified(LocalDateTime dtModified) {
    this.dtModified = dtModified;
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
  @Transient
  public Serializable getPrimaryKey() {
    return createDateTime;
  }
}
