package gov.ca.cwds.cals.inject;

import com.google.inject.Injector;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S3066") //Need to have ability to set injector
public enum InjectorHolder {
  INSTANCE;
  private Injector injector;

  public Injector getInjector() {
    return injector;
  }

  public void setInjector(Injector injector) {
    this.injector = injector;
  }
}
