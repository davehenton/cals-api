package gov.ca.cwds.cals.inject;

import com.google.inject.Inject;
import com.google.inject.Injector;
import gov.ca.cwds.cals.service.rfa.RFA1aFormService;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;

/**
 * @author CWDS CALS API Team
 */

public class RFA1aFormServiceProvider extends AbstractInjectProvider<RFA1aFormService> {

  @Inject
  public RFA1aFormServiceProvider(Injector injector,
      UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory) {
    super(injector, unitOfWorkAwareProxyFactory);
  }

  @Override
  public Class<RFA1aFormService> getServiceClass() {
    return RFA1aFormService.class;
  }
}



