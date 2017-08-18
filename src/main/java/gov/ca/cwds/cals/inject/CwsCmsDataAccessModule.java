package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import gov.ca.cwds.cals.persistence.dao.cms.ClientDao;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.OtherAdultsInPlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.OtherChildrenInPlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeUcDao;
import gov.ca.cwds.cals.persistence.dao.cms.RecordChangeCwsCmsDao;
import gov.ca.cwds.cals.persistence.model.RecordChange;
import gov.ca.cwds.cals.persistence.model.cms.AddressPhoneticName;
import gov.ca.cwds.cals.persistence.model.cms.AddressPhoneticNamePK;
import gov.ca.cwds.cals.persistence.model.cms.BackgroundCheck;
import gov.ca.cwds.cals.persistence.model.cms.ClientScpEthnicity;
import gov.ca.cwds.cals.persistence.model.cms.County;
import gov.ca.cwds.cals.persistence.model.cms.CountyOwnership;
import gov.ca.cwds.cals.persistence.model.cms.CountyOwnershipPK;
import gov.ca.cwds.cals.persistence.model.cms.EmergencyContactDetail;
import gov.ca.cwds.cals.persistence.model.cms.ExternalInterface;
import gov.ca.cwds.cals.persistence.model.cms.ExternalInterfacePK;
import gov.ca.cwds.cals.persistence.model.cms.FacilityType;
import gov.ca.cwds.cals.persistence.model.cms.LicenseStatus;
import gov.ca.cwds.cals.persistence.model.cms.OtherAdultsInPlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.OtherChildrenInPlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.OtherPeopleScpRelationship;
import gov.ca.cwds.cals.persistence.model.cms.OutOfStateCheck;
import gov.ca.cwds.cals.persistence.model.cms.PhoneContactDetail;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeInformation;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeInformationPK;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeNotes;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeProfile;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeProfilePK;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeUc;
import gov.ca.cwds.cals.persistence.model.cms.State;
import gov.ca.cwds.cals.persistence.model.cms.SubCareProviderPhoneticName;
import gov.ca.cwds.cals.persistence.model.cms.SubstituteCareProvider;
import gov.ca.cwds.cals.persistence.model.cms.SubstituteCareProviderUc;
import gov.ca.cwds.cals.persistence.model.cms.VisitType;
import gov.ca.cwds.cals.persistence.model.cms.legacy.Client;
import gov.ca.cwds.cals.persistence.model.cms.legacy.CountyLicenseCase;
import gov.ca.cwds.cals.persistence.model.cms.legacy.LicensingVisit;
import gov.ca.cwds.cals.persistence.model.cms.legacy.OutOfHomePlacement;
import gov.ca.cwds.cals.persistence.model.cms.legacy.PlacementEpisode;
import gov.ca.cwds.cals.persistence.model.cms.legacy.PlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.legacy.StaffPerson;
import gov.ca.cwds.inject.CmsSessionFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author CWDS TPT-2
 */
public class CwsCmsDataAccessModule extends AbstractModule {
  private SessionFactory cmsSessionFactory;

  public CwsCmsDataAccessModule(String hibernateCfg) {
    this.cmsSessionFactory = new Configuration().configure(hibernateCfg)
        .addAnnotatedClass(RecordChange.class)
        .addAnnotatedClass(Client.class)
        .addAnnotatedClass(OutOfHomePlacement.class)
        .addAnnotatedClass(PlacementEpisode.class)
        .addAnnotatedClass(PlacementHome.class)
        .addAnnotatedClass(CountyLicenseCase.class)
        .addAnnotatedClass(LicensingVisit.class)
        .addAnnotatedClass(StaffPerson.class)
        .addAnnotatedClass(FacilityType.class)
        .addAnnotatedClass(County.class)
        .addAnnotatedClass(VisitType.class)
        .addAnnotatedClass(State.class)
        .addAnnotatedClass(LicenseStatus.class)

        .addAnnotatedClass(AddressPhoneticName.class)
        .addAnnotatedClass(AddressPhoneticNamePK.class)
        .addAnnotatedClass(BackgroundCheck.class)
        .addAnnotatedClass(ClientScpEthnicity.class)
        .addAnnotatedClass(CountyOwnership.class)
        .addAnnotatedClass(CountyOwnershipPK.class)
        .addAnnotatedClass(EmergencyContactDetail.class)
        .addAnnotatedClass(ExternalInterface.class)
        .addAnnotatedClass(ExternalInterfacePK.class)
        .addAnnotatedClass(PlacementHomeProfile.class)
        .addAnnotatedClass(PlacementHomeProfilePK.class)
        .addAnnotatedClass(PlacementHomeInformation.class)
        .addAnnotatedClass(PlacementHomeInformationPK.class)
        .addAnnotatedClass(PlacementHomeNotes.class)
        .addAnnotatedClass(OtherPeopleScpRelationship.class)
        .addAnnotatedClass(OutOfStateCheck.class)
        .addAnnotatedClass(OtherAdultsInPlacementHome.class)
        .addAnnotatedClass(OtherChildrenInPlacementHome.class)
        .addAnnotatedClass(PhoneContactDetail.class)
        .addAnnotatedClass(PlacementHomeUc.class)
        .addAnnotatedClass(SubstituteCareProvider.class)
        .addAnnotatedClass(SubstituteCareProviderUc.class)
        .addAnnotatedClass(SubCareProviderPhoneticName.class)

        .buildSessionFactory();
  }

  @Override
  protected void configure() {
    bind(SessionFactory.class).annotatedWith(CmsSessionFactory.class).toInstance(cmsSessionFactory);

    // schema: cwscms
    bind(RecordChangeCwsCmsDao.class);
    bind(CountiesDao.class);
    bind(ClientDao.class);
    bind(PlacementHomeDao.class);
    bind(PlacementHomeUcDao.class);
    bind(OtherChildrenInPlacementHomeDao.class);
    bind(OtherAdultsInPlacementHomeDao.class);
  }
}
