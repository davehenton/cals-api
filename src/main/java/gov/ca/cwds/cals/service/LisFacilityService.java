package gov.ca.cwds.cals.service;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;

import com.google.inject.Inject;
import gov.ca.cwds.cals.exceptions.DictionaryEntryNotFoundException;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileLisDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisTableFileDao;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.FacilityType;
import gov.ca.cwds.cals.persistence.model.fas.FacilityInformation;
import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Alexander Serbin on 3/26/2018.
 */
@SuppressWarnings("WeakerAccess")
public class LisFacilityService {

  private static final Logger LOGGER = LoggerFactory.getLogger(LisFacilityService.class);

  private static final Integer[] acceptableFacilityTypes =
      new Integer[] {
          400, 403, 430, 431, 433, 710, 711, 720, 721, 722, 726, 728, 729, 730, 731, 732, 733
      };
  private static final Set<Integer> acceptableFacilityTypesSet =
      new HashSet<>(Arrays.asList(acceptableFacilityTypes));


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
  public FacilityDTO loadFacilityFromLis(FacilityParameterObject parameterObject) {
    LisFacFile lisDsLisFacFile = findLisFacilityByLicenseNumber(parameterObject);
    if (lisDsLisFacFile == null) {
      LOGGER.warn(
          "Facility was not found in LIS by license number {}",
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
      lisFacFile = addFacilityTypeToLisFacility(lisFacFile);
    }
    return lisFacFile;
  }

  @UnitOfWork(LIS)
  LisFacFile findLisFacilityByLicenseNumberLis(FacilityParameterObject parameterObject) {
    LisFacFile lisFacFile = lisFacFileLisDao
        .find(Integer.valueOf(parameterObject.getFacilityId()));
    if (lisFacFile == null || !acceptableFacilityTypesSet.contains(lisFacFile.getFacilityTypeCode())) {
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
  LisFacFile addFacilityTypeToLisFacility(LisFacFile lisFacFile) {
    Integer facilityTypeCode = lisFacFile.getFacilityTypeCode();
    if (facilityTypeCode != null) {
      FacilityType facilityType;
      try {
        facilityType = facilityTypeService.getFacilityTypeByLisFacilityTypeId(facilityTypeCode);
      } catch (DictionaryEntryNotFoundException e) {
        facilityType = null;
        LOGGER.warn("Can't find facility type for code {}", facilityTypeCode);
      }
      lisFacFile.setFacilityType(facilityType);
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
