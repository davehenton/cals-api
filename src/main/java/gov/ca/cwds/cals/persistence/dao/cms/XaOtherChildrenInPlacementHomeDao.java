package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.XaCmsSessionFactory;
import gov.ca.cwds.cals.persistence.model.cms.OtherChildrenInPlacementHome;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class XaOtherChildrenInPlacementHomeDao extends BaseDaoImpl<OtherChildrenInPlacementHome> {

  /**
   * Constructor
   *
   * @param sessionFactory The session factory
   */
  @Inject
  public XaOtherChildrenInPlacementHomeDao(@XaCmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }
}
