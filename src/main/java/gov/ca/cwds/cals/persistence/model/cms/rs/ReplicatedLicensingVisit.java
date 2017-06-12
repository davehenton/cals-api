package gov.ca.cwds.cals.persistence.model.cms.rs;

import gov.ca.cwds.cals.persistence.model.cms.BaseLicensingVisit;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 * @author CWDS CALS API Team
 */
@Entity
@Table(name = "LIC_VSTT")
public class ReplicatedLicensingVisit extends BaseLicensingVisit {

  private static final long serialVersionUID = -8288205929550791795L;

  private String replicationOperation;

  private Date replicationDate;

  @Column(name = "IBMSNAP_OPERATION", updatable = false)
  public String getReplicationOperation() {
    return replicationOperation;
  }

  public void setReplicationOperation(String replicationOperation) {
    this.replicationOperation = replicationOperation;
  }

  @Type(type = "timestamp")
  @Column(name = "IBMSNAP_LOGMARKER", updatable = false)
  public Date getReplicationDate() {
    return replicationDate;
  }

  public void setReplicationDate(Date replicationDate) {
    this.replicationDate = replicationDate;
  }
}
