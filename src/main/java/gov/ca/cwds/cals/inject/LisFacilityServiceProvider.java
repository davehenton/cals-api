package gov.ca.cwds.cals.inject;

import com.google.inject.Inject;
import com.google.inject.Injector;
import gov.ca.cwds.cals.service.LisFacilityService;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;

/**
 * Guice provider for proxy service creating.
 *
 * @author CWDS TPT-2
 */
public class LisFacilityServiceProvider extends AbstractInjectProvider<LisFacilityService> {

  @Inject
  public LisFacilityServiceProvider(Injector injector,
      UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory) {
    super(injector, unitOfWorkAwareProxyFactory);
  }

  @Override
  public Class<LisFacilityService> getServiceClass() {
    return LisFacilityService.class;
  }
}
