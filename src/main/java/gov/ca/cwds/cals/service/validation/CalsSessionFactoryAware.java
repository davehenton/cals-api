package gov.ca.cwds.cals.service.validation;

import com.google.inject.Key;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.inject.InjectorHolder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public interface CalsSessionFactoryAware {

  default Session getCurrentSession() {
    return getSessionFactory().getCurrentSession();
  }

  default Session openSession() {
    return getSessionFactory().openSession();
  }

  default SessionFactory getSessionFactory() {
    return InjectorHolder.INSTANCE
        .getInjector()
        .getInstance(Key.get(SessionFactory.class, CalsnsSessionFactory.class));
  }

}
