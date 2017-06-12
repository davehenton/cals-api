package gov.ca.cwds.cals.persistence.model.cms.rs;

import gov.ca.cwds.cals.persistence.model.cms.BaseStaffPerson;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.annotations.Type;

/**
 * @author CWDS TPT-2
 */
@Entity
@javax.persistence.Table(name = "STFPERST")
public class ReplicatedStaffPerson extends BaseStaffPerson {
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
