package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import gov.ca.cwds.cals.service.mapper.ClientScpEthnicityMapper;
import gov.ca.cwds.cals.service.mapper.ComplaintMapper;
import gov.ca.cwds.cals.service.mapper.CountyMapper;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.service.mapper.FacilityInspectionMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FacilityTypeMapper;
import gov.ca.cwds.cals.service.mapper.FasFacilityMapper;
import gov.ca.cwds.cals.service.mapper.OtherAdultsInPlacementHomeMapper;
import gov.ca.cwds.cals.service.mapper.OtherChildrenInPlacementHomeMapper;
import gov.ca.cwds.cals.service.mapper.OtherPeopleScpRelationshipMapper;
import gov.ca.cwds.cals.service.mapper.OutOfStateCheckMapper;
import gov.ca.cwds.cals.service.mapper.PhoneContactDetailMapper;
import gov.ca.cwds.cals.service.mapper.PlacementHomeInformationMapper;
import gov.ca.cwds.cals.service.mapper.PlacementHomeMapper;
import gov.ca.cwds.cals.service.mapper.PlacementHomeProfileMapper;
import gov.ca.cwds.cals.service.mapper.SubstituteCareProviderMapper;
import gov.ca.cwds.cals.service.mapper.SubstituteCareProviderUCMapper;
import gov.ca.cwds.cals.service.mapper.rfa.RFA1aFormMapper;

/**
 * DI (dependency injection) setup for mapping classes.
 *
 * @author CWDS CALS API Team
 */

public class MappingModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(FacilityMapper.class).to(FacilityMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(PlacementHomeMapper.class).to(PlacementHomeMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(FasFacilityMapper.class).to(FasFacilityMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(FacilityChildMapper.class).to(FacilityChildMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(FacilityInspectionMapper.class).to(FacilityInspectionMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(ComplaintMapper.class).to(ComplaintMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(CountyMapper.class).to(CountyMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(FacilityTypeMapper.class).to(FacilityTypeMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(RFA1aFormMapper.class).to(RFA1aFormMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(SubstituteCareProviderMapper.class).to(SubstituteCareProviderMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(SubstituteCareProviderUCMapper.class).to(SubstituteCareProviderUCMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(PlacementHomeInformationMapper.class).to(PlacementHomeInformationMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(PlacementHomeProfileMapper.class).to(PlacementHomeProfileMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(ClientScpEthnicityMapper.class).to(ClientScpEthnicityMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(PhoneContactDetailMapper.class).to(PhoneContactDetailMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(OtherChildrenInPlacementHomeMapper.class)
        .to(OtherChildrenInPlacementHomeMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(OtherAdultsInPlacementHomeMapper.class)
        .to(OtherAdultsInPlacementHomeMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(OtherPeopleScpRelationshipMapper.class)
        .to(OtherPeopleScpRelationshipMapper.INSTANCE.getClass())
        .asEagerSingleton();
    bind(OutOfStateCheckMapper.class).to(OutOfStateCheckMapper.INSTANCE.getClass())
        .asEagerSingleton();

  }

}


