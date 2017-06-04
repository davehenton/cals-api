package gov.ca.cwds.cals.persistence.model.cms;

import gov.ca.cwds.data.persistence.PersistentObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S3437") //LocalDate is serializable
@Entity
@javax.persistence.Table(name = "CHLD_CLT")
public class ChildClient implements PersistentObject {
    private static final long serialVersionUID = 7067130319350286796L;

    private String fcEligtB;
    private String edonflInd;
    private String hlonflInd;
    private String curntCsid;
    private Short dthCirc;
    private String volPlcdoc;
    private String fc2ApltB;
    private String icwaElgcd;
    private String medeligtB;
    private String mnrmomInd;
    private String prgTrmtB;
    private String ptrnIntB;
    private Integer sawsCsNo;
    private String saw1ApltB;
    private String ssiSspind;
    private String lstUpdId;
    private Timestamp lstUpdTs;
    private String fkclientT;
    private String acqEdDsc;
    private String acqHthdsc;
    private String hepdocOld;
    private String prtlimInd;
    private String indianInd;
    private String preadptCd;
    private Short adptAge;
    private String adoptblCd;
    private String drmshepdoc;
    private String bhistInd;
    private String sfsurbInd;
    private String awolAbCd;
    private String trbaNotB;
    private String disabldCd;
    private String fstampInd;
    private LocalDate fstampDt;
    private LocalDate sijIntDt;
    private LocalDate ssiScrDt;
    private String clgInd;
    private String psvocInd;
    private String tcadptInd;
    private LocalDate tcadptDt;
    private String icadslInd;
    private String icadsrInd;

    @Basic
    @javax.persistence.Column(name = "FC_ELIGT_B", nullable = false, length = 1)
    public String getFcEligtB() {
        return fcEligtB;
    }

    public void setFcEligtB(String fcEligtB) {
        this.fcEligtB = fcEligtB;
    }

    @Basic
    @javax.persistence.Column(name = "EDONFL_IND", nullable = false, length = 1)
    public String getEdonflInd() {
        return edonflInd;
    }

    public void setEdonflInd(String edonflInd) {
        this.edonflInd = edonflInd;
    }

    @Basic
    @javax.persistence.Column(name = "HLONFL_IND", nullable = false, length = 1)
    public String getHlonflInd() {
        return hlonflInd;
    }

    public void setHlonflInd(String hlonflInd) {
        this.hlonflInd = hlonflInd;
    }

    @Basic
    @javax.persistence.Column(name = "CURNT_CSID", nullable = true, length = 10)
    public String getCurntCsid() {
        return curntCsid;
    }

    public void setCurntCsid(String curntCsid) {
        this.curntCsid = curntCsid;
    }

    @Basic
    @javax.persistence.Column(name = "DTH_CIRC", nullable = false)
    public Short getDthCirc() {
        return dthCirc;
    }

    public void setDthCirc(Short dthCirc) {
        this.dthCirc = dthCirc;
    }

    @Basic
    @javax.persistence.Column(name = "VOL_PLCDOC", nullable = true, length = 10)
    public String getVolPlcdoc() {
        return volPlcdoc;
    }

    public void setVolPlcdoc(String volPlcdoc) {
        this.volPlcdoc = volPlcdoc;
    }

    @Basic
    @javax.persistence.Column(name = "FC2_APLT_B", nullable = false, length = 1)
    public String getFc2ApltB() {
        return fc2ApltB;
    }

    public void setFc2ApltB(String fc2ApltB) {
        this.fc2ApltB = fc2ApltB;
    }

    @Basic
    @javax.persistence.Column(name = "ICWA_ELGCD", nullable = false, length = 1)
    public String getIcwaElgcd() {
        return icwaElgcd;
    }

    public void setIcwaElgcd(String icwaElgcd) {
        this.icwaElgcd = icwaElgcd;
    }

    @Basic
    @javax.persistence.Column(name = "MEDELIGT_B", nullable = false, length = 1)
    public String getMedeligtB() {
        return medeligtB;
    }

    public void setMedeligtB(String medeligtB) {
        this.medeligtB = medeligtB;
    }

    @Basic
    @javax.persistence.Column(name = "MNRMOM_IND", nullable = false, length = 1)
    public String getMnrmomInd() {
        return mnrmomInd;
    }

    public void setMnrmomInd(String mnrmomInd) {
        this.mnrmomInd = mnrmomInd;
    }

    @Basic
    @javax.persistence.Column(name = "PRG_TRMT_B", nullable = false, length = 1)
    public String getPrgTrmtB() {
        return prgTrmtB;
    }

    public void setPrgTrmtB(String prgTrmtB) {
        this.prgTrmtB = prgTrmtB;
    }

    @Basic
    @javax.persistence.Column(name = "PTRN_INT_B", nullable = false, length = 1)
    public String getPtrnIntB() {
        return ptrnIntB;
    }

    public void setPtrnIntB(String ptrnIntB) {
        this.ptrnIntB = ptrnIntB;
    }

    @Basic
    @javax.persistence.Column(name = "SAWS_CS_NO", nullable = false)
    public Integer getSawsCsNo() {
        return sawsCsNo;
    }

    public void setSawsCsNo(Integer sawsCsNo) {
        this.sawsCsNo = sawsCsNo;
    }

    @Basic
    @javax.persistence.Column(name = "SAW1APLT_B", nullable = false, length = 1)
    public String getSaw1ApltB() {
        return saw1ApltB;
    }

    public void setSaw1ApltB(String saw1ApltB) {
        this.saw1ApltB = saw1ApltB;
    }

    @Basic
    @javax.persistence.Column(name = "SSI_SSPIND", nullable = false, length = 1)
    public String getSsiSspind() {
        return ssiSspind;
    }

    public void setSsiSspind(String ssiSspind) {
        this.ssiSspind = ssiSspind;
    }

    @Basic
    @javax.persistence.Column(name = "LST_UPD_ID", nullable = false, length = 3)
    public String getLstUpdId() {
        return lstUpdId;
    }

    public void setLstUpdId(String lstUpdId) {
        this.lstUpdId = lstUpdId;
    }

    @Basic
    @javax.persistence.Column(name = "LST_UPD_TS", nullable = false)
    public Timestamp getLstUpdTs() {
        return lstUpdTs;
    }

    public void setLstUpdTs(Timestamp lstUpdTs) {
        this.lstUpdTs = lstUpdTs;
    }

    @Id
    @javax.persistence.Column(name = "FKCLIENT_T", nullable = false, length = 10)
    public String getFkclientT() {
        return fkclientT;
    }

    public void setFkclientT(String fkclientT) {
        this.fkclientT = fkclientT;
    }

    @Basic
    @javax.persistence.Column(name = "ACQ_ED_DSC", nullable = false, length = 254)
    public String getAcqEdDsc() {
        return acqEdDsc;
    }

    public void setAcqEdDsc(String acqEdDsc) {
        this.acqEdDsc = acqEdDsc;
    }

    @Basic
    @javax.persistence.Column(name = "ACQ_HTHDSC", nullable = false, length = 254)
    public String getAcqHthdsc() {
        return acqHthdsc;
    }

    public void setAcqHthdsc(String acqHthdsc) {
        this.acqHthdsc = acqHthdsc;
    }

    @Basic
    @javax.persistence.Column(name = "HEPDOC_OLD", nullable = true, length = 10)
    public String getHepdocOld() {
        return hepdocOld;
    }

    public void setHepdocOld(String hepdocOld) {
        this.hepdocOld = hepdocOld;
    }

    @Basic
    @javax.persistence.Column(name = "PRTLIM_IND", nullable = false, length = 1)
    public String getPrtlimInd() {
        return prtlimInd;
    }

    public void setPrtlimInd(String prtlimInd) {
        this.prtlimInd = prtlimInd;
    }

    @Basic
    @javax.persistence.Column(name = "INDIAN_IND", nullable = false, length = 1)
    public String getIndianInd() {
        return indianInd;
    }

    public void setIndianInd(String indianInd) {
        this.indianInd = indianInd;
    }

    @Basic
    @javax.persistence.Column(name = "PREADPT_CD", nullable = false, length = 1)
    public String getPreadptCd() {
        return preadptCd;
    }

    public void setPreadptCd(String preadptCd) {
        this.preadptCd = preadptCd;
    }

    @Basic
    @javax.persistence.Column(name = "ADPT_AGE", nullable = false)
    public Short getAdptAge() {
        return adptAge;
    }

    public void setAdptAge(Short adptAge) {
        this.adptAge = adptAge;
    }

    @Basic
    @javax.persistence.Column(name = "ADOPTBL_CD", nullable = false, length = 2)
    public String getAdoptblCd() {
        return adoptblCd;
    }

    public void setAdoptblCd(String adoptblCd) {
        this.adoptblCd = adoptblCd;
    }

    @Basic
    @javax.persistence.Column(name = "DRMSHEPDOC", nullable = true, length = 10)
    public String getDrmshepdoc() {
        return drmshepdoc;
    }

    public void setDrmshepdoc(String drmshepdoc) {
        this.drmshepdoc = drmshepdoc;
    }

    @Basic
    @javax.persistence.Column(name = "BHIST_IND", nullable = false, length = 1)
    public String getBhistInd() {
        return bhistInd;
    }

    public void setBhistInd(String bhistInd) {
        this.bhistInd = bhistInd;
    }

    @Basic
    @javax.persistence.Column(name = "SFSURB_IND", nullable = false, length = 1)
    public String getSfsurbInd() {
        return sfsurbInd;
    }

    public void setSfsurbInd(String sfsurbInd) {
        this.sfsurbInd = sfsurbInd;
    }

    @Basic
    @javax.persistence.Column(name = "AWOL_AB_CD", nullable = false, length = 1)
    public String getAwolAbCd() {
        return awolAbCd;
    }

    public void setAwolAbCd(String awolAbCd) {
        this.awolAbCd = awolAbCd;
    }

    @Basic
    @javax.persistence.Column(name = "TRBA_NOT_B", nullable = false, length = 1)
    public String getTrbaNotB() {
        return trbaNotB;
    }

    public void setTrbaNotB(String trbaNotB) {
        this.trbaNotB = trbaNotB;
    }

    @Basic
    @javax.persistence.Column(name = "DISABLD_CD", nullable = true, length = 1)
    public String getDisabldCd() {
        return disabldCd;
    }

    public void setDisabldCd(String disabldCd) {
        this.disabldCd = disabldCd;
    }

    @Basic
    @javax.persistence.Column(name = "FSTAMP_IND", nullable = false, length = 1)
    public String getFstampInd() {
        return fstampInd;
    }

    public void setFstampInd(String fstampInd) {
        this.fstampInd = fstampInd;
    }

    @Basic
    @javax.persistence.Column(name = "FSTAMP_DT", nullable = true)
    public LocalDate getFstampDt() {
        return fstampDt;
    }

    public void setFstampDt(LocalDate fstampDt) {
        this.fstampDt = fstampDt;
    }

    @Basic
    @javax.persistence.Column(name = "SIJ_INT_DT", nullable = true)
    public LocalDate getSijIntDt() {
        return sijIntDt;
    }

    public void setSijIntDt(LocalDate sijIntDt) {
        this.sijIntDt = sijIntDt;
    }

    @Basic
    @javax.persistence.Column(name = "SSI_SCR_DT", nullable = true)
    public LocalDate getSsiScrDt() {
        return ssiScrDt;
    }

    public void setSsiScrDt(LocalDate ssiScrDt) {
        this.ssiScrDt = ssiScrDt;
    }

    @Basic
    @javax.persistence.Column(name = "CLG_IND", nullable = true, length = 1)
    public String getClgInd() {
        return clgInd;
    }

    public void setClgInd(String clgInd) {
        this.clgInd = clgInd;
    }

    @Basic
    @javax.persistence.Column(name = "PSVOC_IND", nullable = true, length = 1)
    public String getPsvocInd() {
        return psvocInd;
    }

    public void setPsvocInd(String psvocInd) {
        this.psvocInd = psvocInd;
    }

    @Basic
    @javax.persistence.Column(name = "TCADPT_IND", nullable = false, length = 1)
    public String getTcadptInd() {
        return tcadptInd;
    }

    public void setTcadptInd(String tcadptInd) {
        this.tcadptInd = tcadptInd;
    }

    @Basic
    @javax.persistence.Column(name = "TCADPT_DT", nullable = true)
    public LocalDate getTcadptDt() {
        return tcadptDt;
    }

    public void setTcadptDt(LocalDate tcadptDt) {
        this.tcadptDt = tcadptDt;
    }

    @Basic
    @javax.persistence.Column(name = "ICADSL_IND", nullable = false, length = 1)
    public String getIcadslInd() {
        return icadslInd;
    }

    public void setIcadslInd(String icadslInd) {
        this.icadslInd = icadslInd;
    }

    @Basic
    @javax.persistence.Column(name = "ICADSR_IND", nullable = false, length = 1)
    public String getIcadsrInd() {
        return icadsrInd;
    }

    public void setIcadsrInd(String icadsrInd) {
        this.icadsrInd = icadsrInd;
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
        return getFkclientT();
    }
}
