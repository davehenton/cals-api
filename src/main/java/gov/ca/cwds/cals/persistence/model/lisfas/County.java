package gov.ca.cwds.cals.persistence.model.lisfas;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author CWDS CALS API Team
 */

@Deprecated //https://www.pivotaltracker.com/story/show/148551617
@Embeddable
public class County {

    private static final long serialVersionUID = 8555919317317204161L;

    @Basic
    @Column(name = "tbl_co_nbr")
    private Integer tblCoNbr;

    @Basic
    @Column(name = "tbl_co_desc", length = 20)
    private String tblCoDesc;

    public Integer getTblCoNbr() {
        return tblCoNbr;
    }

    public void setTblCoNbr(Integer tblCoNbr) {
        this.tblCoNbr = tblCoNbr;
    }

    public String getTblCoDesc() {
        return tblCoDesc;
    }

    public void setTblCoDesc(String tblCoDesc) {
        this.tblCoDesc = tblCoDesc;
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

