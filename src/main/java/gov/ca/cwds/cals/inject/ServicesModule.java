package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import gov.ca.cwds.cals.rest.services.FacilityService;

/**
 * Identifies all CALS API business layer (services) classes available for dependency injection by Guice.
 *
 * @author CALS API Team
 *
 */
public class ServicesModule extends AbstractModule{

    /**
     *  Default constructor
     */
    public ServicesModule() {

    }

    @Override
    protected void configure() {
        bind(FacilityService.class);
    }
}
