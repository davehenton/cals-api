package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.PhoneNumberType;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class PhoneNumberTypeDao extends BaseDaoImpl<PhoneNumberType> {

  /**
   * Constructor
   *
   * @param sessionFactory The session factory
   */
  @Inject
  public PhoneNumberTypeDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }
}
