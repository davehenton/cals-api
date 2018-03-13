package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.dao.fas.ComplaintReportLic802Dao;
import gov.ca.cwds.cals.persistence.dao.fas.FacilityInformationDao;
import gov.ca.cwds.cals.persistence.dao.fas.InspectionDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileLisDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisTableFileDao;
import gov.ca.cwds.cals.persistence.model.fas.FacilityInformation;
import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.cals.service.builder.PlacementHomeEntityAwareDTOBuilder;
import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.FacilityInspectionDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.mapper.ComplaintMapper;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.service.mapper.FacilityInspectionMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FasFacilityMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.cms.data.access.service.DataAccessServicesException;
import gov.ca.cwds.cms.data.access.service.impl.PlacementHomeCoreService;
import gov.ca.cwds.data.legacy.cms.dao.ClientDao;
import gov.ca.cwds.data.legacy.cms.dao.CountiesDao;
import gov.ca.cwds.data.legacy.cms.dao.FacilityTypeDao;
import gov.ca.cwds.data.legacy.cms.dao.LicenseStatusDao;
import gov.ca.cwds.data.legacy.cms.dao.PlacementHomeDao;
import gov.ca.cwds.data.legacy.cms.dao.StateDao;
import gov.ca.cwds.data.legacy.cms.entity.BaseCountyLicenseCase;
import gov.ca.cwds.data.legacy.cms.entity.BasePlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.BaseStaffPerson;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import gov.ca.cwds.inject.InjectorHolder;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;
import io.dropwizard.hibernate.UnitOfWork;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;
import static javax.ws.rs.core.Response.Status.EXPECTATION_FAILED;

/**
 * CRUD service for {@link gov.ca.cwds.cals.service.dto.FacilityDTO}
 *
 * @author CALS API Team
 */
public class FacilityService implements CrudsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(FacilityService.class);

  @Inject
  private PlacementHomeCoreService placementHomeService;

  @Inject
  private LisFacFileLisDao lisFacFileLisDao;

  @Inject
  private FacilityInformationDao facilityInformationDao;

  @Inject
  private LisTableFileDao lisTableFileDao;

  @Inject
  private PlacementHomeDao placementHomeDao;

  @Inject
  private CountiesDao countiesDao;

  @Inject
  private FacilityMapper facilityMapper;

  @Inject
  private FacilityInspectionMapper facilityInspectionMapper;

  @Inject
  private ComplaintMapper complaintMapper;

  @Inject
  private FasFacilityMapper fasFacilityMapper;

  @Inject
  private LpaInformationDao lpaInformationDao;

  @Inject
  private FacilityChildMapper facilityChildMapper;

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

  protected FacilityDTO findByParameterObject(FacilityParameterObject parameterObject) {
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
      LOGGER.warn(
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
              findFacilityChildredByLicenseNumber(Integer.valueOf(parameterObject.getLicenseNumber()));
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
  protected List<FacilityInspectionDTO> findInspectionsByFacilityId(String licenseNumber) {
    if (StringUtils.isNotBlank(licenseNumber)) {
      return inspectionDao
              .findDeficienciesByFacilityNumber(licenseNumber).stream()
              .map(facilityInspectionMapper::toFacilityInspectionDto).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  @UnitOfWork(FAS)
  protected List<ComplaintDTO> findComplaintsByFacilityId(String licenseNumber) {
    if (StringUtils.isNotBlank(licenseNumber)) {
      return complaintReportLic802Dao
              .findComplaintsByFacilityNumber(licenseNumber).stream()
              .map(complaintMapper::entityToDTO).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
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
    LisFacFile lisFacFile = lisFacFileLisDao.find(Integer.valueOf(parameterObject.getLicenseNumber()));
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
    Long facilityLastVisitReasonCode = facilityInformation.getFacLastVisitReason();
    if (facilityLastVisitReasonCode != null) {
      LisTableFile facilityLastVisitReason =
          lisTableFileDao.findVisitReasonType(facilityLastVisitReasonCode.intValue());
      facilityInformation.setFacilityLastVisitReason(facilityLastVisitReason);
    }

    Long facilityLastDeferredVisitReasonCode = facilityInformation.getFacLastDeferVisitReason();
    if (facilityLastDeferredVisitReasonCode != null) {
      LisTableFile facilityLastDeferredVisitReason =
          lisTableFileDao.findVisitReasonType(facilityLastDeferredVisitReasonCode.intValue());
      facilityInformation.setFacilityLastDeferredVisitReason(facilityLastDeferredVisitReason);
    }
  }

  @UnitOfWork(FAS)
  protected FacilityInformation findFacilityInfoByLicenseNumber(
      FacilityParameterObject parameterObject) {
    return facilityInformationDao.find(Long.valueOf(parameterObject.getLicenseNumber()));
  }

  @UnitOfWork(FAS)
  protected LpaInformation findAssignedWorkerInformation(LisFacFile lisFacFile) {
    if (lisFacFile.getFacDoNbr() == null) {
      LOGGER.warn(Constants.ExpectedExceptionMessages.DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN);
      return null;
    } else {
      String lpaCode =
              String.format("%02d", lisFacFile.getFacDoNbr().getDoNbr()) + lisFacFile.getFacDoEvalCode();
      return lpaInformationDao.findByLpaCode(lpaCode);
    }
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

  public PlacementHome createPlacementHomeByRfaApplication(RFA1aFormDTO formDTO)
      throws DataAccessServicesException {
    return storePlacementHome(formDTO);
  }

  protected PlacementHome storePlacementHome(RFA1aFormDTO form) throws DataAccessServicesException {
    PlacementHomeEntityAwareDTOBuilder builder = new PlacementHomeEntityAwareDTOBuilder();
    InjectorHolder.INSTANCE.getInjector().injectMembers(builder);
    builder
        .appendForm(form)
        .appendEntity()
        .appendEmergencyContactDetail()
        .appendHomeLanguages()
        .appendSubstituteCareProviders()
        .appendOtherChildrenInHome()
        .appendOtherAdultsInPlacementHome();
    PlacementHome placementHome =
        placementHomeService.create(builder.getPlacementHomeEntityAwareDTO());

    return placementHome;
  }

}
