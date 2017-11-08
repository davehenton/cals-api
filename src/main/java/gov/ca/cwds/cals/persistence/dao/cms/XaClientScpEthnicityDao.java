package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.XaCmsSessionFactory;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.data.legacy.cms.entity.ClientScpEthnicity;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class XaClientScpEthnicityDao extends BaseDaoImpl<ClientScpEthnicity> {

  /**
   * Constructor
   *
   * @param sessionFactory The session factory
   */
  @Inject
  public XaClientScpEthnicityDao(@XaCmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }
}
