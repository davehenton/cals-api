package gov.ca.cwds.cals.persistence.model.cms;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author CWDS CALS API Team
 */
public class ExternalInterfacePK implements Serializable {

  private static final long serialVersionUID = -5937783687228572512L;

  @Column(name = "SEQ_NO", nullable = false)
  @Id
  private int seqNo;

  @Column(name = "SUBMTL_TS", nullable = false)
  @Id
  private Timestamp submtlTs;

  @Column(name = "L_USERID", nullable = false, length = 8)
  @Id
  private String lUserid;

  public int getSeqNo() {
    return seqNo;
  }

  public void setSeqNo(int seqNo) {
    this.seqNo = seqNo;
  }

  public Timestamp getSubmtlTs() {
    return submtlTs;
  }

  public void setSubmtlTs(Timestamp submtlTs) {
    this.submtlTs = submtlTs;
  }

  public String getlUserid() {
    return lUserid;
  }

  public void setlUserid(String lUserid) {
    this.lUserid = lUserid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ExternalInterfacePK that = (ExternalInterfacePK) o;

    if (seqNo != that.seqNo) {
      return false;
    }
    if (submtlTs != null ? !submtlTs.equals(that.submtlTs) : that.submtlTs != null) {
      return false;
    }
    if (lUserid != null ? !lUserid.equals(that.lUserid) : that.lUserid != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = seqNo;
    result = 31 * result + (submtlTs != null ? submtlTs.hashCode() : 0);
    result = 31 * result + (lUserid != null ? lUserid.hashCode() : 0);
    return result;
  }
}
