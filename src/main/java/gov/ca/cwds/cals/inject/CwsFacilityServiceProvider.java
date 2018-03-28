package gov.ca.cwds.cals.inject;

import com.google.inject.Inject;
import com.google.inject.Injector;
import gov.ca.cwds.cals.service.CwsFacilityService;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;

/**
 * Guice provider for proxy service creating.
 *
 * @author CWDS TPT-2
 */
public class CwsFacilityServiceProvider extends AbstractInjectProvider<CwsFacilityService> {

  @Inject
  public CwsFacilityServiceProvider(Injector injector,
      UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory) {
    super(injector, unitOfWorkAwareProxyFactory);
  }

  @Override
  public Class<CwsFacilityService> getServiceClass() {
    return CwsFacilityService.class;
  }
}
