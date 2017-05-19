package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import gov.ca.cwds.cals.service.mapper.ComplaintMapper;
import gov.ca.cwds.cals.service.mapper.ComplaintMapperImpl;
import gov.ca.cwds.cals.service.mapper.CountyMapper;
import gov.ca.cwds.cals.service.mapper.CountyMapperImpl;
import gov.ca.cwds.cals.service.mapper.FacilityChildrenMapper;
import gov.ca.cwds.cals.service.mapper.FacilityChildrenMapperImpl;
import gov.ca.cwds.cals.service.mapper.FacilityInspectionMapper;
import gov.ca.cwds.cals.service.mapper.FacilityInspectionMapperImpl;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapperImpl;

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
        bind(FacilityInspectionMapper.class).to(FacilityInspectionMapperImpl.class).asEagerSingleton();
        bind(ComplaintMapper.class).to(ComplaintMapperImpl.class).asEagerSingleton();
        bind(CountyMapper.class).to(CountyMapperImpl.class).asEagerSingleton();
    }

}


