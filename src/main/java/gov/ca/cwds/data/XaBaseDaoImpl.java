package gov.ca.cwds.data;

import gov.ca.cwds.data.persistence.PersistentObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class XaBaseDaoImpl<T extends PersistentObject> extends BaseDaoImpl<T> {

  public XaBaseDaoImpl(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  protected Session currentSession() {
    SessionFactory sessionFactory = getSessionFactory();
    sessionFactory.isOpen();
    return super.currentSession();
  }
}
