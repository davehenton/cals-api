package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.XaCmsSessionFactory;
import gov.ca.cwds.cals.persistence.model.cms.PhoneContactDetail;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class XaPhoneContactDetailDao extends BaseDaoImpl<PhoneContactDetail> {

  /**
   * Constructor
   *
   * @param sessionFactory The session factory
   */
  @Inject
  public XaPhoneContactDetailDao(@XaCmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }
}
