package gov.ca.cwds.cals.service;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;
import static gov.ca.cwds.cals.web.rest.exception.CalsExceptionInfo.DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN;
import static javax.ws.rs.core.Response.Status.EXPECTATION_FAILED;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.persistence.dao.cms.ClientDao;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.FacilityTypeDao;
import gov.ca.cwds.cals.persistence.dao.cms.LicenseStatusDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.StateDao;
import gov.ca.cwds.cals.persistence.dao.fas.ComplaintReportLic802Dao;
import gov.ca.cwds.cals.persistence.dao.fas.InspectionDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileLisDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.dao.lis.LisTableFileDao;
import gov.ca.cwds.cals.persistence.model.cms.BaseCountyLicenseCase;
import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.BaseStaffPerson;
import gov.ca.cwds.cals.persistence.model.cms.County;
import gov.ca.cwds.cals.persistence.model.cms.legacy.PlacementHome;
import gov.ca.cwds.cals.persistence.model.fas.ComplaintReportLic802;
import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.fas.Rr809Dn;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FasFacilityMapper;
import gov.ca.cwds.cals.web.rest.exception.UserFriendlyException;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;
import io.dropwizard.hibernate.UnitOfWork;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CRUD service for {@link gov.ca.cwds.cals.service.dto.FacilityDTO}
 *
 * @author CALS API Team
 */
public class FacilityService implements CrudsService {

  @Inject
  private LisFacFileLisDao lisFacFileLisDao;

/*
  @Inject
  private LisFacFileFasDao lisFacFileFasDao;
*/

  @Inject
  private LisTableFileDao lisTableFileDao;

  @Inject
  private PlacementHomeDao placementHomeDao;

  @Inject
  private CountiesDao countiesDao;

  @Inject
  private FacilityMapper facilityMapper;

  @Inject
  private FasFacilityMapper fasFacilityMapper;

  @Inject
  private LpaInformationDao lpaInformationDao;

  @Inject
  private ClientDao clientDao;

  @Inject
  private FacilityChildMapper facilityChildMapper;

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
    LpaInformation lpaInformation =
        lisDsLisFacFile != null ? findAssignedWorkerInformation(lisDsLisFacFile) : null;
    FacilityDTO facilityDTO = facilityMapper.toFacilityDTO(lisDsLisFacFile, lpaInformation);

    LisFacFile fasDsLisFacFile = findFasFacilityByLicenseNumber(parameterObject);
    fasFacilityMapper.toFacilityDTO(facilityDTO, fasDsLisFacFile);

    if (parameterObject.isExpanded()) {
      List<FacilityChildDTO> facilityChildren = clientDao
          .streamByLicenseNumber(parameterObject.getLicenseNumber())
          .map(facilityChildMapper::toFacilityChildDTO).collect(Collectors.toList());

      List<Rr809Dn> inspections = inspectionDao
          .findDeficienciesByFacilityNumber(parameterObject.getLicenseNumber());

      List<ComplaintReportLic802> complaints = complaintReportLic802Dao
          .findComplaintsByFacilityNumber(parameterObject.getLicenseNumber());

      facilityDTO = facilityMapper
          .toExpandedFacilityDTO(facilityDTO, facilityChildren, inspections, complaints);
    }

    return facilityDTO;
  }

  private FacilityDTO loadFacilityFromCwsCms(FacilityParameterObject parameterObject) {
    BasePlacementHome placementHome = findFacilityById(parameterObject);
    FacilityDTO facilityDTO = facilityMapper.toFacilityDTO(placementHome);

    if (parameterObject.isExpanded()) {
      List<FacilityChildDTO> facilityChildren = clientDao
          .streamByFacilityId(parameterObject.getFacilityId())
          .map(facilityChildMapper::toFacilityChildDTO).collect(Collectors.toList());

      List<Rr809Dn> inspections = inspectionDao
          .findDeficienciesByFacilityNumber(parameterObject.getLicenseNumber());

      List<ComplaintReportLic802> complaints = complaintReportLic802Dao
          .findComplaintsByFacilityNumber(parameterObject.getLicenseNumber());

      facilityDTO = facilityMapper
          .toExpandedFacilityDTO(facilityDTO, facilityChildren, inspections, complaints);
    }

    return facilityDTO;
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

  @UnitOfWork(FAS)
  protected LisFacFile findFasFacilityByLicenseNumber(FacilityParameterObject parameterObject) {
/*
    LisFacFile lisFacFile = lisFacFileFasDao.find(parameterObject.getLicenseNumber());
    if (lisFacFile == null) {
      return null;
    }

    Integer facilityLastVisitReasonCode = lisFacFile.getFacilityLastVisitReasonCode();
    if (facilityLastVisitReasonCode != null) {
      LisTableFile facilityLastVisitReason = lisTableFileDao.findFacilityVisitReason(facilityLastVisitReasonCode);
      lisFacFile.setFacilityLastVisitReason(facilityLastVisitReason);
    }

    Integer facilityLastDeferredVisitReasonCode = lisFacFile.getFacilityLastDeferredVisitReasonCode();
    if (facilityLastDeferredVisitReasonCode != null) {
      LisTableFile facilityLastDeferredVisitReason = lisTableFileDao.findFacilityVisitReason(facilityLastVisitReasonCode);
      lisFacFile.setFacilityLastDeferredVisitReason(facilityLastDeferredVisitReason);
    }

    return lisFacFile;
*/
    return null;
  }

  @UnitOfWork(FAS)
  protected LpaInformation findAssignedWorkerInformation(LisFacFile lisFacFile) {
    if (lisFacFile.getFacDoNbr() == null) {
      throw new UserFriendlyException(DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN, EXPECTATION_FAILED);
    }
    String lpaCode =
        String.format("%02d", lisFacFile.getFacDoNbr().getDoNbr()) + lisFacFile.getFacDoEvalCode();
    return lpaInformationDao.findByLpaCode(lpaCode);
  }

  @UnitOfWork(CMS)
  protected BasePlacementHome findFacilityById(FacilityParameterObject parameterObject) {
    // todo refactor to java8
    BasePlacementHome placementHome = placementHomeDao.findByParameterObject(parameterObject);
    if (placementHome != null) {
      BaseCountyLicenseCase countyLicenseCase = placementHome.getCountyLicenseCase();
      if (countyLicenseCase != null) {
        BaseStaffPerson staffPerson = countyLicenseCase.getStaffPerson();
        if (staffPerson != null) {
          County county = countiesDao.findByLogicalId(staffPerson.getCntySpfcd());
          staffPerson.setCounty(county);
        }
      }
    }
    return placementHome;
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

  @UnitOfWork(CMS)
  public PlacementHome createPlacementHomeByRfaApplication(RFA1aForm form) {
    PlacementHome placementHome = facilityMapper.toPlacementHome(form);
    placementHome.setFacilityType(facilityTypeDao.findAll().get(0));
    placementHome.setCounty(countiesDao.findAll().get(0));
    placementHome.setLicenseStatus(licenseStatusDao.findAll().get(0));
    placementHome.setStateCode(stateDao.findAll().get(0));
    placementHome.setLstUpdTs(LocalDateTime.now());
    placementHome.setLaPayeeState(stateDao.findAll().get(0));
    //
    return placementHomeDao.create(placementHome);
  }

}
