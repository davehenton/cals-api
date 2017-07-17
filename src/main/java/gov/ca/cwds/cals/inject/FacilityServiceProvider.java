package gov.ca.cwds.cals.inject;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import gov.ca.cwds.cals.service.FacilityService;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;

/**
 * @author CWDS TPT-2
 */
public class FacilityServiceProvider implements Provider<FacilityService> {

  private Injector injector;

  private final UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory;

  @Inject
  public FacilityServiceProvider(Injector injector,
      UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory) {
    this.injector = injector;
    this.unitOfWorkAwareProxyFactory = unitOfWorkAwareProxyFactory;
  }

  @Override
  public FacilityService get() {
    FacilityService facilityService = unitOfWorkAwareProxyFactory.create(FacilityService.class);
    injector.injectMembers(facilityService);
    return facilityService;
  }
}
