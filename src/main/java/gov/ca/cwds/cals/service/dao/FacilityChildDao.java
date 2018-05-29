package gov.ca.cwds.cals.service.dao;

import static gov.ca.cwds.cals.service.dto.ChildPlacementInformation.CHILDREN_IDS_PARAMETER_NAME;
import static gov.ca.cwds.cals.service.dto.ChildPlacementInformation.RETRIEVE_CHILDREN_PLACEMENT_INFORMATION_QUERY;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.ChildPlacementInformation;
import gov.ca.cwds.inject.CmsSessionFactory;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildDao extends CustomDao {

  @Inject
  public FacilityChildDao(@CmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public List<ChildPlacementInformation> retireveChildPlacementInformation(String[] clientIds) {

    List<ChildPlacementInformation> childPlacementInformationList =
        currentSession()
            .createNamedQuery(
                RETRIEVE_CHILDREN_PLACEMENT_INFORMATION_QUERY, ChildPlacementInformation.class)
            .setParameterList(CHILDREN_IDS_PARAMETER_NAME, clientIds)
            .list();
    if (childPlacementInformationList.size() > clientIds.length) {
      throw new IllegalStateException(
          "Child is not expected to get more than one " + "current placements");
    }

    return childPlacementInformationList;
  }
}
