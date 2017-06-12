package gov.ca.cwds.cals.persistence.model.cms.rs;

import gov.ca.cwds.cals.persistence.model.cms.BaseOutOfHomePlacement;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Type;

/**
 * @author CWDS TPT-2
 */
@Entity
@javax.persistence.Table(name = "O_HM_PLT")
public class ReplicatedOutOfHomePlacement extends BaseOutOfHomePlacement {

  private String replicationOperation;

  private Date replicationDate;

  private ReplicatedPlacementHome placementHome;

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

  @Override
  @ManyToOne
  @JoinColumn(name = "FKPLC_HM_T", referencedColumnName = "IDENTIFIER")
  public ReplicatedPlacementHome getPlacementHome() {
    return placementHome;
  }

  public void setPlacementHome(ReplicatedPlacementHome replicatedPlacementHome) {
    this.placementHome = replicatedPlacementHome;
  }
}
