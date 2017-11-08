package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.XaCmsSessionFactory;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.data.legacy.cms.entity.OtherAdultsInPlacementHome;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class XaOtherAdultsInPlacementHomeDao extends BaseDaoImpl<OtherAdultsInPlacementHome> {

  /**
   * Constructor
   *
   * @param sessionFactory The session factory
   */
  @Inject
  public XaOtherAdultsInPlacementHomeDao(@XaCmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }
}
