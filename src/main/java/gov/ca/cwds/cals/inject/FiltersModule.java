package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import gov.ca.cwds.cals.CalsApiApplication;
import gov.ca.cwds.cals.web.rest.filters.RequestExecutionContextFilter;
import gov.ca.cwds.cals.web.rest.filters.RequestResponseLoggingFilter;
import gov.ca.cwds.rest.filters.WebSecurityFilter;

/**
 * Dependency injection (DI) for Filter classes.
 * 
 * <p>
 * Register filters her with Guice and configure them in {@link CalsApiApplication}, method
 * registerFilters.
 * </p>
 * 
 * @author CWDS API Team
 */
public class FiltersModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(RequestExecutionContextFilter.class);
    bind(RequestResponseLoggingFilter.class);
    bind(WebSecurityFilter.class);
  }

}
