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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 */
@Entity
@Table(name = "facility_info_lis")
@SuppressWarnings("squid:S3437") //LocalDate is serializable
public class FacilityInfoLis implements PersistentObject {
  private String facActionCode;

  @Basic
  @Column(name = "fac_action_code", length = 254)
  public String getFacActionCode() {
    return facActionCode;
  }

  public void setFacActionCode(String facActionCode) {
    this.facActionCode = facActionCode;
  }

  private String facAggStatus;

  @Basic
  @Column(name = "fac_agg_status", length = 254)
  public String getFacAggStatus() {
    return facAggStatus;
  }

  public void setFacAggStatus(String facAggStatus) {
    this.facAggStatus = facAggStatus;
  }

  private Integer facAmb1864Nbr;

  @Basic
  @Column(name = "fac_amb_18_64_nbr")
  public Integer getFacAmb1864Nbr() {
    return facAmb1864Nbr;
  }

  public void setFacAmb1864Nbr(Integer facAmb1864Nbr) {
    this.facAmb1864Nbr = facAmb1864Nbr;
  }

  private Integer facAmb117Nbr;

  @Basic
  @Column(name = "fac_amb_1_17_nbr")
  public Integer getFacAmb117Nbr() {
    return facAmb117Nbr;
  }

  public void setFacAmb117Nbr(Integer facAmb117Nbr) {
    this.facAmb117Nbr = facAmb117Nbr;
  }

  private Integer facAmb65PlusNbr;

  @Basic
  @Column(name = "fac_amb_65_plus_nbr")
  public Integer getFacAmb65PlusNbr() {
    return facAmb65PlusNbr;
  }

  public void setFacAmb65PlusNbr(Integer facAmb65PlusNbr) {
    this.facAmb65PlusNbr = facAmb65PlusNbr;
  }

  private LocalDate facAnnual10MoDeferDate;

  @Basic
  @Column(name = "fac_annual_10_mo_defer_date")
  public LocalDate getFacAnnual10MoDeferDate() {
    return facAnnual10MoDeferDate;
  }

  public void setFacAnnual10MoDeferDate(LocalDate facAnnual10MoDeferDate) {
    this.facAnnual10MoDeferDate = facAnnual10MoDeferDate;
  }

  private String facAnnual10MoVisitAppr;

  @Basic
  @Column(name = "fac_annual_10_mo_visit_appr", length = 254)
  public String getFacAnnual10MoVisitAppr() {
    return facAnnual10MoVisitAppr;
  }

  public void setFacAnnual10MoVisitAppr(String facAnnual10MoVisitAppr) {
    this.facAnnual10MoVisitAppr = facAnnual10MoVisitAppr;
  }

  private LocalDate facAnnual10MoVisitDate;

  @Basic
  @Column(name = "fac_annual_10_mo_visit_date")
  public LocalDate getFacAnnual10MoVisitDate() {
    return facAnnual10MoVisitDate;
  }

  public void setFacAnnual10MoVisitDate(LocalDate facAnnual10MoVisitDate) {
    this.facAnnual10MoVisitDate = facAnnual10MoVisitDate;
  }

  private LocalDate facAnnual22MoDeferDate;

  @Basic
  @Column(name = "fac_annual_22_mo_defer_date")
  public LocalDate getFacAnnual22MoDeferDate() {
    return facAnnual22MoDeferDate;
  }

  public void setFacAnnual22MoDeferDate(LocalDate facAnnual22MoDeferDate) {
    this.facAnnual22MoDeferDate = facAnnual22MoDeferDate;
  }

  private String facAnnual22MoVisitAppr;

  @Basic
  @Column(name = "fac_annual_22_mo_visit_appr", length = 254)
  public String getFacAnnual22MoVisitAppr() {
    return facAnnual22MoVisitAppr;
  }

  public void setFacAnnual22MoVisitAppr(String facAnnual22MoVisitAppr) {
    this.facAnnual22MoVisitAppr = facAnnual22MoVisitAppr;
  }

  private LocalDate facAnnual22MoVisitDate;

  @Basic
  @Column(name = "fac_annual_22_mo_visit_date")
  public LocalDate getFacAnnual22MoVisitDate() {
    return facAnnual22MoVisitDate;
  }

  public void setFacAnnual22MoVisitDate(LocalDate facAnnual22MoVisitDate) {
    this.facAnnual22MoVisitDate = facAnnual22MoVisitDate;
  }

  private Integer facAnnualVisitYear;

  @Basic
  @Column(name = "fac_annual_visit_year")
  public Integer getFacAnnualVisitYear() {
    return facAnnualVisitYear;
  }

  public void setFacAnnualVisitYear(Integer facAnnualVisitYear) {
    this.facAnnualVisitYear = facAnnualVisitYear;
  }

  private LocalDate facBillingDate;

  @Basic
  @Column(name = "fac_billing_date")
  public LocalDate getFacBillingDate() {
    return facBillingDate;
  }

  public void setFacBillingDate(LocalDate facBillingDate) {
    this.facBillingDate = facBillingDate;
  }

  private Integer facCapacity;

  @Basic
  @Column(name = "fac_capacity")
  public Integer getFacCapacity() {
    return facCapacity;
  }

  public void setFacCapacity(Integer facCapacity) {
    this.facCapacity = facCapacity;
  }

  private Integer facCfrsIdNbr;

  @Basic
  @Column(name = "fac_cfrs_id_nbr")
  public Integer getFacCfrsIdNbr() {
    return facCfrsIdNbr;
  }

  public void setFacCfrsIdNbr(Integer facCfrsIdNbr) {
    this.facCfrsIdNbr = facCfrsIdNbr;
  }

  private LocalDate facClientServedApprDate;

  @Basic
  @Column(name = "fac_client_served_appr_date")
  public LocalDate getFacClientServedApprDate() {
    return facClientServedApprDate;
  }

  public void setFacClientServedApprDate(LocalDate facClientServedApprDate) {
    this.facClientServedApprDate = facClientServedApprDate;
  }

  private LocalDate facClosedDate;

  @Basic
  @Column(name = "fac_closed_date")
  public LocalDate getFacClosedDate() {
    return facClosedDate;
  }

  public void setFacClosedDate(LocalDate facClosedDate) {
    this.facClosedDate = facClosedDate;
  }

  private String facComplaint;

  @Basic
  @Column(name = "fac_complaint", length = 254)
  public String getFacComplaint() {
    return facComplaint;
  }

  public void setFacComplaint(String facComplaint) {
    this.facComplaint = facComplaint;
  }

  private Integer facCoNbr;

  @Basic
  @Column(name = "fac_co_nbr")
  public Integer getFacCoNbr() {
    return facCoNbr;
  }

  public void setFacCoNbr(Integer facCoNbr) {
    this.facCoNbr = facCoNbr;
  }

  private LocalDate facCapIncClosedDate;

  @Basic
  @Column(name = "fac_cap_inc_closed_date")
  public LocalDate getFacCapIncClosedDate() {
    return facCapIncClosedDate;
  }

  public void setFacCapIncClosedDate(LocalDate facCapIncClosedDate) {
    this.facCapIncClosedDate = facCapIncClosedDate;
  }

  private LocalDate facCapIncRecDate;

  @Basic
  @Column(name = "fac_cap_inc_rec_date")
  public LocalDate getFacCapIncRecDate() {
    return facCapIncRecDate;
  }

  public void setFacCapIncRecDate(LocalDate facCapIncRecDate) {
    this.facCapIncRecDate = facCapIncRecDate;
  }

  private Integer facClientServed;

  @Basic
  @Column(name = "fac_client_served")
  public Integer getFacClientServed() {
    return facClientServed;
  }

  public void setFacClientServed(Integer facClientServed) {
    this.facClientServed = facClientServed;
  }

  private LocalDate facClosedProcessDate;

  @Basic
  @Column(name = "fac_closed_process_date")
  public LocalDate getFacClosedProcessDate() {
    return facClosedProcessDate;
  }

  public void setFacClosedProcessDate(LocalDate facClosedProcessDate) {
    this.facClosedProcessDate = facClosedProcessDate;
  }

  private String facDeficiency;

  @Basic
  @Column(name = "fac_deficiency", length = 254)
  public String getFacDeficiency() {
    return facDeficiency;
  }

  public void setFacDeficiency(String facDeficiency) {
    this.facDeficiency = facDeficiency;
  }

  private String facDoEvalCode;

  @Basic
  @Column(name = "fac_do_eval_code", length = 254)
  public String getFacDoEvalCode() {
    return facDoEvalCode;
  }

  public void setFacDoEvalCode(String facDoEvalCode) {
    this.facDoEvalCode = facDoEvalCode;
  }

  private Integer facDoNbr;

  @Basic
  @Column(name = "fac_do_nbr")
  public Integer getFacDoNbr() {
    return facDoNbr;
  }

  public void setFacDoNbr(Integer facDoNbr) {
    this.facDoNbr = facDoNbr;
  }

  private String facDualId;

  @Basic
  @Column(name = "fac_dual_id", length = 254)
  public String getFacDualId() {
    return facDualId;
  }

  public void setFacDualId(String facDualId) {
    this.facDualId = facDualId;
  }

  private String facDualNumber;

  @Basic
  @Column(name = "fac_dual_number", length = 254)
  public String getFacDualNumber() {
    return facDualNumber;
  }

  public void setFacDualNumber(String facDualNumber) {
    this.facDualNumber = facDualNumber;
  }

  private String facEmailAddress;

  @Basic
  @Column(name = "fac_email_address", length = 254)
  public String getFacEmailAddress() {
    return facEmailAddress;
  }

  public void setFacEmailAddress(String facEmailAddress) {
    this.facEmailAddress = facEmailAddress;
  }

  private String facFcrbProgram;

  @Basic
  @Column(name = "fac_fcrb_program", length = 254)
  public String getFacFcrbProgram() {
    return facFcrbProgram;
  }

  public void setFacFcrbProgram(String facFcrbProgram) {
    this.facFcrbProgram = facFcrbProgram;
  }

  private Integer facFmRegionNbr;

  @Basic
  @Column(name = "fac_fm_region_nbr")
  public Integer getFacFmRegionNbr() {
    return facFmRegionNbr;
  }

  public void setFacFmRegionNbr(Integer facFmRegionNbr) {
    this.facFmRegionNbr = facFmRegionNbr;
  }

  private String facGhIndicator;

  @Basic
  @Column(name = "fac_gh_indicator", length = 254)
  public String getFacGhIndicator() {
    return facGhIndicator;
  }

  public void setFacGhIndicator(String facGhIndicator) {
    this.facGhIndicator = facGhIndicator;
  }

  private LocalDate facIncCapEffDate;

  @Basic
  @Column(name = "fac_inc_cap_eff_date")
  public LocalDate getFacIncCapEffDate() {
    return facIncCapEffDate;
  }

  public void setFacIncCapEffDate(LocalDate facIncCapEffDate) {
    this.facIncCapEffDate = facIncCapEffDate;
  }

  private LocalDate facLastDeferVisitDate;

  @Basic
  @Column(name = "fac_last_defer_visit_date")
  public LocalDate getFacLastDeferVisitDate() {
    return facLastDeferVisitDate;
  }

  public void setFacLastDeferVisitDate(LocalDate facLastDeferVisitDate) {
    this.facLastDeferVisitDate = facLastDeferVisitDate;
  }

  private Integer facLastDeferVisitReason;

  @Basic
  @Column(name = "fac_last_defer_visit_reason")
  public Integer getFacLastDeferVisitReason() {
    return facLastDeferVisitReason;
  }

  public void setFacLastDeferVisitReason(Integer facLastDeferVisitReason) {
    this.facLastDeferVisitReason = facLastDeferVisitReason;
  }

  private LocalDate facLastVisitDate;

  @Basic
  @Column(name = "fac_last_visit_date")
  public LocalDate getFacLastVisitDate() {
    return facLastVisitDate;
  }

  public void setFacLastVisitDate(LocalDate facLastVisitDate) {
    this.facLastVisitDate = facLastVisitDate;
  }

  private Integer facLastVisitReason;

  @Basic
  @Column(name = "fac_last_visit_reason")
  public Integer getFacLastVisitReason() {
    return facLastVisitReason;
  }

  public void setFacLastVisitReason(Integer facLastVisitReason) {
    this.facLastVisitReason = facLastVisitReason;
  }

  private String facLegalActionIndicator;

  @Basic
  @Column(name = "fac_legal_action_indicator", length = 254)
  public String getFacLegalActionIndicator() {
    return facLegalActionIndicator;
  }

  public void setFacLegalActionIndicator(String facLegalActionIndicator) {
    this.facLegalActionIndicator = facLegalActionIndicator;
  }

  private String facLicenseeName;

  @Basic
  @Column(name = "fac_licensee_name", length = 254)
  public String getFacLicenseeName() {
    return facLicenseeName;
  }

  public void setFacLicenseeName(String facLicenseeName) {
    this.facLicenseeName = facLicenseeName;
  }

  private String facLicComments;

  @Basic
  @Column(name = "fac_lic_comments", length = 254)
  public String getFacLicComments() {
    return facLicComments;
  }

  public void setFacLicComments(String facLicComments) {
    this.facLicComments = facLicComments;
  }

  private String facLicComments2;

  @Basic
  @Column(name = "fac_lic_comments_2", length = 254)
  public String getFacLicComments2() {
    return facLicComments2;
  }

  public void setFacLicComments2(String facLicComments2) {
    this.facLicComments2 = facLicComments2;
  }

  private LocalDate facLicEffDate;

  @Basic
  @Column(name = "fac_lic_eff_date")
  public LocalDate getFacLicEffDate() {
    return facLicEffDate;
  }

  public void setFacLicEffDate(LocalDate facLicEffDate) {
    this.facLicEffDate = facLicEffDate;
  }

  private LocalDate facLicExpirDate;

  @Basic
  @Column(name = "fac_lic_expir_date")
  public LocalDate getFacLicExpirDate() {
    return facLicExpirDate;
  }

  public void setFacLicExpirDate(LocalDate facLicExpirDate) {
    this.facLicExpirDate = facLicExpirDate;
  }

  private LocalDate facLicFirstDate;

  @Basic
  @Column(name = "fac_lic_first_date")
  public LocalDate getFacLicFirstDate() {
    return facLicFirstDate;
  }

  public void setFacLicFirstDate(LocalDate facLicFirstDate) {
    this.facLicFirstDate = facLicFirstDate;
  }

  private String facLicMailCity;

  @Basic
  @Column(name = "fac_lic_mail_city", length = 254)
  public String getFacLicMailCity() {
    return facLicMailCity;
  }

  public void setFacLicMailCity(String facLicMailCity) {
    this.facLicMailCity = facLicMailCity;
  }

  private String facLicMailState;

  @Basic
  @Column(name = "fac_lic_mail_state", length = 254)
  public String getFacLicMailState() {
    return facLicMailState;
  }

  public void setFacLicMailState(String facLicMailState) {
    this.facLicMailState = facLicMailState;
  }

  private String facLicMailStreetAddr;

  @Basic
  @Column(name = "fac_lic_mail_street_addr", length = 254)
  public String getFacLicMailStreetAddr() {
    return facLicMailStreetAddr;
  }

  public void setFacLicMailStreetAddr(String facLicMailStreetAddr) {
    this.facLicMailStreetAddr = facLicMailStreetAddr;
  }

  private String facLicMailZipCode;

  @Basic
  @Column(name = "fac_lic_mail_zip_code", length = 254)
  public String getFacLicMailZipCode() {
    return facLicMailZipCode;
  }

  public void setFacLicMailZipCode(String facLicMailZipCode) {
    this.facLicMailZipCode = facLicMailZipCode;
  }

  private LocalDate facLastChangeDate;

  @Basic
  @Column(name = "fac_last_change_date")
  public LocalDate getFacLastChangeDate() {
    return facLastChangeDate;
  }

  public void setFacLastChangeDate(LocalDate facLastChangeDate) {
    this.facLastChangeDate = facLastChangeDate;
  }

  private LocalDate facLastFireclearDate;

  @Basic
  @Column(name = "fac_last_fireclear_date")
  public LocalDate getFacLastFireclearDate() {
    return facLastFireclearDate;
  }

  public void setFacLastFireclearDate(LocalDate facLastFireclearDate) {
    this.facLastFireclearDate = facLastFireclearDate;
  }

  private LocalDate facLastUpdDate;

  @Basic
  @Column(name = "fac_last_upd_date")
  public LocalDate getFacLastUpdDate() {
    return facLastUpdDate;
  }

  public void setFacLastUpdDate(LocalDate facLastUpdDate) {
    this.facLastUpdDate = facLastUpdDate;
  }

  private String facLicenseeType;

  @Basic
  @Column(name = "fac_licensee_type", length = 254)
  public String getFacLicenseeType() {
    return facLicenseeType;
  }

  public void setFacLicenseeType(String facLicenseeType) {
    this.facLicenseeType = facLicenseeType;
  }

  private String facMailCity;

  @Basic
  @Column(name = "fac_mail_city", length = 254)
  public String getFacMailCity() {
    return facMailCity;
  }

  public void setFacMailCity(String facMailCity) {
    this.facMailCity = facMailCity;
  }

  private String facMailState;

  @Basic
  @Column(name = "fac_mail_state", length = 254)
  public String getFacMailState() {
    return facMailState;
  }

  public void setFacMailState(String facMailState) {
    this.facMailState = facMailState;
  }

  private String facMailStreetAddr;

  @Basic
  @Column(name = "fac_mail_street_addr", length = 254)
  public String getFacMailStreetAddr() {
    return facMailStreetAddr;
  }

  public void setFacMailStreetAddr(String facMailStreetAddr) {
    this.facMailStreetAddr = facMailStreetAddr;
  }

  private String facMailZipCode;

  @Basic
  @Column(name = "fac_mail_zip_code", length = 254)
  public String getFacMailZipCode() {
    return facMailZipCode;
  }

  public void setFacMailZipCode(String facMailZipCode) {
    this.facMailZipCode = facMailZipCode;
  }

  private String facName;

  @Basic
  @Column(name = "fac_name", length = 254)
  public String getFacName() {
    return facName;
  }

  public void setFacName(String facName) {
    this.facName = facName;
  }

  private Integer facNbr;

  @Id
  @Column(name = "fac_nbr")
  public Integer getFacNbr() {
    return facNbr;
  }

  public void setFacNbr(Integer facNbr) {
    this.facNbr = facNbr;
  }

  private Integer facNbrNew;

  @Basic
  @Column(name = "fac_nbr_new")
  public Integer getFacNbrNew() {
    return facNbrNew;
  }

  public void setFacNbrNew(Integer facNbrNew) {
    this.facNbrNew = facNbrNew;
  }

  private Integer facNonamb1864Nbr;

  @Basic
  @Column(name = "fac_nonamb_18_64_nbr")
  public Integer getFacNonamb1864Nbr() {
    return facNonamb1864Nbr;
  }

  public void setFacNonamb1864Nbr(Integer facNonamb1864Nbr) {
    this.facNonamb1864Nbr = facNonamb1864Nbr;
  }

  private Integer facNonamb117Nbr;

  @Basic
  @Column(name = "fac_nonamb_1_17_nbr")
  public Integer getFacNonamb117Nbr() {
    return facNonamb117Nbr;
  }

  public void setFacNonamb117Nbr(Integer facNonamb117Nbr) {
    this.facNonamb117Nbr = facNonamb117Nbr;
  }

  private Integer facNonamb65PlusNbr;

  @Basic
  @Column(name = "fac_nonamb_65_plus_nbr")
  public Integer getFacNonamb65PlusNbr() {
    return facNonamb65PlusNbr;
  }

  public void setFacNonamb65PlusNbr(Integer facNonamb65PlusNbr) {
    this.facNonamb65PlusNbr = facNonamb65PlusNbr;
  }

  private LocalDate facOrigApplRecDate;

  @Basic
  @Column(name = "fac_orig_appl_rec_date")
  public LocalDate getFacOrigApplRecDate() {
    return facOrigApplRecDate;
  }

  public void setFacOrigApplRecDate(LocalDate facOrigApplRecDate) {
    this.facOrigApplRecDate = facOrigApplRecDate;
  }

  private LocalDate facPostLicDeferDate;

  @Basic
  @Column(name = "fac_post_lic_defer_date")
  public LocalDate getFacPostLicDeferDate() {
    return facPostLicDeferDate;
  }

  public void setFacPostLicDeferDate(LocalDate facPostLicDeferDate) {
    this.facPostLicDeferDate = facPostLicDeferDate;
  }

  private String facPostLicVisitAppr;

  @Basic
  @Column(name = "fac_post_lic_visit_appr", length = 254)
  public String getFacPostLicVisitAppr() {
    return facPostLicVisitAppr;
  }

  public void setFacPostLicVisitAppr(String facPostLicVisitAppr) {
    this.facPostLicVisitAppr = facPostLicVisitAppr;
  }

  private LocalDate facPostLicVisitDate;

  @Basic
  @Column(name = "fac_post_lic_visit_date")
  public LocalDate getFacPostLicVisitDate() {
    return facPostLicVisitDate;
  }

  public void setFacPostLicVisitDate(LocalDate facPostLicVisitDate) {
    this.facPostLicVisitDate = facPostLicVisitDate;
  }

  private LocalDate facPreLicVisitDate;

  @Basic
  @Column(name = "fac_pre_lic_visit_date")
  public LocalDate getFacPreLicVisitDate() {
    return facPreLicVisitDate;
  }

  public void setFacPreLicVisitDate(LocalDate facPreLicVisitDate) {
    this.facPreLicVisitDate = facPreLicVisitDate;
  }

  private Integer facPrimaryNbr;

  @Basic
  @Column(name = "fac_primary_nbr")
  public Integer getFacPrimaryNbr() {
    return facPrimaryNbr;
  }

  public void setFacPrimaryNbr(Integer facPrimaryNbr) {
    this.facPrimaryNbr = facPrimaryNbr;
  }

  private String facRegionCo;

  @Basic
  @Column(name = "fac_region_co", length = 254)
  public String getFacRegionCo() {
    return facRegionCo;
  }

  public void setFacRegionCo(String facRegionCo) {
    this.facRegionCo = facRegionCo;
  }

  private String facRegionDo;

  @Basic
  @Column(name = "fac_region_do", length = 254)
  public String getFacRegionDo() {
    return facRegionDo;
  }

  public void setFacRegionDo(String facRegionDo) {
    this.facRegionDo = facRegionDo;
  }

  private Integer facRegionNbr;

  @Basic
  @Column(name = "fac_region_nbr")
  public Integer getFacRegionNbr() {
    return facRegionNbr;
  }

  public void setFacRegionNbr(Integer facRegionNbr) {
    this.facRegionNbr = facRegionNbr;
  }

  private LocalDate facRenewalDeferDate;

  @Basic
  @Column(name = "fac_renewal_defer_date")
  public LocalDate getFacRenewalDeferDate() {
    return facRenewalDeferDate;
  }

  public void setFacRenewalDeferDate(LocalDate facRenewalDeferDate) {
    this.facRenewalDeferDate = facRenewalDeferDate;
  }

  private String facRenewalVisitAppr;

  @Basic
  @Column(name = "fac_renewal_visit_appr", length = 254)
  public String getFacRenewalVisitAppr() {
    return facRenewalVisitAppr;
  }

  public void setFacRenewalVisitAppr(String facRenewalVisitAppr) {
    this.facRenewalVisitAppr = facRenewalVisitAppr;
  }

  private LocalDate facRenewalVisitDate;

  @Basic
  @Column(name = "fac_renewal_visit_date")
  public LocalDate getFacRenewalVisitDate() {
    return facRenewalVisitDate;
  }

  public void setFacRenewalVisitDate(LocalDate facRenewalVisitDate) {
    this.facRenewalVisitDate = facRenewalVisitDate;
  }

  private String facRequiredVisit;

  @Basic
  @Column(name = "fac_required_visit", length = 254)
  public String getFacRequiredVisit() {
    return facRequiredVisit;
  }

  public void setFacRequiredVisit(String facRequiredVisit) {
    this.facRequiredVisit = facRequiredVisit;
  }

  private String facResCity;

  @Basic
  @Column(name = "fac_res_city", length = 254)
  public String getFacResCity() {
    return facResCity;
  }

  public void setFacResCity(String facResCity) {
    this.facResCity = facResCity;
  }

  private String facResState;

  @Basic
  @Column(name = "fac_res_state", length = 254)
  public String getFacResState() {
    return facResState;
  }

  public void setFacResState(String facResState) {
    this.facResState = facResState;
  }

  private String facResStreetAddr;

  @Basic
  @Column(name = "fac_res_street_addr", length = 254)
  public String getFacResStreetAddr() {
    return facResStreetAddr;
  }

  public void setFacResStreetAddr(String facResStreetAddr) {
    this.facResStreetAddr = facResStreetAddr;
  }

  private String facResZipCode;

  @Basic
  @Column(name = "fac_res_zip_code", length = 254)
  public String getFacResZipCode() {
    return facResZipCode;
  }

  public void setFacResZipCode(String facResZipCode) {
    this.facResZipCode = facResZipCode;
  }

  private Integer facStatus;

  @Basic
  @Column(name = "fac_status")
  public Integer getFacStatus() {
    return facStatus;
  }

  public void setFacStatus(Integer facStatus) {
    this.facStatus = facStatus;
  }

  private Integer facType;

  @Basic
  @Column(name = "fac_type")
  public Integer getFacType() {
    return facType;
  }

  public void setFacType(Integer facType) {
    this.facType = facType;
  }

  private LocalDate facUnlicOrigInputDate;

  @Basic
  @Column(name = "fac_unlic_orig_input_date")
  public LocalDate getFacUnlicOrigInputDate() {
    return facUnlicOrigInputDate;
  }

  public void setFacUnlicOrigInputDate(LocalDate facUnlicOrigInputDate) {
    this.facUnlicOrigInputDate = facUnlicOrigInputDate;
  }

  private String fnAmb;

  @Basic
  @Column(name = "fn_amb", length = 254)
  public String getFnAmb() {
    return fnAmb;
  }

  public void setFnAmb(String fnAmb) {
    this.fnAmb = fnAmb;
  }

  private String fnComment;

  @Basic
  @Column(name = "fn_comment", length = 254)
  public String getFnComment() {
    return fnComment;
  }

  public void setFnComment(String fnComment) {
    this.fnComment = fnComment;
  }

  private String fnLegal;

  @Basic
  @Column(name = "fn_legal", length = 254)
  public String getFnLegal() {
    return fnLegal;
  }

  public void setFnLegal(String fnLegal) {
    this.fnLegal = fnLegal;
  }

  private String fnLicensing;

  @Basic
  @Column(name = "fn_licensing", length = 254)
  public String getFnLicensing() {
    return fnLicensing;
  }

  public void setFnLicensing(String fnLicensing) {
    this.fnLicensing = fnLicensing;
  }

  private String fnMailing;

  @Basic
  @Column(name = "fn_mailing", length = 254)
  public String getFnMailing() {
    return fnMailing;
  }

  public void setFnMailing(String fnMailing) {
    this.fnMailing = fnMailing;
  }

  private String fnVisit;

  @Basic
  @Column(name = "fn_visit", length = 254)
  public String getFnVisit() {
    return fnVisit;
  }

  public void setFnVisit(String fnVisit) {
    this.fnVisit = fnVisit;
  }

  private String fnamAmb;

  @Basic
  @Column(name = "fnam_amb", length = 254)
  public String getFnamAmb() {
    return fnamAmb;
  }

  public void setFnamAmb(String fnamAmb) {
    this.fnamAmb = fnamAmb;
  }

  private String fnamComment;

  @Basic
  @Column(name = "fnam_comment", length = 254)
  public String getFnamComment() {
    return fnamComment;
  }

  public void setFnamComment(String fnamComment) {
    this.fnamComment = fnamComment;
  }

  private String fnamLegal;

  @Basic
  @Column(name = "fnam_legal", length = 254)
  public String getFnamLegal() {
    return fnamLegal;
  }

  public void setFnamLegal(String fnamLegal) {
    this.fnamLegal = fnamLegal;
  }

  private String fnamLicensing;

  @Basic
  @Column(name = "fnam_licensing", length = 254)
  public String getFnamLicensing() {
    return fnamLicensing;
  }

  public void setFnamLicensing(String fnamLicensing) {
    this.fnamLicensing = fnamLicensing;
  }

  private String fnamMailing;

  @Basic
  @Column(name = "fnam_mailing", length = 254)
  public String getFnamMailing() {
    return fnamMailing;
  }

  public void setFnamMailing(String fnamMailing) {
    this.fnamMailing = fnamMailing;
  }

  private String fnamVisit;

  @Basic
  @Column(name = "fnam_visit", length = 254)
  public String getFnamVisit() {
    return fnamVisit;
  }

  public void setFnamVisit(String fnamVisit) {
    this.fnamVisit = fnamVisit;
  }

  private String facilityAdmin;

  @Basic
  @Column(name = "facility_admin", length = 254)
  public String getFacilityAdmin() {
    return facilityAdmin;
  }

  public void setFacilityAdmin(String facilityAdmin) {
    this.facilityAdmin = facilityAdmin;
  }

  private String facilityTelephone;

  @Basic
  @Column(name = "facility_telephone", length = 254)
  public String getFacilityTelephone() {
    return facilityTelephone;
  }

  public void setFacilityTelephone(String facilityTelephone) {
    this.facilityTelephone = facilityTelephone;
  }

  private String noexport;

  @Basic
  @Column(name = "noexport", length = 254)
  public String getNoexport() {
    return noexport;
  }

  public void setNoexport(String noexport) {
    this.noexport = noexport;
  }

  private LocalDateTime dtModified;

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
