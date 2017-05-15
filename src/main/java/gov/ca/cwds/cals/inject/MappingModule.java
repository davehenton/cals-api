package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import gov.ca.cwds.cals.service.mapper.FacilityChildrenMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapperImpl;
import gov.ca.cwds.cals.service.mapper.FacilityChildrenMapperImpl;

/**
 * DI (dependency injection) setup for mapping classes.
 *
 * @author CWDS CALS API Team
 */

public class MappingModule extends AbstractModule {

    @Override
    protected void configure() {
       bind(FacilityMapper.class).to(FacilityMapperImpl.class).asEagerSingleton();
       bind(FacilityChildrenMapper.class).to(FacilityChildrenMapperImpl.class).asEagerSingleton();
    }

}


