package gov.ca.cwds.cals.persistence.model.fas;

import gov.ca.cwds.data.persistence.PersistentObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import static gov.ca.cwds.cals.persistence.model.fas.Rr809Dn.NQ_FIND_BY_FACILITY_NUMBER;
import static gov.ca.cwds.cals.persistence.model.fas.Rr809Dn.NQ_FIND_BY_FACILITY_NUMBER_AND_DEFICIENCY_ID;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(
    name = NQ_FIND_BY_FACILITY_NUMBER,
    query = "FROM Rr809Dn d WHERE d.facilityNumberText = :facilityNumberText"
)

@NamedQuery(
    name = NQ_FIND_BY_FACILITY_NUMBER_AND_DEFICIENCY_ID,
    query = "FROM Rr809Dn d WHERE d.facilityNumberText = :facilityNumberText " +
        "AND d.originalunidkey = :deficiencyId"
)

@Entity
@Table(name = "rr809dn")
@SuppressWarnings("squid:S3437") //LocalDate is serializable
public class Rr809Dn implements PersistentObject {
  private static final long serialVersionUID = 704473363282773670L;

  public static final String NQ_FIND_BY_FACILITY_NUMBER = "Rr809Dn.findByFacilityNumber";
  public static final String NQ_FIND_BY_FACILITY_NUMBER_AND_DEFICIENCY_ID =
      "Rr809Dn.findByFacilityNumberAndInspectionId";

  private String visitDate;
  private String responseDescription;
  private String dontSaveFlag;
  private String auditHistory;
  private String originalunidkey;
  private LocalDateTime dtEdited;
  private String editorName;
  private String editHistory;
  private LocalDateTime dtCreated;
  private String creatorName;
  private String pageTotal;
  private String pageNr;
  private String formNumber;
  private BigInteger pageTotal1;
  private BigInteger pageNr1;
  private String formNumber1;
  private LocalDateTime date2;
  private String pLicensing;
  private LocalDateTime date1;
  private String lpaTelephone;
  private String lpaName;
  private String lpaSupTelephone;
  private String supervisorName;
  private String correctionPlan3;
  private String deficiency13;
  private String facSectionviolated3;
  private LocalDateTime pocDate3;
  private String deftype4Ab;
  private String correctionPlan3Cont;
  private String deficiency3Cont;
  private String correctionPlan2;
  private String deficiency12;
  private String facSectionviolated2;
  private LocalDateTime pocDate2;
  private String deftype3Ab;
  private String correctionPlan2Cont;
  private String deficiency2Cont;
  private String correctionPlan1;
  private String deficiency11;
  private String facSectionviolated1;
  private LocalDateTime pocDate1;
  private String deftype2Ab;
  private String correctionPlanCont;
  private String deficiency1Cont;
  private String correctionPlan;
  private String deficiency1;
  private String facSectionviolated;
  private LocalDateTime pocDate;
  private String deftype1;
  private LocalDateTime fercdVisitDate;
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
  private LocalDateTime timeCreated;
  private LocalDateTime createDate;
  private String createdBy;
  private String facilityNumNew;
  private String originalForm;
  private String orgtoamdUnid;
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
  private String allowableeditors;
  private String authornames;
  private String lpaCode;
  private String deleteFlag;
  private String signature;
  private String formAbrievation;
  private String controlNumber;
  private String amdDoc;
  private LocalDateTime amendDate1;
  private LocalDateTime amendDate;
  private String orgDoc;
  private LocalDateTime dateSigned;
  private String appealStatus4;
  private String appealStatus3;
  private String appealStatus2;
  private String appealStatus1;
  private String firstSave;
  private String headerinfoblk;
  private String newsignblock;
  private String aPublic;
  private String continuesectionthree;
  private String continuesectiontwo;
  private String continuesectionone;
  private String formName;
  private LocalDateTime dtModified;
  private Rrcpoc rrcpoc;

  @OneToOne
  @JoinColumns({
      @JoinColumn(name = "facility_number_text", referencedColumnName = "facility_number_text", insertable = false, updatable = false),
      @JoinColumn(name = "fac_SectionViolated", referencedColumnName = "cpoc_SectionViolated", insertable = false, updatable = false),
      @JoinColumn(name = "POC_Date", referencedColumnName = "cpoc_POC_Date", insertable = false, updatable = false)
  })
  public Rrcpoc getRrcpoc() {
    return rrcpoc;
  }

  public void setRrcpoc(Rrcpoc rrcpoc) {
    this.rrcpoc = rrcpoc;
  }

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

  @Id
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
  @Column(name = "page_total")
  public String getPageTotal() {
    return pageTotal;
  }

  public void setPageTotal(String pageTotal) {
    this.pageTotal = pageTotal;
  }

  @Basic
  @Column(name = "page_nr")
  public String getPageNr() {
    return pageNr;
  }

  public void setPageNr(String pageNr) {
    this.pageNr = pageNr;
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
  @Column(name = "page_total_1")
  public BigInteger getPageTotal1() {
    return pageTotal1;
  }

  public void setPageTotal1(BigInteger pageTotal1) {
    this.pageTotal1 = pageTotal1;
  }

  @Basic
  @Column(name = "page_nr_1")
  public BigInteger getPageNr1() {
    return pageNr1;
  }

  public void setPageNr1(BigInteger pageNr1) {
    this.pageNr1 = pageNr1;
  }

  @Basic
  @Column(name = "form_number_1")
  public String getFormNumber1() {
    return formNumber1;
  }

  public void setFormNumber1(String formNumber1) {
    this.formNumber1 = formNumber1;
  }

  @Basic
  @Column(name = "date2")
  public LocalDateTime getDate2() {
    return date2;
  }

  public void setDate2(LocalDateTime date2) {
    this.date2 = date2;
  }

  @Basic
  @Column(name = "p_licensing")
  public String getpLicensing() {
    return pLicensing;
  }

  public void setpLicensing(String pLicensing) {
    this.pLicensing = pLicensing;
  }

  @Basic
  @Column(name = "date1")
  public LocalDateTime getDate1() {
    return date1;
  }

  public void setDate1(LocalDateTime date1) {
    this.date1 = date1;
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

  @Basic
  @Column(name = "lpa_sup_telephone")
  public String getLpaSupTelephone() {
    return lpaSupTelephone;
  }

  public void setLpaSupTelephone(String lpaSupTelephone) {
    this.lpaSupTelephone = lpaSupTelephone;
  }

  @Basic
  @Column(name = "supervisor_name")
  public String getSupervisorName() {
    return supervisorName;
  }

  public void setSupervisorName(String supervisorName) {
    this.supervisorName = supervisorName;
  }

  @Basic
  @Column(name = "correction_plan_3")
  public String getCorrectionPlan3() {
    return correctionPlan3;
  }

  public void setCorrectionPlan3(String correctionPlan3) {
    this.correctionPlan3 = correctionPlan3;
  }

  @Basic
  @Column(name = "deficiency_1_3")
  public String getDeficiency13() {
    return deficiency13;
  }

  public void setDeficiency13(String deficiency13) {
    this.deficiency13 = deficiency13;
  }

  @Basic
  @Column(name = "fac_sectionviolated_3")
  public String getFacSectionviolated3() {
    return facSectionviolated3;
  }

  public void setFacSectionviolated3(String facSectionviolated3) {
    this.facSectionviolated3 = facSectionviolated3;
  }

  @Basic
  @Column(name = "poc_date_3")
  public LocalDateTime getPocDate3() {
    return pocDate3;
  }

  public void setPocDate3(LocalDateTime pocDate3) {
    this.pocDate3 = pocDate3;
  }

  @Basic
  @Column(name = "deftype4_ab")
  public String getDeftype4Ab() {
    return deftype4Ab;
  }

  public void setDeftype4Ab(String deftype4Ab) {
    this.deftype4Ab = deftype4Ab;
  }

  @Basic
  @Column(name = "correction_plan_3_cont")
  public String getCorrectionPlan3Cont() {
    return correctionPlan3Cont;
  }

  public void setCorrectionPlan3Cont(String correctionPlan3Cont) {
    this.correctionPlan3Cont = correctionPlan3Cont;
  }

  @Basic
  @Column(name = "deficiency_3_cont")
  public String getDeficiency3Cont() {
    return deficiency3Cont;
  }

  public void setDeficiency3Cont(String deficiency3Cont) {
    this.deficiency3Cont = deficiency3Cont;
  }

  @Basic
  @Column(name = "correction_plan_2")
  public String getCorrectionPlan2() {
    return correctionPlan2;
  }

  public void setCorrectionPlan2(String correctionPlan2) {
    this.correctionPlan2 = correctionPlan2;
  }

  @Basic
  @Column(name = "deficiency_1_2")
  public String getDeficiency12() {
    return deficiency12;
  }

  public void setDeficiency12(String deficiency12) {
    this.deficiency12 = deficiency12;
  }

  @Basic
  @Column(name = "fac_sectionviolated_2")
  public String getFacSectionviolated2() {
    return facSectionviolated2;
  }

  public void setFacSectionviolated2(String facSectionviolated2) {
    this.facSectionviolated2 = facSectionviolated2;
  }

  @Basic
  @Column(name = "poc_date_2")
  public LocalDateTime getPocDate2() {
    return pocDate2;
  }

  public void setPocDate2(LocalDateTime pocDate2) {
    this.pocDate2 = pocDate2;
  }

  @Basic
  @Column(name = "deftype3_ab")
  public String getDeftype3Ab() {
    return deftype3Ab;
  }

  public void setDeftype3Ab(String deftype3Ab) {
    this.deftype3Ab = deftype3Ab;
  }

  @Basic
  @Column(name = "correction_plan_2_cont")
  public String getCorrectionPlan2Cont() {
    return correctionPlan2Cont;
  }

  public void setCorrectionPlan2Cont(String correctionPlan2Cont) {
    this.correctionPlan2Cont = correctionPlan2Cont;
  }

  @Basic
  @Column(name = "deficiency_2_cont")
  public String getDeficiency2Cont() {
    return deficiency2Cont;
  }

  public void setDeficiency2Cont(String deficiency2Cont) {
    this.deficiency2Cont = deficiency2Cont;
  }

  @Basic
  @Column(name = "correction_plan_1")
  public String getCorrectionPlan1() {
    return correctionPlan1;
  }

  public void setCorrectionPlan1(String correctionPlan1) {
    this.correctionPlan1 = correctionPlan1;
  }

  @Basic
  @Column(name = "deficiency_1_1")
  public String getDeficiency11() {
    return deficiency11;
  }

  public void setDeficiency11(String deficiency11) {
    this.deficiency11 = deficiency11;
  }

  @Basic
  @Column(name = "fac_sectionviolated_1")
  public String getFacSectionviolated1() {
    return facSectionviolated1;
  }

  public void setFacSectionviolated1(String facSectionviolated1) {
    this.facSectionviolated1 = facSectionviolated1;
  }

  @Basic
  @Column(name = "poc_date_1")
  public LocalDateTime getPocDate1() {
    return pocDate1;
  }

  public void setPocDate1(LocalDateTime pocDate1) {
    this.pocDate1 = pocDate1;
  }

  @Basic
  @Column(name = "deftype2_ab")
  public String getDeftype2Ab() {
    return deftype2Ab;
  }

  public void setDeftype2Ab(String deftype2Ab) {
    this.deftype2Ab = deftype2Ab;
  }

  @Basic
  @Column(name = "correction_plan_cont")
  public String getCorrectionPlanCont() {
    return correctionPlanCont;
  }

  public void setCorrectionPlanCont(String correctionPlanCont) {
    this.correctionPlanCont = correctionPlanCont;
  }

  @Basic
  @Column(name = "deficiency_1_cont")
  public String getDeficiency1Cont() {
    return deficiency1Cont;
  }

  public void setDeficiency1Cont(String deficiency1Cont) {
    this.deficiency1Cont = deficiency1Cont;
  }

  @Basic
  @Column(name = "correction_plan")
  public String getCorrectionPlan() {
    return correctionPlan;
  }

  public void setCorrectionPlan(String correctionPlan) {
    this.correctionPlan = correctionPlan;
  }

  @Basic
  @Column(name = "deficiency_1")
  public String getDeficiency1() {
    return deficiency1;
  }

  public void setDeficiency1(String deficiency1) {
    this.deficiency1 = deficiency1;
  }

  @Basic
  @Column(name = "fac_sectionviolated")
  public String getFacSectionviolated() {
    return facSectionviolated;
  }

  public void setFacSectionviolated(String facSectionviolated) {
    this.facSectionviolated = facSectionviolated;
  }

  @Basic
  @Column(name = "poc_date")
  public LocalDateTime getPocDate() {
    return pocDate;
  }

  public void setPocDate(LocalDateTime pocDate) {
    this.pocDate = pocDate;
  }

  @Basic
  @Column(name = "deftype1")
  public String getDeftype1() {
    return deftype1;
  }

  public void setDeftype1(String deftype1) {
    this.deftype1 = deftype1;
  }

  @Basic
  @Column(name = "fercd_visit_date")
  public LocalDateTime getFercdVisitDate() {
    return fercdVisitDate;
  }

  public void setFercdVisitDate(LocalDateTime fercdVisitDate) {
    this.fercdVisitDate = fercdVisitDate;
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
  public LocalDateTime getTimeCreated() {
    return timeCreated;
  }

  public void setTimeCreated(LocalDateTime timeCreated) {
    this.timeCreated = timeCreated;
  }

  @Basic
  @Column(name = "create_date")
  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
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
  @Column(name = "original_form")
  public String getOriginalForm() {
    return originalForm;
  }

  public void setOriginalForm(String originalForm) {
    this.originalForm = originalForm;
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

  @Basic
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
  @Column(name = "allowableeditors")
  public String getAllowableeditors() {
    return allowableeditors;
  }

  public void setAllowableeditors(String allowableeditors) {
    this.allowableeditors = allowableeditors;
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
  @Column(name = "amd_doc")
  public String getAmdDoc() {
    return amdDoc;
  }

  public void setAmdDoc(String amdDoc) {
    this.amdDoc = amdDoc;
  }

  @Basic
  @Column(name = "amend_date_1")
  public LocalDateTime getAmendDate1() {
    return amendDate1;
  }

  public void setAmendDate1(LocalDateTime amendDate1) {
    this.amendDate1 = amendDate1;
  }

  @Basic
  @Column(name = "amend_date")
  public LocalDateTime getAmendDate() {
    return amendDate;
  }

  public void setAmendDate(LocalDateTime amendDate) {
    this.amendDate = amendDate;
  }

  @Basic
  @Column(name = "org_doc")
  public String getOrgDoc() {
    return orgDoc;
  }

  public void setOrgDoc(String orgDoc) {
    this.orgDoc = orgDoc;
  }

  @Basic
  @Column(name = "date_signed")
  public LocalDateTime getDateSigned() {
    return dateSigned;
  }

  public void setDateSigned(LocalDateTime dateSigned) {
    this.dateSigned = dateSigned;
  }

  @Basic
  @Column(name = "appeal_status_4")
  public String getAppealStatus4() {
    return appealStatus4;
  }

  public void setAppealStatus4(String appealStatus4) {
    this.appealStatus4 = appealStatus4;
  }

  @Basic
  @Column(name = "appeal_status_3")
  public String getAppealStatus3() {
    return appealStatus3;
  }

  public void setAppealStatus3(String appealStatus3) {
    this.appealStatus3 = appealStatus3;
  }

  @Basic
  @Column(name = "appeal_status_2")
  public String getAppealStatus2() {
    return appealStatus2;
  }

  public void setAppealStatus2(String appealStatus2) {
    this.appealStatus2 = appealStatus2;
  }

  @Basic
  @Column(name = "appeal_status_1")
  public String getAppealStatus1() {
    return appealStatus1;
  }

  public void setAppealStatus1(String appealStatus1) {
    this.appealStatus1 = appealStatus1;
  }

  @Basic
  @Column(name = "first_save")
  public String getFirstSave() {
    return firstSave;
  }

  public void setFirstSave(String firstSave) {
    this.firstSave = firstSave;
  }

  @Basic
  @Column(name = "headerinfoblk")
  public String getHeaderinfoblk() {
    return headerinfoblk;
  }

  public void setHeaderinfoblk(String headerinfoblk) {
    this.headerinfoblk = headerinfoblk;
  }

  @Basic
  @Column(name = "newsignblock")
  public String getNewsignblock() {
    return newsignblock;
  }

  public void setNewsignblock(String newsignblock) {
    this.newsignblock = newsignblock;
  }

  @Basic
  @Column(name = "public")
  public String getaPublic() {
    return aPublic;
  }

  public void setaPublic(String aPublic) {
    this.aPublic = aPublic;
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
