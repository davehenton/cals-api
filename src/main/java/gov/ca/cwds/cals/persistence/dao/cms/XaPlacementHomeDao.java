package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.XaCmsSessionFactory;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class XaPlacementHomeDao extends BaseDaoImpl<PlacementHome> {

  @Inject
  public XaPlacementHomeDao(@XaCmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
