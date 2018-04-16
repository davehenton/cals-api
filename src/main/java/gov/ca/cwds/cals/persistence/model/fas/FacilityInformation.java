package gov.ca.cwds.cals.persistence.model.fas;

import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.data.persistence.PersistentObject;
import java.time.LocalDate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
@Entity
@Table(name = "facility_information")
@SuppressWarnings("squid:S3437")
public class FacilityInformation implements PersistentObject {
  private static final long serialVersionUID = -2566583736496919788L;

  private String responseDescription;
  private String dontSaveFlag;
  private String auditHistory;
  private String originalunidkey;
  private String editorName;
  private String editHistory;
  private LocalDateTime dtCreated;
  private String creatorName;
  private String fnLoc;
  private String fnamLoc;
  private String rndmeditcomments;
  private String untitled;
  private Long fasAnlReqVstNbrTimesMissed;
  private Long fasAnlReqVstTimesOverdue;
  private LocalDateTime fasAnlReqLastVstMissedDate;
  private LocalDateTime fasAnlReqVstOverdueDate;
  private LocalDateTime fasAnlReqVstMissedDate;
  private String fasAnlReqVstOverdue;
  private String fasAnlReqVstMissed;
  private LocalDateTime fasAnlReqVstDuebyDate;
  private LocalDateTime fasLastAnlReqVisitDate;
  private String fasAnlReqVstDue;
  private Long fasReqVstNbrTimesMissed;
  private Long fasReqVstNbrTimesOverdue;
  private LocalDateTime fasReqLastVstMissedDate;
  private LocalDateTime fasReqVstOverdueDate;
  private LocalDateTime fasReqVstMissedDate;
  private String fasReqVstOverdue;
  private String fasReqVstMissed;
  private LocalDateTime fasReqVstDuebyDate;
  private LocalDateTime fasLastReqVisitDate;
  private String fasReqVstDue;
  private String d8;
  private String facLicComments2;
  private String facLicComments;
  private String d7;
  private LocalDateTime facGhLicEffDate;
  private LocalDateTime facRfhConvertDate;
  private LocalDateTime facPlanOfOpRecvDate;
  private LocalDateTime facPlanOfOpApprovalDate;
  private LocalDateTime facMentalHealthExpireDate;
  private LocalDateTime facMentalHealthDate;
  private LocalDateTime facAccreditationExpireDate;
  private LocalDateTime facAccreditationDate;
  private String fasAccAgency;
  private Long facAccreditationCode;
  private LocalDateTime facStrtpAppDeniedDate;
  private LocalDateTime facStrtpAppRecvDate;
  private String fnAccred;
  private String fnamAccred;
  private Long facLocIndicator;
  private Long facChangeLocInd;
  private Long facDoNbr;
  private String facEmailAddress;
  private String facRegionCo;
  private String facRegionDo;
  private String facDeficiency;
  private String facComplaint;
  private Long facNbrNew;
  private String facGhIndicator;
  private String facFcrbProgram;
  private String facDualNumber;
  private String facDualId;
  private LocalDateTime facBillingDate;
  private Long facPrimaryNbr;
  private String facAggStatus;
  private String facLegalActionIndicator;
  private String facActionCode;
  private Long facRegionNbr;
  private Long facCfrsIdNbr;
  private Long facFmRegionNbr;
  private Long facCoNbr;
  private LocalDateTime facClientServedApprDate;
  private Long facClientServed;
  private String fnLegal;
  private String fnamLegal;
  private String d6;
  private Long facNonamb65PlusNbr;
  private Long facAmb65PlusNbr;
  private Long facNonamb1864Nbr;
  private Long facAmb1864Nbr;
  private Long facNonamb117Nbr;
  private Long facAmb117Nbr;
  private String fnAmb;
  private String fnamAmb;
  private String d5;
  private LocalDateTime facPreLicVisitDate;
  private Long facAnnualVisitYear;
  private LocalDateTime facLastDeferVisitDate;
  private Long facLastDeferVisitReason;
  private LocalDate facRenewalDeferDate;
  private LocalDate facPostLicDeferDate;
  private LocalDate facRenewalVisitDate;
  private LocalDate facPostLicVisitDate;
  private String facRenewalVisitAppr;
  private String facPostLicVisitAppr;
  private LocalDate facAnnual22MoDeferDate;
  private LocalDate facAnnual10MoDeferDate;
  private LocalDate facAnnual22MoVisitDate;
  private LocalDate facAnnual10MoVisitDate;
  private String facAnnual22MoVisitAppr;
  private String facAnnual10MoVisitAppr;
  private Long facLastVisitReason;
  private LocalDateTime facLastVisitDate;
  private String fnVisit;
  private String fnamVisit;
  private String d4;
  private LocalDateTime facLastUpdDate;
  private LocalDateTime facIncCapEffDate;
  private LocalDateTime facCapIncClosedDate;
  private LocalDateTime facCapIncRecDate;
  private LocalDateTime facClosedProcessDate;
  private LocalDateTime facUnlicOrigInputDate;
  private LocalDateTime facLastChangeDate;
  private LocalDateTime facLicFirstDate;
  private LocalDateTime facLicExpirDate;
  private LocalDateTime facLicEffDate;
  private LocalDateTime facLastFireclearDate;
  private String facLicenseeType;
  private String fnLicensing;
  private String fnamLicensing;
  private String d3;
  private String facLicMailZipCode;
  private String facMailZipCode;
  private String facLicMailState;
  private String facMailState;
  private String facLicMailCity;
  private String facMailCity;
  private String facLicMailStreetAddr;
  private String facMailStreetAddr;
  private String fnMailing;
  private String fnamMailing;
  private String d2;
  private String facilitycomments;
  private LocalDateTime facInactiveNoticeDate;
  private LocalDateTime facInactiveStartDate;
  private String fasLpaCode;
  private String facRequiredVisit;
  private LocalDateTime facClosedDate;
  private LocalDateTime facOrigApplRecDate;
  private String lpaCode;
  private String facilityTelephone;
  private LocalDateTime facilityAnnivDate;
  private String licenseeName;
  private String facCountyName;
  private String facilityAdmin;
  private String facilityZip;
  private String facilityStatusText;
  private String facilityState;
  private Long facilityStatus;
  private String facilityCity;
  private Long facilityCapacity;
  private String facilityAddress;
  private String facilityTypeText;
  private String facilityName;
  private String facilityType;
  private Long facilityNumber;
  private String d1;
  private Long facOldFfaNbr;
  private String facPin;
  private String archiveFlag;
  private Long facSfmIdNbr;
  private LocalDateTime facPlacementDate;
  private String facPlacementReady;
  private LocalDateTime facMidYrVisitDate;
  private String facMidYrVisitAppr;
  private LocalDateTime facMidYrDeferDate;
  private String facStreetSearch;
  private String buttonSelected;
  private String sqlCommand;
  private String dataSource;
  private String extErrorMessage;
  private String errorMessage;
  private String connected;
  private String formStatus;
  private String doNumber;
  private String randomChangeReason;
  private String facilityNumberText;
  private String deleteFlag;
  private String editors;
  private LocalDateTime dtModified;

  @Basic
  @Column(name = "response_description", nullable = true, length = 8000)
  public String getResponseDescription() {
    return responseDescription;
  }

  public void setResponseDescription(String responseDescription) {
    this.responseDescription = responseDescription;
  }

  @Basic
  @Column(name = "dont_save_flag", nullable = true, length = 256)
  public String getDontSaveFlag() {
    return dontSaveFlag;
  }

  public void setDontSaveFlag(String dontSaveFlag) {
    this.dontSaveFlag = dontSaveFlag;
  }

  @Basic
  @Column(name = "audit_history", nullable = true, length = 8000)
  public String getAuditHistory() {
    return auditHistory;
  }

  public void setAuditHistory(String auditHistory) {
    this.auditHistory = auditHistory;
  }

  @Basic
  @Column(name = "originalunidkey", nullable = true, length = 256)
  public String getOriginalunidkey() {
    return originalunidkey;
  }

  public void setOriginalunidkey(String originalunidkey) {
    this.originalunidkey = originalunidkey;
  }

  @Basic
  @Column(name = "editor_name", nullable = true, length = 256)
  public String getEditorName() {
    return editorName;
  }

  public void setEditorName(String editorName) {
    this.editorName = editorName;
  }

  @Basic
  @Column(name = "edit_history", nullable = true, length = 8000)
  public String getEditHistory() {
    return editHistory;
  }

  public void setEditHistory(String editHistory) {
    this.editHistory = editHistory;
  }

  @Basic
  @Column(name = "dt_created", nullable = true)
  public LocalDateTime getDtCreated() {
    return dtCreated;
  }

  public void setDtCreated(LocalDateTime dtCreated) {
    this.dtCreated = dtCreated;
  }

  @Basic
  @Column(name = "creator_name", nullable = true, length = 256)
  public String getCreatorName() {
    return creatorName;
  }

  public void setCreatorName(String creatorName) {
    this.creatorName = creatorName;
  }

  @Basic
  @Column(name = "fn_loc", nullable = true, length = 256)
  public String getFnLoc() {
    return fnLoc;
  }

  public void setFnLoc(String fnLoc) {
    this.fnLoc = fnLoc;
  }

  @Basic
  @Column(name = "fnam_loc", nullable = true, length = 256)
  public String getFnamLoc() {
    return fnamLoc;
  }

  public void setFnamLoc(String fnamLoc) {
    this.fnamLoc = fnamLoc;
  }

  @Basic
  @Column(name = "rndmeditcomments", nullable = true, length = 8000)
  public String getRndmeditcomments() {
    return rndmeditcomments;
  }

  public void setRndmeditcomments(String rndmeditcomments) {
    this.rndmeditcomments = rndmeditcomments;
  }

  @Basic
  @Column(name = "untitled", nullable = true, length = 256)
  public String getUntitled() {
    return untitled;
  }

  public void setUntitled(String untitled) {
    this.untitled = untitled;
  }

  @Basic
  @Column(name = "fas_anl_req_vst_nbr_times_missed", nullable = true, precision = 0)
  public Long getFasAnlReqVstNbrTimesMissed() {
    return fasAnlReqVstNbrTimesMissed;
  }

  public void setFasAnlReqVstNbrTimesMissed(Long fasAnlReqVstNbrTimesMissed) {
    this.fasAnlReqVstNbrTimesMissed = fasAnlReqVstNbrTimesMissed;
  }

  @Basic
  @Column(name = "fas_anl_req_vst_times_overdue", nullable = true, precision = 0)
  public Long getFasAnlReqVstTimesOverdue() {
    return fasAnlReqVstTimesOverdue;
  }

  public void setFasAnlReqVstTimesOverdue(Long fasAnlReqVstTimesOverdue) {
    this.fasAnlReqVstTimesOverdue = fasAnlReqVstTimesOverdue;
  }

  @Basic
  @Column(name = "fas_anl_req_last_vst_missed_date", nullable = true)
  public LocalDateTime getFasAnlReqLastVstMissedDate() {
    return fasAnlReqLastVstMissedDate;
  }

  public void setFasAnlReqLastVstMissedDate(LocalDateTime fasAnlReqLastVstMissedDate) {
    this.fasAnlReqLastVstMissedDate = fasAnlReqLastVstMissedDate;
  }

  @Basic
  @Column(name = "fas_anl_req_vst_overdue_date", nullable = true)
  public LocalDateTime getFasAnlReqVstOverdueDate() {
    return fasAnlReqVstOverdueDate;
  }

  public void setFasAnlReqVstOverdueDate(LocalDateTime fasAnlReqVstOverdueDate) {
    this.fasAnlReqVstOverdueDate = fasAnlReqVstOverdueDate;
  }

  @Basic
  @Column(name = "fas_anl_req_vst_missed_date", nullable = true)
  public LocalDateTime getFasAnlReqVstMissedDate() {
    return fasAnlReqVstMissedDate;
  }

  public void setFasAnlReqVstMissedDate(LocalDateTime fasAnlReqVstMissedDate) {
    this.fasAnlReqVstMissedDate = fasAnlReqVstMissedDate;
  }

  @Basic
  @Column(name = "fas_anl_req_vst_overdue", nullable = true, length = 256)
  public String getFasAnlReqVstOverdue() {
    return fasAnlReqVstOverdue;
  }

  public void setFasAnlReqVstOverdue(String fasAnlReqVstOverdue) {
    this.fasAnlReqVstOverdue = fasAnlReqVstOverdue;
  }

  @Basic
  @Column(name = "fas_anl_req_vst_missed", nullable = true, length = 256)
  public String getFasAnlReqVstMissed() {
    return fasAnlReqVstMissed;
  }

  public void setFasAnlReqVstMissed(String fasAnlReqVstMissed) {
    this.fasAnlReqVstMissed = fasAnlReqVstMissed;
  }

  @Basic
  @Column(name = "fas_anl_req_vst_dueby_date", nullable = true)
  public LocalDateTime getFasAnlReqVstDuebyDate() {
    return fasAnlReqVstDuebyDate;
  }

  public void setFasAnlReqVstDuebyDate(LocalDateTime fasAnlReqVstDuebyDate) {
    this.fasAnlReqVstDuebyDate = fasAnlReqVstDuebyDate;
  }

  @Basic
  @Column(name = "fas_last_anl_req_visit_date", nullable = true)
  public LocalDateTime getFasLastAnlReqVisitDate() {
    return fasLastAnlReqVisitDate;
  }

  public void setFasLastAnlReqVisitDate(LocalDateTime fasLastAnlReqVisitDate) {
    this.fasLastAnlReqVisitDate = fasLastAnlReqVisitDate;
  }

  @Basic
  @Column(name = "fas_anl_req_vst_due", nullable = true, length = 256)
  public String getFasAnlReqVstDue() {
    return fasAnlReqVstDue;
  }

  public void setFasAnlReqVstDue(String fasAnlReqVstDue) {
    this.fasAnlReqVstDue = fasAnlReqVstDue;
  }

  @Basic
  @Column(name = "fas_req_vst_nbr_times_missed", nullable = true, precision = 0)
  public Long getFasReqVstNbrTimesMissed() {
    return fasReqVstNbrTimesMissed;
  }

  public void setFasReqVstNbrTimesMissed(Long fasReqVstNbrTimesMissed) {
    this.fasReqVstNbrTimesMissed = fasReqVstNbrTimesMissed;
  }

  @Basic
  @Column(name = "fas_req_vst_nbr_times_overdue", nullable = true, precision = 0)
  public Long getFasReqVstNbrTimesOverdue() {
    return fasReqVstNbrTimesOverdue;
  }

  public void setFasReqVstNbrTimesOverdue(Long fasReqVstNbrTimesOverdue) {
    this.fasReqVstNbrTimesOverdue = fasReqVstNbrTimesOverdue;
  }

  @Basic
  @Column(name = "fas_req_last_vst_missed_date", nullable = true)
  public LocalDateTime getFasReqLastVstMissedDate() {
    return fasReqLastVstMissedDate;
  }

  public void setFasReqLastVstMissedDate(LocalDateTime fasReqLastVstMissedDate) {
    this.fasReqLastVstMissedDate = fasReqLastVstMissedDate;
  }

  @Basic
  @Column(name = "fas_req_vst_overdue_date", nullable = true)
  public LocalDateTime getFasReqVstOverdueDate() {
    return fasReqVstOverdueDate;
  }

  public void setFasReqVstOverdueDate(LocalDateTime fasReqVstOverdueDate) {
    this.fasReqVstOverdueDate = fasReqVstOverdueDate;
  }

  @Basic
  @Column(name = "fas_req_vst_missed_date", nullable = true)
  public LocalDateTime getFasReqVstMissedDate() {
    return fasReqVstMissedDate;
  }

  public void setFasReqVstMissedDate(LocalDateTime fasReqVstMissedDate) {
    this.fasReqVstMissedDate = fasReqVstMissedDate;
  }

  @Basic
  @Column(name = "fas_req_vst_overdue", nullable = true, length = 256)
  public String getFasReqVstOverdue() {
    return fasReqVstOverdue;
  }

  public void setFasReqVstOverdue(String fasReqVstOverdue) {
    this.fasReqVstOverdue = fasReqVstOverdue;
  }

  @Basic
  @Column(name = "fas_req_vst_missed", nullable = true, length = 256)
  public String getFasReqVstMissed() {
    return fasReqVstMissed;
  }

  public void setFasReqVstMissed(String fasReqVstMissed) {
    this.fasReqVstMissed = fasReqVstMissed;
  }

  @Basic
  @Column(name = "fas_req_vst_dueby_date", nullable = true)
  public LocalDateTime getFasReqVstDuebyDate() {
    return fasReqVstDuebyDate;
  }

  public void setFasReqVstDuebyDate(LocalDateTime fasReqVstDuebyDate) {
    this.fasReqVstDuebyDate = fasReqVstDuebyDate;
  }

  @Basic
  @Column(name = "fas_last_req_visit_date", nullable = true)
  public LocalDateTime getFasLastReqVisitDate() {
    return fasLastReqVisitDate;
  }

  public void setFasLastReqVisitDate(LocalDateTime fasLastReqVisitDate) {
    this.fasLastReqVisitDate = fasLastReqVisitDate;
  }

  @Basic
  @Column(name = "fas_req_vst_due", nullable = true, length = 256)
  public String getFasReqVstDue() {
    return fasReqVstDue;
  }

  public void setFasReqVstDue(String fasReqVstDue) {
    this.fasReqVstDue = fasReqVstDue;
  }

  @Basic
  @Column(name = "d_8", nullable = true, length = 256)
  public String getD8() {
    return d8;
  }

  public void setD8(String d8) {
    this.d8 = d8;
  }

  @Basic
  @Column(name = "fac_lic_comments_2", nullable = true, length = 8000)
  public String getFacLicComments2() {
    return facLicComments2;
  }

  public void setFacLicComments2(String facLicComments2) {
    this.facLicComments2 = facLicComments2;
  }

  @Basic
  @Column(name = "fac_lic_comments", nullable = true, length = 8000)
  public String getFacLicComments() {
    return facLicComments;
  }

  public void setFacLicComments(String facLicComments) {
    this.facLicComments = facLicComments;
  }

  @Basic
  @Column(name = "d_7", nullable = true, length = 256)
  public String getD7() {
    return d7;
  }

  public void setD7(String d7) {
    this.d7 = d7;
  }

  @Basic
  @Column(name = "fac_gh_lic_eff_date", nullable = true)
  public LocalDateTime getFacGhLicEffDate() {
    return facGhLicEffDate;
  }

  public void setFacGhLicEffDate(LocalDateTime facGhLicEffDate) {
    this.facGhLicEffDate = facGhLicEffDate;
  }

  @Basic
  @Column(name = "fac_rfh_convert_date", nullable = true)
  public LocalDateTime getFacRfhConvertDate() {
    return facRfhConvertDate;
  }

  public void setFacRfhConvertDate(LocalDateTime facRfhConvertDate) {
    this.facRfhConvertDate = facRfhConvertDate;
  }

  @Basic
  @Column(name = "fac_plan_of_op_recv_date", nullable = true)
  public LocalDateTime getFacPlanOfOpRecvDate() {
    return facPlanOfOpRecvDate;
  }

  public void setFacPlanOfOpRecvDate(LocalDateTime facPlanOfOpRecvDate) {
    this.facPlanOfOpRecvDate = facPlanOfOpRecvDate;
  }

  @Basic
  @Column(name = "fac_plan_of_op_approval_date", nullable = true)
  public LocalDateTime getFacPlanOfOpApprovalDate() {
    return facPlanOfOpApprovalDate;
  }

  public void setFacPlanOfOpApprovalDate(LocalDateTime facPlanOfOpApprovalDate) {
    this.facPlanOfOpApprovalDate = facPlanOfOpApprovalDate;
  }

  @Basic
  @Column(name = "fac_mental_health_expire_date", nullable = true)
  public LocalDateTime getFacMentalHealthExpireDate() {
    return facMentalHealthExpireDate;
  }

  public void setFacMentalHealthExpireDate(LocalDateTime facMentalHealthExpireDate) {
    this.facMentalHealthExpireDate = facMentalHealthExpireDate;
  }

  @Basic
  @Column(name = "fac_mental_health_date", nullable = true)
  public LocalDateTime getFacMentalHealthDate() {
    return facMentalHealthDate;
  }

  public void setFacMentalHealthDate(LocalDateTime facMentalHealthDate) {
    this.facMentalHealthDate = facMentalHealthDate;
  }

  @Basic
  @Column(name = "fac_accreditation_expire_date", nullable = true)
  public LocalDateTime getFacAccreditationExpireDate() {
    return facAccreditationExpireDate;
  }

  public void setFacAccreditationExpireDate(LocalDateTime facAccreditationExpireDate) {
    this.facAccreditationExpireDate = facAccreditationExpireDate;
  }

  @Basic
  @Column(name = "fac_accreditation_date", nullable = true)
  public LocalDateTime getFacAccreditationDate() {
    return facAccreditationDate;
  }

  public void setFacAccreditationDate(LocalDateTime facAccreditationDate) {
    this.facAccreditationDate = facAccreditationDate;
  }

  @Basic
  @Column(name = "fas_acc_agency", nullable = true, length = 256)
  public String getFasAccAgency() {
    return fasAccAgency;
  }

  public void setFasAccAgency(String fasAccAgency) {
    this.fasAccAgency = fasAccAgency;
  }

  @Basic
  @Column(name = "fac_accreditation_code", nullable = true, precision = 0)
  public Long getFacAccreditationCode() {
    return facAccreditationCode;
  }

  public void setFacAccreditationCode(Long facAccreditationCode) {
    this.facAccreditationCode = facAccreditationCode;
  }

  @Basic
  @Column(name = "fac_strtp_app_denied_date", nullable = true)
  public LocalDateTime getFacStrtpAppDeniedDate() {
    return facStrtpAppDeniedDate;
  }

  public void setFacStrtpAppDeniedDate(LocalDateTime facStrtpAppDeniedDate) {
    this.facStrtpAppDeniedDate = facStrtpAppDeniedDate;
  }

  @Basic
  @Column(name = "fac_strtp_app_recv_date", nullable = true)
  public LocalDateTime getFacStrtpAppRecvDate() {
    return facStrtpAppRecvDate;
  }

  public void setFacStrtpAppRecvDate(LocalDateTime facStrtpAppRecvDate) {
    this.facStrtpAppRecvDate = facStrtpAppRecvDate;
  }

  @Basic
  @Column(name = "fn_accred", nullable = true, length = 256)
  public String getFnAccred() {
    return fnAccred;
  }

  public void setFnAccred(String fnAccred) {
    this.fnAccred = fnAccred;
  }

  @Basic
  @Column(name = "fnam_accred", nullable = true, length = 256)
  public String getFnamAccred() {
    return fnamAccred;
  }

  public void setFnamAccred(String fnamAccred) {
    this.fnamAccred = fnamAccred;
  }

  @Basic
  @Column(name = "fac_loc_indicator", nullable = true, precision = 0)
  public Long getFacLocIndicator() {
    return facLocIndicator;
  }

  public void setFacLocIndicator(Long facLocIndicator) {
    this.facLocIndicator = facLocIndicator;
  }

  @Basic
  @Column(name = "fac_change_loc_ind", nullable = true, precision = 0)
  public Long getFacChangeLocInd() {
    return facChangeLocInd;
  }

  public void setFacChangeLocInd(Long facChangeLocInd) {
    this.facChangeLocInd = facChangeLocInd;
  }

  @Basic
  @Column(name = "fac_do_nbr", nullable = true, precision = 0)
  public Long getFacDoNbr() {
    return facDoNbr;
  }

  public void setFacDoNbr(Long facDoNbr) {
    this.facDoNbr = facDoNbr;
  }

  @Basic
  @Column(name = "fac_email_address", nullable = true, length = 256)
  public String getFacEmailAddress() {
    return facEmailAddress;
  }

  public void setFacEmailAddress(String facEmailAddress) {
    this.facEmailAddress = facEmailAddress;
  }

  @Basic
  @Column(name = "fac_region_co", nullable = true, length = 256)
  public String getFacRegionCo() {
    return facRegionCo;
  }

  public void setFacRegionCo(String facRegionCo) {
    this.facRegionCo = facRegionCo;
  }

  @Basic
  @Column(name = "fac_region_do", nullable = true, length = 256)
  public String getFacRegionDo() {
    return facRegionDo;
  }

  public void setFacRegionDo(String facRegionDo) {
    this.facRegionDo = facRegionDo;
  }

  @Basic
  @Column(name = "fac_deficiency", nullable = true, length = 8000)
  public String getFacDeficiency() {
    return facDeficiency;
  }

  public void setFacDeficiency(String facDeficiency) {
    this.facDeficiency = facDeficiency;
  }

  @Basic
  @Column(name = "fac_complaint", nullable = true, length = 256)
  public String getFacComplaint() {
    return facComplaint;
  }

  public void setFacComplaint(String facComplaint) {
    this.facComplaint = facComplaint;
  }

  @Basic
  @Column(name = "fac_nbr_new", nullable = true, precision = 0)
  public Long getFacNbrNew() {
    return facNbrNew;
  }

  public void setFacNbrNew(Long facNbrNew) {
    this.facNbrNew = facNbrNew;
  }

  @Basic
  @Column(name = "fac_gh_indicator", nullable = true, length = 256)
  public String getFacGhIndicator() {
    return facGhIndicator;
  }

  public void setFacGhIndicator(String facGhIndicator) {
    this.facGhIndicator = facGhIndicator;
  }

  @Basic
  @Column(name = "fac_fcrb_program", nullable = true, length = 256)
  public String getFacFcrbProgram() {
    return facFcrbProgram;
  }

  public void setFacFcrbProgram(String facFcrbProgram) {
    this.facFcrbProgram = facFcrbProgram;
  }

  @Basic
  @Column(name = "fac_dual_number", nullable = true, length = 256)
  public String getFacDualNumber() {
    return facDualNumber;
  }

  public void setFacDualNumber(String facDualNumber) {
    this.facDualNumber = facDualNumber;
  }

  @Basic
  @Column(name = "fac_dual_id", nullable = true, length = 256)
  public String getFacDualId() {
    return facDualId;
  }

  public void setFacDualId(String facDualId) {
    this.facDualId = facDualId;
  }

  @Basic
  @Column(name = "fac_billing_date", nullable = true)
  public LocalDateTime getFacBillingDate() {
    return facBillingDate;
  }

  public void setFacBillingDate(LocalDateTime facBillingDate) {
    this.facBillingDate = facBillingDate;
  }

  @Basic
  @Column(name = "fac_primary_nbr", nullable = true, precision = 0)
  public Long getFacPrimaryNbr() {
    return facPrimaryNbr;
  }

  public void setFacPrimaryNbr(Long facPrimaryNbr) {
    this.facPrimaryNbr = facPrimaryNbr;
  }

  @Basic
  @Column(name = "fac_agg_status", nullable = true, length = 256)
  public String getFacAggStatus() {
    return facAggStatus;
  }

  public void setFacAggStatus(String facAggStatus) {
    this.facAggStatus = facAggStatus;
  }

  @Basic
  @Column(name = "fac_legal_action_indicator", nullable = true, length = 8000)
  public String getFacLegalActionIndicator() {
    return facLegalActionIndicator;
  }

  public void setFacLegalActionIndicator(String facLegalActionIndicator) {
    this.facLegalActionIndicator = facLegalActionIndicator;
  }

  @Basic
  @Column(name = "fac_action_code", nullable = true, length = 8000)
  public String getFacActionCode() {
    return facActionCode;
  }

  public void setFacActionCode(String facActionCode) {
    this.facActionCode = facActionCode;
  }

  @Basic
  @Column(name = "fac_region_nbr", nullable = true, precision = 0)
  public Long getFacRegionNbr() {
    return facRegionNbr;
  }

  public void setFacRegionNbr(Long facRegionNbr) {
    this.facRegionNbr = facRegionNbr;
  }

  @Basic
  @Column(name = "fac_cfrs_id_nbr", nullable = true, precision = 0)
  public Long getFacCfrsIdNbr() {
    return facCfrsIdNbr;
  }

  public void setFacCfrsIdNbr(Long facCfrsIdNbr) {
    this.facCfrsIdNbr = facCfrsIdNbr;
  }

  @Basic
  @Column(name = "fac_fm_region_nbr", nullable = true, precision = 0)
  public Long getFacFmRegionNbr() {
    return facFmRegionNbr;
  }

  public void setFacFmRegionNbr(Long facFmRegionNbr) {
    this.facFmRegionNbr = facFmRegionNbr;
  }

  @Basic
  @Column(name = "fac_co_nbr", nullable = true, precision = 0)
  public Long getFacCoNbr() {
    return facCoNbr;
  }

  public void setFacCoNbr(Long facCoNbr) {
    this.facCoNbr = facCoNbr;
  }

  @Basic
  @Column(name = "fac_client_served_appr_date", nullable = true)
  public LocalDateTime getFacClientServedApprDate() {
    return facClientServedApprDate;
  }

  public void setFacClientServedApprDate(LocalDateTime facClientServedApprDate) {
    this.facClientServedApprDate = facClientServedApprDate;
  }

  @Basic
  @Column(name = "fac_client_served", nullable = true, precision = 0)
  public Long getFacClientServed() {
    return facClientServed;
  }

  public void setFacClientServed(Long facClientServed) {
    this.facClientServed = facClientServed;
  }

  @Basic
  @Column(name = "fn_legal", nullable = true, length = 256)
  public String getFnLegal() {
    return fnLegal;
  }

  public void setFnLegal(String fnLegal) {
    this.fnLegal = fnLegal;
  }

  @Basic
  @Column(name = "fnam_legal", nullable = true, length = 256)
  public String getFnamLegal() {
    return fnamLegal;
  }

  public void setFnamLegal(String fnamLegal) {
    this.fnamLegal = fnamLegal;
  }

  @Basic
  @Column(name = "d_6", nullable = true, length = 256)
  public String getD6() {
    return d6;
  }

  public void setD6(String d6) {
    this.d6 = d6;
  }

  @Basic
  @Column(name = "fac_nonamb_65_plus_nbr", nullable = true, precision = 0)
  public Long getFacNonamb65PlusNbr() {
    return facNonamb65PlusNbr;
  }

  public void setFacNonamb65PlusNbr(Long facNonamb65PlusNbr) {
    this.facNonamb65PlusNbr = facNonamb65PlusNbr;
  }

  @Basic
  @Column(name = "fac_amb_65_plus_nbr", nullable = true, precision = 0)
  public Long getFacAmb65PlusNbr() {
    return facAmb65PlusNbr;
  }

  public void setFacAmb65PlusNbr(Long facAmb65PlusNbr) {
    this.facAmb65PlusNbr = facAmb65PlusNbr;
  }

  @Basic
  @Column(name = "fac_nonamb_18_64_nbr", nullable = true, precision = 0)
  public Long getFacNonamb1864Nbr() {
    return facNonamb1864Nbr;
  }

  public void setFacNonamb1864Nbr(Long facNonamb1864Nbr) {
    this.facNonamb1864Nbr = facNonamb1864Nbr;
  }

  @Basic
  @Column(name = "fac_amb_18_64_nbr", nullable = true, precision = 0)
  public Long getFacAmb1864Nbr() {
    return facAmb1864Nbr;
  }

  public void setFacAmb1864Nbr(Long facAmb1864Nbr) {
    this.facAmb1864Nbr = facAmb1864Nbr;
  }

  @Basic
  @Column(name = "fac_nonamb_1_17_nbr", nullable = true, precision = 0)
  public Long getFacNonamb117Nbr() {
    return facNonamb117Nbr;
  }

  public void setFacNonamb117Nbr(Long facNonamb117Nbr) {
    this.facNonamb117Nbr = facNonamb117Nbr;
  }

  @Basic
  @Column(name = "fac_amb_1_17_nbr", nullable = true, precision = 0)
  public Long getFacAmb117Nbr() {
    return facAmb117Nbr;
  }

  public void setFacAmb117Nbr(Long facAmb117Nbr) {
    this.facAmb117Nbr = facAmb117Nbr;
  }

  @Basic
  @Column(name = "fn_amb", nullable = true, length = 256)
  public String getFnAmb() {
    return fnAmb;
  }

  public void setFnAmb(String fnAmb) {
    this.fnAmb = fnAmb;
  }

  @Basic
  @Column(name = "fnam_amb", nullable = true, length = 256)
  public String getFnamAmb() {
    return fnamAmb;
  }

  public void setFnamAmb(String fnamAmb) {
    this.fnamAmb = fnamAmb;
  }

  @Basic
  @Column(name = "d_5", nullable = true, length = 256)
  public String getD5() {
    return d5;
  }

  public void setD5(String d5) {
    this.d5 = d5;
  }

  @Basic
  @Column(name = "fac_pre_lic_visit_date", nullable = true)
  public LocalDateTime getFacPreLicVisitDate() {
    return facPreLicVisitDate;
  }

  public void setFacPreLicVisitDate(LocalDateTime facPreLicVisitDate) {
    this.facPreLicVisitDate = facPreLicVisitDate;
  }

  @Basic
  @Column(name = "fac_annual_visit_year", nullable = true, precision = 0)
  public Long getFacAnnualVisitYear() {
    return facAnnualVisitYear;
  }

  public void setFacAnnualVisitYear(Long facAnnualVisitYear) {
    this.facAnnualVisitYear = facAnnualVisitYear;
  }

  @Basic
  @Column(name = "fac_last_defer_visit_date", nullable = true)
  public LocalDateTime getFacLastDeferVisitDate() {
    return facLastDeferVisitDate;
  }

  public void setFacLastDeferVisitDate(LocalDateTime facLastDeferVisitDate) {
    this.facLastDeferVisitDate = facLastDeferVisitDate;
  }

  @Basic
  @Column(name = "fac_last_defer_visit_reason", nullable = true, precision = 0)
  public Long getFacLastDeferVisitReason() {
    return facLastDeferVisitReason;
  }

  public void setFacLastDeferVisitReason(Long facLastDeferVisitReason) {
    this.facLastDeferVisitReason = facLastDeferVisitReason;
  }

  @Basic
  @Column(name = "fac_renewal_defer_date", nullable = true)
  public LocalDate getFacRenewalDeferDate() {
    return facRenewalDeferDate;
  }

  public void setFacRenewalDeferDate(LocalDate facRenewalDeferDate) {
    this.facRenewalDeferDate = facRenewalDeferDate;
  }

  @Basic
  @Column(name = "fac_post_lic_defer_date", nullable = true)
  public LocalDate getFacPostLicDeferDate() {
    return facPostLicDeferDate;
  }

  public void setFacPostLicDeferDate(LocalDate facPostLicDeferDate) {
    this.facPostLicDeferDate = facPostLicDeferDate;
  }

  @Basic
  @Column(name = "fac_renewal_visit_date", nullable = true)
  public LocalDate getFacRenewalVisitDate() {
    return facRenewalVisitDate;
  }

  public void setFacRenewalVisitDate(LocalDate facRenewalVisitDate) {
    this.facRenewalVisitDate = facRenewalVisitDate;
  }

  @Basic
  @Column(name = "fac_post_lic_visit_date", nullable = true)
  public LocalDate getFacPostLicVisitDate() {
    return facPostLicVisitDate;
  }

  public void setFacPostLicVisitDate(LocalDate facPostLicVisitDate) {
    this.facPostLicVisitDate = facPostLicVisitDate;
  }

  @Basic
  @Column(name = "fac_renewal_visit_appr", nullable = true, length = 256)
  public String getFacRenewalVisitAppr() {
    return facRenewalVisitAppr;
  }

  public void setFacRenewalVisitAppr(String facRenewalVisitAppr) {
    this.facRenewalVisitAppr = facRenewalVisitAppr;
  }

  @Basic
  @Column(name = "fac_post_lic_visit_appr", nullable = true, length = 256)
  public String getFacPostLicVisitAppr() {
    return facPostLicVisitAppr;
  }

  public void setFacPostLicVisitAppr(String facPostLicVisitAppr) {
    this.facPostLicVisitAppr = facPostLicVisitAppr;
  }

  @Basic
  @Column(name = "fac_annual_22_mo_defer_date", nullable = true)
  public LocalDate getFacAnnual22MoDeferDate() {
    return facAnnual22MoDeferDate;
  }

  public void setFacAnnual22MoDeferDate(LocalDate facAnnual22MoDeferDate) {
    this.facAnnual22MoDeferDate = facAnnual22MoDeferDate;
  }

  @Basic
  @Column(name = "fac_annual_10_mo_defer_date", nullable = true)
  public LocalDate getFacAnnual10MoDeferDate() {
    return facAnnual10MoDeferDate;
  }

  public void setFacAnnual10MoDeferDate(LocalDate facAnnual10MoDeferDate) {
    this.facAnnual10MoDeferDate = facAnnual10MoDeferDate;
  }

  @Basic
  @Column(name = "fac_annual_22_mo_visit_date", nullable = true)
  public LocalDate getFacAnnual22MoVisitDate() {
    return facAnnual22MoVisitDate;
  }

  public void setFacAnnual22MoVisitDate(LocalDate facAnnual22MoVisitDate) {
    this.facAnnual22MoVisitDate = facAnnual22MoVisitDate;
  }

  @Basic
  @Column(name = "fac_annual_10_mo_visit_date", nullable = true)
  public LocalDate getFacAnnual10MoVisitDate() {
    return facAnnual10MoVisitDate;
  }

  public void setFacAnnual10MoVisitDate(LocalDate facAnnual10MoVisitDate) {
    this.facAnnual10MoVisitDate = facAnnual10MoVisitDate;
  }

  @Basic
  @Column(name = "fac_annual_22_mo_visit_appr", nullable = true, length = 256)
  public String getFacAnnual22MoVisitAppr() {
    return facAnnual22MoVisitAppr;
  }

  public void setFacAnnual22MoVisitAppr(String facAnnual22MoVisitAppr) {
    this.facAnnual22MoVisitAppr = facAnnual22MoVisitAppr;
  }

  @Basic
  @Column(name = "fac_annual_10_mo_visit_appr", nullable = true, length = 256)
  public String getFacAnnual10MoVisitAppr() {
    return facAnnual10MoVisitAppr;
  }

  public void setFacAnnual10MoVisitAppr(String facAnnual10MoVisitAppr) {
    this.facAnnual10MoVisitAppr = facAnnual10MoVisitAppr;
  }

  @Basic
  @Column(name = "fac_last_visit_reason", nullable = true, precision = 0)
  public Long getFacLastVisitReason() {
    return facLastVisitReason;
  }

  public void setFacLastVisitReason(Long facLastVisitReason) {
    this.facLastVisitReason = facLastVisitReason;
  }

  @Basic
  @Column(name = "fac_last_visit_date", nullable = true)
  public LocalDateTime getFacLastVisitDate() {
    return facLastVisitDate;
  }

  public void setFacLastVisitDate(LocalDateTime facLastVisitDate) {
    this.facLastVisitDate = facLastVisitDate;
  }

  @Basic
  @Column(name = "fn_visit", nullable = true, length = 256)
  public String getFnVisit() {
    return fnVisit;
  }

  public void setFnVisit(String fnVisit) {
    this.fnVisit = fnVisit;
  }

  @Basic
  @Column(name = "fnam_visit", nullable = true, length = 256)
  public String getFnamVisit() {
    return fnamVisit;
  }

  public void setFnamVisit(String fnamVisit) {
    this.fnamVisit = fnamVisit;
  }

  @Basic
  @Column(name = "d_4", nullable = true, length = 256)
  public String getD4() {
    return d4;
  }

  public void setD4(String d4) {
    this.d4 = d4;
  }

  @Basic
  @Column(name = "fac_last_upd_date", nullable = true)
  public LocalDateTime getFacLastUpdDate() {
    return facLastUpdDate;
  }

  public void setFacLastUpdDate(LocalDateTime facLastUpdDate) {
    this.facLastUpdDate = facLastUpdDate;
  }

  @Basic
  @Column(name = "fac_inc_cap_eff_date", nullable = true)
  public LocalDateTime getFacIncCapEffDate() {
    return facIncCapEffDate;
  }

  public void setFacIncCapEffDate(LocalDateTime facIncCapEffDate) {
    this.facIncCapEffDate = facIncCapEffDate;
  }

  @Basic
  @Column(name = "fac_cap_inc_closed_date", nullable = true)
  public LocalDateTime getFacCapIncClosedDate() {
    return facCapIncClosedDate;
  }

  public void setFacCapIncClosedDate(LocalDateTime facCapIncClosedDate) {
    this.facCapIncClosedDate = facCapIncClosedDate;
  }

  @Basic
  @Column(name = "fac_cap_inc_rec_date", nullable = true)
  public LocalDateTime getFacCapIncRecDate() {
    return facCapIncRecDate;
  }

  public void setFacCapIncRecDate(LocalDateTime facCapIncRecDate) {
    this.facCapIncRecDate = facCapIncRecDate;
  }

  @Basic
  @Column(name = "fac_closed_process_date", nullable = true)
  public LocalDateTime getFacClosedProcessDate() {
    return facClosedProcessDate;
  }

  public void setFacClosedProcessDate(LocalDateTime facClosedProcessDate) {
    this.facClosedProcessDate = facClosedProcessDate;
  }

  @Basic
  @Column(name = "fac_unlic_orig_input_date", nullable = true)
  public LocalDateTime getFacUnlicOrigInputDate() {
    return facUnlicOrigInputDate;
  }

  public void setFacUnlicOrigInputDate(LocalDateTime facUnlicOrigInputDate) {
    this.facUnlicOrigInputDate = facUnlicOrigInputDate;
  }

  @Basic
  @Column(name = "fac_last_change_date", nullable = true)
  public LocalDateTime getFacLastChangeDate() {
    return facLastChangeDate;
  }

  public void setFacLastChangeDate(LocalDateTime facLastChangeDate) {
    this.facLastChangeDate = facLastChangeDate;
  }

  @Basic
  @Column(name = "fac_lic_first_date", nullable = true)
  public LocalDateTime getFacLicFirstDate() {
    return facLicFirstDate;
  }

  public void setFacLicFirstDate(LocalDateTime facLicFirstDate) {
    this.facLicFirstDate = facLicFirstDate;
  }

  @Basic
  @Column(name = "fac_lic_expir_date", nullable = true)
  public LocalDateTime getFacLicExpirDate() {
    return facLicExpirDate;
  }

  public void setFacLicExpirDate(LocalDateTime facLicExpirDate) {
    this.facLicExpirDate = facLicExpirDate;
  }

  @Basic
  @Column(name = "fac_lic_eff_date", nullable = true)
  public LocalDateTime getFacLicEffDate() {
    return facLicEffDate;
  }

  public void setFacLicEffDate(LocalDateTime facLicEffDate) {
    this.facLicEffDate = facLicEffDate;
  }

  @Basic
  @Column(name = "fac_last_fireclear_date", nullable = true)
  public LocalDateTime getFacLastFireclearDate() {
    return facLastFireclearDate;
  }

  public void setFacLastFireclearDate(LocalDateTime facLastFireclearDate) {
    this.facLastFireclearDate = facLastFireclearDate;
  }

  @Basic
  @Column(name = "fac_licensee_type", nullable = true, length = 256)
  public String getFacLicenseeType() {
    return facLicenseeType;
  }

  public void setFacLicenseeType(String facLicenseeType) {
    this.facLicenseeType = facLicenseeType;
  }

  @Basic
  @Column(name = "fn_licensing", nullable = true, length = 256)
  public String getFnLicensing() {
    return fnLicensing;
  }

  public void setFnLicensing(String fnLicensing) {
    this.fnLicensing = fnLicensing;
  }

  @Basic
  @Column(name = "fnam_licensing", nullable = true, length = 256)
  public String getFnamLicensing() {
    return fnamLicensing;
  }

  public void setFnamLicensing(String fnamLicensing) {
    this.fnamLicensing = fnamLicensing;
  }

  @Basic
  @Column(name = "d_3", nullable = true, length = 256)
  public String getD3() {
    return d3;
  }

  public void setD3(String d3) {
    this.d3 = d3;
  }

  @Basic
  @Column(name = "fac_lic_mail_zip_code", nullable = true, length = 256)
  public String getFacLicMailZipCode() {
    return facLicMailZipCode;
  }

  public void setFacLicMailZipCode(String facLicMailZipCode) {
    this.facLicMailZipCode = facLicMailZipCode;
  }

  @Basic
  @Column(name = "fac_mail_zip_code", nullable = true, length = 256)
  public String getFacMailZipCode() {
    return facMailZipCode;
  }

  public void setFacMailZipCode(String facMailZipCode) {
    this.facMailZipCode = facMailZipCode;
  }

  @Basic
  @Column(name = "fac_lic_mail_state", nullable = true, length = 256)
  public String getFacLicMailState() {
    return facLicMailState;
  }

  public void setFacLicMailState(String facLicMailState) {
    this.facLicMailState = facLicMailState;
  }

  @Basic
  @Column(name = "fac_mail_state", nullable = true, length = 256)
  public String getFacMailState() {
    return facMailState;
  }

  public void setFacMailState(String facMailState) {
    this.facMailState = facMailState;
  }

  @Basic
  @Column(name = "fac_lic_mail_city", nullable = true, length = 256)
  public String getFacLicMailCity() {
    return facLicMailCity;
  }

  public void setFacLicMailCity(String facLicMailCity) {
    this.facLicMailCity = facLicMailCity;
  }

  @Basic
  @Column(name = "fac_mail_city", nullable = true, length = 256)
  public String getFacMailCity() {
    return facMailCity;
  }

  public void setFacMailCity(String facMailCity) {
    this.facMailCity = facMailCity;
  }

  @Basic
  @Column(name = "fac_lic_mail_street_addr", nullable = true, length = 256)
  public String getFacLicMailStreetAddr() {
    return facLicMailStreetAddr;
  }

  public void setFacLicMailStreetAddr(String facLicMailStreetAddr) {
    this.facLicMailStreetAddr = facLicMailStreetAddr;
  }

  @Basic
  @Column(name = "fac_mail_street_addr", nullable = true, length = 256)
  public String getFacMailStreetAddr() {
    return facMailStreetAddr;
  }

  public void setFacMailStreetAddr(String facMailStreetAddr) {
    this.facMailStreetAddr = facMailStreetAddr;
  }

  @Basic
  @Column(name = "fn_mailing", nullable = true, length = 256)
  public String getFnMailing() {
    return fnMailing;
  }

  public void setFnMailing(String fnMailing) {
    this.fnMailing = fnMailing;
  }

  @Basic
  @Column(name = "fnam_mailing", nullable = true, length = 256)
  public String getFnamMailing() {
    return fnamMailing;
  }

  public void setFnamMailing(String fnamMailing) {
    this.fnamMailing = fnamMailing;
  }

  @Basic
  @Column(name = "d_2", nullable = true, length = 256)
  public String getD2() {
    return d2;
  }

  public void setD2(String d2) {
    this.d2 = d2;
  }

  @Basic
  @Column(name = "facilitycomments", nullable = true, length = 8000)
  public String getFacilitycomments() {
    return facilitycomments;
  }

  public void setFacilitycomments(String facilitycomments) {
    this.facilitycomments = facilitycomments;
  }

  @Basic
  @Column(name = "fac_inactive_notice_date", nullable = true)
  public LocalDateTime getFacInactiveNoticeDate() {
    return facInactiveNoticeDate;
  }

  public void setFacInactiveNoticeDate(LocalDateTime facInactiveNoticeDate) {
    this.facInactiveNoticeDate = facInactiveNoticeDate;
  }

  @Basic
  @Column(name = "fac_inactive_start_date", nullable = true)
  public LocalDateTime getFacInactiveStartDate() {
    return facInactiveStartDate;
  }

  public void setFacInactiveStartDate(LocalDateTime facInactiveStartDate) {
    this.facInactiveStartDate = facInactiveStartDate;
  }

  @Basic
  @Column(name = "fas_lpa_code", nullable = true, length = 64)
  public String getFasLpaCode() {
    return fasLpaCode;
  }

  public void setFasLpaCode(String fasLpaCode) {
    this.fasLpaCode = fasLpaCode;
  }

  @Basic
  @Column(name = "fac_required_visit", nullable = true, length = 256)
  public String getFacRequiredVisit() {
    return facRequiredVisit;
  }

  public void setFacRequiredVisit(String facRequiredVisit) {
    this.facRequiredVisit = facRequiredVisit;
  }

  @Basic
  @Column(name = "fac_closed_date", nullable = true)
  public LocalDateTime getFacClosedDate() {
    return facClosedDate;
  }

  public void setFacClosedDate(LocalDateTime facClosedDate) {
    this.facClosedDate = facClosedDate;
  }

  @Basic
  @Column(name = "fac_orig_appl_rec_date", nullable = true)
  public LocalDateTime getFacOrigApplRecDate() {
    return facOrigApplRecDate;
  }

  public void setFacOrigApplRecDate(LocalDateTime facOrigApplRecDate) {
    this.facOrigApplRecDate = facOrigApplRecDate;
  }

  @Basic
  @Column(name = "lpa_code", nullable = true, length = 64)
  public String getLpaCode() {
    return lpaCode;
  }

  public void setLpaCode(String lpaCode) {
    this.lpaCode = lpaCode;
  }

  @Basic
  @Column(name = "facility_telephone", nullable = true, length = 256)
  public String getFacilityTelephone() {
    return facilityTelephone;
  }

  public void setFacilityTelephone(String facilityTelephone) {
    this.facilityTelephone = facilityTelephone;
  }

  @Basic
  @Column(name = "facility_anniv_date", nullable = true)
  public LocalDateTime getFacilityAnnivDate() {
    return facilityAnnivDate;
  }

  public void setFacilityAnnivDate(LocalDateTime facilityAnnivDate) {
    this.facilityAnnivDate = facilityAnnivDate;
  }

  @Basic
  @Column(name = "licensee_name", nullable = true, length = 256)
  public String getLicenseeName() {
    return licenseeName;
  }

  public void setLicenseeName(String licenseeName) {
    this.licenseeName = licenseeName;
  }

  @Basic
  @Column(name = "fac_county_name", nullable = true, length = 256)
  public String getFacCountyName() {
    return facCountyName;
  }

  public void setFacCountyName(String facCountyName) {
    this.facCountyName = facCountyName;
  }

  @Basic
  @Column(name = "facility_admin", nullable = true, length = 256)
  public String getFacilityAdmin() {
    return facilityAdmin;
  }

  public void setFacilityAdmin(String facilityAdmin) {
    this.facilityAdmin = facilityAdmin;
  }

  @Basic
  @Column(name = "facility_zip", nullable = true, length = 256)
  public String getFacilityZip() {
    return facilityZip;
  }

  public void setFacilityZip(String facilityZip) {
    this.facilityZip = facilityZip;
  }

  @Basic
  @Column(name = "facility_status_text", nullable = true, length = 256)
  public String getFacilityStatusText() {
    return facilityStatusText;
  }

  public void setFacilityStatusText(String facilityStatusText) {
    this.facilityStatusText = facilityStatusText;
  }

  @Basic
  @Column(name = "facility_state", nullable = true, length = 256)
  public String getFacilityState() {
    return facilityState;
  }

  public void setFacilityState(String facilityState) {
    this.facilityState = facilityState;
  }

  @Basic
  @Column(name = "facility_status", nullable = true, precision = 0)
  public Long getFacilityStatus() {
    return facilityStatus;
  }

  public void setFacilityStatus(Long facilityStatus) {
    this.facilityStatus = facilityStatus;
  }

  @Basic
  @Column(name = "facility_city", nullable = true, length = 256)
  public String getFacilityCity() {
    return facilityCity;
  }

  public void setFacilityCity(String facilityCity) {
    this.facilityCity = facilityCity;
  }

  @Basic
  @Column(name = "facility_capacity", nullable = true, precision = 0)
  public Long getFacilityCapacity() {
    return facilityCapacity;
  }

  public void setFacilityCapacity(Long facilityCapacity) {
    this.facilityCapacity = facilityCapacity;
  }

  @Basic
  @Column(name = "facility_address", nullable = true, length = 256)
  public String getFacilityAddress() {
    return facilityAddress;
  }

  public void setFacilityAddress(String facilityAddress) {
    this.facilityAddress = facilityAddress;
  }

  @Basic
  @Column(name = "facility_type_text", nullable = true, length = 256)
  public String getFacilityTypeText() {
    return facilityTypeText;
  }

  public void setFacilityTypeText(String facilityTypeText) {
    this.facilityTypeText = facilityTypeText;
  }

  @Basic
  @Column(name = "facility_name", nullable = true, length = 256)
  public String getFacilityName() {
    return facilityName;
  }

  public void setFacilityName(String facilityName) {
    this.facilityName = facilityName;
  }

  @Basic
  @Column(name = "facility_type", nullable = true, length = 256)
  public String getFacilityType() {
    return facilityType;
  }

  public void setFacilityType(String facilityType) {
    this.facilityType = facilityType;
  }

  @Id
  @Column(name = "facility_number")
  public Long getFacilityNumber() {
    return facilityNumber;
  }

  public void setFacilityNumber(Long facilityNumber) {
    this.facilityNumber = facilityNumber;
  }

  @Basic
  @Column(name = "d_1", nullable = true, length = 256)
  public String getD1() {
    return d1;
  }

  public void setD1(String d1) {
    this.d1 = d1;
  }

  @Basic
  @Column(name = "fac_old_ffa_nbr", nullable = true, precision = 0)
  public Long getFacOldFfaNbr() {
    return facOldFfaNbr;
  }

  public void setFacOldFfaNbr(Long facOldFfaNbr) {
    this.facOldFfaNbr = facOldFfaNbr;
  }

  @Basic
  @Column(name = "fac_pin", nullable = true, length = 256)
  public String getFacPin() {
    return facPin;
  }

  public void setFacPin(String facPin) {
    this.facPin = facPin;
  }

  @Basic
  @Column(name = "archive_flag", nullable = true, length = 256)
  public String getArchiveFlag() {
    return archiveFlag;
  }

  public void setArchiveFlag(String archiveFlag) {
    this.archiveFlag = archiveFlag;
  }

  @Basic
  @Column(name = "fac_sfm_id_nbr", nullable = true, precision = 0)
  public Long getFacSfmIdNbr() {
    return facSfmIdNbr;
  }

  public void setFacSfmIdNbr(Long facSfmIdNbr) {
    this.facSfmIdNbr = facSfmIdNbr;
  }

  @Basic
  @Column(name = "fac_placement_date", nullable = true)
  public LocalDateTime getFacPlacementDate() {
    return facPlacementDate;
  }

  public void setFacPlacementDate(LocalDateTime facPlacementDate) {
    this.facPlacementDate = facPlacementDate;
  }

  @Basic
  @Column(name = "fac_placement_ready", nullable = true, length = 256)
  public String getFacPlacementReady() {
    return facPlacementReady;
  }

  public void setFacPlacementReady(String facPlacementReady) {
    this.facPlacementReady = facPlacementReady;
  }

  @Basic
  @Column(name = "fac_mid_yr_visit_date", nullable = true)
  public LocalDateTime getFacMidYrVisitDate() {
    return facMidYrVisitDate;
  }

  public void setFacMidYrVisitDate(LocalDateTime facMidYrVisitDate) {
    this.facMidYrVisitDate = facMidYrVisitDate;
  }

  @Basic
  @Column(name = "fac_mid_yr_visit_appr", nullable = true, length = 256)
  public String getFacMidYrVisitAppr() {
    return facMidYrVisitAppr;
  }

  public void setFacMidYrVisitAppr(String facMidYrVisitAppr) {
    this.facMidYrVisitAppr = facMidYrVisitAppr;
  }

  @Basic
  @Column(name = "fac_mid_yr_defer_date", nullable = true)
  public LocalDateTime getFacMidYrDeferDate() {
    return facMidYrDeferDate;
  }

  public void setFacMidYrDeferDate(LocalDateTime facMidYrDeferDate) {
    this.facMidYrDeferDate = facMidYrDeferDate;
  }

  @Basic
  @Column(name = "fac_street_search", nullable = true, length = 256)
  public String getFacStreetSearch() {
    return facStreetSearch;
  }

  public void setFacStreetSearch(String facStreetSearch) {
    this.facStreetSearch = facStreetSearch;
  }

  @Basic
  @Column(name = "button_selected", nullable = true, length = 256)
  public String getButtonSelected() {
    return buttonSelected;
  }

  public void setButtonSelected(String buttonSelected) {
    this.buttonSelected = buttonSelected;
  }

  @Basic
  @Column(name = "sql_command", nullable = true, length = 256)
  public String getSqlCommand() {
    return sqlCommand;
  }

  public void setSqlCommand(String sqlCommand) {
    this.sqlCommand = sqlCommand;
  }

  @Basic
  @Column(name = "data_source", nullable = true, length = 256)
  public String getDataSource() {
    return dataSource;
  }

  public void setDataSource(String dataSource) {
    this.dataSource = dataSource;
  }

  @Basic
  @Column(name = "ext_error_message", nullable = true, length = 256)
  public String getExtErrorMessage() {
    return extErrorMessage;
  }

  public void setExtErrorMessage(String extErrorMessage) {
    this.extErrorMessage = extErrorMessage;
  }

  @Basic
  @Column(name = "error_message", nullable = true, length = 256)
  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @Basic
  @Column(name = "connected", nullable = true, length = 256)
  public String getConnected() {
    return connected;
  }

  public void setConnected(String connected) {
    this.connected = connected;
  }

  @Basic
  @Column(name = "form_status", nullable = true, length = 256)
  public String getFormStatus() {
    return formStatus;
  }

  public void setFormStatus(String formStatus) {
    this.formStatus = formStatus;
  }

  @Basic
  @Column(name = "do_number", nullable = true, length = 256)
  public String getDoNumber() {
    return doNumber;
  }

  public void setDoNumber(String doNumber) {
    this.doNumber = doNumber;
  }

  @Basic
  @Column(name = "random_change_reason", nullable = true, length = 256)
  public String getRandomChangeReason() {
    return randomChangeReason;
  }

  public void setRandomChangeReason(String randomChangeReason) {
    this.randomChangeReason = randomChangeReason;
  }

  @Basic
  @Column(name = "facility_number_text", nullable = true, length = 256)
  public String getFacilityNumberText() {
    return facilityNumberText;
  }

  public void setFacilityNumberText(String facilityNumberText) {
    this.facilityNumberText = facilityNumberText;
  }

  @Basic
  @Column(name = "delete_flag", nullable = true, length = 256)
  public String getDeleteFlag() {
    return deleteFlag;
  }

  public void setDeleteFlag(String deleteFlag) {
    this.deleteFlag = deleteFlag;
  }

  @Basic
  @Column(name = "editors", nullable = true, length = 8000)
  public String getEditors() {
    return editors;
  }

  public void setEditors(String editors) {
    this.editors = editors;
  }

  @Basic
  @Column(name = "dt_lastmod", nullable = true)
  public LocalDateTime getDtModified() {
    return dtModified;
  }

  public void setDtModified(LocalDateTime dtModified) {
    this.dtModified = dtModified;
  }

  private LisTableFile facilityLastVisitReason;

  private LisTableFile facilityLastDeferredVisitReason;


  @Transient
  public LisTableFile getFacilityLastVisitReason() {
    return facilityLastVisitReason;
  }

  public void setFacilityLastVisitReason(LisTableFile facilityLastVisitReason) {
    this.facilityLastVisitReason = facilityLastVisitReason;
  }

  @Transient
  public LisTableFile getFacilityLastDeferredVisitReason() {
    return facilityLastDeferredVisitReason;
  }

  public void setFacilityLastDeferredVisitReason(LisTableFile facilityLastDeferredVisitReason) {
    this.facilityLastDeferredVisitReason = facilityLastDeferredVisitReason;
  }

  @Transient
  @Override
  public Serializable getPrimaryKey() {
    return getFacilityNumber();
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
