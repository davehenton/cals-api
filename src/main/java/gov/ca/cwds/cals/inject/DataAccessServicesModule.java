package gov.ca.cwds.cals.inject;

import com.google.inject.Injector;
import com.google.inject.Key;
import gov.ca.cwds.cms.data.access.inject.AbstractDataAccessServicesModule;
import gov.ca.cwds.inject.CmsSessionFactory;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public class DataAccessServicesModule extends AbstractDataAccessServicesModule {

  private SessionFactory getCmsSessionFactory(Injector injector) {
    return injector.getInstance(Key.get(SessionFactory.class, CmsSessionFactory.class));
  }

  @Override
  protected SessionFactory getDataAccessServicesSessionFactory(Injector injector) {
    return getCmsSessionFactory(injector);
  }
}
