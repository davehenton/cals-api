package gov.ca.cwds.cals.persistence.model.cms.legacy;

import gov.ca.cwds.cals.persistence.model.cms.BaseClient;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.HashSet;
import java.util.Set;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(
    name = "Client.find",
    query = "SELECT c FROM Client c"
        + " JOIN c.placementEpisodes pe"
        + " JOIN pe.outOfHomePlacements ohp"
        + " JOIN ohp.placementHome ph"
        + " WHERE ph.licenseNo = :licenseNumber AND c.identifier = :childId"
)
@NamedQuery(
    name = "Client.findAll",
    query = "SELECT c FROM Client c" +
        " JOIN c.placementEpisodes pe" +
        " JOIN pe.outOfHomePlacements ohp" +
        " JOIN ohp.placementHome ph" +
        " WHERE ph.licenseNo = :licenseNumber"
)
@Entity
@javax.persistence.Table(name = "CLIENT_T")
public class Client extends BaseClient {

  private static final long serialVersionUID = -1570433180700848831L;

  private Set<PlacementEpisode> placementEpisodes = new HashSet<>();

  @Override
  @OneToMany
  @JoinColumn(name = "FKCLIENT_T", referencedColumnName = "IDENTIFIER")
  @OrderBy("removalDt DESC")
  public Set<PlacementEpisode> getPlacementEpisodes() {
    return placementEpisodes;
  }

  public void setPlacementEpisodes(Set<PlacementEpisode> placementEpisodes) {
    this.placementEpisodes = placementEpisodes;
  }
}
