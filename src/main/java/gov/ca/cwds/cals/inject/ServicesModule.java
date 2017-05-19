package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import gov.ca.cwds.cals.service.ComplaintService;
import gov.ca.cwds.cals.service.CountiesService;
import gov.ca.cwds.cals.service.FacilityInspectionCollectionService;
import gov.ca.cwds.cals.service.FacilityInspectionService;
import gov.ca.cwds.cals.service.FacilityService;

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
        bind(ComplaintService.class);
        bind(FacilityInspectionCollectionService.class);
        bind(FacilityInspectionService.class);
        bind(CountiesService.class);
    }
}
