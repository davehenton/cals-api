package gov.ca.cwds.cals.persistence.model.cms.rs;

import gov.ca.cwds.cals.persistence.model.cms.BasePlacementEpisode;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Type;

/**
 * @author CWDS TPT-2
 */
@Entity
@javax.persistence.Table(name = "PLC_EPST")
public class ReplicatedPlacementEpisode extends BasePlacementEpisode {
  private String replicationOperation;

  private Date replicationDate;

  private Set<ReplicatedOutOfHomePlacement> outOfHomePlacements = new HashSet<>();

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
  @OneToMany
  @JoinColumn(name = "FKPLC_EPS0", referencedColumnName = "THIRD_ID")
  @OrderBy("startDt DESC")
  public Set<ReplicatedOutOfHomePlacement> getOutOfHomePlacements() {
    return outOfHomePlacements;
  }

  public void setOutOfHomePlacements(Set<ReplicatedOutOfHomePlacement> outOfHomePlacements) {
    this.outOfHomePlacements = outOfHomePlacements;
  }
}
