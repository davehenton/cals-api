package gov.ca.cwds.cals.inject;

import com.google.inject.Inject;
import com.google.inject.Injector;
import gov.ca.cwds.cals.service.builder.FacilityParameterObjectCMSAwareBuilder;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;

/**
 * @author CWDS TPT-2
 */
public class FacilityParameterObjectBuilderProvider extends AbstractInjectProvider<FacilityParameterObjectCMSAwareBuilder> {

    @Inject
    public FacilityParameterObjectBuilderProvider(Injector injector,
                                                  UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory) {
        super(injector, unitOfWorkAwareProxyFactory);
    }

    @Override
    public Class<FacilityParameterObjectCMSAwareBuilder> getServiceClass() {
        return FacilityParameterObjectCMSAwareBuilder.class;
    }
}
