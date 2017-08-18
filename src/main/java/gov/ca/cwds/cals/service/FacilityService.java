package gov.ca.cwds.cals.service;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;
import static gov.ca.cwds.cals.exception.ExpectedExceptionInfo.DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN;
import static javax.ws.rs.core.Response.Status.EXPECTATION_FAILED;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.Utils.Id;
import gov.ca.cwds.cals.exception.ExpectedException;
import gov.ca.cwds.cals.persistence.dao.calsns.CountyTypeDao;
import gov.ca.cwds.cals.persistence.dao.calsns.EducationLevelTypeDao;
import gov.ca.cwds.cals.persistence.dao.cms.BackgroundCheckDao;
import gov.ca.cwds.cals.persistence.dao.cms.CountyOwnershipDao;
import gov.ca.cwds.cals.persistence.dao.cms.EmergencyContactDetailDao;
import gov.ca.cwds.cals.persistence.dao.calsns.EthnicityTypeDao;
import gov.ca.cwds.cals.persistence.dao.calsns.GenderTypeDao;
import gov.ca.cwds.cals.persistence.dao.calsns.LanguageTypeDao;
import gov.ca.cwds.cals.persistence.dao.calsns.PhoneNumberTypeDao;
import gov.ca.cwds.cals.persistence.dao.calsns.StateTypeDao;
import gov.ca.cwds.cals.persistence.dao.cms.ClientDao;
import gov.ca.cwds.cals.persistence.dao.cms.ClientScpEthnicityDao;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.ExternalInterfaceDao;
import gov.ca.cwds.cals.persistence.dao.cms.FacilityTypeDao;
import gov.ca.cwds.cals.persistence.dao.cms.LicenseStatusDao;
import gov.ca.cwds.cals.persistence.dao.cms.OtherAdultsInPlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.OtherChildrenInPlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.OtherPeopleScpRelationshipDao;
import gov.ca.cwds.cals.persistence.dao.cms.OutOfStateCheckDao;
import gov.ca.cwds.cals.persistence.dao.cms.PhoneContactDetailDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeInformationDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeNotesDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeProfileDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeUcDao;
import gov.ca.cwds.cals.persistence.dao.cms.StateDao;
import gov.ca.cwds.cals.persistence.dao.cms.SubstituteCareProviderDao;
import gov.ca.cwds.cals.persistence.dao.cms.SubstituteCareProviderUCDao;
import gov.ca.cwds.cals.persistence.dao.fas.ComplaintReportLic802Dao;
import gov.ca.cwds.cals.persistence.dao.fas.FacilityInfoLisDao;
import gov.ca.cwds.cals.persistence.dao.fas.InspectionDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileLisDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisTableFileDao;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenderType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LanguageType;
import gov.ca.cwds.cals.persistence.model.cms.BackgroundCheck;
import gov.ca.cwds.cals.persistence.model.cms.BaseCountyLicenseCase;
import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.BaseStaffPerson;
import gov.ca.cwds.cals.persistence.model.cms.CountyOwnership;
import gov.ca.cwds.cals.persistence.model.cms.EmergencyContactDetail;
import gov.ca.cwds.cals.persistence.model.cms.ExternalInterface;
import gov.ca.cwds.cals.persistence.model.cms.ClientScpEthnicity;
import gov.ca.cwds.cals.persistence.model.cms.OtherAdultsInPlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.OtherChildrenInPlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.OtherPeopleScpRelationship;
import gov.ca.cwds.cals.persistence.model.cms.OutOfStateCheck;
import gov.ca.cwds.cals.persistence.model.cms.PhoneContactDetail;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeInformation;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeNotes;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeProfile;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeUc;
import gov.ca.cwds.cals.persistence.model.cms.SubstituteCareProvider;
import gov.ca.cwds.cals.persistence.model.cms.SubstituteCareProviderUc;
import gov.ca.cwds.cals.persistence.model.cms.legacy.PlacementHome;
import gov.ca.cwds.cals.persistence.model.fas.FacilityInfoLis;
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
import gov.ca.cwds.cals.service.mapper.PlacementHomeMapper;
import gov.ca.cwds.cals.service.mapper.PlacementHomeNotesMapper;
import gov.ca.cwds.cals.service.mapper.PlacementHomeProfileMapper;
import gov.ca.cwds.cals.service.mapper.SubstituteCareProviderMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;
import io.dropwizard.hibernate.UnitOfWork;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * CRUD service for {@link gov.ca.cwds.cals.service.dto.FacilityDTO}
 *
 * @author CALS API Team
 */
public class FacilityService implements CrudsService {

  @Inject
  private CountyTypeDao countyTypeDao;

  @Inject
  private GenderTypeDao genderTypeDao;

  @Inject
  private EducationLevelTypeDao educationLevelTypeDao;

  @Inject
  private LanguageTypeDao languageTypeDao;

  @Inject
  private LisFacFileLisDao lisFacFileLisDao;

  @Inject
  private FacilityInfoLisDao facilityInfoLisDao;

  @Inject
  private LisTableFileDao lisTableFileDao;

  @Inject
  private ExternalInterfaceDao externalInterfaceDao;

  @Inject
  private PlacementHomeDao placementHomeDao;

  @Inject
  private PlacementHomeUcDao placementHomeUcDao;

  @Inject
  private PlacementHomeNotesDao placementHomeNotesDao;

  @Inject
  private EmergencyContactDetailDao emergencyContactDetailDao;

  @Inject
  private BackgroundCheckDao backgroundCheckDao;

  @Inject
  private CountyOwnershipDao countyOwnershipDao;

  @Inject
  private SubstituteCareProviderDao substituteCareProviderDao;

  @Inject
  private SubstituteCareProviderUCDao substituteCareProviderUCDao;

  @Inject
  private PlacementHomeInformationDao placementHomeInformationDao;

  @Inject
  private PlacementHomeProfileDao placementHomeProfileDao;

  @Inject
  private PhoneContactDetailDao phoneContactDetailDao;

  @Inject
  private ClientScpEthnicityDao clientScpEthnicityDao;

  @Inject
  private OtherChildrenInPlacementHomeDao otherChildrenDao;

  @Inject
  private OtherAdultsInPlacementHomeDao otherAdultDao;

  @Inject
  private OtherPeopleScpRelationshipDao otherPeopleScpRelationshipDao;

  @Inject
  private CountiesDao countiesDao;

  @Inject
  private PhoneNumberTypeDao phoneNumberTypeDao;

  @Inject
  private EthnicityTypeDao ethnicityTypeDao;

  @Inject
  private OutOfStateCheckDao outOfStateCheckDao;

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
  private PlacementHomeNotesMapper placementHomeNotesMapper;

  @Inject
  private CountyOwnershipMapper countyOwnershipMapper;

  @Inject
  private SubstituteCareProviderMapper substituteCareProviderMapper;

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

  @Inject
  private StateTypeDao stateTypeDao;

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
    LpaInformation lpaInformation =
        lisDsLisFacFile != null && lisDsLisFacFile.getFacDoEvalCode() != null
            ? findAssignedWorkerInformation(lisDsLisFacFile)
            : null;
    FacilityDTO facilityDTO = facilityMapper.toFacilityDTO(lisDsLisFacFile, lpaInformation);

    FacilityInfoLis facilityInfoLis = findFacilityInfoByLicenseNumber(parameterObject);
    if (facilityInfoLis != null) {
      attachVisitsData(facilityInfoLis);
    }

    fasFacilityMapper.toFacilityDTO(facilityDTO, facilityInfoLis);

    if (parameterObject.isExpanded()) {
      List<FacilityChildDTO> facilityChildren = clientDao
          .streamByLicenseNumber(parameterObject.getLicenseNumber())
          .map(facilityChildMapper::toFacilityChildDTO).collect(Collectors.toList());

      List<FacilityInspectionDTO> inspections = inspectionDao
          .findDeficienciesByFacilityNumber(parameterObject.getLicenseNumber()).stream()
          .map(facilityInspectionMapper::toFacilityInspectionDto).collect(Collectors.toList());

      List<ComplaintDTO> complaints = complaintReportLic802Dao
          .findComplaintsByFacilityNumber(parameterObject.getLicenseNumber()).stream()
          .map(complaintMapper::entityToDTO).collect(Collectors.toList());

      facilityDTO = facilityMapper
          .toExpandedFacilityDTO(facilityDTO, facilityChildren, inspections, complaints);
    }

    return facilityDTO;
  }

  private FacilityDTO loadFacilityFromCwsCms(FacilityParameterObject parameterObject) {
    BasePlacementHome placementHome = findFacilityById(parameterObject);
    CMSDictionaryEntriesHolder dictionaryEntriesHolder = buildCMSDictionaryEntriesHolder(
        placementHome);
    FacilityDTO facilityDTO = facilityMapper.toFacilityDTO(placementHome, dictionaryEntriesHolder);

    if (parameterObject.isExpanded()) {
      List<FacilityChildDTO> facilityChildren = clientDao
          .streamByFacilityId(parameterObject.getFacilityId())
          .map(facilityChildMapper::toFacilityChildDTO)
          .collect(Collectors.toList());

      List<FacilityInspectionDTO> inspections = inspectionDao
          .findDeficienciesByFacilityNumber(parameterObject.getLicenseNumber()).stream()
          .map(facilityInspectionMapper::toFacilityInspectionDto).collect(Collectors.toList());

      List<ComplaintDTO> complaints = complaintReportLic802Dao
          .findComplaintsByFacilityNumber(parameterObject.getLicenseNumber()).stream()
          .map(complaintMapper::entityToDTO).collect(Collectors.toList());

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
  protected void attachVisitsData(FacilityInfoLis facilityInfoLis) {
    BigInteger facilityLastVisitReasonCode = facilityInfoLis.getFacLastVisitReason();
    if (facilityLastVisitReasonCode != null) {
      LisTableFile facilityLastVisitReason =
          lisTableFileDao.findVisitReasonType(facilityLastVisitReasonCode.intValue());
      facilityInfoLis.setFacilityLastVisitReason(facilityLastVisitReason);
    }

    BigInteger facilityLastDeferredVisitReasonCode = facilityInfoLis.getFacLastDeferVisitReason();
    if (facilityLastDeferredVisitReasonCode != null) {
      LisTableFile facilityLastDeferredVisitReason =
          lisTableFileDao.findVisitReasonType(facilityLastDeferredVisitReasonCode.intValue());
      facilityInfoLis.setFacilityLastDeferredVisitReason(facilityLastDeferredVisitReason);
    }
  }

  @UnitOfWork(FAS)
  protected FacilityInfoLis findFacilityInfoByLicenseNumber(
      FacilityParameterObject parameterObject) {
    return facilityInfoLisDao.find(BigInteger.valueOf(parameterObject.getLicenseNumber()));
  }

  @UnitOfWork(FAS)
  protected LpaInformation findAssignedWorkerInformation(LisFacFile lisFacFile) {
    if (lisFacFile.getFacDoNbr() == null) {
      throw new ExpectedException(DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN, EXPECTATION_FAILED);
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
    enrichDictionaryEntries(formDTO);
    return storePlacementHome(formDTO);
  }

  @UnitOfWork(CALSNS)
  private void enrichDictionaryEntries(RFA1aFormDTO formDTO) {
    Optional.ofNullable(formDTO.getApplicationCounty()).ifPresent(
        countyType -> Optional.ofNullable(countyTypeDao.find(countyType.getId()))
            .ifPresent(formDTO::setApplicationCounty)
    );

    Optional.ofNullable(formDTO.getResidence())
        .map(ResidenceDTO::getHomeLanguages)
        .orElse(Collections.emptySet()).forEach(languageType -> {
          Optional.ofNullable(languageTypeDao.find(languageType.getId()))
              .ifPresent(persistentLanguageType -> {
                languageType.setCwsId(persistentLanguageType.getCwsId());
                languageType.setLisId(persistentLanguageType.getLisId());
              });
        });

    Optional.ofNullable(formDTO.getApplicants())
        .orElse(Collections.emptyList()).forEach(applicantDTO -> {
          Optional.ofNullable(applicantDTO.getGender()).ifPresent(
              genderType -> Optional.ofNullable(genderTypeDao.find(genderType.getId()))
                  .ifPresent(applicantDTO::setGender)
          );

          Optional.ofNullable(applicantDTO.getHighestEducationLevel()).ifPresent(
              educationLevelType -> Optional.ofNullable(educationLevelTypeDao.find(educationLevelType.getId()))
                  .ifPresent(applicantDTO::setHighestEducationLevel)
              );

          Optional.ofNullable(applicantDTO.getPhones())
              .ifPresent(
                  phoneDTOS -> phoneDTOS.forEach(
                      phoneDTO -> Optional.ofNullable(phoneDTO.getPhoneType()).ifPresent(
                          phoneNumberType -> Optional.ofNullable(phoneNumberTypeDao.find(phoneNumberType.getId()))
                              .ifPresent(phoneDTO::setPhoneType))
                  )
              );

          Optional.ofNullable(applicantDTO.getEthnicity()).ifPresent(
             ethnicityType -> Optional.ofNullable(ethnicityTypeDao.find(ethnicityType.getId()))
                  .ifPresent(applicantDTO::setEthnicity)
          );
      });

    Optional.ofNullable(formDTO.getResidence())
        .map(ResidenceDTO::getAddresses)
        .orElse(Collections.emptyList()).forEach(address ->
            Optional.ofNullable(address.getState()).ifPresent(
                stateType -> Optional.ofNullable(stateTypeDao.find(stateType.getId()))
                    .ifPresent(address::setState)
            ));

    Optional.ofNullable(formDTO.getMinorChildren())
        .ifPresent(list -> list.forEach(this::enrichMinorChild));

  }

  private void enrichMinorChild(MinorChildDTO minorChildDTO) {
    GenderType genderType = minorChildDTO.getGender();
    String genderCwsShortCode = genderTypeDao.find(genderType.getPrimaryKey()).getCwsShortCode();
    genderType.setCwsShortCode(genderCwsShortCode);
  }

  @UnitOfWork(CMS)
  protected PlacementHome storePlacementHome(RFA1aFormDTO form) {
    PlacementHome placementHome = placementHomeMapper.toPlacementHome(
        form, Utils.Address.getByType(form, Constants.AddressTypes.RESIDENTIAL));

    placementHome.setIdentifier(Utils.Id.generate());
    PlacementHome storedPlacementHome = placementHomeDao.create(placementHome);
    String placementHomeId = storedPlacementHome.getIdentifier();

    storePlacementHomeUc(storedPlacementHome);

    storeExternalInterfaceMapper();

    storeEmergencyContactDetail(placementHomeId);

    storeBackgroundCheck();

    storePlacementHomeNotes(placementHomeId);

    storePlacementHomeProfile(form, placementHomeId);

    Map<Long, SubstituteCareProvider> rfaApplicantIdsMap = storeSubstituteCareProviders(form,
        placementHomeId);

    storeOtherChildren(form, storedPlacementHome, rfaApplicantIdsMap);

    storeOtherAdults(form, storedPlacementHome, rfaApplicantIdsMap);

    return storedPlacementHome;
  }

  private void storeExternalInterfaceMapper() {
    ExternalInterface externalInterface = externalInterfaceMapper.toExternalInterface("");
    externalInterfaceDao.create(externalInterface);
  }

  private void storeEmergencyContactDetail(String placementHomeId) {
    EmergencyContactDetail emergencyContactDetail =
        emergencyContactDetailMapper.toEmergencyContactDetail(placementHomeId);
    emergencyContactDetailDao.create(emergencyContactDetail);
  }

  private void storeBackgroundCheck() {
    BackgroundCheck backgroundCheck = backgroundCheckMapper.toBackgroundCheck("");
    backgroundCheckDao.create(backgroundCheck);
  }

  private void storePlacementHomeNotes(String placementHomeId) {
    PlacementHomeNotes placementHomeNotes = placementHomeNotesMapper.toPlacementHomeNotes(placementHomeId);
    placementHomeNotesDao.create(placementHomeNotes);
  }

  RFA1bFormDTO get1BForm(RFA1aFormDTO form, ApplicantDTO applicantDTO) {
    List<RFA1bFormDTO> rfa1bForms = form.getRfa1bForms();
    if (rfa1bForms != null) {
      for (RFA1bFormDTO rfa1bForm : rfa1bForms) {
        if (Objects.equals(rfa1bForm.getRfa1aApplicantId(), applicantDTO.getId())) {
          return rfa1bForm;
        }
      }
    }
    return null;
  }

  private PlacementHomeUc storePlacementHomeUc(PlacementHome persistedPlacementHome) {
    PlacementHomeUc placementHomeUc = placementHomeMapper.toPlacementHomeUc(persistedPlacementHome);
    placementHomeUc.setLstUpdId(Id.getStaffPersonId());
    placementHomeUc.setLstUpdTs(LocalDateTime.now());
    placementHomeUc.setPkplcHmt(persistedPlacementHome.getIdentifier());

    return placementHomeUcDao.create(placementHomeUc);
  }

  private Map<Long, SubstituteCareProvider> storeSubstituteCareProviders(RFA1aFormDTO form,
      String placementHomeId) {

    CountyOwnership phCountyOwnership =
        countyOwnershipMapper.toCountyOwnership(placementHomeId, "P", Collections.emptyList());
    countyOwnershipDao.create(phCountyOwnership);// TODO: 8/18/2017

    List<ApplicantDTO> applicants = Optional.ofNullable(form.getApplicants())
        .orElse(Collections.emptyList());

    Map<Long, SubstituteCareProvider> rfaApplicantIdsMap = new HashMap<>();

    for (int i = 0; i < applicants.size(); i++) {
      ApplicantDTO applicantDTO = applicants.get(i);

      SubstituteCareProvider substituteCareProvider =
          substituteCareProviderMapper.toSubstituteCareProvider(applicantDTO);

      RFA1bFormDTO bForm = get1BForm(form, applicantDTO);
      substituteCareProviderMapper.toSubstituteCareProvider(substituteCareProvider, bForm);

      RFAAddressDTO residentialAddress = Utils.Address
          .getByType(form, Constants.AddressTypes.RESIDENTIAL);
      substituteCareProviderMapper.toSubstituteCareProviderFromResidentialAddress(
          substituteCareProvider, residentialAddress);

      RFAAddressDTO mailingAddress = Utils.Address.getByType(form, Constants.AddressTypes.MAIL);
      substituteCareProviderMapper.toSubstituteCareProviderFromMailingAddress(
          substituteCareProvider, mailingAddress);

      SubstituteCareProvider storedSubstituteCareProvider = substituteCareProviderDao
          .create(substituteCareProvider);

      CountyOwnership scpCountyOwnership =
          countyOwnershipMapper.toCountyOwnership(
              substituteCareProvider.getIdentifier(), "S", Collections.emptyList());
      countyOwnershipDao.create(scpCountyOwnership);// TODO: 8/18/2017


      rfaApplicantIdsMap.put(applicantDTO.getId(), storedSubstituteCareProvider);

      PlacementHomeInformation placementHomeInformation =
          substituteCareProviderMapper.toPlacementHomeInformation(
              form, applicantDTO, placementHomeId, substituteCareProvider.getIdentifier());

      placementHomeInformationDao.create(placementHomeInformation);

      SubstituteCareProviderUc substituteCareProviderUc = substituteCareProviderMapper
          .toSubstituteCareProviderUC(
              storedSubstituteCareProvider.getIdentifier(), applicantDTO);

      substituteCareProviderUCDao.create(substituteCareProviderUc);

      storePhoneContactDetails(applicantDTO, substituteCareProvider.getIdentifier());
      storeEthnicity(applicantDTO, substituteCareProvider.getIdentifier());
      storeOutOfStateCheck(substituteCareProvider);
    }

    return rfaApplicantIdsMap;
  }

  private void storePlacementHomeProfile(RFA1aFormDTO form, String placementHomeId) {
    Set<LanguageType> languageTypes = Optional.ofNullable(form.getResidence())
        .map(ResidenceDTO::getHomeLanguages)
        .orElse(Collections.emptySet());
    for (LanguageType languageType : languageTypes) {
      PlacementHomeProfile placementHomeProfile =
          placementHomeProfileMapper.toPlacementHomeProfile(languageType, placementHomeId);
      placementHomeProfileDao.create(placementHomeProfile);
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
          phoneContactDetailDao.create(phoneContactDetail);
        })
    );

  }

  private void storeEthnicity(ApplicantDTO applicantDTO, String substituteCareProviderId) {
    ClientScpEthnicity clientScpEthnicity = substituteCareProviderMapper
        .toClientScpEthnicity(applicantDTO, substituteCareProviderId);
    clientScpEthnicity.setIdentifier(Utils.Id.generate());
    clientScpEthnicity.setLstUpdId(Id.getStaffPersonId());
    clientScpEthnicity.setLstUpdTs(LocalDateTime.now());
    clientScpEthnicityDao.create(clientScpEthnicity);
  }

  private void storeOutOfStateCheck(SubstituteCareProvider substituteCareProvider) {
    OutOfStateCheck outOfStateCheck = outOfStateCheckMapper
        .toOutOfStateCheck(substituteCareProvider);
    storeOutOfStateCheck(outOfStateCheck);
  }

  private void storeOtherChildren(RFA1aFormDTO form, PlacementHome persistedPlacementHome,
      Map<Long, SubstituteCareProvider> rfaApplicantIdsMap) {
    List<MinorChildDTO> minorChildren = form.getMinorChildren();

    minorChildren.forEach(minorChildDTO -> {

      OtherChildrenInPlacementHome otherChild = otherChildMapper.toOtherChild(minorChildDTO);
      otherChild.setIdentifier(Utils.Id.generate());
      otherChild.setLstUpdId(Id.getStaffPersonId());
      otherChild.setLstUpdTs(LocalDateTime.now());
      otherChild.setFkplcHmT(persistedPlacementHome.getIdentifier());
      final OtherChildrenInPlacementHome storedOtherChild = otherChildrenDao.create(otherChild);

      storeOtherChildrenScpRelationships(rfaApplicantIdsMap, minorChildDTO, storedOtherChild);

    });
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
          otherPeopleScpRelationshipDao.create(relationship);
        });
  }


  private void storeOtherAdults(RFA1aFormDTO form, PlacementHome persistedPlacementHome,
      Map<Long, SubstituteCareProvider> rfaApplicantIdsMap) {
    List<OtherAdultDTO> otherAdults = form.getOtherAdults();

    otherAdults.forEach(otherAdultDTO -> {
      OtherAdultsInPlacementHome otherAdult = otherAdultMapper.toOtherAdult(otherAdultDTO);
      otherAdult.setIdentifier(Utils.Id.generate());
      otherAdult.setLstUpdId(Id.getStaffPersonId());
      otherAdult.setLstUpdTs(LocalDateTime.now());
      otherAdult.setFkplcHmT(persistedPlacementHome.getIdentifier());

      final OtherAdultsInPlacementHome storedOtherAdult = otherAdultDao.create(otherAdult);
      storeOtherAdultScpRelationships(rfaApplicantIdsMap, otherAdultDTO, storedOtherAdult);
      storeOutOfStateCheck(storedOtherAdult);
    });
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
          otherPeopleScpRelationshipDao.create(relationship);
        });
  }

  private void storeOutOfStateCheck(OtherAdultsInPlacementHome otherAdultsInPlacementHome) {
    OutOfStateCheck outOfStateCheck = outOfStateCheckMapper
        .toOutOfStateCheck(otherAdultsInPlacementHome);
    storeOutOfStateCheck(outOfStateCheck);
  }

  private void storeOutOfStateCheck(OutOfStateCheck outOfStateCheck) {
    outOfStateCheck.setIdentifier(Id.generate());
    outOfStateCheck.setLstUpdId(Id.getStaffPersonId());
    outOfStateCheck.setLstUpdTs(LocalDateTime.now());
    outOfStateCheckDao.create(outOfStateCheck);
  }

}
