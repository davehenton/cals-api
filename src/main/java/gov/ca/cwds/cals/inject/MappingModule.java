package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapperImpl;

/**
 * DI (dependency injection) setup for mapping classes.
 *
 * @author CWDS API Team
 */
public class MappingModule extends AbstractModule {

    @Override
    protected void configure() {
       bind(FacilityMapper.class).to(FacilityMapperImpl.class).asEagerSingleton();
    }

}


