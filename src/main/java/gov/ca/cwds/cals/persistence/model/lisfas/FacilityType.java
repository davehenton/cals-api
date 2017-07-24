package gov.ca.cwds.cals.persistence.model.lisfas;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author CWDS CALS API Team
 */
@Deprecated //https://www.pivotaltracker.com/story/show/148550507
@Embeddable
public class FacilityType {

    private static final long serialVersionUID = -3760714538653384441L;

    @Basic
    @Column(name = "tbl_fac_type_code")
    private Integer tblFacTypeCode;

    @Basic
    @Column(name = "tbl_fac_type_desc", length = 70)
    private String tblFacTypeDesc;

    public Integer getTblFacTypeCode() {
        return tblFacTypeCode;
    }

    public void setTblFacTypeCode(Integer tblFacTypeCode) {
        this.tblFacTypeCode = tblFacTypeCode;
    }

    public String getTblFacTypeDesc() {
        return tblFacTypeDesc;
    }

    public void setTblFacTypeDesc(String tblFacTypeDesc) {
        this.tblFacTypeDesc = tblFacTypeDesc;
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
