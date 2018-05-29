package gov.ca.cwds.cals.service.dto;

import static gov.ca.cwds.cals.service.dto.ChildPlacementInformation.CHILDREN_IDS_PARAMETER_NAME;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.NamedQuery;

/**
 * @author Alex Serbin
 */

@NamedQuery(
    name = ChildPlacementInformation.RETRIEVE_CHILDREN_PLACEMENT_INFORMATION_QUERY,
    query = "SELECT NEW gov.ca.cwds.cals.service.dto.ChildPlacementInformation(c.identifier,"
        + " pe.county.shortDescription, ohp.startDt)"
        + " FROM gov.ca.cwds.data.legacy.cms.entity.Client c"
        + " JOIN c.placementEpisodes pe"
        + " JOIN pe.outOfHomePlacements ohp"
        + " JOIN ohp.placementHome ph"
        + " WHERE ohp.endDt is null"
        + " AND pe.plepsEndt is null"
        + " AND c.identifier in (:" + CHILDREN_IDS_PARAMETER_NAME + ")"
)
@Entity
public class ChildPlacementInformation {

  private static final long serialVersionUID = -322201548279096083L;

  public static final String RETRIEVE_CHILDREN_PLACEMENT_INFORMATION_QUERY =
      "ChildPlacementInformation.retrieveChildrenPlacementInformation";

  public static final String CHILDREN_IDS_PARAMETER_NAME = "children_ids";

  public ChildPlacementInformation() {
  }

  public ChildPlacementInformation(String childIdentifier, String county,
      LocalDate dateOfPlacement) {
    this.childIdentifier = childIdentifier;
    this.county = county;
    this.dateOfPlacement = dateOfPlacement;
  }

  @Id
  private String childIdentifier;

  private String county;

  private LocalDate dateOfPlacement;

  public String getCounty() {
    return county;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public LocalDate getDateOfPlacement() {
    return dateOfPlacement;
  }

  public void setDateOfPlacement(LocalDate dateOfPlacement) {
    this.dateOfPlacement = dateOfPlacement;
  }

  public String getChildIdentifier() {
    return childIdentifier;
  }

  public void setChildIdentifier(String childIdentifier) {
    this.childIdentifier = childIdentifier;
  }
}
