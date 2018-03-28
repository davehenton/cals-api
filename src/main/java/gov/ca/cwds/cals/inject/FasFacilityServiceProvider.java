package gov.ca.cwds.cals.inject;

import com.google.inject.Inject;
import com.google.inject.Injector;
import gov.ca.cwds.cals.service.FasFacilityService;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;

/**
 * Guice provider for proxy service creating.
 *
 * @author CWDS TPT-2
 */
public class FasFacilityServiceProvider extends AbstractInjectProvider<FasFacilityService> {

  @Inject
  public FasFacilityServiceProvider(Injector injector,
      UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory) {
    super(injector, unitOfWorkAwareProxyFactory);
  }

  @Override
  public Class<FasFacilityService> getServiceClass() {
    return FasFacilityService.class;
  }
}
