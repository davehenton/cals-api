package gov.ca.cwds.cals.service;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;
import static gov.ca.cwds.cals.exception.ExpectedExceptionInfo.DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN;
import static javax.ws.rs.core.Response.Status.EXPECTATION_FAILED;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.Utils.Id;
import gov.ca.cwds.cals.exception.ExpectedException;
import gov.ca.cwds.cals.persistence.dao.cms.ClientDao;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.FacilityTypeDao;
import gov.ca.cwds.cals.persistence.dao.cms.LicenseStatusDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeUcDao;
import gov.ca.cwds.cals.persistence.dao.cms.StateDao;
import gov.ca.cwds.cals.persistence.dao.fas.ComplaintReportLic802Dao;
import gov.ca.cwds.cals.persistence.dao.fas.FacilityInfoLisDao;
import gov.ca.cwds.cals.persistence.dao.fas.InspectionDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileLisDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisTableFileDao;
import gov.ca.cwds.cals.persistence.model.cms.BaseCountyLicenseCase;
import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.BaseStaffPerson;
import gov.ca.cwds.cals.persistence.model.cms.County;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeUc;
import gov.ca.cwds.cals.persistence.model.cms.legacy.PlacementHome;
import gov.ca.cwds.cals.persistence.model.fas.ComplaintReportLic802;
import gov.ca.cwds.cals.persistence.model.fas.FacilityInfoLis;
import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.fas.Rr809Dn;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FasFacilityMapper;
import gov.ca.cwds.cals.service.mapper.PlacementHomeMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;
import io.dropwizard.hibernate.UnitOfWork;
import java.io.Serializable;
import java.math.BigInteger;
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

  @Inject
  private FacilityInfoLisDao facilityInfoLisDao;

  @Inject
  private LisTableFileDao lisTableFileDao;

  @Inject
  private PlacementHomeDao placementHomeDao;

  @Inject
  private PlacementHomeUcDao placementHomeUcDao;

  @Inject
  private CountiesDao countiesDao;

  @Inject
  private FacilityMapper facilityMapper;

  @Inject
  private PlacementHomeMapper placementHomeMapper;

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
    LpaInformation lpaInformation = lisDsLisFacFile != null && lisDsLisFacFile.getFacDoEvalCode() != null ?
            findAssignedWorkerInformation(lisDsLisFacFile) : null;
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

  @UnitOfWork(LIS)
  protected void attachVisitsData(FacilityInfoLis facilityInfoLis) {
    BigInteger facilityLastVisitReasonCode = facilityInfoLis.getFacLastVisitReason();
    if (facilityLastVisitReasonCode != null) {
      LisTableFile facilityLastVisitReason = lisTableFileDao.findVisitReasonType(facilityLastVisitReasonCode.intValue());
      facilityInfoLis.setFacilityLastVisitReason(facilityLastVisitReason);
    }

    BigInteger facilityLastDeferredVisitReasonCode = facilityInfoLis.getFacLastDeferVisitReason();
    if (facilityLastDeferredVisitReasonCode != null) {
      LisTableFile facilityLastDeferredVisitReason = lisTableFileDao.findVisitReasonType(facilityLastDeferredVisitReasonCode.intValue());
      facilityInfoLis.setFacilityLastDeferredVisitReason(facilityLastDeferredVisitReason);
    }
  }

  @UnitOfWork(FAS)
  protected FacilityInfoLis findFacilityInfoByLicenseNumber(FacilityParameterObject parameterObject) {
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
  public PlacementHome createPlacementHomeByRfaApplication(RFA1aFormDTO formDTO) {
    PlacementHome persistedPlacementHome = storePlacementHome(formDTO);
    storePlacementHomeUc(persistedPlacementHome);

    return persistedPlacementHome;
  }

  private PlacementHomeUc storePlacementHomeUc(PlacementHome persistedPlacementHome) {
    PlacementHomeUc placementHomeUc = placementHomeMapper.toPlacementHomeUc(persistedPlacementHome);

    placementHomeUc.setLstUpdId(Id.getStaffPersonId());
    placementHomeUc.setLstUpdTs(LocalDateTime.now());
    placementHomeUc.setPkplcHmt(persistedPlacementHome.getIdentifier());

    return placementHomeUcDao.create(placementHomeUc);
  }

  private PlacementHome storePlacementHome(RFA1aFormDTO form) {
    PlacementHome placementHome = placementHomeMapper.toPlacementHome(form);
     /*TODO Fix below*/
    placementHome.setCounty(countiesDao.findAll().get(0));
    placementHome.setLicenseStatus(licenseStatusDao.findAll().get(0));
    placementHome.setFacilityType(facilityTypeDao.findAll().get(0));
    placementHome.setStateCode(stateDao.findAll().get(0));
    placementHome.setLaPayeeState(stateDao.findAll().get(0));
    return placementHomeDao.create(placementHome);
  }


}
