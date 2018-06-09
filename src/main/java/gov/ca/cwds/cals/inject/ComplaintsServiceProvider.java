package gov.ca.cwds.cals.inject;

import com.google.inject.Inject;
import com.google.inject.Injector;
import gov.ca.cwds.cals.service.ComplaintsService;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;

/**
 * Guice provider for proxy service creating.
 *
 * @author CWDS TPT-2
 */
public class ComplaintsServiceProvider extends AbstractInjectProvider<ComplaintsService> {

  @Inject
  public ComplaintsServiceProvider(Injector injector,
      UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory) {
    super(injector, unitOfWorkAwareProxyFactory);
  }

  @Override
  public Class<ComplaintsService> getServiceClass() {
    return ComplaintsService.class;
  }
}
