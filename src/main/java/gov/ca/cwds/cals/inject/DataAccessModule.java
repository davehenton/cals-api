package gov.ca.cwds.cals.inject;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS_RS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.XA_CALSNS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.XA_CMS;

import com.google.common.collect.ImmutableList;
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
import gov.ca.cwds.cals.persistence.model.calsns.rfa.LIC198bForm;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aMinorChild;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aOtherAdult;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1cForm;
import gov.ca.cwds.cals.persistence.model.fas.ComplaintReportLic802;
import gov.ca.cwds.cals.persistence.model.fas.FacilityInformation;
import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.fas.Rr809Dn;
import gov.ca.cwds.cals.persistence.model.fas.Rrcpoc;
import gov.ca.cwds.cals.persistence.model.lisfas.LisDoFile;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.data.legacy.cms.entity.AddressPhoneticName;
import gov.ca.cwds.data.legacy.cms.entity.AddressPhoneticNamePK;
import gov.ca.cwds.data.legacy.cms.entity.BackgroundCheck;
import gov.ca.cwds.data.legacy.cms.entity.Case;
import gov.ca.cwds.data.legacy.cms.entity.CaseAssignment;
import gov.ca.cwds.data.legacy.cms.entity.CaseLoad;
import gov.ca.cwds.data.legacy.cms.entity.CaseLoadWeighting;
import gov.ca.cwds.data.legacy.cms.entity.ChildClient;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import gov.ca.cwds.data.legacy.cms.entity.ClientOtherEthnicity;
import gov.ca.cwds.data.legacy.cms.entity.CountyLicenseCase;
import gov.ca.cwds.data.legacy.cms.entity.CountyOwnership;
import gov.ca.cwds.data.legacy.cms.entity.CountyOwnershipPK;
import gov.ca.cwds.data.legacy.cms.entity.EmergencyContactDetail;
import gov.ca.cwds.data.legacy.cms.entity.ExternalInterface;
import gov.ca.cwds.data.legacy.cms.entity.ExternalInterfacePK;
import gov.ca.cwds.data.legacy.cms.entity.LicensingVisit;
import gov.ca.cwds.data.legacy.cms.entity.LongText;
import gov.ca.cwds.data.legacy.cms.entity.OtherAdultsInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OtherChildrenInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OtherEthnicity;
import gov.ca.cwds.data.legacy.cms.entity.OtherPeopleScpRelationship;
import gov.ca.cwds.data.legacy.cms.entity.OutOfHomePlacement;
import gov.ca.cwds.data.legacy.cms.entity.OutOfStateCheck;
import gov.ca.cwds.data.legacy.cms.entity.PhoneContactDetail;
import gov.ca.cwds.data.legacy.cms.entity.PlacementEpisode;
import gov.ca.cwds.data.legacy.cms.entity.PlacementFacilityTypeHistory;
import gov.ca.cwds.data.legacy.cms.entity.PlacementFacilityTypeHistoryPK;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHomeInformation;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHomeInformationPK;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHomeNotes;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHomeProfile;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHomeProfilePK;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHomeUc;
import gov.ca.cwds.data.legacy.cms.entity.Referral;
import gov.ca.cwds.data.legacy.cms.entity.ReferralAssignment;
import gov.ca.cwds.data.legacy.cms.entity.ReferralClient;
import gov.ca.cwds.data.legacy.cms.entity.ScpOtherEthnicity;
import gov.ca.cwds.data.legacy.cms.entity.StaffPerson;
import gov.ca.cwds.data.legacy.cms.entity.StaffPersonCaseLoad;
import gov.ca.cwds.data.legacy.cms.entity.SubCareProviderPhoneticName;
import gov.ca.cwds.data.legacy.cms.entity.SubstituteCareProvider;
import gov.ca.cwds.data.legacy.cms.entity.SubstituteCareProviderUc;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.ActiveServiceComponentType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.ApprovalStatusType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.CaseClosureReasonType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.Country;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.County;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.DeathCircumstancesType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.Ethnicity;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.FacilityType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.ImmigrationStatus;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.Language;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.LicenseStatus;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.MaritalStatus;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.Religion;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.SecondaryAssignmentRoleType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.State;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.VisitType;
import gov.ca.cwds.inject.CmsHibernateBundle;
import gov.ca.cwds.inject.CmsSessionFactory;
import gov.ca.cwds.inject.CwsRsHibernateBundle;
import gov.ca.cwds.inject.CwsRsSessionFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;
import io.dropwizard.setup.Bootstrap;
import org.hibernate.SessionFactory;

/** @author CWDS CALS API Team */
public class DataAccessModule extends AbstractModule {

  private final ImmutableList<Class<?>> lisEntities = ImmutableList.<Class<?>>builder().add(
      LisFacFile.class,
      LisTableFile.class,
      LisDoFile.class
  ).build();


  private final ImmutableList<Class<?>> fasEntities = ImmutableList.<Class<?>>builder().add(
      FacilityInformation.class,
      LisTableFile.class,
      LisDoFile.class,
      ComplaintReportLic802.class,
      LpaInformation.class,
      Rrcpoc.class,
      Rr809Dn.class
  ).build();


  private final ImmutableList<Class<?>> cmsEntities = ImmutableList.<Class<?>>builder().add(
      Client.class,
      OutOfHomePlacement.class,
      PlacementEpisode.class,
      PlacementHome.class,
      StaffPerson.class,
      FacilityType.class,
      County.class,
      CountyLicenseCase.class,
      LicensingVisit.class,
      VisitType.class,
      State.class,
      LicenseStatus.class,

      AddressPhoneticName.class,
      AddressPhoneticNamePK.class,
      BackgroundCheck.class,
      OtherEthnicity.class,
      ClientOtherEthnicity.class,
      ScpOtherEthnicity.class,
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
      PlacementFacilityTypeHistory.class,
      PlacementFacilityTypeHistoryPK.class,
      OtherPeopleScpRelationship.class,
      OutOfStateCheck.class,
      OtherAdultsInPlacementHome.class,
      OtherChildrenInPlacementHome.class,
      PhoneContactDetail.class,
      PlacementHomeUc.class,
      SubstituteCareProvider.class,
      SubstituteCareProviderUc.class,
      SubCareProviderPhoneticName.class,
      Country.class,
      ImmigrationStatus.class,
      MaritalStatus.class,
      gov.ca.cwds.data.legacy.cms.entity.syscodes.NameType.class,
      Ethnicity.class,
      Language.class,
      ActiveServiceComponentType.class,
      ApprovalStatusType.class,
      CaseClosureReasonType.class,
      DeathCircumstancesType.class,
      Religion.class,

      SecondaryAssignmentRoleType.class,
      Case.class,
      LongText.class,
      ChildClient.class,
      CaseAssignment.class,
      CaseLoad.class,
      StaffPersonCaseLoad.class,
      ReferralAssignment.class,
      Referral.class,
      CaseLoadWeighting.class,
      ReferralClient.class

  ).build();

  public static final ImmutableList<Class<?>> cwsRsEntities = ImmutableList.<Class<?>>builder().add(

  ).build();


  private final ImmutableList<Class<?>> calsnsEntities = ImmutableList.<Class<?>>builder().add(
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
      LIC198bForm.class,
      RFA1cForm.class
  ).build();

  private final HibernateBundle<CalsApiConfiguration> lisHibernateBundle =
      new HibernateBundle<CalsApiConfiguration>(lisEntities, new SessionFactoryFactory()) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
          return configuration.getLisDataSourceFactory();
        }

        @Override
        public String name() {
          return LIS;
        }
      };

  private final HibernateBundle<CalsApiConfiguration> fasHibernateBundle =
      new HibernateBundle<CalsApiConfiguration>(fasEntities, new SessionFactoryFactory()) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
          return configuration.getFasDataSourceFactory();
        }

        @Override
        public String name() {
          return FAS;
        }
      };

  private final HibernateBundle<CalsApiConfiguration> cmsHibernateBundle =
      new HibernateBundle<CalsApiConfiguration>(cmsEntities, new SessionFactoryFactory()) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
          return configuration.getCmsDataSourceFactory();
        }

        @Override
        public String name() {
          return CMS;
        }
      };

  private final HibernateBundle<CalsApiConfiguration> cmsRsHibernateBundle =
      new HibernateBundle<CalsApiConfiguration>(cwsRsEntities, new SessionFactoryFactory()) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
          return configuration.getCmsRsDataSourceFactory();
        }

        @Override
        public String name() {
          return CMS_RS;
        }
      };

  private final HibernateBundle<CalsApiConfiguration> xaCmsHibernateBundle =
      new HibernateBundle<CalsApiConfiguration>(cmsEntities, new SessionFactoryFactory()) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
          return configuration.getXaCmsDataSourceFactory();
        }

        @Override
        public String name() {
          return XA_CMS;
        }
      };

  private final HibernateBundle<CalsApiConfiguration> calsnsHibernateBundle =
      new HibernateBundle<CalsApiConfiguration>(calsnsEntities, new SessionFactoryFactory()) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
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

  private final HibernateBundle<CalsApiConfiguration> xaCalsnsHibernateBundle =
      new HibernateBundle<CalsApiConfiguration>(calsnsEntities, new SessionFactoryFactory()) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
          return configuration.getXaCalsnsDataSourceFactory();
        }

        @Override
        public String name() {
          return XA_CALSNS;
        }

        @Override
        public void configure(org.hibernate.cfg.Configuration configuration) {
          configuration.addPackage("gov.ca.cwds.cals.persistence.model.calsns.rfa");
        }
      };


  public DataAccessModule(Bootstrap<? extends CalsApiConfiguration> bootstrap) {
    bootstrap.addBundle(fasHibernateBundle);
    bootstrap.addBundle(cmsHibernateBundle);
    bootstrap.addBundle(cmsRsHibernateBundle);
    bootstrap.addBundle(lisHibernateBundle);
    bootstrap.addBundle(calsnsHibernateBundle);
    bootstrap.addBundle(xaCmsHibernateBundle);
    bootstrap.addBundle(xaCalsnsHibernateBundle);
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
  @CwsRsSessionFactory
  SessionFactory cmsRsSessionFactory() {
    return cmsRsHibernateBundle.getSessionFactory();
  }

  @Provides
  @XaCmsSessionFactory
  SessionFactory xaCmsSessionFactory() {
    return xaCmsHibernateBundle.getSessionFactory();
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
  @XaCalsnsSessionFactory
  SessionFactory xaCalsnsSessionFactory() {
    return xaCalsnsHibernateBundle.getSessionFactory();
  }

  @Provides
  @FasHibernateBundle
  public HibernateBundle<CalsApiConfiguration> getFasHibernateBundle() {
    return fasHibernateBundle;
  }

  @Provides
  @CmsHibernateBundle
  public HibernateBundle<CalsApiConfiguration> getCmsHibernateBundle() {
    return cmsHibernateBundle;
  }

  @Provides
  @CwsRsHibernateBundle
  public HibernateBundle<CalsApiConfiguration> getCmsRsHibernateBundle() {
    return cmsRsHibernateBundle;
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
  @XaCmsHibernateBundle
  public HibernateBundle<CalsApiConfiguration> getXaCmsHibernateBundle() {
    return xaCmsHibernateBundle;
  }

  @Provides
  @XaCalsnsHibernateBundle
  public HibernateBundle<CalsApiConfiguration> getXaCalsnsHibernateBundle() {
    return xaCalsnsHibernateBundle;
  }

}
