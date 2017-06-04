package gov.ca.cwds.cals.persistence.model.fas;

import gov.ca.cwds.data.persistence.PersistentObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 */

@NamedQuery(
        name = ComplaintReportLic802.FIND_COMPLAINTS_BY_FACILITY_ID,
        query = "SELECT c FROM ComplaintReportLic802 c WHERE c.crp2FacilityNumber = :" +
                ComplaintReportLic802.PARAM_FACILITY_NUMBER
)
@NamedQuery(
        name = ComplaintReportLic802.FIND_COMPLAINTS_BY_FACILITY_ID_AND_COMPLAINT_NUMBER,
        query = "SELECT c FROM ComplaintReportLic802 c WHERE c.crp2FacilityNumber = :" +
                ComplaintReportLic802.PARAM_FACILITY_NUMBER + " AND c.originalunidkey = :" +
                ComplaintReportLic802.PARAM_COMPLAINT_ID
)
@Entity
@Access(AccessType.FIELD)
@Table(name = "complaint_report_lic802", schema = "fas")
@SuppressWarnings("squid:S3437") //LocalDate is serializable
public class ComplaintReportLic802 implements PersistentObject {

    public static final String FIND_COMPLAINTS_BY_FACILITY_ID = "findComplaintsByFacilityId";
    public static final String FIND_COMPLAINTS_BY_FACILITY_ID_AND_COMPLAINT_NUMBER = "findComplaintsByFacilityIdAndFacilityNumber";


    public static final String PARAM_FACILITY_NUMBER = "facilityNumber";
    public static final String PARAM_COMPLAINT_ID = "complaintId";

    @Basic
    @Column(name = "response_description", length = 8192)
    private String ressponseDescription;

    @Basic
    @Column(name = "dont_save_flag", length = 8)
    private String dontSaveFlag;

    @Basic
    @Column(name = "audit_history", length = 8192)
    private String auditHistory;

    @Id
    @Column(name = "originalunidkey", length = 256)
    private String originalunidkey;

    @Basic
    @Column(name = "edit_history", length = 8192)
    private String editHistory;

    @Basic
    @Column(name = "dt_created")
    private LocalDateTime dtCreated;

    @Basic
    @Column(name = "creator_name", length = 256)
    private String creatorName;

    @Basic
    @Column(name = "page_total", length = 256)
    private String pageTotal;

    @Basic
    @Column(name = "page_nr", length = 256)
    private String pageNr;

    @Basic
    @Column(name = "crp2_formnumber", length = 256)
    private String crp2Formnumber;

    @Basic
    @Column(name = "cr_lps_signdate")
    private LocalDateTime crLpsSigndate;

    @Basic
    @Column(name = "cr_lps_signname", length = 256)
    private String crLpssignname;

    @Basic
    @Column(name = "cr_lpa_signdate")
    private LocalDateTime crLpaSigndate;

    @Basic
    @Column(name = "cr_lpa_signname", length = 256)
    private String crLpaSignname;

    @Basic
    @Column(name = "crp2_followup", length = 256)
    private String crp2Followup;

    @Basic
    @Column(name = "z1_3", length = 256)
    private String z13;

    @Basic
    @Column(name = "crp2_contactsummary", length = 8192)
    private String crp2Contactsummary;

    @Basic
    @Column(name = "z1_2", length = 256)
    private String z12;

    @Basic
    @Column(name = "crp2_postcomments", length = 8192)
    private String crp2Postcomments;

    @Basic
    @Column(name = "z1_4", length = 256)
    private String z14;

    @Basic
    @Column(name = "crp2_precomments", length = 8192)
    private String crp2Precomments;

    @Basic
    @Column(name = "z1_1", length = 256)
    private String z11;

    @Basic
    @Column(name = "p_posthowcontact", length = 256)
    private String pPosthowcontact;

    @Basic
    @Column(name = "crp2_posthowcontact", length = 256)
    private String crp2Posthowcontact;

    @Basic
    @Column(name = "p_prehowcontact", length = 256)
    private String pPrehowcontact;

    @Basic
    @Column(name = "crp2_prehowcontact", length = 256)
    private String crp2Prehowcontact;

    @Basic
    @Column(name = "crp2_postcontact", length = 256)
    private String crp2Postcontact;

    @Basic
    @Column(name = "crp2_postdate")
    private LocalDate crp2Postdate;

    @Basic
    @Column(name = "crp2_precontact", length = 256)
    private String crp2Precontact;

    @Basic
    @Column(name = "crp2_predate")
    private LocalDate crp2Predate;

    @Basic
    @Column(name = "crp2_detaildescription", length = 8192)
    private String crp2Detaildescription;

    @Basic
    @Column(name = "z1", length = 256)
    private String z1;

    @Basic
    @Column(name = "crp2_facility_number", length = 256)
    private Integer crp2FacilityNumber;

    @Basic
    @Column(name = "crp2_facility_type", length = 256)
    private String crp2FacilityType;

    @Basic
    @Column(name = "crp2_facility_name", length = 256)
    private String  crp2FacilityName;

    @Basic
    @Column(name = "crp2_controlnumber", length = 256)
    private String crp2Controlnumber;

    @Basic
    @Column(name = "crp2_visitduedate")
    private LocalDate crp2Visitduedate;

    @Basic
    @Column(name = "crp2_prioritynr", length = 256)
    private String crp2Prioritynr;

    @Basic
    @Column(name = "crp2_priority", length = 256)
    private String crp2Priority;

    @Basic
    @Column(name = "do_zip2", length = 256)
    private String doZip2;

    @Basic
    @Column(name = "do_state2", length = 256)
    private String doState2;

    @Basic
    @Column(name = "do_city2", length = 256)
    private String doCity2;

    @Basic
    @Column(name = "do_address2", length = 256)
    private String doAddress2;

    @Basic
    @Column(name = "do_name2", length = 256)
    private String doName2;

    @Basic
    @Column(name = "reportname2", length = 256)
    private String reportname2;

    @Basic
    @Column(name = "page_total1", length = 256)
    private String pageTotal1;

    @Basic
    @Column(name = "page_nr1", length = 256)
    private String pageNr1;

    @Basic
    @Column(name = "form_number", length = 256)
    private String formNumber;

    @Basic
    @Column(name = "cr_time")
    private String crTime;

    @Basic
    @Column(name = "cr_date")
    private LocalDate crDate;

    @Basic
    @Column(name = "cr_receivedby", length = 256)
    private String crReceivedby;

    @Basic
    @Column(name = "cr_unsub_7", length = 256)
    private String crUnSub7;

    @Basic
    @Column(name = "cr_insub_7", length = 256)
    private String crInSub7;

    @Basic
    @Column(name = "cr_sub_7", length = 256)
    private String crSub7;

    @Basic
    @Column(name = "cr_allegation_7", length = 256)
    private String crAllegation7;

    @Basic
    @Column(name = "z_7", length = 256)
    private String z7;

    @Basic
    @Column(name = "undr_apl_7", length = 256)
    private String undrApl7;

    @Basic
    @Column(name = "cr_code_7", length = 256)
    private String crCode7;

    @Basic
    @Column(name = "cr_unsub_6", length = 256)
    private String crUnSub6;

    @Basic
    @Column(name = "cr_insub_6", length = 256)
    private String crInSub6;

    @Basic
    @Column(name = "cr_sub_6", length = 256)
    private String crSub6;

    @Basic
    @Column(name = "cr_allegation_6", length = 256)
    private String crAllegation6;

    @Basic
    @Column(name = "z_6", length = 256)
    private String z6;

    @Basic
    @Column(name = "undr_apl_6", length = 256)
    private String undrApl6;

    @Basic
    @Column(name = "cr_code_6", length = 256)
    private String crCode6;

    @Basic
    @Column(name = "cr_unsub_5", length = 256)
    private String crUnSub5;

    @Basic
    @Column(name = "cr_insub_5", length = 256)
    private String crInSub5;

    @Basic
    @Column(name = "cr_sub_5", length = 256)
    private String crSub5;

    @Basic
    @Column(name = "cr_allegation_5", length = 256)
    private String crAllegation5;

    @Basic
    @Column(name = "z_5", length = 256)
    private String z5;

    @Basic
    @Column(name = "undr_apl_5", length = 256)
    private String undrApl5;

    @Basic
    @Column(name = "cr_code_5", length = 256)
    private String crCode5;

    @Basic
    @Column(name = "cr_unsub_4", length = 256)
    private String crUnSub4;

    @Basic
    @Column(name = "cr_insub_4", length = 256)
    private String crInSub4;

    @Basic
    @Column(name = "cr_sub_4", length = 256)
    private String crSub4;

    @Basic
    @Column(name = "cr_allegation_4", length = 256)
    private String crAllegation4;

    @Basic
    @Column(name = "z_4", length = 256)
    private String z4;

    @Basic
    @Column(name = "undr_apl_4", length = 256)
    private String undrApl4;

    @Basic
    @Column(name = "cr_code_4", length = 256)
    private String crCode4;

    @Basic
    @Column(name = "cr_unsub_3", length = 256)
    private String crUnSub3;

    @Basic
    @Column(name = "cr_insub_3", length = 256)
    private String crInSub3;

    @Basic
    @Column(name = "cr_sub_3", length = 256)
    private String crSub3;

    @Basic
    @Column(name = "cr_allegation_3", length = 256)
    private String crAllegation3;

    @Basic
    @Column(name = "z_3", length = 256)
    private String z3;

    @Basic
    @Column(name = "undr_apl_3", length = 256)
    private String undrApl3;

    @Basic
    @Column(name = "cr_code_3", length = 256)
    private String crCode3;

    @Basic
    @Column(name = "cr_unsub_2", length = 256)
    private String crUnSub2;

    @Basic
    @Column(name = "cr_insub_2", length = 256)
    private String crInSub2;

    @Basic
    @Column(name = "cr_sub_2", length = 256)
    private String crSub2;

    @Basic
    @Column(name = "cr_allegation_2", length = 256)
    private String crAllegation2;

    @Basic
    @Column(name = "z_2", length = 256)
    private String z2;

    @Basic
    @Column(name = "undr_apl_2", length = 256)
    private String undrApl2;

    @Basic
    @Column(name = "cr_code_2", length = 256)
    private String crCode2;

    @Basic
    @Column(name = "cr_unsub_1", length = 256)
    private String crUnSub1;

    @Basic
    @Column(name = "cr_insub_1", length = 256)
    private String crInSub1;

    @Basic
    @Column(name = "cr_sub_1", length = 256)
    private String crSub1;

    @Basic
    @Column(name = "cr_allegation_1", length = 256)
    private String crAllegation1;

    @Basic
    @Column(name = "z_1", length = 256)
    private String zUndr1;

    @Basic
    @Column(name = "undr_apl_1", length = 256)
    private String undrApl1;

    @Basic
    @Column(name = "cr_code_1", length = 256)
    private String crCode1;

    @Basic
    @Column(name = "cr_unsub", length = 256)
    private String crUnSub;

    @Basic
    @Column(name = "cr_insub", length = 256)
    private String crInSub;

    @Basic
    @Column(name = "cr_sub", length = 256)
    private String crSub;

    @Basic
    @Column(name = "cr_allegation", length = 256)
    private String crAllegation;

    @Basic
    @Column(name = "z", length = 256)
    private String z;

    @Basic
    @Column(name = "undr_apl", length = 256)
    private String undrApl;

    @Basic
    @Column(name = "cr_code", length = 256)
    private String crCode;

    @Basic
    @Column(name = "facility_telephone", length = 256)
    private String facilityTelephone;

    @Basic
    @Column(name = "facility_zip", length = 256)
    private String facilityZip;

    @Basic
    @Column(name = "facility_city", length = 256)
    private String facilityCity;

    @Basic
    @Column(name = "facility_address", length = 256)
    private String facilityAddress;

    @Basic
    @Column(name = "facility_name", length = 256)
    private String facilityName;

    @Basic
    @Column(name = "facility_number_text", length = 256)
    private String facilityNumberText;

    @Basic
    @Column(name = "facility_type")
    private java.math.BigDecimal facilityType;

    @Basic
    @Column(name = "cr_controlnumber", length = 256)
    private String crControlnumber;

    @Basic
    @Column(name = "cr_visitduedate")
    private LocalDate crVisitduedate;

    @Basic
    @Column(name = "cr_doname", length = 256)
    private String crDoname;

    @Basic
    @Column(name = "cr_prioritynr", length = 256)
    private String crPrioritynr;

    @Basic
    @Column(name = "cr_priority", length = 256)
    private String crPriority;

    @Basic
    @Column(name = "p_complainantanonymous", length = 256)
    private String pComplainantanonymous;

    @Basic
    @Column(name = "cr_complainantanonymous", length = 256)
    private String crComplainantanonymous;

    @Basic
    @Column(name = "p_abusereportfiled", length = 256)
    private String pAbusereportfiled;

    @Basic
    @Column(name = "cr_abusereportfiled", length = 256)
    private String crAbusereportfiled;

    @Basic
    @Column(name = "cr_complainantrelation", length = 256)
    private String crComplainantrelation;

    @Basic
    @Column(name = "cr_complainantnightphone", length = 256)
    private String crComplainantnightphone;

    @Basic
    @Column(name = "cr_complainantdayphone", length = 256)
    private String crComplainantdayphone;

    @Basic
    @Column(name = "cr_complainantzip", length = 256)
    private String crComplainantzip;

    @Basic
    @Column(name = "cr_complainantcity", length = 256)
    private String crComplainantcity;

    @Basic
    @Column(name = "cr_complainantaddress", length = 256)
    private String crComplainantaddress;

    @Basic
    @Column(name = "cr_complainantname", length = 256)
    private String crComplainantname;

    @Basic
    @Column(name = "p_howreported", length = 256)
    private String pHowreported;

    @Basic
    @Column(name = "cr_howreported", length = 256)
    private String crHowreported;

    @Basic
    @Column(name = "do_zip", length = 256)
    private String doZip;

    @Basic
    @Column(name = "do_state", length = 256)
    private String doState;

    @Basic
    @Column(name = "do_city", length = 256)
    private String doCity;

    @Basic
    @Column(name = "do_address", length = 256)
    private String doAddress;

    @Basic
    @Column(name = "do_name", length = 256)
    private String doName;

    @Basic
    @Column(name = "report_title", length = 256)
    private String reportTitle;

    @Basic
    @Column(name = "reportname", length = 256)
    private String reportname;

    @Basic
    @Column(name = "linked_pages", length = 256)
    private String linkedPages;

    @Basic
    @Column(name = "time_created", length = 256)
    private String timeCreated;

    @Basic
    @Column(name = "create_date", length = 256)
    private String createDate;

    @Basic
    @Column(name = "created_by", length = 256)
    private String createdBy;

    @Basic
    @Column(name = "facility_num_new", length = 256)
    private String facilityNumNew;

    @Basic
    @Column(name = "orgtoamd_unid", length = 256)
    private String orgtoamdUnid;

    @Basic
    @Column(name = "amend_printed", length = 256)
    private String amendPrinted;

    @Basic
    @Column(name = "times_amended", length = 256)
    private String timesAmended;

    @Basic
    @Column(name = "amend_flag", length = 8)
    private String amendFlag;

    @Basic
    @Column(name = "proof", length = 256)
    private String proof;

    @Basic
    @Column(name = "program_code", length = 256)
    private String programCode;

    @Basic
    @Column(name = "do_number", length = 256)
    private String doNumber;

    @Basic
    @Column(name = "create_date_time", length = 256)
    private String createDateTime;

    @Basic
    @Column(name = "link_count", length = 256)
    private String linkCount;

    @Basic
    @Column(name = "show_linked_pages", length = 256)
    private String showLinkedPages;

    @Basic
    @Column(name = "fas_lpa_code", length = 64)
    private String fasLpaCode;

    @Basic
    @Column(name = "facility_number", length = 256)
    private String facilityNumber;

    @Basic
    @Column(name = "allowableeditors", length = 256)
    private String allowableeditors;

    @Basic
    @Column(name = "authornames", length = 256)
    private String authornames;

    @Basic
    @Column(name = "lpa_code", length = 64)
    private String lpaCode;

    @Basic
    @Column(name = "delete_flag", length = 8)
    private String deleteFlag;

    @Basic
    @Column(name = "signature", length = 256)
    private String signature;

    @Basic
    @Column(name = "form_abrievation", length = 256)
    private String formAbrievation;

    @Basic
    @Column(name = "sign_off_date", length = 256)
    private String signOffDate;

    @Basic
    @Column(name = "ai_action_taken", length = 256)
    private String aiActionTaken;

    @Basic
    @Column(name = "ai_total_hours", length = 256)
    private String aiTotalHours;

    @Basic
    @Column(name = "ai_num_visits", length = 256)
    private String aiNumVisits;

    @Basic
    @Column(name = "audit_acpt_date", length = 256)
    private String auditAcptDate;

    @Basic
    @Column(name = "p_audit_accept", length = 256)
    private String pAuditAccept;

    @Basic
    @Column(name = "audit_accept", length = 256)
    private String auditAccept;

    @Basic
    @Column(name = "audit_rfr_date", length = 256)
    private String auditRfrDate;

    @Basic
    @Column(name = "p_audit_refer", length = 256)
    private String pAuditRefer;

    @Basic
    @Column(name = "audit_refer", length = 256)
    private String auditRefer;

    @Basic
    @Column(name = "why", length = 256)
    private String why;

    @Basic
    @Column(name = "p_follow_up", length = 256)
    private String pFollowUp;

    @Basic
    @Column(name = "follow_up", length = 256)
    private String followUp;

    @Basic
    @Column(name = "admin_total_hours", length = 256)
    private String adminTotalHours;

    @Basic
    @Column(name = "action_taken", length = 256)
    private String actionTaken;

    @Basic
    @Column(name = "total_hours", length = 256)
    private String totalHours;

    @Basic
    @Column(name = "num_of_visits", length = 256)
    private String numOfVisits;

    @Basic
    @Column(name = "c_home_type", length = 256)
    private String cHomeType;

    @Basic
    @Column(name = "c_home_zip", length = 256)
    private String cHomeZip;

    @Basic
    @Column(name = "c_home_telephone", length = 256)
    private String cHomeTelephone;

    @Basic
    @Column(name = "c_home_city", length = 256)
    private String cHomeCity;

    @Basic
    @Column(name = "c_home_licensee", length = 256)
    private String cHomeLicensee;

    @Basic
    @Column(name = "c_home_address", length = 256)
    private String cHomeAddress;

    @Basic
    @Column(name = "c_home_name", length = 256)
    private String cHomeName;

    @Basic
    @Column(name = "cr_lps_rejectcomments", length = 256)
    private String crLPSRejectComments;

    @Basic
    @Column(name = "cr_lps_rejname", length = 256)
    private String crLPSRejName;

    @Basic
    @Column(name = "cr_lps_rejdate", length = 256)
    private String crLPSRejDate;

    @Basic
    @Column(name = "c_home_number", length = 256)
    private String cHomeNumber;

    @Basic
    @Column(name = "cr_lps_appname", length = 256)
    private String crLpsAppname;

    @Basic
    @Column(name = "cr_lps_appdate")
    private LocalDateTime crLpsAppdate;

    @Basic
    @Column(name = "cr_lps_name", length = 256)
    private String crLpsName;

    @Basic
    @Column(name = "cr_lpa_subname", length = 256)
    private String crLpaSubname;

    @Basic
    @Column(name = "cr_lpa_subdate")
    private LocalDateTime crLpaSubdate;

    @Basic
    @Column(name = "cr_lpa_name", length = 256)
    private String crLpaName;

    @Basic
    @Column(name = "cr_status", length = 256)
    private String crStatus;

    @Basic
    @Column(name = "date_assigned")
    private LocalDateTime dateAssigned;

    @Basic
    @Column(name = "lpa_assigned", length = 256)
    private String lpaAssigned;

    @Basic
    @Column(name = "date_signed")
    private LocalDateTime dateSigned;

    @Basic
    @Column(name = "cre_authornames", length = 256)
    private String creAuthornames;

    @Basic
    @Column(name = "verified_by", length = 256)
    private String verifiedBy;

    @Basic
    @Column(name = "verified_date", length = 256)
    private String verifiedDate;

    @Basic
    @Column(name = "verified", length = 256)
    private String verified;

    @Basic
    @Column(name = "agency_selection_1", length = 256)
    private String agencySelection1;

    @Basic
    @Column(name = "agency_selection", length = 256)
    private String agencySelection;
    
    @Basic
    @Column(name = "from_intake", length = 256)
    private String fromIntake;

    @Basic
    @Column(name = "fac_do_nbr")
    private java.math.BigDecimal facDoNbr;

    @Basic
    @Column(name = "tendaymissed", length = 256)
    private String tendaymissed;

    @Basic
    @Column(name = "tendayvisit_flag", length = 8)
    private String tendayvisitFlag;

    @Basic
    @Column(name = "fac_primary_nbr")
    private java.math.BigDecimal facPrimaryNbr;

    @Basic
    @Column(name = "faslpacode_1", length = 256)
    private String faslpacode1;

    @Basic
    @Column(name = "reject_comments", length = 8192)
    private String rejectComments;

    @Basic
    @Column(name = "ancs", length = 256)
    private String ancs;

    @Basic
    @Column(name = "approve_by_history", length = 256)
    private String approveByHistory;

    @Basic
    @Column(name = "approve_date_history")
    private LocalDateTime approveDateHistory;

    @Basic
    @Column(name = "approve_history", length = 256)
    private String approveHistory;

    @Basic
    @Column(name = "assign_by_history", length = 256)
    private String assignByHistory;

    @Basic
    @Column(name = "assign_date_history")
    private LocalDateTime assignDateHistory;

    @Basic
    @Column(name = "assign_history", length = 256)
    private String assignHistory;

    @Basic
    @Column(name = "complaintsumunid", length = 256)
    private String complaintsumunid;

    @Basic
    @Column(name = "complaintunid", length = 256)
    private String complaintunid;

    @Basic
    @Column(name = "search_address", length = 256)
    private String searchAddress;

    @Basic
    @Column(name = "search_name", length = 256)
    private String searchName;

    @Basic
    @Column(name = "search_number", length = 256)
    private String searchNumber;

    @Basic
    @Column(name = "createddbname", length = 256)
    private String createddbname;

    @Basic
    @Column(name = "facility_state", length = 256)
    private String facilityState;

    @Basic
    @Column(name = "facility_admin", length = 256)
    private String facilityAdmin;
    
    @Basic
    @Column(name = "cr_visit_date")
    private LocalDate crVisitDate;

    @Basic
    @Column(name = "form_name", length = 48)
    private String formName;

    public String getRessponseDescription() {
        return ressponseDescription;
    }

    public void setRessponseDescription(String ressponseDescription) {
        this.ressponseDescription = ressponseDescription;
    }

    public String getDontSaveFlag() {
        return dontSaveFlag;
    }

    public void setDontSaveFlag(String dontSaveFlag) {
        this.dontSaveFlag = dontSaveFlag;
    }

    public String getAuditHistory() {
        return auditHistory;
    }

    public void setAuditHistory(String auditHistory) {
        this.auditHistory = auditHistory;
    }

    public String getOriginalunidkey() {
        return originalunidkey;
    }

    public void setOriginalunidkey(String originalunidkey) {
        this.originalunidkey = originalunidkey;
    }

    public String getEditHistory() {
        return editHistory;
    }

    public void setEditHistory(String editHistory) {
        this.editHistory = editHistory;
    }

    public LocalDateTime getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(LocalDateTime dtCreated) {
        this.dtCreated = dtCreated;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(String pageTotal) {
        this.pageTotal = pageTotal;
    }

    public String getPageNr() {
        return pageNr;
    }

    public void setPageNr(String pageNr) {
        this.pageNr = pageNr;
    }

    public String getCrp2Formnumber() {
        return crp2Formnumber;
    }

    public void setCrp2Formnumber(String crp2Formnumber) {
        this.crp2Formnumber = crp2Formnumber;
    }

    public LocalDateTime getCrLpsSigndate() {
        return crLpsSigndate;
    }

    public void setCrLpsSigndate(LocalDateTime crLpsSigndate) {
        this.crLpsSigndate = crLpsSigndate;
    }

    public String getCrLpssignname() {
        return crLpssignname;
    }

    public void setCrLpssignname(String crLpssignname) {
        this.crLpssignname = crLpssignname;
    }

    public LocalDateTime getCrLpaSigndate() {
        return crLpaSigndate;
    }

    public void setCrLpaSigndate(LocalDateTime crLpaSigndate) {
        this.crLpaSigndate = crLpaSigndate;
    }

    public String getCrLpaSignname() {
        return crLpaSignname;
    }

    public void setCrLpaSignname(String crLpaSignname) {
        this.crLpaSignname = crLpaSignname;
    }

    public String getCrp2Followup() {
        return crp2Followup;
    }

    public void setCrp2Followup(String crp2Followup) {
        this.crp2Followup = crp2Followup;
    }

    public String getZ13() {
        return z13;
    }

    public void setZ13(String z13) {
        this.z13 = z13;
    }

    public String getCrp2Contactsummary() {
        return crp2Contactsummary;
    }

    public void setCrp2Contactsummary(String crp2Contactsummary) {
        this.crp2Contactsummary = crp2Contactsummary;
    }

    public String getZ12() {
        return z12;
    }

    public void setZ12(String z12) {
        this.z12 = z12;
    }

    public String getCrp2Postcomments() {
        return crp2Postcomments;
    }

    public void setCrp2Postcomments(String crp2Postcomments) {
        this.crp2Postcomments = crp2Postcomments;
    }

    public String getZ14() {
        return z14;
    }

    public void setZ14(String z14) {
        this.z14 = z14;
    }

    public String getCrp2Precomments() {
        return crp2Precomments;
    }

    public void setCrp2Precomments(String crp2Precomments) {
        this.crp2Precomments = crp2Precomments;
    }

    public String getZ11() {
        return z11;
    }

    public void setZ11(String z11) {
        this.z11 = z11;
    }

    public String getpPosthowcontact() {
        return pPosthowcontact;
    }

    public void setpPosthowcontact(String pPosthowcontact) {
        this.pPosthowcontact = pPosthowcontact;
    }

    public String getCrp2Posthowcontact() {
        return crp2Posthowcontact;
    }

    public void setCrp2Posthowcontact(String crp2Posthowcontact) {
        this.crp2Posthowcontact = crp2Posthowcontact;
    }

    public String getpPrehowcontact() {
        return pPrehowcontact;
    }

    public void setpPrehowcontact(String pPrehowcontact) {
        this.pPrehowcontact = pPrehowcontact;
    }

    public String getCrp2Prehowcontact() {
        return crp2Prehowcontact;
    }

    public void setCrp2Prehowcontact(String crp2Prehowcontact) {
        this.crp2Prehowcontact = crp2Prehowcontact;
    }

    public String getCrp2Postcontact() {
        return crp2Postcontact;
    }

    public void setCrp2Postcontact(String crp2Postcontact) {
        this.crp2Postcontact = crp2Postcontact;
    }

    public LocalDate getCrp2Postdate() {
        return crp2Postdate;
    }

    public void setCrp2Postdate(LocalDate crp2Postdate) {
        this.crp2Postdate = crp2Postdate;
    }

    public String getCrp2Precontact() {
        return crp2Precontact;
    }

    public void setCrp2Precontact(String crp2Precontact) {
        this.crp2Precontact = crp2Precontact;
    }

    public LocalDate getCrp2Predate() {
        return crp2Predate;
    }

    public void setCrp2Predate(LocalDate crp2Predate) {
        this.crp2Predate = crp2Predate;
    }

    public String getCrp2Detaildescription() {
        return crp2Detaildescription;
    }

    public void setCrp2Detaildescription(String crp2Detaildescription) {
        this.crp2Detaildescription = crp2Detaildescription;
    }

    public String getzZUndr1() {
        return zUndr1;
    }

    public void setzZUndr1(String zUndr1) {
        this.zUndr1 = zUndr1;
    }

    public Integer getCrp2FacilityNumber() {
        return crp2FacilityNumber;
    }

    public void setCrp2FacilityNumber(Integer crp2FacilityNumber) {
        this.crp2FacilityNumber = crp2FacilityNumber;
    }

    public String getCrp2FacilityType() {
        return crp2FacilityType;
    }

    public void setCrp2FacilityType(String crp2FacilityType) {
        this.crp2FacilityType = crp2FacilityType;
    }

    public String getCrp2FacilityName() {
        return crp2FacilityName;
    }

    public void setCrp2FacilityName(String crp2FacilityName) {
        this.crp2FacilityName = crp2FacilityName;
    }

    public String getCrp2Controlnumber() {
        return crp2Controlnumber;
    }

    public void setCrp2Controlnumber(String crp2Controlnumber) {
        this.crp2Controlnumber = crp2Controlnumber;
    }

    public LocalDate getCrp2Visitduedate() {
        return crp2Visitduedate;
    }

    public void setCrp2Visitduedate(LocalDate crp2Visitduedate) {
        this.crp2Visitduedate = crp2Visitduedate;
    }

    public String getCrp2Prioritynr() {
        return crp2Prioritynr;
    }

    public void setCrp2Prioritynr(String crp2Prioritynr) {
        this.crp2Prioritynr = crp2Prioritynr;
    }

    public String getCrp2Priority() {
        return crp2Priority;
    }

    public void setCrp2Priority(String crp2Priority) {
        this.crp2Priority = crp2Priority;
    }

    public String getDoZip2() {
        return doZip2;
    }

    public void setDoZip2(String doZip2) {
        this.doZip2 = doZip2;
    }

    public String getDoState2() {
        return doState2;
    }

    public void setDoState2(String doState2) {
        this.doState2 = doState2;
    }

    public String getDoCity2() {
        return doCity2;
    }

    public void setDoCity2(String doCity2) {
        this.doCity2 = doCity2;
    }

    public String getDoAddress2() {
        return doAddress2;
    }

    public void setDoAddress2(String doAddress2) {
        this.doAddress2 = doAddress2;
    }

    public String getDoName2() {
        return doName2;
    }

    public void setDoName2(String doName2) {
        this.doName2 = doName2;
    }

    public String getReportname2() {
        return reportname2;
    }

    public void setReportname2(String reportname2) {
        this.reportname2 = reportname2;
    }

    public String getPageTotal1() {
        return pageTotal1;
    }

    public void setPageTotal1(String pageTotal1) {
        this.pageTotal1 = pageTotal1;
    }

    public String getPageNr1() {
        return pageNr1;
    }

    public void setPageNr1(String pageNr1) {
        this.pageNr1 = pageNr1;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public String getCrTime() {
        return crTime;
    }

    public void setCrTime(String crTime) {
        this.crTime = crTime;
    }

    public LocalDate getCrDate() {
        return crDate;
    }

    public void setCrDate(LocalDate crDate) {
        this.crDate = crDate;
    }

    public String getCrReceivedby() {
        return crReceivedby;
    }

    public void setCrReceivedby(String crReceivedby) {
        this.crReceivedby = crReceivedby;
    }

    public String getCrAllegation7() {
        return crAllegation7;
    }

    public void setCrAllegation7(String crAllegation7) {
        this.crAllegation7 = crAllegation7;
    }

    public String getZ7() {
        return z7;
    }

    public void setZ7(String z7) {
        this.z7 = z7;
    }

    public String getUndrApl7() {
        return undrApl7;
    }

    public void setUndrApl7(String undrApl7) {
        this.undrApl7 = undrApl7;
    }

    public String getCrCode7() {
        return crCode7;
    }

    public void setCrCode7(String crCode7) {
        this.crCode7 = crCode7;
    }

    public String getCrAllegation6() {
        return crAllegation6;
    }

    public void setCrAllegation6(String crAllegation6) {
        this.crAllegation6 = crAllegation6;
    }

    public String getZ6() {
        return z6;
    }

    public void setZ6(String z6) {
        this.z6 = z6;
    }

    public String getUndrApl6() {
        return undrApl6;
    }

    public void setUndrApl6(String undrApl6) {
        this.undrApl6 = undrApl6;
    }

    public String getCrCode6() {
        return crCode6;
    }

    public void setCrCode6(String crCode6) {
        this.crCode6 = crCode6;
    }

    public String getCrAllegation5() {
        return crAllegation5;
    }

    public void setCrAllegation5(String crAllegation5) {
        this.crAllegation5 = crAllegation5;
    }

    public String getZ5() {
        return z5;
    }

    public void setZ5(String z5) {
        this.z5 = z5;
    }

    public String getUndrApl5() {
        return undrApl5;
    }

    public void setUndrApl5(String undrApl5) {
        this.undrApl5 = undrApl5;
    }

    public String getCrCode5() {
        return crCode5;
    }

    public void setCrCode5(String crCode5) {
        this.crCode5 = crCode5;
    }

    public String getCrAllegation4() {
        return crAllegation4;
    }

    public void setCrAllegation4(String crAllegation4) {
        this.crAllegation4 = crAllegation4;
    }

    public String getZ4() {
        return z4;
    }

    public void setZ4(String z4) {
        this.z4 = z4;
    }

    public String getUndrApl4() {
        return undrApl4;
    }

    public void setUndrApl4(String undrApl4) {
        this.undrApl4 = undrApl4;
    }

    public String getCrCode4() {
        return crCode4;
    }

    public void setCrCode4(String crCode4) {
        this.crCode4 = crCode4;
    }

    public String getCrAllegation3() {
        return crAllegation3;
    }

    public void setCrAllegation3(String crAllegation3) {
        this.crAllegation3 = crAllegation3;
    }

    public String getZ3() {
        return z3;
    }

    public void setZ3(String z3) {
        this.z3 = z3;
    }

    public String getUndrApl3() {
        return undrApl3;
    }

    public void setUndrApl3(String undrApl3) {
        this.undrApl3 = undrApl3;
    }

    public String getCrCode3() {
        return crCode3;
    }

    public void setCrCode3(String crCode3) {
        this.crCode3 = crCode3;
    }

    public String getCrAllegation2() {
        return crAllegation2;
    }

    public void setCrAllegation2(String crAllegation2) {
        this.crAllegation2 = crAllegation2;
    }

    public String getZ2() {
        return z2;
    }

    public void setZ2(String z2) {
        this.z2 = z2;
    }

    public String getUndrApl2() {
        return undrApl2;
    }

    public void setUndrApl2(String undrApl2) {
        this.undrApl2 = undrApl2;
    }

    public String getCrCode2() {
        return crCode2;
    }

    public void setCrCode2(String crCode2) {
        this.crCode2 = crCode2;
    }

    public String getCrAllegation1() {
        return crAllegation1;
    }

    public void setCrAllegation1(String crAllegation1) {
        this.crAllegation1 = crAllegation1;
    }

    public String getZ1() {
        return z1;
    }

    public void setZ1(String z1) {
        this.z1 = z1;
    }

    public String getUndrApl1() {
        return undrApl1;
    }

    public void setUndrApl1(String undrApl1) {
        this.undrApl1 = undrApl1;
    }

    public String getCrCode1() {
        return crCode1;
    }

    public void setCrCode1(String crCode1) {
        this.crCode1 = crCode1;
    }

    public String getCrAllegation() {
        return crAllegation;
    }

    public void setCrAllegation(String crAllegation) {
        this.crAllegation = crAllegation;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public String getUndrApl() {
        return undrApl;
    }

    public void setUndrApl(String undrApl) {
        this.undrApl = undrApl;
    }

    public String getCrCode() {
        return crCode;
    }

    public void setCrCode(String crCode) {
        this.crCode = crCode;
    }

    public String getFacilityTelephone() {
        return facilityTelephone;
    }

    public void setFacilityTelephone(String facilityTelephone) {
        this.facilityTelephone = facilityTelephone;
    }

    public String getFacilityZip() {
        return facilityZip;
    }

    public void setFacilityZip(String facilityZip) {
        this.facilityZip = facilityZip;
    }

    public String getFacilityCity() {
        return facilityCity;
    }

    public void setFacilityCity(String facilityCity) {
        this.facilityCity = facilityCity;
    }

    public String getFacilityAddress() {
        return facilityAddress;
    }

    public void setFacilityAddress(String facilityAddress) {
        this.facilityAddress = facilityAddress;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityNumberText() {
        return facilityNumberText;
    }

    public void setFacilityNumberText(String facilityNumberText) {
        this.facilityNumberText = facilityNumberText;
    }

    public BigDecimal getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(BigDecimal facilityType) {
        this.facilityType = facilityType;
    }

    public String getCrControlnumber() {
        return crControlnumber;
    }

    public void setCrControlnumber(String crControlnumber) {
        this.crControlnumber = crControlnumber;
    }

    public LocalDate getCrVisitduedate() {
        return crVisitduedate;
    }

    public void setCrVisitduedate(LocalDate crVisitduedate) {
        this.crVisitduedate = crVisitduedate;
    }

    public String getCrDoname() {
        return crDoname;
    }

    public void setCrDoname(String crDoname) {
        this.crDoname = crDoname;
    }

    public String getCrPrioritynr() {
        return crPrioritynr;
    }

    public void setCrPrioritynr(String crPrioritynr) {
        this.crPrioritynr = crPrioritynr;
    }

    public String getpComplainantanonymous() {
        return pComplainantanonymous;
    }

    public void setpComplainantanonymous(String pComplainantanonymous) {
        this.pComplainantanonymous = pComplainantanonymous;
    }

    public String getCrComplainantanonymous() {
        return crComplainantanonymous;
    }

    public void setCrComplainantanonymous(String crComplainantanonymous) {
        this.crComplainantanonymous = crComplainantanonymous;
    }

    public String getpAbusereportfiled() {
        return pAbusereportfiled;
    }

    public void setpAbusereportfiled(String pAbusereportfiled) {
        this.pAbusereportfiled = pAbusereportfiled;
    }

    public String getCrAbusereportfiled() {
        return crAbusereportfiled;
    }

    public void setCrAbusereportfiled(String crAbusereportfiled) {
        this.crAbusereportfiled = crAbusereportfiled;
    }

    public String getCrComplainantrelation() {
        return crComplainantrelation;
    }

    public void setCrComplainantrelation(String crComplainantrelation) {
        this.crComplainantrelation = crComplainantrelation;
    }

    public String getCrComplainantnightphone() {
        return crComplainantnightphone;
    }

    public void setCrComplainantnightphone(String crComplainantnightphone) {
        this.crComplainantnightphone = crComplainantnightphone;
    }

    public String getCrComplainantdayphone() {
        return crComplainantdayphone;
    }

    public void setCrComplainantdayphone(String crComplainantdayphone) {
        this.crComplainantdayphone = crComplainantdayphone;
    }

    public String getCrComplainantzip() {
        return crComplainantzip;
    }

    public void setCrComplainantzip(String crComplainantzip) {
        this.crComplainantzip = crComplainantzip;
    }

    public String getCrComplainantcity() {
        return crComplainantcity;
    }

    public void setCrComplainantcity(String crComplainantcity) {
        this.crComplainantcity = crComplainantcity;
    }

    public String getCrComplainantaddress() {
        return crComplainantaddress;
    }

    public void setCrComplainantaddress(String crComplainantaddress) {
        this.crComplainantaddress = crComplainantaddress;
    }

    public String getCrComplainantname() {
        return crComplainantname;
    }

    public void setCrComplainantname(String crComplainantname) {
        this.crComplainantname = crComplainantname;
    }

    public String getpHowreported() {
        return pHowreported;
    }

    public void setpHowreported(String pHowreported) {
        this.pHowreported = pHowreported;
    }

    public String getCrHowreported() {
        return crHowreported;
    }

    public void setCrHowreported(String crHowreported) {
        this.crHowreported = crHowreported;
    }

    public String getDoZip() {
        return doZip;
    }

    public void setDoZip(String doZip) {
        this.doZip = doZip;
    }

    public String getDoState() {
        return doState;
    }

    public void setDoState(String doState) {
        this.doState = doState;
    }

    public String getDoCity() {
        return doCity;
    }

    public void setDoCity(String doCity) {
        this.doCity = doCity;
    }

    public String getDoAddress() {
        return doAddress;
    }

    public void setDoAddress(String doAddress) {
        this.doAddress = doAddress;
    }

    public String getDoName() {
        return doName;
    }

    public void setDoName(String doName) {
        this.doName = doName;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getReportname() {
        return reportname;
    }

    public void setReportname(String reportname) {
        this.reportname = reportname;
    }

    public String getLinkedPages() {
        return linkedPages;
    }

    public void setLinkedPages(String linkedPages) {
        this.linkedPages = linkedPages;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getFacilityNumNew() {
        return facilityNumNew;
    }

    public void setFacilityNumNew(String facilityNumNew) {
        this.facilityNumNew = facilityNumNew;
    }

    public String getOrgtoamdUnid() {
        return orgtoamdUnid;
    }

    public void setOrgtoamdUnid(String orgtoamdUnid) {
        this.orgtoamdUnid = orgtoamdUnid;
    }

    public String getAmendPrinted() {
        return amendPrinted;
    }

    public void setAmendPrinted(String amendPrinted) {
        this.amendPrinted = amendPrinted;
    }

    public String getTimesAmended() {
        return timesAmended;
    }

    public void setTimesAmended(String timesAmended) {
        this.timesAmended = timesAmended;
    }

    public String getAmendFlag() {
        return amendFlag;
    }

    public void setAmendFlag(String amendFlag) {
        this.amendFlag = amendFlag;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getDoNumber() {
        return doNumber;
    }

    public void setDoNumber(String doNumber) {
        this.doNumber = doNumber;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getLinkCount() {
        return linkCount;
    }

    public void setLinkCount(String linkCount) {
        this.linkCount = linkCount;
    }

    public String getShowLinkedPages() {
        return showLinkedPages;
    }

    public void setShowLinkedPages(String showLinkedPages) {
        this.showLinkedPages = showLinkedPages;
    }

    public String getFasLpaCode() {
        return fasLpaCode;
    }

    public void setFasLpaCode(String fasLpaCode) {
        this.fasLpaCode = fasLpaCode;
    }

    public String getFacilityNumber() {
        return facilityNumber;
    }

    public void setFacilityNumber(String facilityNumber) {
        this.facilityNumber = facilityNumber;
    }

    public String getAllowableeditors() {
        return allowableeditors;
    }

    public void setAllowableeditors(String allowableeditors) {
        this.allowableeditors = allowableeditors;
    }

    public String getAuthornames() {
        return authornames;
    }

    public void setAuthornames(String authornames) {
        this.authornames = authornames;
    }

    public String getLpaCode() {
        return lpaCode;
    }

    public void setLpaCode(String lpaCode) {
        this.lpaCode = lpaCode;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getFormAbrievation() {
        return formAbrievation;
    }

    public void setFormAbrievation(String formAbrievation) {
        this.formAbrievation = formAbrievation;
    }

    public String getSignOffDate() {
        return signOffDate;
    }

    public void setSignOffDate(String signOffDate) {
        this.signOffDate = signOffDate;
    }

    public String getAiActionTaken() {
        return aiActionTaken;
    }

    public void setAiActionTaken(String aiActionTaken) {
        this.aiActionTaken = aiActionTaken;
    }

    public String getAiTotalHours() {
        return aiTotalHours;
    }

    public void setAiTotalHours(String aiTotalHours) {
        this.aiTotalHours = aiTotalHours;
    }

    public String getAiNumVisits() {
        return aiNumVisits;
    }

    public void setAiNumVisits(String aiNumVisits) {
        this.aiNumVisits = aiNumVisits;
    }

    public String getAuditAcptDate() {
        return auditAcptDate;
    }

    public void setAuditAcptDate(String auditAcptDate) {
        this.auditAcptDate = auditAcptDate;
    }

    public String getpAuditAccept() {
        return pAuditAccept;
    }

    public void setpAuditAccept(String pAuditAccept) {
        this.pAuditAccept = pAuditAccept;
    }

    public String getAuditAccept() {
        return auditAccept;
    }

    public void setAuditAccept(String auditAccept) {
        this.auditAccept = auditAccept;
    }

    public String getAuditRfrDate() {
        return auditRfrDate;
    }

    public void setAuditRfrDate(String auditRfrDate) {
        this.auditRfrDate = auditRfrDate;
    }

    public String getpAuditRefer() {
        return pAuditRefer;
    }

    public void setpAuditRefer(String pAuditRefer) {
        this.pAuditRefer = pAuditRefer;
    }

    public String getAuditRefer() {
        return auditRefer;
    }

    public void setAuditRefer(String auditRefer) {
        this.auditRefer = auditRefer;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    public String getpFollowUp() {
        return pFollowUp;
    }

    public void setpFollowUp(String pFollowUp) {
        this.pFollowUp = pFollowUp;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public String getAdminTotalHours() {
        return adminTotalHours;
    }

    public void setAdminTotalHours(String adminTotalHours) {
        this.adminTotalHours = adminTotalHours;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public String getNumOfVisits() {
        return numOfVisits;
    }

    public void setNumOfVisits(String numOfVisits) {
        this.numOfVisits = numOfVisits;
    }

    public String getcHomeType() {
        return cHomeType;
    }

    public void setcHomeType(String cHomeType) {
        this.cHomeType = cHomeType;
    }

    public String getcHomeZip() {
        return cHomeZip;
    }

    public void setcHomeZip(String cHomeZip) {
        this.cHomeZip = cHomeZip;
    }

    public String getcHomeTelephone() {
        return cHomeTelephone;
    }

    public void setcHomeTelephone(String cHomeTelephone) {
        this.cHomeTelephone = cHomeTelephone;
    }

    public String getcHomeCity() {
        return cHomeCity;
    }

    public void setcHomeCity(String cHomeCity) {
        this.cHomeCity = cHomeCity;
    }

    public String getcHomeLicensee() {
        return cHomeLicensee;
    }

    public void setcHomeLicensee(String cHomeLicensee) {
        this.cHomeLicensee = cHomeLicensee;
    }

    public String getcHomeAddress() {
        return cHomeAddress;
    }

    public void setcHomeAddress(String cHomeAddress) {
        this.cHomeAddress = cHomeAddress;
    }

    public String getcHomeName() {
        return cHomeName;
    }

    public void setcHomeName(String cHomeName) {
        this.cHomeName = cHomeName;
    }

    public String getcHomeNumber() {
        return cHomeNumber;
    }

    public void setcHomeNumber(String cHomeNumber) {
        this.cHomeNumber = cHomeNumber;
    }

    public String getCrLpsAppname() {
        return crLpsAppname;
    }

    public void setCrLpsAppname(String crLpsAppname) {
        this.crLpsAppname = crLpsAppname;
    }

    public LocalDateTime getCrLpsAppdate() {
        return crLpsAppdate;
    }

    public void setCrLpsAppdate(LocalDateTime crLpsAppdate) {
        this.crLpsAppdate = crLpsAppdate;
    }

    public String getCrLpsName() {
        return crLpsName;
    }

    public void setCrLpsName(String crLpsName) {
        this.crLpsName = crLpsName;
    }

    public String getCrLpaSubname() {
        return crLpaSubname;
    }

    public void setCrLpaSubname(String crLpaSubname) {
        this.crLpaSubname = crLpaSubname;
    }

    public LocalDateTime getCrLpaSubdate() {
        return crLpaSubdate;
    }

    public void setCrLpaSubdate(LocalDateTime crLpaSubdate) {
        this.crLpaSubdate = crLpaSubdate;
    }

    public String getCrLpaName() {
        return crLpaName;
    }

    public void setCrLpaName(String crLpaName) {
        this.crLpaName = crLpaName;
    }

    public String getCrStatus() {
        return crStatus;
    }

    public void setCrStatus(String crStatus) {
        this.crStatus = crStatus;
    }

    public LocalDateTime getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(LocalDateTime dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public String getLpaAssigned() {
        return lpaAssigned;
    }

    public void setLpaAssigned(String lpaAssigned) {
        this.lpaAssigned = lpaAssigned;
    }

    public LocalDateTime getDateSigned() {
        return dateSigned;
    }

    public void setDateSigned(LocalDateTime dateSigned) {
        this.dateSigned = dateSigned;
    }

    public String getCreAuthornames() {
        return creAuthornames;
    }

    public void setCreAuthornames(String creAuthornames) {
        this.creAuthornames = creAuthornames;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(String verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getAgencySelection1() {
        return agencySelection1;
    }

    public void setAgencySelection1(String agencySelection1) {
        this.agencySelection1 = agencySelection1;
    }

    public String getAgencySelection() {
        return agencySelection;
    }

    public void setAgencySelection(String agencySelection) {
        this.agencySelection = agencySelection;
    }

    public String getFromIntake() {
        return fromIntake;
    }

    public void setFromIntake(String fromIntake) {
        this.fromIntake = fromIntake;
    }

    public BigDecimal getFacDoNbr() {
        return facDoNbr;
    }

    public void setFacDoNbr(BigDecimal facDoNbr) {
        this.facDoNbr = facDoNbr;
    }

    public String getTendaymissed() {
        return tendaymissed;
    }

    public void setTendaymissed(String tendaymissed) {
        this.tendaymissed = tendaymissed;
    }

    public String getTendayvisitFlag() {
        return tendayvisitFlag;
    }

    public void setTendayvisitFlag(String tendayvisitFlag) {
        this.tendayvisitFlag = tendayvisitFlag;
    }

    public BigDecimal getFacPrimaryNbr() {
        return facPrimaryNbr;
    }

    public void setFacPrimaryNbr(BigDecimal facPrimaryNbr) {
        this.facPrimaryNbr = facPrimaryNbr;
    }

    public String getFaslpacode1() {
        return faslpacode1;
    }

    public void setFaslpacode1(String faslpacode1) {
        this.faslpacode1 = faslpacode1;
    }

    public String getRejectComments() {
        return rejectComments;
    }

    public void setRejectComments(String rejectComments) {
        this.rejectComments = rejectComments;
    }

    public String getAncs() {
        return ancs;
    }

    public void setAncs(String ancs) {
        this.ancs = ancs;
    }

    public String getComplaintsumunid() {
        return complaintsumunid;
    }

    public void setComplaintsumunid(String complaintsumunid) {
        this.complaintsumunid = complaintsumunid;
    }

    public String getComplaintunid() {
        return complaintunid;
    }

    public void setComplaintunid(String complaintunid) {
        this.complaintunid = complaintunid;
    }

    public String getSearchAddress() {
        return searchAddress;
    }

    public void setSearchAddress(String searchAddress) {
        this.searchAddress = searchAddress;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchNumber() {
        return searchNumber;
    }

    public void setSearchNumber(String searchNumber) {
        this.searchNumber = searchNumber;
    }

    public String getCreateddbname() {
        return createddbname;
    }

    public void setCreateddbname(String createddbname) {
        this.createddbname = createddbname;
    }

    public String getFacilityState() {
        return facilityState;
    }

    public void setFacilityState(String facilityState) {
        this.facilityState = facilityState;
    }

    public String getFacilityAdmin() {
        return facilityAdmin;
    }

    public void setFacilityAdmin(String facilityAdmin) {
        this.facilityAdmin = facilityAdmin;
    }

    public LocalDate getCrVisitDate() {
        return crVisitDate;
    }

    public void setCrVisitDate(LocalDate crVisitDate) {
        this.crVisitDate = crVisitDate;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getCrUnSub7() {
        return crUnSub7;
    }

    public void setCrUnSub7(String crUnSub7) {
        this.crUnSub7 = crUnSub7;
    }

    public String getCrInSub7() {
        return crInSub7;
    }

    public void setCrInSub7(String crInSub7) {
        this.crInSub7 = crInSub7;
    }

    public String getCrSub7() {
        return crSub7;
    }

    public void setCrSub7(String crSub7) {
        this.crSub7 = crSub7;
    }

    public String getCrUnSub6() {
        return crUnSub6;
    }

    public void setCrUnSub6(String crUnSub6) {
        this.crUnSub6 = crUnSub6;
    }

    public String getCrInSub6() {
        return crInSub6;
    }

    public void setCrInSub6(String crInSub6) {
        this.crInSub6 = crInSub6;
    }

    public String getCrSub6() {
        return crSub6;
    }

    public void setCrSub6(String crSub6) {
        this.crSub6 = crSub6;
    }

    public String getCrUnSub5() {
        return crUnSub5;
    }

    public void setCrUnSub5(String crUnSub5) {
        this.crUnSub5 = crUnSub5;
    }

    public String getCrInSub5() {
        return crInSub5;
    }

    public void setCrInSub5(String crInSub5) {
        this.crInSub5 = crInSub5;
    }

    public String getCrSub5() {
        return crSub5;
    }

    public void setCrSub5(String crSub5) {
        this.crSub5 = crSub5;
    }

    public String getCrUnSub4() {
        return crUnSub4;
    }

    public void setCrUnSub4(String crUnSub4) {
        this.crUnSub4 = crUnSub4;
    }

    public String getCrInSub4() {
        return crInSub4;
    }

    public void setCrInSub4(String crInSub4) {
        this.crInSub4 = crInSub4;
    }

    public String getCrSub4() {
        return crSub4;
    }

    public void setCrSub4(String crSub4) {
        this.crSub4 = crSub4;
    }

    public String getCrUnSub3() {
        return crUnSub3;
    }

    public void setCrUnSub3(String crUnSub3) {
        this.crUnSub3 = crUnSub3;
    }

    public String getCrInSub3() {
        return crInSub3;
    }

    public void setCrInSub3(String crInSub3) {
        this.crInSub3 = crInSub3;
    }

    public String getCrSub3() {
        return crSub3;
    }

    public void setCrSub3(String crSub3) {
        this.crSub3 = crSub3;
    }

    public String getCrUnSub2() {
        return crUnSub2;
    }

    public void setCrUnSub2(String crUnSub2) {
        this.crUnSub2 = crUnSub2;
    }

    public String getCrInSub2() {
        return crInSub2;
    }

    public void setCrInSub2(String crInSub2) {
        this.crInSub2 = crInSub2;
    }

    public String getCrSub2() {
        return crSub2;
    }

    public void setCrSub2(String crSub2) {
        this.crSub2 = crSub2;
    }

    public String getCrUnSub1() {
        return crUnSub1;
    }

    public void setCrUnSub1(String crUnSub1) {
        this.crUnSub1 = crUnSub1;
    }

    public String getCrInSub1() {
        return crInSub1;
    }

    public void setCrInSub1(String crInSub1) {
        this.crInSub1 = crInSub1;
    }

    public String getCrSub1() {
        return crSub1;
    }

    public void setCrSub1(String crSub1) {
        this.crSub1 = crSub1;
    }

    public String getCrUnSub() {
        return crUnSub;
    }

    public void setCrUnSub(String crUnSub) {
        this.crUnSub = crUnSub;
    }

    public String getCrInSub() {
        return crInSub;
    }

    public void setCrInSub(String crInSub) {
        this.crInSub = crInSub;
    }

    public String getCrSub() {
        return crSub;
    }

    public void setCrSub(String crSub) {
        this.crSub = crSub;
    }

    public String getCrPriority() {
        return crPriority;
    }

    public void setCrPriority(String crPriority) {
        this.crPriority = crPriority;
    }

    public String getCrLPSRejectComments() {
        return crLPSRejectComments;
    }

    public void setCrLPSRejectComments(String crLPSRejectComments) {
        this.crLPSRejectComments = crLPSRejectComments;
    }

    public String getCrLPSRejName() {
        return crLPSRejName;
    }

    public void setCrLPSRejName(String crLPSRejName) {
        this.crLPSRejName = crLPSRejName;
    }

    public String getCrLPSRejDate() {
        return crLPSRejDate;
    }

    public void setCrLPSRejDate(String crLPSRejDate) {
        this.crLPSRejDate = crLPSRejDate;
    }

    public String getApproveByHistory() {
        return approveByHistory;
    }

    public void setApproveByHistory(String approveByHistory) {
        this.approveByHistory = approveByHistory;
    }

    public LocalDateTime getApproveDateHistory() {
        return approveDateHistory;
    }

    public void setApproveDateHistory(LocalDateTime approveDateHistory) {
        this.approveDateHistory = approveDateHistory;
    }

    public String getApproveHistory() {
        return approveHistory;
    }

    public void setApproveHistory(String approveHistory) {
        this.approveHistory = approveHistory;
    }

    public String getAssignByHistory() {
        return assignByHistory;
    }

    public void setAssignByHistory(String assignByHistory) {
        this.assignByHistory = assignByHistory;
    }

    public LocalDateTime getAssignDateHistory() {
        return assignDateHistory;
    }

    public void setAssignDateHistory(LocalDateTime assignDateHistory) {
        this.assignDateHistory = assignDateHistory;
    }

    public String getAssignHistory() {
        return assignHistory;
    }

    public void setAssignHistory(String assignHistory) {
        this.assignHistory = assignHistory;
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
        return originalunidkey;
    }
}
