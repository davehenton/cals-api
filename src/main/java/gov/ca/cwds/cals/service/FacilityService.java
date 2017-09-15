package gov.ca.cwds.cals.service;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;
import static javax.ws.rs.core.Response.Status.EXPECTATION_FAILED;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.Utils.Id;
import gov.ca.cwds.cals.persistence.dao.cms.ClientDao;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.FacilityTypeDao;
import gov.ca.cwds.cals.persistence.dao.cms.LicenseStatusDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.StateDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaBackgroundCheckDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaClientScpEthnicityDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaCountyOwnershipDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaEmergencyContactDetailDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaExternalInterfaceDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaOtherAdultsInPlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaOtherChildrenInPlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaOtherPeopleScpRelationshipDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaOutOfStateCheckDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaPhoneContactDetailDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaPlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaPlacementHomeInformationDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaPlacementHomeNotesDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaPlacementHomeProfileDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaPlacementHomeUcDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaSubstituteCareProviderDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaSubstituteCareProviderUCDao;
import gov.ca.cwds.cals.persistence.dao.fas.ComplaintReportLic802Dao;
import gov.ca.cwds.cals.persistence.dao.fas.FacilityInformationDao;
import gov.ca.cwds.cals.persistence.dao.fas.InspectionDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileLisDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisTableFileDao;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LanguageType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.persistence.model.cms.BackgroundCheck;
import gov.ca.cwds.cals.persistence.model.cms.BaseCountyLicenseCase;
import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.BaseStaffPerson;
import gov.ca.cwds.cals.persistence.model.cms.ClientScpEthnicity;
import gov.ca.cwds.cals.persistence.model.cms.CountyOwnership;
import gov.ca.cwds.cals.persistence.model.cms.EmergencyContactDetail;
import gov.ca.cwds.cals.persistence.model.cms.ExternalInterface;
import gov.ca.cwds.cals.persistence.model.cms.OtherAdultsInPlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.OtherChildrenInPlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.OtherPeopleScpRelationship;
import gov.ca.cwds.cals.persistence.model.cms.OutOfStateCheck;
import gov.ca.cwds.cals.persistence.model.cms.PhoneContactDetail;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeInformation;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeProfile;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeUc;
import gov.ca.cwds.cals.persistence.model.cms.SubstituteCareProvider;
import gov.ca.cwds.cals.persistence.model.cms.SubstituteCareProviderUc;
import gov.ca.cwds.cals.persistence.model.cms.legacy.PlacementHome;
import gov.ca.cwds.cals.persistence.model.fas.FacilityInformation;
import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.FacilityInspectionDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.mapper.BackgroundCheckMapper;
import gov.ca.cwds.cals.service.mapper.ClientScpEthnicityMapper;
import gov.ca.cwds.cals.service.mapper.ComplaintMapper;
import gov.ca.cwds.cals.service.mapper.CountyOwnershipMapper;
import gov.ca.cwds.cals.service.mapper.EmergencyContactDetailMapper;
import gov.ca.cwds.cals.service.mapper.ExternalInterfaceMapper;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.service.mapper.FacilityInspectionMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
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
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.exception.ExpectedException;
import gov.ca.cwds.rest.services.CrudsService;
import io.dropwizard.hibernate.UnitOfWork;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CRUD service for {@link gov.ca.cwds.cals.service.dto.FacilityDTO}
 *
 * @author CALS API Team
 */
public class FacilityService implements CrudsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(FacilityService.class);

  @Inject
  private LisFacFileLisDao lisFacFileLisDao;

  @Inject
  private FacilityInformationDao facilityInformationDao;

  @Inject
  private LisTableFileDao lisTableFileDao;

  @Inject
  private XaExternalInterfaceDao xaExternalInterfaceDao;

  @Inject
  private PlacementHomeDao placementHomeDao;

  @Inject
  private XaPlacementHomeDao xaPlacementHomeDao;

  @Inject
  private XaPlacementHomeUcDao xaPlacementHomeUcDao;

  @Inject
  private XaPlacementHomeNotesDao xaPlacementHomeNotesDao;

  @Inject
  private XaEmergencyContactDetailDao xaEmergencyContactDetailDao;

  @Inject
  private XaBackgroundCheckDao xaBackgroundCheckDao;

  @Inject
  private XaCountyOwnershipDao xaCountyOwnershipDao;

  @Inject
  private XaSubstituteCareProviderDao xaSubstituteCareProviderDao;

  @Inject
  private XaSubstituteCareProviderUCDao xaSubstituteCareProviderUCDao;

  @Inject
  private XaPlacementHomeInformationDao xaPlacementHomeInformationDao;

  @Inject
  private XaPlacementHomeProfileDao xaPlacementHomeProfileDao;

  @Inject
  private XaPhoneContactDetailDao xaPhoneContactDetailDao;

  @Inject
  private XaClientScpEthnicityDao xaClientScpEthnicityDao;

  @Inject
  private XaOtherChildrenInPlacementHomeDao xaOtherChildrenDao;

  @Inject
  private XaOtherAdultsInPlacementHomeDao xaOtherAdultDao;

  @Inject
  private XaOtherPeopleScpRelationshipDao xaOtherPeopleScpRelationshipDao;

  @Inject
  private CountiesDao countiesDao;

  @Inject
  private XaOutOfStateCheckDao xaOutOfStateCheckDao;

  @Inject
  private FacilityMapper facilityMapper;

  @Inject
  private FacilityInspectionMapper facilityInspectionMapper;

  @Inject
  private ComplaintMapper complaintMapper;

  @Inject
  private ExternalInterfaceMapper externalInterfaceMapper;

  @Inject
  private PlacementHomeMapper placementHomeMapper;

  @Inject
  private EmergencyContactDetailMapper emergencyContactDetailMapper;

  @Inject
  private BackgroundCheckMapper backgroundCheckMapper;

  @Inject
  private ClientScpEthnicityMapper clientScpEthnicityMapper;

  @Inject
  private CountyOwnershipMapper countyOwnershipMapper;

  @Inject
  private SubstituteCareProviderMapper substituteCareProviderMapper;

  @Inject
  private SubstituteCareProviderUCMapper substituteCareProviderUCMapper;

  @Inject
  private PlacementHomeInformationMapper placementHomeInformationMapper;

  @Inject
  private PlacementHomeProfileMapper placementHomeProfileMapper;

  @Inject
  private PhoneContactDetailMapper phoneContactDetailMapper;

  @Inject
  private FasFacilityMapper fasFacilityMapper;

  @Inject
  private LpaInformationDao lpaInformationDao;

  @Inject
  private FacilityChildMapper facilityChildMapper;

  @Inject
  private OtherChildrenInPlacementHomeMapper otherChildMapper;

  @Inject
  private OtherPeopleScpRelationshipMapper otherPeopleScpRelationshipMapper;

  @Inject
  private OtherAdultsInPlacementHomeMapper otherAdultMapper;

  @Inject
  private OutOfStateCheckMapper outOfStateCheckMapper;

  @Inject
  private ClientDao clientDao;

  @Inject
  private InspectionDao inspectionDao;

  @Inject
  private ComplaintReportLic802Dao complaintReportLic802Dao;

  @Inject
  private FacilityTypeDao facilityTypeDao;

  @Inject
  private LicenseStatusDao licenseStatusDao;

  @Inject
  private StateDao stateDao;

  public FacilityService() {
    // default constructor
  }

  @Override
  public Response find(Serializable params) {
    return findByParameterObject((FacilityParameterObject) params);
  }

  protected FacilityDTO findExpandedById(String id) {
    return findByParameterObject(Utils.createExpandedFacilityParameterObject(id));
  }

  private FacilityDTO findByParameterObject(FacilityParameterObject parameterObject) {
    FacilityDTO facilityDTO = null;
    if (LIS.equals(parameterObject.getUnitOfWork())) {
      facilityDTO = loadFacilityFromLis(parameterObject);
    } else if (CMS.equals(parameterObject.getUnitOfWork())) {
      facilityDTO = loadFacilityFromCwsCms(parameterObject);
    }
    return facilityDTO;
  }

  private FacilityDTO loadFacilityFromLis(FacilityParameterObject parameterObject) {
    LisFacFile lisDsLisFacFile = findLisFacilityByLicenseNumber(parameterObject);
    if (lisDsLisFacFile == null) {
      LOGGER.error(
          "!!!Facility was not found in LIS by license number {}",
          parameterObject.getLicenseNumber());
      return null;
    }
    LpaInformation lpaInformation = lisDsLisFacFile.getFacDoEvalCode() != null
            ? findAssignedWorkerInformation(lisDsLisFacFile)
            : null;
    FacilityDTO facilityDTO = facilityMapper.toFacilityDTO(lisDsLisFacFile, lpaInformation);

    FacilityInformation facilityInformation = findFacilityInfoByLicenseNumber(parameterObject);
    if (facilityInformation != null) {
      attachVisitsData(facilityInformation);
    }

    fasFacilityMapper.toFacilityDTO(facilityDTO, facilityInformation);

    if (parameterObject.isExpanded()) {
      List<FacilityChildDTO> facilityChildren =
          findFacilityChildredByLicenseNumber(parameterObject.getLicenseNumber());
      List<FacilityInspectionDTO> inspections =
          findInspectionsByFacilityId(parameterObject.getLicenseNumber());
      List<ComplaintDTO> complaints =
          findComplaintsByFacilityId(parameterObject.getLicenseNumber());
      facilityDTO = facilityMapper
          .toExpandedFacilityDTO(facilityDTO, facilityChildren, inspections, complaints);
    }

    return facilityDTO;
  }

  @UnitOfWork(CMS)
  protected List<FacilityChildDTO> findFacilityChildredByLicenseNumber(Integer licenseNumber) {
    return clientDao.streamByLicenseNumber(licenseNumber)
        .map(facilityChildMapper::toFacilityChildDTO).collect(Collectors.toList());
  }

  @UnitOfWork(CMS)
  protected List<FacilityChildDTO> findFacilityChildredByFacilityId(String facilityId) {
    return clientDao
        .streamByFacilityId(facilityId)
        .map(facilityChildMapper::toFacilityChildDTO)
        .collect(Collectors.toList());
  }

  @UnitOfWork(FAS)
  protected List<FacilityInspectionDTO> findInspectionsByFacilityId(Integer licenseNumber) {
    return inspectionDao
        .findDeficienciesByFacilityNumber(licenseNumber).stream()
        .map(facilityInspectionMapper::toFacilityInspectionDto).collect(Collectors.toList());
  }

  @UnitOfWork(FAS)
  protected List<ComplaintDTO> findComplaintsByFacilityId(Integer licenseNumber) {
    return complaintReportLic802Dao
        .findComplaintsByFacilityNumber(licenseNumber).stream()
        .map(complaintMapper::entityToDTO).collect(Collectors.toList());
  }

  private FacilityDTO loadFacilityFromCwsCms(FacilityParameterObject parameterObject) {
    BasePlacementHome placementHome = findFacilityById(parameterObject);
    CMSDictionaryEntriesHolder dictionaryEntriesHolder = buildCMSDictionaryEntriesHolder(
        placementHome);
    FacilityDTO facilityDTO = facilityMapper.toFacilityDTO(placementHome, dictionaryEntriesHolder);

    if (parameterObject.isExpanded()) {
      List<FacilityChildDTO> facilityChildren = findFacilityChildredByFacilityId(
          parameterObject.getFacilityId());
      List<FacilityInspectionDTO> inspections = findInspectionsByFacilityId(
          parameterObject.getLicenseNumber());
      List<ComplaintDTO> complaints = findComplaintsByFacilityId(
          parameterObject.getLicenseNumber());
      facilityDTO = facilityMapper
          .toExpandedFacilityDTO(facilityDTO, facilityChildren, inspections, complaints);
    }

    return facilityDTO;
  }

  @UnitOfWork(CMS)
  protected CMSDictionaryEntriesHolder buildCMSDictionaryEntriesHolder(
      BasePlacementHome placementHome) {
    CMSDictionaryEntriesHolder dictionaryEntriesHolder = new CMSDictionaryEntriesHolder();
    dictionaryEntriesHolder.setApplicationCounty(
        placementHome.getGvrEntc() != 0 ? countiesDao.find(placementHome.getGvrEntc()) : null);
    dictionaryEntriesHolder.setLicenseStatus(
        placementHome.getLicStc() != 0 ? licenseStatusDao.find(placementHome.getLicStc()) : null);
    dictionaryEntriesHolder.setStateCode(
        placementHome.getStateCode() != 0 ? stateDao.find(placementHome.getStateCode()) : null);
    dictionaryEntriesHolder.setPayeeStateCode(
        placementHome.getPayeeStateCode() != 0 ? stateDao.find(placementHome.getPayeeStateCode())
            : null);
    dictionaryEntriesHolder.setFacilityType(
        placementHome.getFacilityType() != 0 ? facilityTypeDao.find(placementHome.getFacilityType())
            : null);
    return dictionaryEntriesHolder;
  }

  @UnitOfWork(LIS)
  protected LisFacFile findLisFacilityByLicenseNumber(FacilityParameterObject parameterObject) {
    LisFacFile lisFacFile = lisFacFileLisDao.find(parameterObject.getLicenseNumber());
    if (lisFacFile == null) {
      return null;
    }

    Integer countyCode = lisFacFile.getCountyCode();
    if (countyCode != null) {
      LisTableFile county = lisTableFileDao.findCounty(countyCode);
      lisFacFile.setCounty(county);
    }

    Integer facilityStatusCode = lisFacFile.getFacilityStatusCode();
    if (facilityStatusCode != null) {
      LisTableFile facilityStatus = lisTableFileDao.findFacilityStatus(facilityStatusCode);
      lisFacFile.setFacilityStatus(facilityStatus);
    }

    Integer facilityTypeCode = lisFacFile.getFacilityTypeCode();
    if (facilityTypeCode != null) {
      LisTableFile facilityType = lisTableFileDao.findFacilityType(facilityTypeCode);
      lisFacFile.setFacilityType(facilityType);
    }

    return lisFacFile;
  }

  @UnitOfWork(LIS)
  protected void attachVisitsData(FacilityInformation facilityInformation) {
    BigInteger facilityLastVisitReasonCode = facilityInformation.getFacLastVisitReason();
    if (facilityLastVisitReasonCode != null) {
      LisTableFile facilityLastVisitReason =
          lisTableFileDao.findVisitReasonType(facilityLastVisitReasonCode.intValue());
      facilityInformation.setFacilityLastVisitReason(facilityLastVisitReason);
    }

    BigInteger facilityLastDeferredVisitReasonCode = facilityInformation.getFacLastDeferVisitReason();
    if (facilityLastDeferredVisitReasonCode != null) {
      LisTableFile facilityLastDeferredVisitReason =
          lisTableFileDao.findVisitReasonType(facilityLastDeferredVisitReasonCode.intValue());
      facilityInformation.setFacilityLastDeferredVisitReason(facilityLastDeferredVisitReason);
    }
  }

  @UnitOfWork(FAS)
  protected FacilityInformation findFacilityInfoByLicenseNumber(
      FacilityParameterObject parameterObject) {
    return facilityInformationDao.find(BigInteger.valueOf(parameterObject.getLicenseNumber()));
  }

  @UnitOfWork(FAS)
  protected LpaInformation findAssignedWorkerInformation(LisFacFile lisFacFile) {
    if (lisFacFile.getFacDoNbr() == null) {
      throw new ExpectedException(
          Constants.ExpectedExceptionMessages.DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN,
          EXPECTATION_FAILED);
    }
    String lpaCode =
        String.format("%02d", lisFacFile.getFacDoNbr().getDoNbr()) + lisFacFile.getFacDoEvalCode();
    return lpaInformationDao.findByLpaCode(lpaCode);
  }

  @UnitOfWork(CMS)
  protected BasePlacementHome findFacilityById(FacilityParameterObject parameterObject) {
    Optional<PlacementHome> placementHome = Optional
        .of(placementHomeDao.findByParameterObject(parameterObject));
    Optional<BaseStaffPerson> staffPerson = placementHome
        .map(PlacementHome::getCountyLicenseCase)
        .map(BaseCountyLicenseCase::getStaffPerson);
    staffPerson.ifPresent(
        person -> person.setCounty(countiesDao.findByLogicalId(person.getCntySpfcd())));
    return placementHome.orElse(null);
  }

  @Override
  public Response delete(Serializable serializable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Response create(Request request) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Response update(Serializable serializable, Request request) {
    throw new UnsupportedOperationException();
  }

  public PlacementHome createPlacementHomeByRfaApplication(RFA1aFormDTO formDTO) {
    return storePlacementHome(formDTO);
  }

  protected PlacementHome storePlacementHome(RFA1aFormDTO form) {
    PlacementHome placementHome = placementHomeMapper.toPlacementHome(
        form, Utils.Address.getByType(form, Constants.AddressTypes.RESIDENTIAL));

    PlacementHome storedPlacementHome = xaPlacementHomeDao.create(placementHome);
    String placementHomeId = storedPlacementHome.getIdentifier();

    storePlacementHomeUc(storedPlacementHome);

    storeCountyOwnership(placementHomeId, "P", Collections.emptyList());

    storeExternalInterface();

    storeEmergencyContactDetail(placementHomeId);

    storeBackgroundCheck();

    storePlacementHomeProfile(form, placementHomeId);

    Map<Long, SubstituteCareProvider> rfaApplicantIdsMap = new HashMap<>();

    List<ApplicantDTO> applicants = Optional.ofNullable(form.getApplicants())
        .orElse(Collections.emptyList());
    for (ApplicantDTO applicant : applicants) {
      SubstituteCareProvider substituteCareProvider = storeSubstituteCareProvider(form, applicant);
      rfaApplicantIdsMap.put(applicant.getId(), substituteCareProvider);
      if (Utils.Applicant.isPrimary(form, applicant)) {
        storedPlacementHome.setPrimarySubstituteCareProvider(substituteCareProvider);
      }

      storeSubstituteCareProviderUC(substituteCareProvider.getIdentifier(), applicant);

      storeCountyOwnership(substituteCareProvider.getIdentifier(), "S", Collections.emptyList());

      storePlacementHomeInformation(form, applicant, placementHomeId, substituteCareProvider.getIdentifier());

      storePhoneContactDetails(applicant, substituteCareProvider.getIdentifier());
      storeEthnicity(applicant, substituteCareProvider.getIdentifier());

      storeOutOfStateChecks(
          state -> outOfStateCheckMapper.toOutOfStateCheck(substituteCareProvider, state),
          applicant.getRfa1bForm());

    }

    storeOtherChildren(rfaApplicantIdsMap, form, storedPlacementHome);
    storeOtherAdults(rfaApplicantIdsMap, form, storedPlacementHome);

    return storedPlacementHome;
  }

  private CountyOwnership storeCountyOwnership(String entityId, String discriminator, List<CountyType> counties) {
    CountyOwnership countyOwnership =
        countyOwnershipMapper.toCountyOwnership(entityId, discriminator, counties);
    return xaCountyOwnershipDao.create(countyOwnership);
  }

  private SubstituteCareProvider storeSubstituteCareProvider(RFA1aFormDTO form, ApplicantDTO applicant) {
    SubstituteCareProvider substituteCareProvider =
        substituteCareProviderMapper.toSubstituteCareProvider(applicant);

    substituteCareProviderMapper.toSubstituteCareProvider(substituteCareProvider, applicant.getRfa1bForm());

    RFAAddressDTO residentialAddress = Utils.Address.getByType(form, Constants.AddressTypes.RESIDENTIAL);
    substituteCareProviderMapper.toSubstituteCareProviderFromResidentialAddress(
        substituteCareProvider, residentialAddress);

    RFAAddressDTO mailingAddress = Utils.Address.getByType(form, Constants.AddressTypes.MAIL);
    substituteCareProviderMapper.toSubstituteCareProviderFromMailingAddress(
        substituteCareProvider, mailingAddress);

    return xaSubstituteCareProviderDao.create(substituteCareProvider);
  }

  private SubstituteCareProviderUc storeSubstituteCareProviderUC(
      String substituteCareProviderIdentifier, ApplicantDTO applicant) {

    SubstituteCareProviderUc substituteCareProviderUc = substituteCareProviderUCMapper
        .toSubstituteCareProviderUC(substituteCareProviderIdentifier, applicant);

    return xaSubstituteCareProviderUCDao.create(substituteCareProviderUc);
  }

  private void storeExternalInterface() {
    ExternalInterface externalInterface = externalInterfaceMapper.toExternalInterface("");
    xaExternalInterfaceDao.create(externalInterface);
  }

  private void storeEmergencyContactDetail(String placementHomeId) {
    EmergencyContactDetail emergencyContactDetail =
        emergencyContactDetailMapper.toEmergencyContactDetail(placementHomeId);
    xaEmergencyContactDetailDao.create(emergencyContactDetail);
  }

  private void storeBackgroundCheck() {
    BackgroundCheck backgroundCheck = backgroundCheckMapper.toBackgroundCheck("");
    xaBackgroundCheckDao.create(backgroundCheck);
  }

  private PlacementHomeUc storePlacementHomeUc(PlacementHome persistedPlacementHome) {
    PlacementHomeUc placementHomeUc = placementHomeMapper.toPlacementHomeUc(persistedPlacementHome);
    placementHomeUc.setLstUpdId(Id.getStaffPersonId());
    placementHomeUc.setLstUpdTs(LocalDateTime.now());
    placementHomeUc.setPkplcHmt(persistedPlacementHome.getIdentifier());

    return xaPlacementHomeUcDao.create(placementHomeUc);
  }

  private PlacementHomeInformation storePlacementHomeInformation(RFA1aFormDTO form,
      ApplicantDTO applicantDTO, String placementHomeId, String substituteCareProviderId) {
    PlacementHomeInformation placementHomeInformation =
        placementHomeInformationMapper.toPlacementHomeInformation(
            form, applicantDTO, placementHomeId, substituteCareProviderId);

    return xaPlacementHomeInformationDao.create(placementHomeInformation);
  }

  private void storePlacementHomeProfile(RFA1aFormDTO form, String placementHomeId) {
    Set<LanguageType> languageTypes = Optional.ofNullable(form.getResidence())
        .map(ResidenceDTO::getHomeLanguages)
        .orElse(Collections.emptySet());
    for (LanguageType languageType : languageTypes) {
      PlacementHomeProfile placementHomeProfile =
          placementHomeProfileMapper.toPlacementHomeProfile(languageType, placementHomeId);
      xaPlacementHomeProfileDao.create(placementHomeProfile);
    }
  }

  private void storePhoneContactDetails(ApplicantDTO applicantDTO,
      String substituteCareProviderId) {

    Optional.ofNullable(applicantDTO.getPhones()).ifPresent(
        phoneDTOS -> phoneDTOS.forEach(phoneDTO -> {
          PhoneContactDetail phoneContactDetail = phoneContactDetailMapper
              .toPhoneContactDetail(phoneDTO, substituteCareProviderId);
          phoneContactDetail.setThirdId(Utils.Id.generate());
          phoneContactDetail.setLstUpdId(Id.getStaffPersonId());
          phoneContactDetail.setLstUpdTs(LocalDateTime.now());
          xaPhoneContactDetailDao.create(phoneContactDetail);
        })
    );

  }

  private void storeEthnicity(ApplicantDTO applicantDTO, String substituteCareProviderId) {
    ClientScpEthnicity clientScpEthnicity = clientScpEthnicityMapper
        .toClientScpEthnicity(applicantDTO, substituteCareProviderId);
    clientScpEthnicity.setIdentifier(Utils.Id.generate());
    clientScpEthnicity.setLstUpdId(Id.getStaffPersonId());
    clientScpEthnicity.setLstUpdTs(LocalDateTime.now());
    xaClientScpEthnicityDao.create(clientScpEthnicity);
  }

  private void storeOtherChildren(Map<Long, SubstituteCareProvider> rfaApplicantIdsMap,
      RFA1aFormDTO form, PlacementHome persistedPlacementHome) {

    form.getMinorChildren().forEach(minorChildDTO -> {
      OtherChildrenInPlacementHome storedOtherChild = storeOtherChildrenInPlacementHome(persistedPlacementHome, minorChildDTO);
      storeOtherChildrenScpRelationships(rfaApplicantIdsMap, minorChildDTO, storedOtherChild);
    });
  }

  private OtherChildrenInPlacementHome storeOtherChildrenInPlacementHome(PlacementHome placementHome, MinorChildDTO minorChildDTO) {
    OtherChildrenInPlacementHome otherChild = otherChildMapper.toOtherChild(minorChildDTO);
    otherChild.setIdentifier(Id.generate());
    otherChild.setLstUpdId(Id.getStaffPersonId());
    otherChild.setLstUpdTs(LocalDateTime.now());
    otherChild.setFkplcHmT(placementHome.getIdentifier());
    return xaOtherChildrenDao.create(otherChild);
  }

  private void storeOtherChildrenScpRelationships(
      Map<Long, SubstituteCareProvider> rfaApplicantIdsMap, MinorChildDTO minorChildDTO,
      OtherChildrenInPlacementHome storedOtherChild) {
    minorChildDTO.getRelationshipToApplicants().forEach(
        relationshipToApplicantDTO -> {
          SubstituteCareProvider substituteCareProvider = rfaApplicantIdsMap
              .get(relationshipToApplicantDTO.getApplicantId());
          OtherPeopleScpRelationship relationship = otherPeopleScpRelationshipMapper
              .toOtherChildScpRelationship(relationshipToApplicantDTO,
                  storedOtherChild.getIdentifier(),
                  substituteCareProvider);
          relationship.setIdentifier(Id.generate());
          relationship.setLstUpdId(Id.getStaffPersonId());
          relationship.setLstUpdTs(LocalDateTime.now());
          xaOtherPeopleScpRelationshipDao.create(relationship);
        });
  }

  private void storeOtherAdults(Map<Long, SubstituteCareProvider> rfaApplicantIdsMap, RFA1aFormDTO form, PlacementHome persistedPlacementHome) {
    form.getOtherAdults()
        .forEach(
            otherAdultDTO -> {
              OtherAdultsInPlacementHome storedOtherAdult =
                  storeOtherAdultsInPlacementHome(persistedPlacementHome, otherAdultDTO);
              storeOtherAdultScpRelationships(rfaApplicantIdsMap, otherAdultDTO, storedOtherAdult);
              storeOutOfStateChecks(
                  state -> outOfStateCheckMapper.toOutOfStateCheck(storedOtherAdult, state),
                  otherAdultDTO.getRfa1bForm());
            });
  }

  private OtherAdultsInPlacementHome storeOtherAdultsInPlacementHome(
      PlacementHome persistedPlacementHome, OtherAdultDTO otherAdultDTO) {

    OtherAdultsInPlacementHome otherAdult = otherAdultMapper.toOtherAdult(otherAdultDTO);
    otherAdult.setIdentifier(Id.generate());
    otherAdult.setLstUpdId(Id.getStaffPersonId());
    otherAdult.setLstUpdTs(LocalDateTime.now());
    otherAdult.setFkplcHmT(persistedPlacementHome.getIdentifier());

    return xaOtherAdultDao.create(otherAdult);
  }

  private void storeOtherAdultScpRelationships(Map<Long, SubstituteCareProvider> rfaApplicantIdsMap,
      OtherAdultDTO otherAdultDTO, OtherAdultsInPlacementHome storedOtherAdult) {
    otherAdultDTO.getRelationshipToApplicants().forEach(
        relationshipToApplicantDTO -> {
          SubstituteCareProvider substituteCareProvider = rfaApplicantIdsMap
              .get(relationshipToApplicantDTO.getApplicantId());
          OtherPeopleScpRelationship relationship = otherPeopleScpRelationshipMapper
              .toOtherAdultScpRelationship(relationshipToApplicantDTO,
                  storedOtherAdult.getIdentifier(),
                  substituteCareProvider);
          relationship.setIdentifier(Id.generate());
          relationship.setLstUpdId(Id.getStaffPersonId());
          relationship.setLstUpdTs(LocalDateTime.now());
          xaOtherPeopleScpRelationshipDao.create(relationship);
        });
  }

  private void storeOutOfStateChecks(Function<StateType, OutOfStateCheck> buildOutOfStateFunction,
      RFA1bFormDTO formB) {
    if (formB == null || CollectionUtils.isEmpty(formB.getOtherStatesOfLiving())) {
      return;
    }
    formB.getOtherStatesOfLiving().forEach(state -> {
      OutOfStateCheck outOfStateCheck = buildOutOfStateFunction.apply(state);
      storeOutOfStateCheck(outOfStateCheck);
    });
  }

  private void storeOutOfStateCheck(OutOfStateCheck outOfStateCheck) {
    outOfStateCheck.setIdentifier(Id.generate());
    outOfStateCheck.setLstUpdId(Id.getStaffPersonId());
    outOfStateCheck.setLstUpdTs(LocalDateTime.now());
    xaOutOfStateCheckDao.create(outOfStateCheck);
  }

}
