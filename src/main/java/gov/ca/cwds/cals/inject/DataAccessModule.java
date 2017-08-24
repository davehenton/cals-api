package gov.ca.cwds.cals.inject;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import gov.ca.cwds.cals.CalsApiConfiguration;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.AddressType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.AgeGroupType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.ApplicantRelationshipType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.EducationLevelType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.EthnicityType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenderType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.IncomeType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LanguageType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LicenseType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.MarriageTerminationReasonType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NamePrefixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameSuffixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.PhoneNumberType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.RaceType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.RelationshipToApplicantType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.ResidenceOwnershipType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.SchoolGradeType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.SiblingGroupType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aMinorChild;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aOtherAdult;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1cForm;
import gov.ca.cwds.cals.persistence.model.cms.AddressPhoneticName;
import gov.ca.cwds.cals.persistence.model.cms.AddressPhoneticNamePK;
import gov.ca.cwds.cals.persistence.model.cms.BackgroundCheck;
import gov.ca.cwds.cals.persistence.model.cms.ClientScpEthnicity;
import gov.ca.cwds.cals.persistence.model.cms.CountyOwnership;
import gov.ca.cwds.cals.persistence.model.cms.CountyOwnershipPK;
import gov.ca.cwds.cals.persistence.model.cms.EmergencyContactDetail;
import gov.ca.cwds.cals.persistence.model.cms.ExternalInterface;
import gov.ca.cwds.cals.persistence.model.cms.ExternalInterfacePK;
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
import gov.ca.cwds.cals.persistence.model.fas.ComplaintReportLic802;
import gov.ca.cwds.cals.persistence.model.fas.FacilityInformation;
import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.fas.Rr809Dn;
import gov.ca.cwds.cals.persistence.model.fas.Rrcpoc;
import gov.ca.cwds.cals.persistence.model.lisfas.LisDoFile;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.inject.CmsHibernateBundle;
import gov.ca.cwds.inject.CmsSessionFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import org.hibernate.SessionFactory;

/** @author CWDS CALS API Team */
public class DataAccessModule extends AbstractModule {

  private final HibernateBundle<CalsApiConfiguration> lisHibernateBundle =
      new HibernateBundle<CalsApiConfiguration>(
          LisFacFile.class,
          LisTableFile.class,
          LisDoFile.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
          return configuration.getLisDataSourceFactory();
        }

        @Override
        public String name() {
          return LIS;
        }
      };

  private final HibernateBundle<CalsApiConfiguration> fasHibernateBundle =
      new HibernateBundle<CalsApiConfiguration>(
          FacilityInformation.class,
          LisTableFile.class,
          LisDoFile.class,
          ComplaintReportLic802.class,
          LpaInformation.class,
          Rrcpoc.class,
          Rr809Dn.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
          return configuration.getFasDataSourceFactory();
        }

        @Override
        public String name() {
          return FAS;
        }
      };

  private final HibernateBundle<CalsApiConfiguration> cmsHibernateBundle =
      new HibernateBundle<CalsApiConfiguration>(
          Client.class,
          OutOfHomePlacement.class,
          PlacementEpisode.class,
          PlacementHome.class,
          StaffPerson.class,
          gov.ca.cwds.cals.persistence.model.cms.FacilityType.class,
          gov.ca.cwds.cals.persistence.model.cms.County.class,
          CountyLicenseCase.class,
          LicensingVisit.class,
          VisitType.class,
          State.class,
          LicenseStatus.class,

          AddressPhoneticName.class,
          AddressPhoneticNamePK.class,
          BackgroundCheck.class,
          ClientScpEthnicity.class,
          CountyOwnership.class,
          CountyOwnershipPK.class,
          EmergencyContactDetail.class,
          ExternalInterface.class,
          ExternalInterfacePK.class,
          PlacementHomeProfile.class,
          PlacementHomeProfilePK.class,
          PlacementHomeInformation.class,
          PlacementHomeInformationPK.class,
          PlacementHomeNotes.class,
          OtherPeopleScpRelationship.class,
          OutOfStateCheck.class,
          OtherAdultsInPlacementHome.class,
          OtherChildrenInPlacementHome.class,
          PhoneContactDetail.class,
          PlacementHomeUc.class,
          SubstituteCareProvider.class,
          SubstituteCareProviderUc.class,
          SubCareProviderPhoneticName.class
      ) {
        @Override
        public DataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
          return configuration.getCmsDataSourceFactory();
        }

        @Override
        public String name() {
          return CMS;
        }
      };

  private final HibernateBundle<CalsApiConfiguration> calsnsHibernateBundle =
      new HibernateBundle<CalsApiConfiguration>(
          // Dictionaries
          AgeGroupType.class,
          LanguageType.class,
          GenderType.class,
          NameType.class,
          EducationLevelType.class,
          EthnicityType.class,
          gov.ca.cwds.cals.persistence.model.calsns.dictionaries.FacilityType.class,
          RaceType.class,
          RelationshipToApplicantType.class,
          IncomeType.class,
          PhoneNumberType.class,
          AddressType.class,
          SiblingGroupType.class,
          StateType.class,
          ResidenceOwnershipType.class,
          ApplicantRelationshipType.class,
          LicenseType.class,
          MarriageTerminationReasonType.class,
          SchoolGradeType.class,
          CountyType.class,
          NameSuffixType.class,
          NamePrefixType.class,
          //RFA
          RFA1aForm.class,
          RFA1aApplicant.class,
          RFA1aMinorChild.class,
          RFA1aOtherAdult.class,
          RFA1bForm.class,
          RFA1cForm.class
      ) {

        @Override
        public DataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
          return configuration.getCalsnsDataSourceFactory();
        }

        @Override
        public String name() {
          return CALSNS;
        }

        @Override
        public void configure(org.hibernate.cfg.Configuration configuration) {
          configuration.addPackage("gov.ca.cwds.cals.persistence.model.calsns.rfa");
        }

      };

  public DataAccessModule(Bootstrap<CalsApiConfiguration> bootstrap) {
    bootstrap.addBundle(fasHibernateBundle);
    bootstrap.addBundle(cmsHibernateBundle);
    bootstrap.addBundle(lisHibernateBundle);
    bootstrap.addBundle(calsnsHibernateBundle);
  }

  @Override
  protected void configure() {
    //do nothing
  }

  @Provides
  @FasSessionFactory
  SessionFactory fasSessionFactory() {
    return fasHibernateBundle.getSessionFactory();
  }

  @Provides
  @CmsSessionFactory
  SessionFactory cmsSessionFactory() {
    return cmsHibernateBundle.getSessionFactory();
  }

  @Provides
  @LisSessionFactory
  SessionFactory lisSessionFactory() {
    return lisHibernateBundle.getSessionFactory();
  }

  @Provides
  @CalsnsSessionFactory
  SessionFactory calsnsSessionFactory() {
    return calsnsHibernateBundle.getSessionFactory();
  }

  @Provides
  @FasHibernateBundle
  HibernateBundle<CalsApiConfiguration> fasHibernateBundle() {
    return fasHibernateBundle;
  }

  @Provides
  @CmsHibernateBundle
  public HibernateBundle<CalsApiConfiguration> getCmsHibernateBundle() {
    return cmsHibernateBundle;
  }

  @Provides
  @LisHibernateBundle
  public HibernateBundle<CalsApiConfiguration> getLisHibernateBundle() {
    return lisHibernateBundle;
  }

  @Provides
  @CalsnsHibernateBundle
  public HibernateBundle<CalsApiConfiguration> getCalsnsHibernateBundle() {
    return calsnsHibernateBundle;
  }

  @Provides
  UnitOfWorkAwareProxyFactory provideUnitOfWorkAwareProxyFactory() {
    return new UnitOfWorkAwareProxyFactory(
        lisHibernateBundle, fasHibernateBundle, cmsHibernateBundle, calsnsHibernateBundle);
  }
}
