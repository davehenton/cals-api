package gov.ca.cwds.cals.service.dao;

import static gov.ca.cwds.cals.service.dto.ChildPlacementInformation.CHILDREN_IDS_PARAMETER_NAME;
import static gov.ca.cwds.cals.service.dto.ChildPlacementInformation.RETRIEVE_CHILDREN_PLACEMENT_INFORMATION_QUERY;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.ChildPlacementInformation;
import gov.ca.cwds.inject.CmsSessionFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom Dao for implementing dao logic for facility children.
 *
 * @author CWDS CALS API Team
 */
public class FacilityChildDao extends CustomDao {

  private static final Logger LOG = LoggerFactory.getLogger(FacilityChildDao.class);

  @Inject
  public FacilityChildDao(@CmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  /**
   * Get Placement Information for children.
   */
  public List<ChildPlacementInformation> retrieveChildPlacementInformationList(String[] clientIds) {
    if (ArrayUtils.isEmpty(clientIds)) {
      return Collections.emptyList();
    }
    List<ChildPlacementInformation> childPlacementInformationList =
        prepareChildPlacementInformationQuery(clientIds).list();
    if (LOG.isWarnEnabled() && childPlacementInformationList.size() > clientIds.length) {
      LOG.warn("One or more than one child {} in the home has more than one open placement",
          Arrays.toString(clientIds));
    }
    return childPlacementInformationList;
  }

  /**
   * Get Placement Information for child.
   */
  public ChildPlacementInformation retrieveChildPlacementInformation(String clientId) {
    List<ChildPlacementInformation> childPlacementInformation
        = prepareChildPlacementInformationQuery(new String[]{clientId}).list();
    if (childPlacementInformation.size() > 1) {
      LOG.warn("Child with identifier {} has more than one open placement", clientId);
    }
    return childPlacementInformation.get(0);
  }

  private Query<ChildPlacementInformation> prepareChildPlacementInformationQuery(
      String[] clientIds) {
    return currentSession()
        .createNamedQuery(
            RETRIEVE_CHILDREN_PLACEMENT_INFORMATION_QUERY, ChildPlacementInformation.class)
        .setParameterList(CHILDREN_IDS_PARAMETER_NAME, clientIds);
  }

}
