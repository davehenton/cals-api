package gov.ca.cwds.cals.persistence.model.cms.rs;

import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

/**
 * @author CWDS TPT-2
 */
@NamedQuery(
    name = "ReplicatedPlacementHome.findUpdated",
    query = "SELECT ph FROM ReplicatedPlacementHome ph WHERE ph.replicationOperation = 'U'"
)
@Entity
@javax.persistence.Table(name = "PLC_HM_T")
public class ReplicatedPlacementHome extends BasePlacementHome {
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
