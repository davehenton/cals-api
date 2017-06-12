package gov.ca.cwds.cals.persistence.model.cms.legacy;

import gov.ca.cwds.cals.persistence.model.cms.BasePlacementEpisode;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * @author CWDS CALS API Team
 */
@Entity
@javax.persistence.Table(name = "PLC_EPST")
public class PlacementEpisode extends BasePlacementEpisode {
  private static final long serialVersionUID = -3903845942588945920L;

  private Set<OutOfHomePlacement> outOfHomePlacements = new HashSet<>();

  @Override
  @OneToMany
  @JoinColumn(name = "FKPLC_EPS0", referencedColumnName = "THIRD_ID")
  @OrderBy("startDt DESC")
  public Set<OutOfHomePlacement> getOutOfHomePlacements() {
    return outOfHomePlacements;
  }

  public void setOutOfHomePlacements(Set<OutOfHomePlacement> outOfHomePlacements) {
    this.outOfHomePlacements = outOfHomePlacements;
  }
}
