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

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(
    name = ComplaintReportLic802.FIND_COMPLAINTS_BY_FACILITY_ID,
    query = "SELECT c FROM ComplaintReportLic802 c WHERE c.facilityNumberText = :" +
        ComplaintReportLic802.PARAM_FACILITY_NUMBER
)
@NamedQuery(
    name = ComplaintReportLic802.FIND_COMPLAINTS_BY_FACILITY_ID_AND_COMPLAINT_NUMBER,
    query = "SELECT c FROM ComplaintReportLic802 c WHERE c.facilityNumberText = :" +
        ComplaintReportLic802.PARAM_FACILITY_NUMBER + " AND c.originalunidkey = :" +
        ComplaintReportLic802.PARAM_COMPLAINT_ID
)
@Entity
@Table(name = "complaint_report_lic802")
@SuppressWarnings("squid:S3437") //LocalDate is serializable
public class ComplaintReportLic802 implements PersistentObject {
  private static final long serialVersionUID = -1566583736496919788L;

  public static final String FIND_COMPLAINTS_BY_FACILITY_ID = "findComplaintsByFacilityId";
  public static final String FIND_COMPLAINTS_BY_FACILITY_ID_AND_COMPLAINT_NUMBER = "findComplaintsByFacilityIdAndFacilityNumber";

  public static final String PARAM_FACILITY_NUMBER = "facilityNumber";
  public static final String PARAM_COMPLAINT_ID = "complaintId";

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
  private String crp2Formnumber;
  private LocalDateTime crLpsSigndate;
  private String crLpsSignname;
  private LocalDateTime crLpaSigndate;
  private String crLpaSignname;
  private String crp2Followup;
  private String z13;
  private String crp2Contactsummary;
  private String z12;
  private String crp2Postcomments;
  private String z14;
  private String crp2Precomments;
  private String z11;
  private String pPosthowcontact;
  private String crp2Posthowcontact;
  private String pPrehowcontact;
  private String crp2Prehowcontact;
  private String crp2Postcontact;
  private LocalDateTime crp2Postdate;
  private String crp2Precontact;
  private LocalDateTime crp2Predate;
  private String crp2Detaildescription;
  private String z1;
  private String crp2FacilityNumber;
  private String crp2FacilityType;
  private String crp2FacilityName;
  private String crp2Controlnumber;
  private LocalDateTime crp2Visitduedate;
  private String crp2Prioritynr;
  private String crp2Priority;
  private String doZip2;
  private String doState2;
  private String doCity2;
  private String doAddress2;
  private String doName2;
  private String reportname2;
  private String facilityNumNewD2;
  private String pageTotal1;
  private String pageNr1;
  private String formNumber;
  private LocalDateTime crTime;
  private LocalDateTime crDate;
  private String crReceivedby;
  private String crUnsub7;
  private String crInsub7;
  private String crSub7;
  private String crAllegation7;
  private String z_7;
  private String undrApl7;
  private String crCode7;
  private String crUnsub6;
  private String crInsub6;
  private String crSub6;
  private String crAllegation6;
  private String z_6;
  private String undrApl6;
  private String crCode6;
  private String crUnsub5;
  private String crInsub5;
  private String crSub5;
  private String crAllegation5;
  private String z_5;
  private String undrApl5;
  private String crCode5;
  private String crUnsub4;
  private String crInsub4;
  private String crSub4;
  private String crAllegation4;
  private String z_4;
  private String undrApl4;
  private String crCode4;
  private String crUnsub3;
  private String crInsub3;
  private String crSub3;
  private String crAllegation3;
  private String z_3;
  private String undrApl3;
  private String crCode3;
  private String crUnsub2;
  private String crInsub2;
  private String crSub2;
  private String crAllegation2;
  private String z_2;
  private String undrApl2;
  private String crCode2;
  private String crUnsub1;
  private String crInsub1;
  private String crSub1;
  private String crAllegation1;
  private String z_1;
  private String undrApl1;
  private String crCode1;
  private String crUnsub;
  private String crInsub;
  private String crSub;
  private String crAllegation;
  private String z;
  private String undrApl;
  private String crCode;
  private String facilityTelephone;
  private String facilityZip;
  private String facilityCity;
  private String facilityAddress;
  private String facilityName;
  private String facilityNumberText;
  private String facilityType;
  private String crControlnumber;
  private LocalDateTime crVisitduedate;
  private String crDoname;
  private String crPrioritynr;
  private String crPriority;
  private String pComplainantanonymous;
  private String crComplainantanonymous;
  private String pAbusereportfiled;
  private String crAbusereportfiled;
  private String crComplainantrelation;
  private String crComplainantnightphone;
  private String crComplainantdayphone;
  private String crComplainantzip;
  private String crComplainantcity;
  private String crComplainantaddress;
  private String crComplainantname;
  private String pHowreported;
  private String crHowreported;
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
  private String signOffDate;
  private String aiActionTaken;
  private String aiTotalHours;
  private String aiNumVisits;
  private String auditAcptDate;
  private String auditAccept;
  private String auditRfrDate;
  private String auditRefer;
  private String why;
  private String pFollowUp;
  private String followUp;
  private String adminTotalHours;
  private String actionTaken;
  private String totalHours;
  private String numOfVisits;
  private String cHomeType;
  private String cHomeZip;
  private String cHomeTelephone;
  private String cHomeCity;
  private String cHomeLicensee;
  private String cHomeAddress;
  private String cHomeName;
  private String cHomeNumber;
  private String crLpsRejectcomments;
  private String crLpsRejname;
  private String crLpsRejdate;
  private String crLpsAppname1;
  private LocalDateTime crLpsAppdate1;
  private String crLpsAppname;
  private LocalDateTime crLpsAppdate;
  private String crLpsName;
  private String crLpaSubname;
  private LocalDateTime crLpaSubdate;
  private String crLpaName;
  private String crStatus;
  private LocalDateTime dateAssigned;
  private String lpaAssigned;
  private LocalDateTime dateSigned;
  private String creAuthornames;
  private String verifiedBy;
  private String verifiedDate;
  private String verified;
  private String agencySelection1;
  private String agencySelection;
  private String fromIntake;
  private BigInteger facDoNbr;
  private String tendaymissed;
  private String tendayvisitFlag;
  private BigInteger facPrimaryNbr;
  private String faslpacode1;
  private String rejectComments;
  private String ancs;
  private String approveByHistory;
  private String approveDateHistory;
  private String approveHistory;
  private String assignByHistory;
  private String assignDateHistory;
  private String assignHistory;
  private String complaintsumunid;
  private String complaintunid;
  private String searchAddress;
  private String searchName;
  private String searchNumber;
  private String createddbname;
  private String facilityState;
  private String facilityAdmin;
  private LocalDateTime crVisitDate;
  private String formName;
  private LocalDateTime dtModified;

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
  @Column(name = "crp2_formnumber")
  public String getCrp2Formnumber() {
    return crp2Formnumber;
  }

  public void setCrp2Formnumber(String crp2Formnumber) {
    this.crp2Formnumber = crp2Formnumber;
  }

  @Basic
  @Column(name = "cr_lps_signdate")
  public LocalDateTime getCrLpsSigndate() {
    return crLpsSigndate;
  }

  public void setCrLpsSigndate(LocalDateTime crLpsSigndate) {
    this.crLpsSigndate = crLpsSigndate;
  }

  @Basic
  @Column(name = "cr_lps_signname")
  public String getCrLpsSignname() {
    return crLpsSignname;
  }

  public void setCrLpsSignname(String crLpsSignname) {
    this.crLpsSignname = crLpsSignname;
  }

  @Basic
  @Column(name = "cr_lpa_signdate")
  public LocalDateTime getCrLpaSigndate() {
    return crLpaSigndate;
  }

  public void setCrLpaSigndate(LocalDateTime crLpaSigndate) {
    this.crLpaSigndate = crLpaSigndate;
  }

  @Basic
  @Column(name = "cr_lpa_signname")
  public String getCrLpaSignname() {
    return crLpaSignname;
  }

  public void setCrLpaSignname(String crLpaSignname) {
    this.crLpaSignname = crLpaSignname;
  }

  @Basic
  @Column(name = "crp2_followup")
  public String getCrp2Followup() {
    return crp2Followup;
  }

  public void setCrp2Followup(String crp2Followup) {
    this.crp2Followup = crp2Followup;
  }

  @Basic
  @Column(name = "z1_3")
  public String getZ13() {
    return z13;
  }

  public void setZ13(String z13) {
    this.z13 = z13;
  }

  @Basic
  @Column(name = "crp2_contactsummary")
  public String getCrp2Contactsummary() {
    return crp2Contactsummary;
  }

  public void setCrp2Contactsummary(String crp2Contactsummary) {
    this.crp2Contactsummary = crp2Contactsummary;
  }

  @Basic
  @Column(name = "z1_2")
  public String getZ12() {
    return z12;
  }

  public void setZ12(String z12) {
    this.z12 = z12;
  }

  @Basic
  @Column(name = "crp2_postcomments")
  public String getCrp2Postcomments() {
    return crp2Postcomments;
  }

  public void setCrp2Postcomments(String crp2Postcomments) {
    this.crp2Postcomments = crp2Postcomments;
  }

  @Basic
  @Column(name = "z1_4")
  public String getZ14() {
    return z14;
  }

  public void setZ14(String z14) {
    this.z14 = z14;
  }

  @Basic
  @Column(name = "crp2_precomments")
  public String getCrp2Precomments() {
    return crp2Precomments;
  }

  public void setCrp2Precomments(String crp2Precomments) {
    this.crp2Precomments = crp2Precomments;
  }

  @Basic
  @Column(name = "z1_1")
  public String getZ11() {
    return z11;
  }

  public void setZ11(String z11) {
    this.z11 = z11;
  }

  @Basic
  @Column(name = "p_posthowcontact")
  public String getpPosthowcontact() {
    return pPosthowcontact;
  }

  public void setpPosthowcontact(String pPosthowcontact) {
    this.pPosthowcontact = pPosthowcontact;
  }

  @Basic
  @Column(name = "crp2_posthowcontact")
  public String getCrp2Posthowcontact() {
    return crp2Posthowcontact;
  }

  public void setCrp2Posthowcontact(String crp2Posthowcontact) {
    this.crp2Posthowcontact = crp2Posthowcontact;
  }

  @Basic
  @Column(name = "p_prehowcontact")
  public String getpPrehowcontact() {
    return pPrehowcontact;
  }

  public void setpPrehowcontact(String pPrehowcontact) {
    this.pPrehowcontact = pPrehowcontact;
  }

  @Basic
  @Column(name = "crp2_prehowcontact")
  public String getCrp2Prehowcontact() {
    return crp2Prehowcontact;
  }

  public void setCrp2Prehowcontact(String crp2Prehowcontact) {
    this.crp2Prehowcontact = crp2Prehowcontact;
  }

  @Basic
  @Column(name = "crp2_postcontact")
  public String getCrp2Postcontact() {
    return crp2Postcontact;
  }

  public void setCrp2Postcontact(String crp2Postcontact) {
    this.crp2Postcontact = crp2Postcontact;
  }

  @Basic
  @Column(name = "crp2_postdate")
  public LocalDateTime getCrp2Postdate() {
    return crp2Postdate;
  }

  public void setCrp2Postdate(LocalDateTime crp2Postdate) {
    this.crp2Postdate = crp2Postdate;
  }

  @Basic
  @Column(name = "crp2_precontact")
  public String getCrp2Precontact() {
    return crp2Precontact;
  }

  public void setCrp2Precontact(String crp2Precontact) {
    this.crp2Precontact = crp2Precontact;
  }

  @Basic
  @Column(name = "crp2_predate")
  public LocalDateTime getCrp2Predate() {
    return crp2Predate;
  }

  public void setCrp2Predate(LocalDateTime crp2Predate) {
    this.crp2Predate = crp2Predate;
  }

  @Basic
  @Column(name = "crp2_detaildescription")
  public String getCrp2Detaildescription() {
    return crp2Detaildescription;
  }

  public void setCrp2Detaildescription(String crp2Detaildescription) {
    this.crp2Detaildescription = crp2Detaildescription;
  }

  @Basic
  @Column(name = "z1")
  public String getZ1() {
    return z1;
  }

  public void setZ1(String z1) {
    this.z1 = z1;
  }

  @Basic
  @Column(name = "crp2_facility_number")
  public String getCrp2FacilityNumber() {
    return crp2FacilityNumber;
  }

  public void setCrp2FacilityNumber(String crp2FacilityNumber) {
    this.crp2FacilityNumber = crp2FacilityNumber;
  }

  @Basic
  @Column(name = "crp2_facility_type")
  public String getCrp2FacilityType() {
    return crp2FacilityType;
  }

  public void setCrp2FacilityType(String crp2FacilityType) {
    this.crp2FacilityType = crp2FacilityType;
  }

  @Basic
  @Column(name = "crp2_facility_name")
  public String getCrp2FacilityName() {
    return crp2FacilityName;
  }

  public void setCrp2FacilityName(String crp2FacilityName) {
    this.crp2FacilityName = crp2FacilityName;
  }

  @Basic
  @Column(name = "crp2_controlnumber")
  public String getCrp2Controlnumber() {
    return crp2Controlnumber;
  }

  public void setCrp2Controlnumber(String crp2Controlnumber) {
    this.crp2Controlnumber = crp2Controlnumber;
  }

  @Basic
  @Column(name = "crp2_visitduedate")
  public LocalDateTime getCrp2Visitduedate() {
    return crp2Visitduedate;
  }

  public void setCrp2Visitduedate(LocalDateTime crp2Visitduedate) {
    this.crp2Visitduedate = crp2Visitduedate;
  }

  @Basic
  @Column(name = "crp2_prioritynr")
  public String getCrp2Prioritynr() {
    return crp2Prioritynr;
  }

  public void setCrp2Prioritynr(String crp2Prioritynr) {
    this.crp2Prioritynr = crp2Prioritynr;
  }

  @Basic
  @Column(name = "crp2_priority")
  public String getCrp2Priority() {
    return crp2Priority;
  }

  public void setCrp2Priority(String crp2Priority) {
    this.crp2Priority = crp2Priority;
  }

  @Basic
  @Column(name = "do_zip2")
  public String getDoZip2() {
    return doZip2;
  }

  public void setDoZip2(String doZip2) {
    this.doZip2 = doZip2;
  }

  @Basic
  @Column(name = "do_state2")
  public String getDoState2() {
    return doState2;
  }

  public void setDoState2(String doState2) {
    this.doState2 = doState2;
  }

  @Basic
  @Column(name = "do_city2")
  public String getDoCity2() {
    return doCity2;
  }

  public void setDoCity2(String doCity2) {
    this.doCity2 = doCity2;
  }

  @Basic
  @Column(name = "do_address2")
  public String getDoAddress2() {
    return doAddress2;
  }

  public void setDoAddress2(String doAddress2) {
    this.doAddress2 = doAddress2;
  }

  @Basic
  @Column(name = "do_name2")
  public String getDoName2() {
    return doName2;
  }

  public void setDoName2(String doName2) {
    this.doName2 = doName2;
  }

  @Basic
  @Column(name = "reportname2")
  public String getReportname2() {
    return reportname2;
  }

  public void setReportname2(String reportname2) {
    this.reportname2 = reportname2;
  }

  @Basic
  @Column(name = "facility_num_new_d2")
  public String getFacilityNumNewD2() {
    return facilityNumNewD2;
  }

  public void setFacilityNumNewD2(String facilityNumNewD2) {
    this.facilityNumNewD2 = facilityNumNewD2;
  }

  @Basic
  @Column(name = "page_total1")
  public String getPageTotal1() {
    return pageTotal1;
  }

  public void setPageTotal1(String pageTotal1) {
    this.pageTotal1 = pageTotal1;
  }

  @Basic
  @Column(name = "page_nr1")
  public String getPageNr1() {
    return pageNr1;
  }

  public void setPageNr1(String pageNr1) {
    this.pageNr1 = pageNr1;
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
  @Column(name = "cr_time")
  public LocalDateTime getCrTime() {
    return crTime;
  }

  public void setCrTime(LocalDateTime crTime) {
    this.crTime = crTime;
  }

  @Basic
  @Column(name = "cr_date")
  public LocalDateTime getCrDate() {
    return crDate;
  }

  public void setCrDate(LocalDateTime crDate) {
    this.crDate = crDate;
  }

  @Basic
  @Column(name = "cr_receivedby")
  public String getCrReceivedby() {
    return crReceivedby;
  }

  public void setCrReceivedby(String crReceivedby) {
    this.crReceivedby = crReceivedby;
  }

  @Basic
  @Column(name = "cr_unsub_7")
  public String getCrUnsub7() {
    return crUnsub7;
  }

  public void setCrUnsub7(String crUnsub7) {
    this.crUnsub7 = crUnsub7;
  }

  @Basic
  @Column(name = "cr_insub_7")
  public String getCrInsub7() {
    return crInsub7;
  }

  public void setCrInsub7(String crInsub7) {
    this.crInsub7 = crInsub7;
  }

  @Basic
  @Column(name = "cr_sub_7")
  public String getCrSub7() {
    return crSub7;
  }

  public void setCrSub7(String crSub7) {
    this.crSub7 = crSub7;
  }

  @Basic
  @Column(name = "cr_allegation_7")
  public String getCrAllegation7() {
    return crAllegation7;
  }

  public void setCrAllegation7(String crAllegation7) {
    this.crAllegation7 = crAllegation7;
  }

  @Basic
  @Column(name = "z_7")
  public String getZ_7() {
    return z_7;
  }

  public void setZ_7(String z_7) {
    this.z_7 = z_7;
  }

  @Basic
  @Column(name = "undr_apl_7")
  public String getUndrApl7() {
    return undrApl7;
  }

  public void setUndrApl7(String undrApl7) {
    this.undrApl7 = undrApl7;
  }

  @Basic
  @Column(name = "cr_code_7")
  public String getCrCode7() {
    return crCode7;
  }

  public void setCrCode7(String crCode7) {
    this.crCode7 = crCode7;
  }

  @Basic
  @Column(name = "cr_unsub_6")
  public String getCrUnsub6() {
    return crUnsub6;
  }

  public void setCrUnsub6(String crUnsub6) {
    this.crUnsub6 = crUnsub6;
  }

  @Basic
  @Column(name = "cr_insub_6")
  public String getCrInsub6() {
    return crInsub6;
  }

  public void setCrInsub6(String crInsub6) {
    this.crInsub6 = crInsub6;
  }

  @Basic
  @Column(name = "cr_sub_6")
  public String getCrSub6() {
    return crSub6;
  }

  public void setCrSub6(String crSub6) {
    this.crSub6 = crSub6;
  }

  @Basic
  @Column(name = "cr_allegation_6")
  public String getCrAllegation6() {
    return crAllegation6;
  }

  public void setCrAllegation6(String crAllegation6) {
    this.crAllegation6 = crAllegation6;
  }

  @Basic
  @Column(name = "z_6")
  public String getZ_6() {
    return z_6;
  }

  public void setZ_6(String z_6) {
    this.z_6 = z_6;
  }

  @Basic
  @Column(name = "undr_apl_6")
  public String getUndrApl6() {
    return undrApl6;
  }

  public void setUndrApl6(String undrApl6) {
    this.undrApl6 = undrApl6;
  }

  @Basic
  @Column(name = "cr_code_6")
  public String getCrCode6() {
    return crCode6;
  }

  public void setCrCode6(String crCode6) {
    this.crCode6 = crCode6;
  }

  @Basic
  @Column(name = "cr_unsub_5")
  public String getCrUnsub5() {
    return crUnsub5;
  }

  public void setCrUnsub5(String crUnsub5) {
    this.crUnsub5 = crUnsub5;
  }

  @Basic
  @Column(name = "cr_insub_5")
  public String getCrInsub5() {
    return crInsub5;
  }

  public void setCrInsub5(String crInsub5) {
    this.crInsub5 = crInsub5;
  }

  @Basic
  @Column(name = "cr_sub_5")
  public String getCrSub5() {
    return crSub5;
  }

  public void setCrSub5(String crSub5) {
    this.crSub5 = crSub5;
  }

  @Basic
  @Column(name = "cr_allegation_5")
  public String getCrAllegation5() {
    return crAllegation5;
  }

  public void setCrAllegation5(String crAllegation5) {
    this.crAllegation5 = crAllegation5;
  }

  @Basic
  @Column(name = "z_5")
  public String getZ_5() {
    return z_5;
  }

  public void setZ_5(String z_5) {
    this.z_5 = z_5;
  }

  @Basic
  @Column(name = "undr_apl_5")
  public String getUndrApl5() {
    return undrApl5;
  }

  public void setUndrApl5(String undrApl5) {
    this.undrApl5 = undrApl5;
  }

  @Basic
  @Column(name = "cr_code_5")
  public String getCrCode5() {
    return crCode5;
  }

  public void setCrCode5(String crCode5) {
    this.crCode5 = crCode5;
  }

  @Basic
  @Column(name = "cr_unsub_4")
  public String getCrUnsub4() {
    return crUnsub4;
  }

  public void setCrUnsub4(String crUnsub4) {
    this.crUnsub4 = crUnsub4;
  }

  @Basic
  @Column(name = "cr_insub_4")
  public String getCrInsub4() {
    return crInsub4;
  }

  public void setCrInsub4(String crInsub4) {
    this.crInsub4 = crInsub4;
  }

  @Basic
  @Column(name = "cr_sub_4")
  public String getCrSub4() {
    return crSub4;
  }

  public void setCrSub4(String crSub4) {
    this.crSub4 = crSub4;
  }

  @Basic
  @Column(name = "cr_allegation_4")
  public String getCrAllegation4() {
    return crAllegation4;
  }

  public void setCrAllegation4(String crAllegation4) {
    this.crAllegation4 = crAllegation4;
  }

  @Basic
  @Column(name = "z_4")
  public String getZ_4() {
    return z_4;
  }

  public void setZ_4(String z_4) {
    this.z_4 = z_4;
  }

  @Basic
  @Column(name = "undr_apl_4")
  public String getUndrApl4() {
    return undrApl4;
  }

  public void setUndrApl4(String undrApl4) {
    this.undrApl4 = undrApl4;
  }

  @Basic
  @Column(name = "cr_code_4")
  public String getCrCode4() {
    return crCode4;
  }

  public void setCrCode4(String crCode4) {
    this.crCode4 = crCode4;
  }

  @Basic
  @Column(name = "cr_unsub_3")
  public String getCrUnsub3() {
    return crUnsub3;
  }

  public void setCrUnsub3(String crUnsub3) {
    this.crUnsub3 = crUnsub3;
  }

  @Basic
  @Column(name = "cr_insub_3")
  public String getCrInsub3() {
    return crInsub3;
  }

  public void setCrInsub3(String crInsub3) {
    this.crInsub3 = crInsub3;
  }

  @Basic
  @Column(name = "cr_sub_3")
  public String getCrSub3() {
    return crSub3;
  }

  public void setCrSub3(String crSub3) {
    this.crSub3 = crSub3;
  }

  @Basic
  @Column(name = "cr_allegation_3")
  public String getCrAllegation3() {
    return crAllegation3;
  }

  public void setCrAllegation3(String crAllegation3) {
    this.crAllegation3 = crAllegation3;
  }

  @Basic
  @Column(name = "z_3")
  public String getZ_3() {
    return z_3;
  }

  public void setZ_3(String z_3) {
    this.z_3 = z_3;
  }

  @Basic
  @Column(name = "undr_apl_3")
  public String getUndrApl3() {
    return undrApl3;
  }

  public void setUndrApl3(String undrApl3) {
    this.undrApl3 = undrApl3;
  }

  @Basic
  @Column(name = "cr_code_3")
  public String getCrCode3() {
    return crCode3;
  }

  public void setCrCode3(String crCode3) {
    this.crCode3 = crCode3;
  }

  @Basic
  @Column(name = "cr_unsub_2")
  public String getCrUnsub2() {
    return crUnsub2;
  }

  public void setCrUnsub2(String crUnsub2) {
    this.crUnsub2 = crUnsub2;
  }

  @Basic
  @Column(name = "cr_insub_2")
  public String getCrInsub2() {
    return crInsub2;
  }

  public void setCrInsub2(String crInsub2) {
    this.crInsub2 = crInsub2;
  }

  @Basic
  @Column(name = "cr_sub_2")
  public String getCrSub2() {
    return crSub2;
  }

  public void setCrSub2(String crSub2) {
    this.crSub2 = crSub2;
  }

  @Basic
  @Column(name = "cr_allegation_2")
  public String getCrAllegation2() {
    return crAllegation2;
  }

  public void setCrAllegation2(String crAllegation2) {
    this.crAllegation2 = crAllegation2;
  }

  @Basic
  @Column(name = "z_2")
  public String getZ_2() {
    return z_2;
  }

  public void setZ_2(String z_2) {
    this.z_2 = z_2;
  }

  @Basic
  @Column(name = "undr_apl_2")
  public String getUndrApl2() {
    return undrApl2;
  }

  public void setUndrApl2(String undrApl2) {
    this.undrApl2 = undrApl2;
  }

  @Basic
  @Column(name = "cr_code_2")
  public String getCrCode2() {
    return crCode2;
  }

  public void setCrCode2(String crCode2) {
    this.crCode2 = crCode2;
  }

  @Basic
  @Column(name = "cr_unsub_1")
  public String getCrUnsub1() {
    return crUnsub1;
  }

  public void setCrUnsub1(String crUnsub1) {
    this.crUnsub1 = crUnsub1;
  }

  @Basic
  @Column(name = "cr_insub_1")
  public String getCrInsub1() {
    return crInsub1;
  }

  public void setCrInsub1(String crInsub1) {
    this.crInsub1 = crInsub1;
  }

  @Basic
  @Column(name = "cr_sub_1")
  public String getCrSub1() {
    return crSub1;
  }

  public void setCrSub1(String crSub1) {
    this.crSub1 = crSub1;
  }

  @Basic
  @Column(name = "cr_allegation_1")
  public String getCrAllegation1() {
    return crAllegation1;
  }

  public void setCrAllegation1(String crAllegation1) {
    this.crAllegation1 = crAllegation1;
  }

  @Basic
  @Column(name = "z_1")
  public String getZ_1() {
    return z_1;
  }

  public void setZ_1(String z_1) {
    this.z_1 = z_1;
  }

  @Basic
  @Column(name = "undr_apl_1")
  public String getUndrApl1() {
    return undrApl1;
  }

  public void setUndrApl1(String undrApl1) {
    this.undrApl1 = undrApl1;
  }

  @Basic
  @Column(name = "cr_code_1")
  public String getCrCode1() {
    return crCode1;
  }

  public void setCrCode1(String crCode1) {
    this.crCode1 = crCode1;
  }

  @Basic
  @Column(name = "cr_unsub")
  public String getCrUnsub() {
    return crUnsub;
  }

  public void setCrUnsub(String crUnsub) {
    this.crUnsub = crUnsub;
  }

  @Basic
  @Column(name = "cr_insub")
  public String getCrInsub() {
    return crInsub;
  }

  public void setCrInsub(String crInsub) {
    this.crInsub = crInsub;
  }

  @Basic
  @Column(name = "cr_sub")
  public String getCrSub() {
    return crSub;
  }

  public void setCrSub(String crSub) {
    this.crSub = crSub;
  }

  @Basic
  @Column(name = "cr_allegation")
  public String getCrAllegation() {
    return crAllegation;
  }

  public void setCrAllegation(String crAllegation) {
    this.crAllegation = crAllegation;
  }

  @Basic
  @Column(name = "z")
  public String getZ() {
    return z;
  }

  public void setZ(String z) {
    this.z = z;
  }

  @Basic
  @Column(name = "undr_apl")
  public String getUndrApl() {
    return undrApl;
  }

  public void setUndrApl(String undrApl) {
    this.undrApl = undrApl;
  }

  @Basic
  @Column(name = "cr_code")
  public String getCrCode() {
    return crCode;
  }

  public void setCrCode(String crCode) {
    this.crCode = crCode;
  }

  @Basic
  @Column(name = "facility_telephone")
  public String getFacilityTelephone() {
    return facilityTelephone;
  }

  public void setFacilityTelephone(String facilityTelephone) {
    this.facilityTelephone = facilityTelephone;
  }

  @Basic
  @Column(name = "facility_zip")
  public String getFacilityZip() {
    return facilityZip;
  }

  public void setFacilityZip(String facilityZip) {
    this.facilityZip = facilityZip;
  }

  @Basic
  @Column(name = "facility_city")
  public String getFacilityCity() {
    return facilityCity;
  }

  public void setFacilityCity(String facilityCity) {
    this.facilityCity = facilityCity;
  }

  @Basic
  @Column(name = "facility_address")
  public String getFacilityAddress() {
    return facilityAddress;
  }

  public void setFacilityAddress(String facilityAddress) {
    this.facilityAddress = facilityAddress;
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
  @Column(name = "facility_number_text")
  public String getFacilityNumberText() {
    return facilityNumberText;
  }

  public void setFacilityNumberText(String facilityNumberText) {
    this.facilityNumberText = facilityNumberText;
  }

  @Basic
  @Column(name = "facility_type")
  public String getFacilityType() {
    return facilityType;
  }

  public void setFacilityType(String facilityType) {
    this.facilityType = facilityType;
  }

  @Basic
  @Column(name = "cr_controlnumber")
  public String getCrControlnumber() {
    return crControlnumber;
  }

  public void setCrControlnumber(String crControlnumber) {
    this.crControlnumber = crControlnumber;
  }

  @Basic
  @Column(name = "cr_visitduedate")
  public LocalDateTime getCrVisitduedate() {
    return crVisitduedate;
  }

  public void setCrVisitduedate(LocalDateTime crVisitduedate) {
    this.crVisitduedate = crVisitduedate;
  }

  @Basic
  @Column(name = "cr_doname")
  public String getCrDoname() {
    return crDoname;
  }

  public void setCrDoname(String crDoname) {
    this.crDoname = crDoname;
  }

  @Basic
  @Column(name = "cr_prioritynr")
  public String getCrPrioritynr() {
    return crPrioritynr;
  }

  public void setCrPrioritynr(String crPrioritynr) {
    this.crPrioritynr = crPrioritynr;
  }

  @Basic
  @Column(name = "cr_priority")
  public String getCrPriority() {
    return crPriority;
  }

  public void setCrPriority(String crPriority) {
    this.crPriority = crPriority;
  }

  @Basic
  @Column(name = "p_complainantanonymous")
  public String getpComplainantanonymous() {
    return pComplainantanonymous;
  }

  public void setpComplainantanonymous(String pComplainantanonymous) {
    this.pComplainantanonymous = pComplainantanonymous;
  }

  @Basic
  @Column(name = "cr_complainantanonymous")
  public String getCrComplainantanonymous() {
    return crComplainantanonymous;
  }

  public void setCrComplainantanonymous(String crComplainantanonymous) {
    this.crComplainantanonymous = crComplainantanonymous;
  }

  @Basic
  @Column(name = "p_abusereportfiled")
  public String getpAbusereportfiled() {
    return pAbusereportfiled;
  }

  public void setpAbusereportfiled(String pAbusereportfiled) {
    this.pAbusereportfiled = pAbusereportfiled;
  }

  @Basic
  @Column(name = "cr_abusereportfiled")
  public String getCrAbusereportfiled() {
    return crAbusereportfiled;
  }

  public void setCrAbusereportfiled(String crAbusereportfiled) {
    this.crAbusereportfiled = crAbusereportfiled;
  }

  @Basic
  @Column(name = "cr_complainantrelation")
  public String getCrComplainantrelation() {
    return crComplainantrelation;
  }

  public void setCrComplainantrelation(String crComplainantrelation) {
    this.crComplainantrelation = crComplainantrelation;
  }

  @Basic
  @Column(name = "cr_complainantnightphone")
  public String getCrComplainantnightphone() {
    return crComplainantnightphone;
  }

  public void setCrComplainantnightphone(String crComplainantnightphone) {
    this.crComplainantnightphone = crComplainantnightphone;
  }

  @Basic
  @Column(name = "cr_complainantdayphone")
  public String getCrComplainantdayphone() {
    return crComplainantdayphone;
  }

  public void setCrComplainantdayphone(String crComplainantdayphone) {
    this.crComplainantdayphone = crComplainantdayphone;
  }

  @Basic
  @Column(name = "cr_complainantzip")
  public String getCrComplainantzip() {
    return crComplainantzip;
  }

  public void setCrComplainantzip(String crComplainantzip) {
    this.crComplainantzip = crComplainantzip;
  }

  @Basic
  @Column(name = "cr_complainantcity")
  public String getCrComplainantcity() {
    return crComplainantcity;
  }

  public void setCrComplainantcity(String crComplainantcity) {
    this.crComplainantcity = crComplainantcity;
  }

  @Basic
  @Column(name = "cr_complainantaddress")
  public String getCrComplainantaddress() {
    return crComplainantaddress;
  }

  public void setCrComplainantaddress(String crComplainantaddress) {
    this.crComplainantaddress = crComplainantaddress;
  }

  @Basic
  @Column(name = "cr_complainantname")
  public String getCrComplainantname() {
    return crComplainantname;
  }

  public void setCrComplainantname(String crComplainantname) {
    this.crComplainantname = crComplainantname;
  }

  @Basic
  @Column(name = "p_howreported")
  public String getpHowreported() {
    return pHowreported;
  }

  public void setpHowreported(String pHowreported) {
    this.pHowreported = pHowreported;
  }

  @Basic
  @Column(name = "cr_howreported")
  public String getCrHowreported() {
    return crHowreported;
  }

  public void setCrHowreported(String crHowreported) {
    this.crHowreported = crHowreported;
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
  @Column(name = "sign_off_date")
  public String getSignOffDate() {
    return signOffDate;
  }

  public void setSignOffDate(String signOffDate) {
    this.signOffDate = signOffDate;
  }

  @Basic
  @Column(name = "ai_action_taken")
  public String getAiActionTaken() {
    return aiActionTaken;
  }

  public void setAiActionTaken(String aiActionTaken) {
    this.aiActionTaken = aiActionTaken;
  }

  @Basic
  @Column(name = "ai_total_hours")
  public String getAiTotalHours() {
    return aiTotalHours;
  }

  public void setAiTotalHours(String aiTotalHours) {
    this.aiTotalHours = aiTotalHours;
  }

  @Basic
  @Column(name = "ai_num_visits")
  public String getAiNumVisits() {
    return aiNumVisits;
  }

  public void setAiNumVisits(String aiNumVisits) {
    this.aiNumVisits = aiNumVisits;
  }

  @Basic
  @Column(name = "audit_acpt_date")
  public String getAuditAcptDate() {
    return auditAcptDate;
  }

  public void setAuditAcptDate(String auditAcptDate) {
    this.auditAcptDate = auditAcptDate;
  }

  @Basic
  @Column(name = "audit_accept")
  public String getAuditAccept() {
    return auditAccept;
  }

  public void setAuditAccept(String auditAccept) {
    this.auditAccept = auditAccept;
  }

  @Basic
  @Column(name = "audit_rfr_date")
  public String getAuditRfrDate() {
    return auditRfrDate;
  }

  public void setAuditRfrDate(String auditRfrDate) {
    this.auditRfrDate = auditRfrDate;
  }

  @Basic
  @Column(name = "audit_refer")
  public String getAuditRefer() {
    return auditRefer;
  }

  public void setAuditRefer(String auditRefer) {
    this.auditRefer = auditRefer;
  }

  @Basic
  @Column(name = "why")
  public String getWhy() {
    return why;
  }

  public void setWhy(String why) {
    this.why = why;
  }

  @Basic
  @Column(name = "p_follow_up")
  public String getpFollowUp() {
    return pFollowUp;
  }

  public void setpFollowUp(String pFollowUp) {
    this.pFollowUp = pFollowUp;
  }

  @Basic
  @Column(name = "follow_up")
  public String getFollowUp() {
    return followUp;
  }

  public void setFollowUp(String followUp) {
    this.followUp = followUp;
  }

  @Basic
  @Column(name = "admin_total_hours")
  public String getAdminTotalHours() {
    return adminTotalHours;
  }

  public void setAdminTotalHours(String adminTotalHours) {
    this.adminTotalHours = adminTotalHours;
  }

  @Basic
  @Column(name = "action_taken")
  public String getActionTaken() {
    return actionTaken;
  }

  public void setActionTaken(String actionTaken) {
    this.actionTaken = actionTaken;
  }

  @Basic
  @Column(name = "total_hours")
  public String getTotalHours() {
    return totalHours;
  }

  public void setTotalHours(String totalHours) {
    this.totalHours = totalHours;
  }

  @Basic
  @Column(name = "num_of_visits")
  public String getNumOfVisits() {
    return numOfVisits;
  }

  public void setNumOfVisits(String numOfVisits) {
    this.numOfVisits = numOfVisits;
  }

  @Basic
  @Column(name = "c_home_type")
  public String getcHomeType() {
    return cHomeType;
  }

  public void setcHomeType(String cHomeType) {
    this.cHomeType = cHomeType;
  }

  @Basic
  @Column(name = "c_home_zip")
  public String getcHomeZip() {
    return cHomeZip;
  }

  public void setcHomeZip(String cHomeZip) {
    this.cHomeZip = cHomeZip;
  }

  @Basic
  @Column(name = "c_home_telephone")
  public String getcHomeTelephone() {
    return cHomeTelephone;
  }

  public void setcHomeTelephone(String cHomeTelephone) {
    this.cHomeTelephone = cHomeTelephone;
  }

  @Basic
  @Column(name = "c_home_city")
  public String getcHomeCity() {
    return cHomeCity;
  }

  public void setcHomeCity(String cHomeCity) {
    this.cHomeCity = cHomeCity;
  }

  @Basic
  @Column(name = "c_home_licensee")
  public String getcHomeLicensee() {
    return cHomeLicensee;
  }

  public void setcHomeLicensee(String cHomeLicensee) {
    this.cHomeLicensee = cHomeLicensee;
  }

  @Basic
  @Column(name = "c_home_address")
  public String getcHomeAddress() {
    return cHomeAddress;
  }

  public void setcHomeAddress(String cHomeAddress) {
    this.cHomeAddress = cHomeAddress;
  }

  @Basic
  @Column(name = "c_home_name")
  public String getcHomeName() {
    return cHomeName;
  }

  public void setcHomeName(String cHomeName) {
    this.cHomeName = cHomeName;
  }

  @Basic
  @Column(name = "c_home_number")
  public String getcHomeNumber() {
    return cHomeNumber;
  }

  public void setcHomeNumber(String cHomeNumber) {
    this.cHomeNumber = cHomeNumber;
  }

  @Basic
  @Column(name = "cr_lps_rejectcomments")
  public String getCrLpsRejectcomments() {
    return crLpsRejectcomments;
  }

  public void setCrLpsRejectcomments(String crLpsRejectcomments) {
    this.crLpsRejectcomments = crLpsRejectcomments;
  }

  @Basic
  @Column(name = "cr_lps_rejname")
  public String getCrLpsRejname() {
    return crLpsRejname;
  }

  public void setCrLpsRejname(String crLpsRejname) {
    this.crLpsRejname = crLpsRejname;
  }

  @Basic
  @Column(name = "cr_lps_rejdate")
  public String getCrLpsRejdate() {
    return crLpsRejdate;
  }

  public void setCrLpsRejdate(String crLpsRejdate) {
    this.crLpsRejdate = crLpsRejdate;
  }

  @Basic
  @Column(name = "cr_lps_appname_1")
  public String getCrLpsAppname1() {
    return crLpsAppname1;
  }

  public void setCrLpsAppname1(String crLpsAppname1) {
    this.crLpsAppname1 = crLpsAppname1;
  }

  @Basic
  @Column(name = "cr_lps_appdate_1")
  public LocalDateTime getCrLpsAppdate1() {
    return crLpsAppdate1;
  }

  public void setCrLpsAppdate1(LocalDateTime crLpsAppdate1) {
    this.crLpsAppdate1 = crLpsAppdate1;
  }

  @Basic
  @Column(name = "cr_lps_appname")
  public String getCrLpsAppname() {
    return crLpsAppname;
  }

  public void setCrLpsAppname(String crLpsAppname) {
    this.crLpsAppname = crLpsAppname;
  }

  @Basic
  @Column(name = "cr_lps_appdate")
  public LocalDateTime getCrLpsAppdate() {
    return crLpsAppdate;
  }

  public void setCrLpsAppdate(LocalDateTime crLpsAppdate) {
    this.crLpsAppdate = crLpsAppdate;
  }

  @Basic
  @Column(name = "cr_lps_name")
  public String getCrLpsName() {
    return crLpsName;
  }

  public void setCrLpsName(String crLpsName) {
    this.crLpsName = crLpsName;
  }

  @Basic
  @Column(name = "cr_lpa_subname")
  public String getCrLpaSubname() {
    return crLpaSubname;
  }

  public void setCrLpaSubname(String crLpaSubname) {
    this.crLpaSubname = crLpaSubname;
  }

  @Basic
  @Column(name = "cr_lpa_subdate")
  public LocalDateTime getCrLpaSubdate() {
    return crLpaSubdate;
  }

  public void setCrLpaSubdate(LocalDateTime crLpaSubdate) {
    this.crLpaSubdate = crLpaSubdate;
  }

  @Basic
  @Column(name = "cr_lpa_name")
  public String getCrLpaName() {
    return crLpaName;
  }

  public void setCrLpaName(String crLpaName) {
    this.crLpaName = crLpaName;
  }

  @Basic
  @Column(name = "cr_status")
  public String getCrStatus() {
    return crStatus;
  }

  public void setCrStatus(String crStatus) {
    this.crStatus = crStatus;
  }

  @Basic
  @Column(name = "date_assigned")
  public LocalDateTime getDateAssigned() {
    return dateAssigned;
  }

  public void setDateAssigned(LocalDateTime dateAssigned) {
    this.dateAssigned = dateAssigned;
  }

  @Basic
  @Column(name = "lpa_assigned")
  public String getLpaAssigned() {
    return lpaAssigned;
  }

  public void setLpaAssigned(String lpaAssigned) {
    this.lpaAssigned = lpaAssigned;
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
  @Column(name = "cre_authornames")
  public String getCreAuthornames() {
    return creAuthornames;
  }

  public void setCreAuthornames(String creAuthornames) {
    this.creAuthornames = creAuthornames;
  }

  @Basic
  @Column(name = "verified_by")
  public String getVerifiedBy() {
    return verifiedBy;
  }

  public void setVerifiedBy(String verifiedBy) {
    this.verifiedBy = verifiedBy;
  }

  @Basic
  @Column(name = "verified_date")
  public String getVerifiedDate() {
    return verifiedDate;
  }

  public void setVerifiedDate(String verifiedDate) {
    this.verifiedDate = verifiedDate;
  }

  @Basic
  @Column(name = "verified")
  public String getVerified() {
    return verified;
  }

  public void setVerified(String verified) {
    this.verified = verified;
  }

  @Basic
  @Column(name = "agency_selection_1")
  public String getAgencySelection1() {
    return agencySelection1;
  }

  public void setAgencySelection1(String agencySelection1) {
    this.agencySelection1 = agencySelection1;
  }

  @Basic
  @Column(name = "agency_selection")
  public String getAgencySelection() {
    return agencySelection;
  }

  public void setAgencySelection(String agencySelection) {
    this.agencySelection = agencySelection;
  }

  @Basic
  @Column(name = "from_intake")
  public String getFromIntake() {
    return fromIntake;
  }

  public void setFromIntake(String fromIntake) {
    this.fromIntake = fromIntake;
  }

  @Basic
  @Column(name = "fac_do_nbr")
  public BigInteger getFacDoNbr() {
    return facDoNbr;
  }

  public void setFacDoNbr(BigInteger facDoNbr) {
    this.facDoNbr = facDoNbr;
  }

  @Basic
  @Column(name = "tendaymissed")
  public String getTendaymissed() {
    return tendaymissed;
  }

  public void setTendaymissed(String tendaymissed) {
    this.tendaymissed = tendaymissed;
  }

  @Basic
  @Column(name = "tendayvisit_flag")
  public String getTendayvisitFlag() {
    return tendayvisitFlag;
  }

  public void setTendayvisitFlag(String tendayvisitFlag) {
    this.tendayvisitFlag = tendayvisitFlag;
  }

  @Basic
  @Column(name = "fac_primary_nbr")
  public BigInteger getFacPrimaryNbr() {
    return facPrimaryNbr;
  }

  public void setFacPrimaryNbr(BigInteger facPrimaryNbr) {
    this.facPrimaryNbr = facPrimaryNbr;
  }

  @Basic
  @Column(name = "faslpacode_1")
  public String getFaslpacode1() {
    return faslpacode1;
  }

  public void setFaslpacode1(String faslpacode1) {
    this.faslpacode1 = faslpacode1;
  }

  @Basic
  @Column(name = "reject_comments")
  public String getRejectComments() {
    return rejectComments;
  }

  public void setRejectComments(String rejectComments) {
    this.rejectComments = rejectComments;
  }

  @Basic
  @Column(name = "ancs")
  public String getAncs() {
    return ancs;
  }

  public void setAncs(String ancs) {
    this.ancs = ancs;
  }

  @Basic
  @Column(name = "approve_by_history")
  public String getApproveByHistory() {
    return approveByHistory;
  }

  public void setApproveByHistory(String approveByHistory) {
    this.approveByHistory = approveByHistory;
  }

  @Basic
  @Column(name = "approve_date_history")
  public String getApproveDateHistory() {
    return approveDateHistory;
  }

  public void setApproveDateHistory(String approveDateHistory) {
    this.approveDateHistory = approveDateHistory;
  }

  @Basic
  @Column(name = "approve_history")
  public String getApproveHistory() {
    return approveHistory;
  }

  public void setApproveHistory(String approveHistory) {
    this.approveHistory = approveHistory;
  }

  @Basic
  @Column(name = "assign_by_history")
  public String getAssignByHistory() {
    return assignByHistory;
  }

  public void setAssignByHistory(String assignByHistory) {
    this.assignByHistory = assignByHistory;
  }

  @Basic
  @Column(name = "assign_date_history")
  public String getAssignDateHistory() {
    return assignDateHistory;
  }

  public void setAssignDateHistory(String assignDateHistory) {
    this.assignDateHistory = assignDateHistory;
  }

  @Basic
  @Column(name = "assign_history")
  public String getAssignHistory() {
    return assignHistory;
  }

  public void setAssignHistory(String assignHistory) {
    this.assignHistory = assignHistory;
  }

  @Basic
  @Column(name = "complaintsumunid")
  public String getComplaintsumunid() {
    return complaintsumunid;
  }

  public void setComplaintsumunid(String complaintsumunid) {
    this.complaintsumunid = complaintsumunid;
  }

  @Basic
  @Column(name = "complaintunid")
  public String getComplaintunid() {
    return complaintunid;
  }

  public void setComplaintunid(String complaintunid) {
    this.complaintunid = complaintunid;
  }

  @Basic
  @Column(name = "search_address")
  public String getSearchAddress() {
    return searchAddress;
  }

  public void setSearchAddress(String searchAddress) {
    this.searchAddress = searchAddress;
  }

  @Basic
  @Column(name = "search_name")
  public String getSearchName() {
    return searchName;
  }

  public void setSearchName(String searchName) {
    this.searchName = searchName;
  }

  @Basic
  @Column(name = "search_number")
  public String getSearchNumber() {
    return searchNumber;
  }

  public void setSearchNumber(String searchNumber) {
    this.searchNumber = searchNumber;
  }

  @Basic
  @Column(name = "createddbname")
  public String getCreateddbname() {
    return createddbname;
  }

  public void setCreateddbname(String createddbname) {
    this.createddbname = createddbname;
  }

  @Basic
  @Column(name = "facility_state")
  public String getFacilityState() {
    return facilityState;
  }

  public void setFacilityState(String facilityState) {
    this.facilityState = facilityState;
  }

  @Basic
  @Column(name = "facility_admin")
  public String getFacilityAdmin() {
    return facilityAdmin;
  }

  public void setFacilityAdmin(String facilityAdmin) {
    this.facilityAdmin = facilityAdmin;
  }

  @Basic
  @Column(name = "cr_visit_date")
  public LocalDateTime getCrVisitDate() {
    return crVisitDate;
  }

  public void setCrVisitDate(LocalDateTime crVisitDate) {
    this.crVisitDate = crVisitDate;
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
  @Column(name = "dt_lastmod")
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
    return originalunidkey;
  }
}
