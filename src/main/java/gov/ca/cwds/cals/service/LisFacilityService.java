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
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Alexander Serbin on 3/26/2018.
 */
@SuppressWarnings("WeakerAccess")
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
  public Optional<FacilityDTO> loadFacilityFromLis(FacilityParameterObject parameterObject) {
    Optional<LisFacFile> lisFacFile = findLisFacilityByLicenseNumber(parameterObject);
    FacilityDTO facilityDTO = null;
    if (lisFacFile.isPresent()) {
      LpaInformation lpaInformation = lisFacFile.get().getFacDoEvalCode() != null
          ? fasFacilityService.findAssignedWorkerInformation(lisFacFile.get())
          : null;
      facilityDTO = facilityMapper.toFacilityDTO(lisFacFile.get(), lpaInformation);
    } else {
      LOGGER.warn(
          "Facility was not found in LIS by license number {}",
          parameterObject.getFacilityId());
    }
    return Optional.ofNullable(facilityDTO);
  }

  Optional<LisFacFile> findLisFacilityByLicenseNumber(FacilityParameterObject parameterObject) {
    return findLisFacilityByLicenseNumberLis(parameterObject)
        .map(
            lisFacFile ->
                Optional.ofNullable(getFacilityType(lisFacFile.getFacilityTypeCode()))
                    .map(
                        facilityType -> {
                          lisFacFile.setFacilityType(facilityType);
                          return lisFacFile;
                        })
                    .orElse(null));
  }

  @UnitOfWork(LIS)
  Optional<LisFacFile> findLisFacilityByLicenseNumberLis(FacilityParameterObject parameterObject) {
    return Optional.ofNullable(
            lisFacFileLisDao.find(Integer.valueOf(parameterObject.getFacilityId())))
        .map(
            lisFacFile -> {
              Optional.ofNullable(lisFacFile.getCountyCode())
                  .ifPresent(
                      countyCode -> {
                        LisTableFile county = lisTableFileDao.findCounty(countyCode);
                        lisFacFile.setCounty(county);
                      });
              Optional.ofNullable(lisFacFile.getFacilityStatusCode())
                  .ifPresent(
                      facilityStatusCode -> {
                        LisTableFile facilityStatus =
                            lisTableFileDao.findFacilityStatus(facilityStatusCode);
                        lisFacFile.setFacilityStatus(facilityStatus);
                      });
              return lisFacFile;
            });
  }

  @UnitOfWork(CALSNS)
  FacilityType getFacilityType(Integer facilityTypeCode) {
    FacilityType facilityType = null;
    if (facilityTypeCode != null) {
      try {
        facilityType = facilityTypeService.getFacilityTypeByLisFacilityTypeId(facilityTypeCode);
      } catch (DictionaryEntryNotFoundException e) {
        LOGGER.warn("Can't find facility type for code {}", facilityTypeCode);
      }
    }
    return facilityType;
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
