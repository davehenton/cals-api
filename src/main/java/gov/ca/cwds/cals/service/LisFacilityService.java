package gov.ca.cwds.cals.service;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileLisDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisTableFileDao;
import gov.ca.cwds.cals.persistence.model.fas.FacilityInformation;
import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Alexander Serbin on 3/26/2018.
 */
public class LisFacilityService {

  private static final Logger LOGGER = LoggerFactory.getLogger(LisFacilityService.class);

  @Inject
  private LisTableFileDao lisTableFileDao;

  @Inject
  private LisFacFileLisDao lisFacFileLisDao;

  @Inject
  private FacilityMapper facilityMapper;

  @Inject
  private FasFacilityService fasFacilityService;

  @Inject
  private FacilityTypeService facilityTypeService;

  /**
   * Load facility from LIS.
   */
  FacilityDTO loadFacilityFromLis(FacilityParameterObject parameterObject) {
    LisFacFile lisDsLisFacFile = findLisFacilityByLicenseNumber(parameterObject);
    if (lisDsLisFacFile == null) {
      LOGGER.warn(
          "!!!Facility was not found in LIS by license number {}",
          parameterObject.getFacilityId());
      return null;
    }
    LpaInformation lpaInformation = lisDsLisFacFile.getFacDoEvalCode() != null
        ? fasFacilityService.findAssignedWorkerInformation(lisDsLisFacFile)
        : null;
    return facilityMapper.toFacilityDTO(lisDsLisFacFile, lpaInformation);
  }

  LisFacFile findLisFacilityByLicenseNumber(FacilityParameterObject parameterObject) {
    LisFacFile lisFacFile = findLisFacilityByLicenseNumberLis(parameterObject);
    if (null != lisFacFile) {
      lisFacFile = addFacilityTypeToLisFacilityAndReturn(lisFacFile);
    }
    return lisFacFile;
  }

  @UnitOfWork(LIS)
  LisFacFile findLisFacilityByLicenseNumberLis(FacilityParameterObject parameterObject) {
    LisFacFile lisFacFile = lisFacFileLisDao
        .find(Integer.valueOf(parameterObject.getFacilityId()));
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
    return lisFacFile;
  }

  @UnitOfWork(CALSNS)
  LisFacFile addFacilityTypeToLisFacilityAndReturn(LisFacFile lisFacFile) {
    Integer facilityTypeCode = lisFacFile.getFacilityTypeCode();
    if (facilityTypeCode != null) {
      lisFacFile.setFacilityType(
          facilityTypeService.getFacilityTypeByLisFacilityTypeId(facilityTypeCode));
    }
    return lisFacFile;
  }

  @UnitOfWork(LIS)
  void attachVisitsData(FacilityInformation facilityInformation) {
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
}
