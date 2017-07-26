package gov.ca.cwds.cals.persistence.model.fas;

import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.data.persistence.PersistentObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
@Entity
@Table(name = "facility_info_lis")
@SuppressWarnings("squid:S3437") //LocalDate is serializable
public class FacilityInfoLis implements PersistentObject {
  private static final long serialVersionUID = -2566583736496919788L;

  private String facLicComments2;
  private String facLicComments;
  private BigInteger facDoNbr;
  private String facEmailAddress;
  private String facRegionCo;
  private String facRegionDo;
  private String facDeficiency;
  private String facComplaint;
  private BigInteger facNbrNew;
  private String facGhIndicator;
  private String facFcrbProgram;
  private String facDualNumber;
  private String facDualId;
  private LocalDateTime facBillingDate;
  private BigInteger facPrimaryNbr;
  private String facAggStatus;
  private String facLegalActionIndicator;
  private String facActionCode;
  private BigInteger facRegionNbr;
  private BigInteger facCfrsIdNbr;
  private BigInteger facFmRegionNbr;
  private BigInteger facCoNbr;
  private LocalDateTime facClientServedApprDate;
  private BigInteger facClientServed;
  private String fnLegal;
  private BigInteger facNonamb65PlusNbr;
  private BigInteger facAmb65PlusNbr;
  private BigInteger facNonamb1864Nbr;
  private BigInteger facAmb1864Nbr;
  private BigInteger facNonamb117Nbr;
  private BigInteger facAmb117Nbr;
  private LocalDateTime facPreLicVisitDate;
  private BigInteger facAnnualVisitYear;
  private LocalDateTime facLastDeferVisitDate;
  private BigInteger facLastDeferVisitReason;
  private LocalDateTime facRenewalDeferDate;
  private LocalDateTime facPostLicDeferDate;
  private LocalDateTime facRenewalVisitDate;
  private LocalDateTime facPostLicVisitDate;
  private String facRenewalVisitAppr;
  private String facPostLicVisitAppr;
  private LocalDateTime facAnnual22MoDeferDate;
  private LocalDateTime facAnnual10MoDeferDate;
  private LocalDateTime facAnnual22MoVisitDate;
  private LocalDateTime facAnnual10MoVisitDate;
  private String facAnnual22MoVisitAppr;
  private String facAnnual10MoVisitAppr;
  private BigInteger facLastVisitReason;
  private LocalDateTime facLastVisitDate;
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
  private String facLicMailZipCode;
  private String facMailZipCode;
  private String facLicMailState;
  private String facMailState;
  private String facLicMailCity;
  private String facMailCity;
  private String facLicMailStreetAddr;
  private String facMailStreetAddr;
  private String facRequiredVisit;
  private LocalDateTime facClosedDate;
  private LocalDateTime facOrigApplRecDate;
  private String facDoEvalCode;
  private String facilityTelephone;
  private String facResZipCode;
  private String facLicenseeName;
  private String facResState;
  private String facilityAdmin;
  private String facResCity;
  private BigInteger facStatus;
  private String facResStreetAddr;
  private BigInteger facCapacity;
  private String facName;
  private BigInteger facType;
  private BigInteger facNbr;
  private String noexport;
  private LocalDateTime dtCreated;
  private LocalDateTime dtModified;

  @Basic
  @Column(name = "fac_lic_comments_2")
  public String getFacLicComments2() {
    return facLicComments2;
  }

  public void setFacLicComments2(String facLicComments2) {
    this.facLicComments2 = facLicComments2;
  }

  @Basic
  @Column(name = "fac_lic_comments")
  public String getFacLicComments() {
    return facLicComments;
  }

  public void setFacLicComments(String facLicComments) {
    this.facLicComments = facLicComments;
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
  @Column(name = "fac_email_address")
  public String getFacEmailAddress() {
    return facEmailAddress;
  }

  public void setFacEmailAddress(String facEmailAddress) {
    this.facEmailAddress = facEmailAddress;
  }

  @Basic
  @Column(name = "fac_region_co")
  public String getFacRegionCo() {
    return facRegionCo;
  }

  public void setFacRegionCo(String facRegionCo) {
    this.facRegionCo = facRegionCo;
  }

  @Basic
  @Column(name = "fac_region_do")
  public String getFacRegionDo() {
    return facRegionDo;
  }

  public void setFacRegionDo(String facRegionDo) {
    this.facRegionDo = facRegionDo;
  }

  @Basic
  @Column(name = "fac_deficiency")
  public String getFacDeficiency() {
    return facDeficiency;
  }

  public void setFacDeficiency(String facDeficiency) {
    this.facDeficiency = facDeficiency;
  }

  @Basic
  @Column(name = "fac_complaint")
  public String getFacComplaint() {
    return facComplaint;
  }

  public void setFacComplaint(String facComplaint) {
    this.facComplaint = facComplaint;
  }

  @Basic
  @Column(name = "fac_nbr_new")
  public BigInteger getFacNbrNew() {
    return facNbrNew;
  }

  public void setFacNbrNew(BigInteger facNbrNew) {
    this.facNbrNew = facNbrNew;
  }

  @Basic
  @Column(name = "fac_gh_indicator")
  public String getFacGhIndicator() {
    return facGhIndicator;
  }

  public void setFacGhIndicator(String facGhIndicator) {
    this.facGhIndicator = facGhIndicator;
  }

  @Basic
  @Column(name = "fac_fcrb_program")
  public String getFacFcrbProgram() {
    return facFcrbProgram;
  }

  public void setFacFcrbProgram(String facFcrbProgram) {
    this.facFcrbProgram = facFcrbProgram;
  }

  @Basic
  @Column(name = "fac_dual_number")
  public String getFacDualNumber() {
    return facDualNumber;
  }

  public void setFacDualNumber(String facDualNumber) {
    this.facDualNumber = facDualNumber;
  }

  @Basic
  @Column(name = "fac_dual_id")
  public String getFacDualId() {
    return facDualId;
  }

  public void setFacDualId(String facDualId) {
    this.facDualId = facDualId;
  }

  @Basic
  @Column(name = "fac_billing_date")
  public LocalDateTime getFacBillingDate() {
    return facBillingDate;
  }

  public void setFacBillingDate(LocalDateTime facBillingDate) {
    this.facBillingDate = facBillingDate;
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
  @Column(name = "fac_agg_status")
  public String getFacAggStatus() {
    return facAggStatus;
  }

  public void setFacAggStatus(String facAggStatus) {
    this.facAggStatus = facAggStatus;
  }

  @Basic
  @Column(name = "fac_legal_action_indicator")
  public String getFacLegalActionIndicator() {
    return facLegalActionIndicator;
  }

  public void setFacLegalActionIndicator(String facLegalActionIndicator) {
    this.facLegalActionIndicator = facLegalActionIndicator;
  }

  @Basic
  @Column(name = "fac_action_code")
  public String getFacActionCode() {
    return facActionCode;
  }

  public void setFacActionCode(String facActionCode) {
    this.facActionCode = facActionCode;
  }

  @Basic
  @Column(name = "fac_region_nbr")
  public BigInteger getFacRegionNbr() {
    return facRegionNbr;
  }

  public void setFacRegionNbr(BigInteger facRegionNbr) {
    this.facRegionNbr = facRegionNbr;
  }

  @Basic
  @Column(name = "fac_cfrs_id_nbr")
  public BigInteger getFacCfrsIdNbr() {
    return facCfrsIdNbr;
  }

  public void setFacCfrsIdNbr(BigInteger facCfrsIdNbr) {
    this.facCfrsIdNbr = facCfrsIdNbr;
  }

  @Basic
  @Column(name = "fac_fm_region_nbr")
  public BigInteger getFacFmRegionNbr() {
    return facFmRegionNbr;
  }

  public void setFacFmRegionNbr(BigInteger facFmRegionNbr) {
    this.facFmRegionNbr = facFmRegionNbr;
  }

  @Basic
  @Column(name = "fac_co_nbr")
  public BigInteger getFacCoNbr() {
    return facCoNbr;
  }

  public void setFacCoNbr(BigInteger facCoNbr) {
    this.facCoNbr = facCoNbr;
  }

  @Basic
  @Column(name = "fac_client_served_appr_date")
  public LocalDateTime getFacClientServedApprDate() {
    return facClientServedApprDate;
  }

  public void setFacClientServedApprDate(LocalDateTime facClientServedApprDate) {
    this.facClientServedApprDate = facClientServedApprDate;
  }

  @Basic
  @Column(name = "fac_client_served")
  public BigInteger getFacClientServed() {
    return facClientServed;
  }

  public void setFacClientServed(BigInteger facClientServed) {
    this.facClientServed = facClientServed;
  }

  @Basic
  @Column(name = "fn_legal")
  public String getFnLegal() {
    return fnLegal;
  }

  public void setFnLegal(String fnLegal) {
    this.fnLegal = fnLegal;
  }

  @Basic
  @Column(name = "fac_nonamb_65_plus_nbr")
  public BigInteger getFacNonamb65PlusNbr() {
    return facNonamb65PlusNbr;
  }

  public void setFacNonamb65PlusNbr(BigInteger facNonamb65PlusNbr) {
    this.facNonamb65PlusNbr = facNonamb65PlusNbr;
  }

  @Basic
  @Column(name = "fac_amb_65_plus_nbr")
  public BigInteger getFacAmb65PlusNbr() {
    return facAmb65PlusNbr;
  }

  public void setFacAmb65PlusNbr(BigInteger facAmb65PlusNbr) {
    this.facAmb65PlusNbr = facAmb65PlusNbr;
  }

  @Basic
  @Column(name = "fac_nonamb_18_64_nbr")
  public BigInteger getFacNonamb1864Nbr() {
    return facNonamb1864Nbr;
  }

  public void setFacNonamb1864Nbr(BigInteger facNonamb1864Nbr) {
    this.facNonamb1864Nbr = facNonamb1864Nbr;
  }

  @Basic
  @Column(name = "fac_amb_18_64_nbr")
  public BigInteger getFacAmb1864Nbr() {
    return facAmb1864Nbr;
  }

  public void setFacAmb1864Nbr(BigInteger facAmb1864Nbr) {
    this.facAmb1864Nbr = facAmb1864Nbr;
  }

  @Basic
  @Column(name = "fac_nonamb_1_17_nbr")
  public BigInteger getFacNonamb117Nbr() {
    return facNonamb117Nbr;
  }

  public void setFacNonamb117Nbr(BigInteger facNonamb117Nbr) {
    this.facNonamb117Nbr = facNonamb117Nbr;
  }

  @Basic
  @Column(name = "fac_amb_1_17_nbr")
  public BigInteger getFacAmb117Nbr() {
    return facAmb117Nbr;
  }

  public void setFacAmb117Nbr(BigInteger facAmb117Nbr) {
    this.facAmb117Nbr = facAmb117Nbr;
  }

  @Basic
  @Column(name = "fac_pre_lic_visit_date")
  public LocalDateTime getFacPreLicVisitDate() {
    return facPreLicVisitDate;
  }

  public void setFacPreLicVisitDate(LocalDateTime facPreLicVisitDate) {
    this.facPreLicVisitDate = facPreLicVisitDate;
  }

  @Basic
  @Column(name = "fac_annual_visit_year")
  public BigInteger getFacAnnualVisitYear() {
    return facAnnualVisitYear;
  }

  public void setFacAnnualVisitYear(BigInteger facAnnualVisitYear) {
    this.facAnnualVisitYear = facAnnualVisitYear;
  }

  @Basic
  @Column(name = "fac_last_defer_visit_date")
  public LocalDateTime getFacLastDeferVisitDate() {
    return facLastDeferVisitDate;
  }

  public void setFacLastDeferVisitDate(LocalDateTime facLastDeferVisitDate) {
    this.facLastDeferVisitDate = facLastDeferVisitDate;
  }

  @Basic
  @Column(name = "fac_last_defer_visit_reason")
  public BigInteger getFacLastDeferVisitReason() {
    return facLastDeferVisitReason;
  }

  public void setFacLastDeferVisitReason(BigInteger facLastDeferVisitReason) {
    this.facLastDeferVisitReason = facLastDeferVisitReason;
  }

  @Basic
  @Column(name = "fac_renewal_defer_date")
  public LocalDateTime getFacRenewalDeferDate() {
    return facRenewalDeferDate;
  }

  public void setFacRenewalDeferDate(LocalDateTime facRenewalDeferDate) {
    this.facRenewalDeferDate = facRenewalDeferDate;
  }

  @Basic
  @Column(name = "fac_post_lic_defer_date")
  public LocalDateTime getFacPostLicDeferDate() {
    return facPostLicDeferDate;
  }

  public void setFacPostLicDeferDate(LocalDateTime facPostLicDeferDate) {
    this.facPostLicDeferDate = facPostLicDeferDate;
  }

  @Basic
  @Column(name = "fac_renewal_visit_date")
  public LocalDateTime getFacRenewalVisitDate() {
    return facRenewalVisitDate;
  }

  public void setFacRenewalVisitDate(LocalDateTime facRenewalVisitDate) {
    this.facRenewalVisitDate = facRenewalVisitDate;
  }

  @Basic
  @Column(name = "fac_post_lic_visit_date")
  public LocalDateTime getFacPostLicVisitDate() {
    return facPostLicVisitDate;
  }

  public void setFacPostLicVisitDate(LocalDateTime facPostLicVisitDate) {
    this.facPostLicVisitDate = facPostLicVisitDate;
  }

  @Basic
  @Column(name = "fac_renewal_visit_appr")
  public String getFacRenewalVisitAppr() {
    return facRenewalVisitAppr;
  }

  public void setFacRenewalVisitAppr(String facRenewalVisitAppr) {
    this.facRenewalVisitAppr = facRenewalVisitAppr;
  }

  @Basic
  @Column(name = "fac_post_lic_visit_appr")
  public String getFacPostLicVisitAppr() {
    return facPostLicVisitAppr;
  }

  public void setFacPostLicVisitAppr(String facPostLicVisitAppr) {
    this.facPostLicVisitAppr = facPostLicVisitAppr;
  }

  @Basic
  @Column(name = "fac_annual_22_mo_defer_date")
  public LocalDateTime getFacAnnual22MoDeferDate() {
    return facAnnual22MoDeferDate;
  }

  public void setFacAnnual22MoDeferDate(LocalDateTime facAnnual22MoDeferDate) {
    this.facAnnual22MoDeferDate = facAnnual22MoDeferDate;
  }

  @Basic
  @Column(name = "fac_annual_10_mo_defer_date")
  public LocalDateTime getFacAnnual10MoDeferDate() {
    return facAnnual10MoDeferDate;
  }

  public void setFacAnnual10MoDeferDate(LocalDateTime facAnnual10MoDeferDate) {
    this.facAnnual10MoDeferDate = facAnnual10MoDeferDate;
  }

  @Basic
  @Column(name = "fac_annual_22_mo_visit_date")
  public LocalDateTime getFacAnnual22MoVisitDate() {
    return facAnnual22MoVisitDate;
  }

  public void setFacAnnual22MoVisitDate(LocalDateTime facAnnual22MoVisitDate) {
    this.facAnnual22MoVisitDate = facAnnual22MoVisitDate;
  }

  @Basic
  @Column(name = "fac_annual_10_mo_visit_date")
  public LocalDateTime getFacAnnual10MoVisitDate() {
    return facAnnual10MoVisitDate;
  }

  public void setFacAnnual10MoVisitDate(LocalDateTime facAnnual10MoVisitDate) {
    this.facAnnual10MoVisitDate = facAnnual10MoVisitDate;
  }

  @Basic
  @Column(name = "fac_annual_22_mo_visit_appr")
  public String getFacAnnual22MoVisitAppr() {
    return facAnnual22MoVisitAppr;
  }

  public void setFacAnnual22MoVisitAppr(String facAnnual22MoVisitAppr) {
    this.facAnnual22MoVisitAppr = facAnnual22MoVisitAppr;
  }

  @Basic
  @Column(name = "fac_annual_10_mo_visit_appr")
  public String getFacAnnual10MoVisitAppr() {
    return facAnnual10MoVisitAppr;
  }

  public void setFacAnnual10MoVisitAppr(String facAnnual10MoVisitAppr) {
    this.facAnnual10MoVisitAppr = facAnnual10MoVisitAppr;
  }

  @Basic
  @Column(name = "fac_last_visit_reason")
  public BigInteger getFacLastVisitReason() {
    return facLastVisitReason;
  }

  public void setFacLastVisitReason(BigInteger facLastVisitReason) {
    this.facLastVisitReason = facLastVisitReason;
  }

  @Basic
  @Column(name = "fac_last_visit_date")
  public LocalDateTime getFacLastVisitDate() {
    return facLastVisitDate;
  }

  public void setFacLastVisitDate(LocalDateTime facLastVisitDate) {
    this.facLastVisitDate = facLastVisitDate;
  }

  @Basic
  @Column(name = "fac_last_upd_date")
  public LocalDateTime getFacLastUpdDate() {
    return facLastUpdDate;
  }

  public void setFacLastUpdDate(LocalDateTime facLastUpdDate) {
    this.facLastUpdDate = facLastUpdDate;
  }

  @Basic
  @Column(name = "fac_inc_cap_eff_date")
  public LocalDateTime getFacIncCapEffDate() {
    return facIncCapEffDate;
  }

  public void setFacIncCapEffDate(LocalDateTime facIncCapEffDate) {
    this.facIncCapEffDate = facIncCapEffDate;
  }

  @Basic
  @Column(name = "fac_cap_inc_closed_date")
  public LocalDateTime getFacCapIncClosedDate() {
    return facCapIncClosedDate;
  }

  public void setFacCapIncClosedDate(LocalDateTime facCapIncClosedDate) {
    this.facCapIncClosedDate = facCapIncClosedDate;
  }

  @Basic
  @Column(name = "fac_cap_inc_rec_date")
  public LocalDateTime getFacCapIncRecDate() {
    return facCapIncRecDate;
  }

  public void setFacCapIncRecDate(LocalDateTime facCapIncRecDate) {
    this.facCapIncRecDate = facCapIncRecDate;
  }

  @Basic
  @Column(name = "fac_closed_process_date")
  public LocalDateTime getFacClosedProcessDate() {
    return facClosedProcessDate;
  }

  public void setFacClosedProcessDate(LocalDateTime facClosedProcessDate) {
    this.facClosedProcessDate = facClosedProcessDate;
  }

  @Basic
  @Column(name = "fac_unlic_orig_input_date")
  public LocalDateTime getFacUnlicOrigInputDate() {
    return facUnlicOrigInputDate;
  }

  public void setFacUnlicOrigInputDate(LocalDateTime facUnlicOrigInputDate) {
    this.facUnlicOrigInputDate = facUnlicOrigInputDate;
  }

  @Basic
  @Column(name = "fac_last_change_date")
  public LocalDateTime getFacLastChangeDate() {
    return facLastChangeDate;
  }

  public void setFacLastChangeDate(LocalDateTime facLastChangeDate) {
    this.facLastChangeDate = facLastChangeDate;
  }

  @Basic
  @Column(name = "fac_lic_first_date")
  public LocalDateTime getFacLicFirstDate() {
    return facLicFirstDate;
  }

  public void setFacLicFirstDate(LocalDateTime facLicFirstDate) {
    this.facLicFirstDate = facLicFirstDate;
  }

  @Basic
  @Column(name = "fac_lic_expir_date")
  public LocalDateTime getFacLicExpirDate() {
    return facLicExpirDate;
  }

  public void setFacLicExpirDate(LocalDateTime facLicExpirDate) {
    this.facLicExpirDate = facLicExpirDate;
  }

  @Basic
  @Column(name = "fac_lic_eff_date")
  public LocalDateTime getFacLicEffDate() {
    return facLicEffDate;
  }

  public void setFacLicEffDate(LocalDateTime facLicEffDate) {
    this.facLicEffDate = facLicEffDate;
  }

  @Basic
  @Column(name = "fac_last_fireclear_date")
  public LocalDateTime getFacLastFireclearDate() {
    return facLastFireclearDate;
  }

  public void setFacLastFireclearDate(LocalDateTime facLastFireclearDate) {
    this.facLastFireclearDate = facLastFireclearDate;
  }

  @Basic
  @Column(name = "fac_licensee_type")
  public String getFacLicenseeType() {
    return facLicenseeType;
  }

  public void setFacLicenseeType(String facLicenseeType) {
    this.facLicenseeType = facLicenseeType;
  }

  @Basic
  @Column(name = "fac_lic_mail_zip_code")
  public String getFacLicMailZipCode() {
    return facLicMailZipCode;
  }

  public void setFacLicMailZipCode(String facLicMailZipCode) {
    this.facLicMailZipCode = facLicMailZipCode;
  }

  @Basic
  @Column(name = "fac_mail_zip_code")
  public String getFacMailZipCode() {
    return facMailZipCode;
  }

  public void setFacMailZipCode(String facMailZipCode) {
    this.facMailZipCode = facMailZipCode;
  }

  @Basic
  @Column(name = "fac_lic_mail_state")
  public String getFacLicMailState() {
    return facLicMailState;
  }

  public void setFacLicMailState(String facLicMailState) {
    this.facLicMailState = facLicMailState;
  }

  @Basic
  @Column(name = "fac_mail_state")
  public String getFacMailState() {
    return facMailState;
  }

  public void setFacMailState(String facMailState) {
    this.facMailState = facMailState;
  }

  @Basic
  @Column(name = "fac_lic_mail_city")
  public String getFacLicMailCity() {
    return facLicMailCity;
  }

  public void setFacLicMailCity(String facLicMailCity) {
    this.facLicMailCity = facLicMailCity;
  }

  @Basic
  @Column(name = "fac_mail_city")
  public String getFacMailCity() {
    return facMailCity;
  }

  public void setFacMailCity(String facMailCity) {
    this.facMailCity = facMailCity;
  }

  @Basic
  @Column(name = "fac_lic_mail_street_addr")
  public String getFacLicMailStreetAddr() {
    return facLicMailStreetAddr;
  }

  public void setFacLicMailStreetAddr(String facLicMailStreetAddr) {
    this.facLicMailStreetAddr = facLicMailStreetAddr;
  }

  @Basic
  @Column(name = "fac_mail_street_addr")
  public String getFacMailStreetAddr() {
    return facMailStreetAddr;
  }

  public void setFacMailStreetAddr(String facMailStreetAddr) {
    this.facMailStreetAddr = facMailStreetAddr;
  }

  @Basic
  @Column(name = "fac_required_visit")
  public String getFacRequiredVisit() {
    return facRequiredVisit;
  }

  public void setFacRequiredVisit(String facRequiredVisit) {
    this.facRequiredVisit = facRequiredVisit;
  }

  @Basic
  @Column(name = "fac_closed_date")
  public LocalDateTime getFacClosedDate() {
    return facClosedDate;
  }

  public void setFacClosedDate(LocalDateTime facClosedDate) {
    this.facClosedDate = facClosedDate;
  }

  @Basic
  @Column(name = "fac_orig_appl_rec_date")
  public LocalDateTime getFacOrigApplRecDate() {
    return facOrigApplRecDate;
  }

  public void setFacOrigApplRecDate(LocalDateTime facOrigApplRecDate) {
    this.facOrigApplRecDate = facOrigApplRecDate;
  }

  @Basic
  @Column(name = "fac_do_eval_code")
  public String getFacDoEvalCode() {
    return facDoEvalCode;
  }

  public void setFacDoEvalCode(String facDoEvalCode) {
    this.facDoEvalCode = facDoEvalCode;
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
  @Column(name = "fac_res_zip_code")
  public String getFacResZipCode() {
    return facResZipCode;
  }

  public void setFacResZipCode(String facResZipCode) {
    this.facResZipCode = facResZipCode;
  }

  @Basic
  @Column(name = "fac_licensee_name")
  public String getFacLicenseeName() {
    return facLicenseeName;
  }

  public void setFacLicenseeName(String facLicenseeName) {
    this.facLicenseeName = facLicenseeName;
  }

  @Basic
  @Column(name = "fac_res_state")
  public String getFacResState() {
    return facResState;
  }

  public void setFacResState(String facResState) {
    this.facResState = facResState;
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
  @Column(name = "fac_res_city")
  public String getFacResCity() {
    return facResCity;
  }

  public void setFacResCity(String facResCity) {
    this.facResCity = facResCity;
  }

  @Basic
  @Column(name = "fac_status")
  public BigInteger getFacStatus() {
    return facStatus;
  }

  public void setFacStatus(BigInteger facStatus) {
    this.facStatus = facStatus;
  }

  @Basic
  @Column(name = "fac_res_street_addr")
  public String getFacResStreetAddr() {
    return facResStreetAddr;
  }

  public void setFacResStreetAddr(String facResStreetAddr) {
    this.facResStreetAddr = facResStreetAddr;
  }

  @Basic
  @Column(name = "fac_capacity")
  public BigInteger getFacCapacity() {
    return facCapacity;
  }

  public void setFacCapacity(BigInteger facCapacity) {
    this.facCapacity = facCapacity;
  }

  @Basic
  @Column(name = "fac_name")
  public String getFacName() {
    return facName;
  }

  public void setFacName(String facName) {
    this.facName = facName;
  }

  @Basic
  @Column(name = "fac_type")
  public BigInteger getFacType() {
    return facType;
  }

  public void setFacType(BigInteger facType) {
    this.facType = facType;
  }

  @Id
  @Column(name = "fac_nbr")
  public BigInteger getFacNbr() {
    return facNbr;
  }

  public void setFacNbr(BigInteger facNbr) {
    this.facNbr = facNbr;
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
  @Column(name = "dt_created")
  public LocalDateTime getDtCreated() {
    return dtCreated;
  }

  public void setDtCreated(LocalDateTime dtCreated) {
    this.dtCreated = dtCreated;
  }

  @Basic
  @Column(name = "dt_modified")
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
    return getFacNbr();
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
