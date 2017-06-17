package gov.ca.cwds.cals.persistence.model.cms.rs;

import gov.ca.cwds.cals.persistence.model.cms.BaseClient;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

/**
 * @author CWDS TPT-2
 */
@NamedQuery(
    name = "ReplicatedClient.findUpdated",
    query = "SELECT c FROM ReplicatedClient c" +
        " JOIN c.placementEpisodes pe" +
        " JOIN pe.outOfHomePlacements ohp" +
        " JOIN ohp.placementHome ph" +
        " WHERE ph.licenseNo IS NULL AND ph.replicationDate > :dateAfter"
)
@Entity
@javax.persistence.Table(name = "CLIENT_T")
public class ReplicatedClient extends BaseClient {

  private String replicationOperation;

  private Date replicationDate;

  private Set<ReplicatedPlacementEpisode> placementEpisodes = new HashSet<>();

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
  @JoinColumn(name = "FKCLIENT_T", referencedColumnName = "IDENTIFIER")
  @OrderBy("removalDt DESC")
  public Set<ReplicatedPlacementEpisode> getPlacementEpisodes() {
    return placementEpisodes;
  }

  public void setPlacementEpisodes(Set<ReplicatedPlacementEpisode> placementEpisodes) {
    this.placementEpisodes = placementEpisodes;
  }
}
