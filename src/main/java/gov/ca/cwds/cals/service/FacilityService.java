package gov.ca.cwds.cals.service;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;
import static gov.ca.cwds.cals.Utils.StaffPerson.getStaffPersonId;
import static javax.ws.rs.core.Response.Status.EXPECTATION_FAILED;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Constants.PhoneticSearchTables;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.Utils.StaffPerson;
import gov.ca.cwds.cals.persistence.dao.cms.XASsaName3Dao;
import gov.ca.cwds.cals.persistence.dao.cms.XaClientScpEthnicityDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaEmergencyContactDetailDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaOtherAdultsInPlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaOtherChildrenInPlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaOtherPeopleScpRelationshipDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaOutOfStateCheckDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaPhoneContactDetailDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaPlacementHomeInformationDao;
import gov.ca.cwds.cals.persistence.dao.cms.XaSubstituteCareProviderUCDao;
import gov.ca.cwds.cals.persistence.dao.fas.ComplaintReportLic802Dao;
import gov.ca.cwds.cals.persistence.dao.fas.FacilityInformationDao;
import gov.ca.cwds.cals.persistence.dao.fas.InspectionDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileLisDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisTableFileDao;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
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
import gov.ca.cwds.cals.service.mapper.ClientScpEthnicityMapper;
import gov.ca.cwds.cals.service.mapper.ComplaintMapper;
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
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.cms.data.access.CWSIdentifier;
import gov.ca.cwds.cms.data.access.parameter.PlacementHomeParameterObject;
import gov.ca.cwds.cms.data.access.parameter.SCPParameterObject;
import gov.ca.cwds.cms.data.access.service.PlacementHomeService;
import gov.ca.cwds.cms.data.access.service.SubstituteCareProviderService;
import gov.ca.cwds.data.legacy.cms.dao.ClientDao;
import gov.ca.cwds.data.legacy.cms.dao.CountiesDao;
import gov.ca.cwds.data.legacy.cms.dao.FacilityTypeDao;
import gov.ca.cwds.data.legacy.cms.dao.LicenseStatusDao;
import gov.ca.cwds.data.legacy.cms.dao.PlacementHomeDao;
import gov.ca.cwds.data.legacy.cms.dao.SsaName3ParameterObject;
import gov.ca.cwds.data.legacy.cms.dao.StateDao;
import gov.ca.cwds.data.legacy.cms.entity.BaseCountyLicenseCase;
import gov.ca.cwds.data.legacy.cms.entity.BasePlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.BaseStaffPerson;
import gov.ca.cwds.data.legacy.cms.entity.ClientScpEthnicity;
import gov.ca.cwds.data.legacy.cms.entity.CountyOwnership;
import gov.ca.cwds.data.legacy.cms.entity.OtherAdultsInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OtherChildrenInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OtherPeopleScpRelationship;
import gov.ca.cwds.data.legacy.cms.entity.OutOfStateCheck;
import gov.ca.cwds.data.legacy.cms.entity.PhoneContactDetail;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHomeInformation;
import gov.ca.cwds.data.legacy.cms.entity.SubstituteCareProvider;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.exception.ExpectedException;
import gov.ca.cwds.rest.services.CrudsService;
import io.dropwizard.hibernate.UnitOfWork;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
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
  private PlacementHomeService placementHomeService;

  @Inject
  private SubstituteCareProviderService substituteCareProviderService;

  @Inject
  private LisFacFileLisDao lisFacFileLisDao;

  @Inject
  private FacilityInformationDao facilityInformationDao;

  @Inject
  private LisTableFileDao lisTableFileDao;

  @Inject
  private PlacementHomeDao placementHomeDao;

  @Inject
  private XaEmergencyContactDetailDao xaEmergencyContactDetailDao;

  @Inject
  private XaSubstituteCareProviderUCDao xaSubstituteCareProviderUCDao;

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
  private XASsaName3Dao ssaName3Dao;

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
  private PlacementHomeMapper placementHomeMapper;

  @Inject
  private ClientScpEthnicityMapper clientScpEthnicityMapper;

  @Inject
  private SubstituteCareProviderMapper substituteCareProviderMapper;

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
  private XaPlacementHomeInformationDao xaPlacementHomeInformationDao;

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
        .of(placementHomeDao.findByFacilityId(parameterObject.getFacilityId()));
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
    PlacementHome placementHome =
        createPlacementHomeInCWSCMS(form, mapRfaFormToPlacementHome(form));

    List<ApplicantDTO> applicants = Optional.ofNullable(form.getApplicants())
        .orElse(Collections.emptyList());

    Map<Long, SubstituteCareProvider> rfaApplicantIdsMap = new HashMap<>(applicants.size());
    for (ApplicantDTO applicant : applicants) {
      SubstituteCareProvider substituteCareProvider = createSubstituteCarePrivoderInCWSCMS(form,
          applicant);
      rfaApplicantIdsMap.put(applicant.getId(), substituteCareProvider);

      if (Utils.Applicant.isPrimary(form, applicant)) {
        placementHome.setPrimarySubstituteCareProvider(substituteCareProvider);
      }

      storeCountyOwnership(substituteCareProvider.getIdentifier(), "S", Collections.emptyList());

      storePlacementHomeInformation(form, applicant, placementHome.getIdentifier(),
          substituteCareProvider.getIdentifier());

      storePhoneContactDetails(applicant, substituteCareProvider.getIdentifier());
      storeEthnicity(applicant, substituteCareProvider.getIdentifier());

      storeOutOfStateChecks(
          state -> outOfStateCheckMapper.toOutOfStateCheck(substituteCareProvider, state),
          applicant.getRfa1bForm());

      prepareSubstituteCareProviderPhoneticSearchKeywords(substituteCareProvider,
          placementHome);
    }

    storeOtherChildren(rfaApplicantIdsMap, form, placementHome);
    storeOtherAdults(rfaApplicantIdsMap, form, placementHome);

    return placementHome;
  }

  private SubstituteCareProvider createSubstituteCarePrivoderInCWSCMS(RFA1aFormDTO form,
      ApplicantDTO applicant) {
    SCPParameterObject parameterObject = new SCPParameterObject();
    parameterObject.setStaffPersonId(getStaffPersonId());
    return substituteCareProviderService.create(
        mapRFAEntitiesToSCP(form, applicant), parameterObject);
  }

  private void prepareSubstituteCareProviderPhoneticSearchKeywords(
      SubstituteCareProvider substituteCareProvider,
      PlacementHome placementHome) {
    SsaName3ParameterObject parameterObject = new SsaName3ParameterObject();
    parameterObject.setTableName(PhoneticSearchTables.SCP_PHTT);
    parameterObject.setCrudOper("I");
    parameterObject.setIdentifier(substituteCareProvider.getIdentifier());
    parameterObject.setFirstName(substituteCareProvider.getFirstNm());
    parameterObject.setMiddleName(substituteCareProvider.getMidIniNm());
    parameterObject.setLastName(substituteCareProvider.getLastNm());
    parameterObject.setStreetNumber(placementHome.getStreetNo());
    parameterObject.setStreetName(placementHome.getStreetNm());
    parameterObject.setGvrEntc((short) 0);
    parameterObject.setUpdateTimeStamp(new Date());
    parameterObject.setUpdateId(substituteCareProvider.getLstUpdId());

    ssaName3Dao.callStoredProc(parameterObject);
  }

  private PlacementHome createPlacementHomeInCWSCMS(RFA1aFormDTO form,
      PlacementHome placementHome) {
    PlacementHomeParameterObject parameterObject = new PlacementHomeParameterObject();
    parameterObject.setStaffPersonId(getStaffPersonId());
    parameterObject.setHomeLanguages(getHomeLanguages(form));
    return placementHomeService
        .create(placementHome, parameterObject);
  }

  private PlacementHome mapRfaFormToPlacementHome(RFA1aFormDTO form) {
    return placementHomeMapper.toPlacementHome(
        form, Utils.Address.getByType(form, Constants.AddressTypes.RESIDENTIAL));
  }

  private Set<? extends CWSIdentifier> getHomeLanguages(RFA1aFormDTO form) {
    return Optional.ofNullable(form.getResidence())
        .map(ResidenceDTO::getHomeLanguages).orElse(Collections.emptySet());
  }

  private CountyOwnership storeCountyOwnership(String entityId, String discriminator, List<CountyType> counties) {
/*
    CountyOwnership countyOwnership =
        countyOwnershipMapper.toCountyOwnership(entityId, discriminator, counties);
    return xaCountyOwnershipDao.create(countyOwnership);
*/
    return null;
  }

  private SubstituteCareProvider mapRFAEntitiesToSCP(RFA1aFormDTO form, ApplicantDTO applicant) {
    SubstituteCareProvider substituteCareProvider =
        substituteCareProviderMapper.toSubstituteCareProvider(applicant);

    substituteCareProviderMapper.toSubstituteCareProvider(substituteCareProvider, applicant.getRfa1bForm());

    RFAAddressDTO residentialAddress = Utils.Address.getByType(form, Constants.AddressTypes.RESIDENTIAL);
    substituteCareProviderMapper.toSubstituteCareProviderFromResidentialAddress(
        substituteCareProvider, residentialAddress);

    RFAAddressDTO mailingAddress = Utils.Address.getByType(form, Constants.AddressTypes.MAIL);
    substituteCareProviderMapper.toSubstituteCareProviderFromMailingAddress(
        substituteCareProvider, mailingAddress);

    return substituteCareProvider;
  }

  private PlacementHomeInformation storePlacementHomeInformation(RFA1aFormDTO form,
      ApplicantDTO applicantDTO, String placementHomeId, String substituteCareProviderId) {
    PlacementHomeInformation placementHomeInformation =
        placementHomeInformationMapper.toPlacementHomeInformation(
            form, applicantDTO, placementHomeId, substituteCareProviderId);

    return xaPlacementHomeInformationDao.create(placementHomeInformation);
  }

  private void storePhoneContactDetails(ApplicantDTO applicantDTO,
      String substituteCareProviderId) {

    Optional.ofNullable(applicantDTO.getPhones()).ifPresent(
        phoneDTOS -> phoneDTOS.forEach(phoneDTO -> {
          PhoneContactDetail phoneContactDetail = phoneContactDetailMapper
              .toPhoneContactDetail(phoneDTO, substituteCareProviderId);
          phoneContactDetail.setThirdId(StaffPerson.generate());
          phoneContactDetail.setLstUpdId(getStaffPersonId());
          phoneContactDetail.setLstUpdTs(LocalDateTime.now());
          xaPhoneContactDetailDao.create(phoneContactDetail);
        })
    );

  }

  private void storeEthnicity(ApplicantDTO applicantDTO, String substituteCareProviderId) {
    ClientScpEthnicity clientScpEthnicity = clientScpEthnicityMapper
        .toClientScpEthnicity(applicantDTO, substituteCareProviderId);
    clientScpEthnicity.setIdentifier(StaffPerson.generate());
    clientScpEthnicity.setLstUpdId(getStaffPersonId());
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
    otherChild.setIdentifier(StaffPerson.generate());
    otherChild.setLstUpdId(getStaffPersonId());
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
          relationship.setIdentifier(StaffPerson.generate());
          relationship.setLstUpdId(getStaffPersonId());
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
    otherAdult.setIdentifier(StaffPerson.generate());
    otherAdult.setLstUpdId(getStaffPersonId());
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
          relationship.setIdentifier(StaffPerson.generate());
          relationship.setLstUpdId(getStaffPersonId());
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
    outOfStateCheck.setIdentifier(StaffPerson.generate());
    outOfStateCheck.setLstUpdId(getStaffPersonId());
    outOfStateCheck.setLstUpdTs(LocalDateTime.now());
    xaOutOfStateCheckDao.create(outOfStateCheck);
  }

}
