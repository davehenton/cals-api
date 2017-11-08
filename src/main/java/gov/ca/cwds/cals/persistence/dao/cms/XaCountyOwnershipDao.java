package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.XaCmsSessionFactory;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.data.legacy.cms.entity.CountyOwnership;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class XaCountyOwnershipDao extends BaseDaoImpl<CountyOwnership> {
  @Inject
  public XaCountyOwnershipDao(@XaCmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }
}