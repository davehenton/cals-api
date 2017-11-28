package gov.ca.cwds.cals.inject;

import com.google.inject.Injector;
import com.google.inject.Key;
import gov.ca.cwds.cms.data.access.inject.AbstractDataAccessServicesModule;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public class DataAccessServicesModule extends AbstractDataAccessServicesModule {

  @Override
  protected SessionFactory getPlacementHomeSessionFactory(Injector injector) {
    return injector.getInstance(Key.get(SessionFactory.class, XaCmsSessionFactory.class));
  }

}
