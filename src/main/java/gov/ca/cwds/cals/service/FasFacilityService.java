package gov.ca.cwds.cals.service;

import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.dao.fas.FacilityInformationDao;
import gov.ca.cwds.cals.persistence.dao.fas.InspectionDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.persistence.model.fas.FacilityInformation;
import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import gov.ca.cwds.cals.service.dto.FacilityInspectionDTO;
import gov.ca.cwds.cals.service.mapper.FacilityInspectionMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Alexander Serbin on 3/26/2018.
 */
public class FasFacilityService {

  private static final Logger LOGGER = LoggerFactory.getLogger(FasFacilityService.class);

  @Inject
  private FacilityInformationDao facilityInformationDao;

  @Inject
  private LpaInformationDao lpaInformationDao;

  @Inject
  private InspectionDao inspectionDao;

  @Inject
  private ComplaintsService complaintsService;

  @Inject
  private FacilityInspectionMapper facilityInspectionMapper;

  @UnitOfWork(FAS)
  FacilityInformation findFacilityInfoByLicenseNumber(
      FacilityParameterObject parameterObject) {
    return facilityInformationDao.find(Long.valueOf(parameterObject.getFacilityId()));
  }

  @UnitOfWork(FAS)
  LpaInformation findAssignedWorkerInformation(LisFacFile lisFacFile) {
    if (lisFacFile.getFacDoNbr() == null) {
      LOGGER.warn(Constants.ExpectedExceptionMessages.DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN);
      return null;
    } else {
      return lpaInformationDao.findByLpaCode(lisFacFile.getFacDoEvalCode());
    }
  }

  @UnitOfWork(FAS)
  List<FacilityInspectionDTO> findInspectionsByFacilityId(String licenseNumber) {
    if (StringUtils.isNotBlank(licenseNumber)) {
      return inspectionDao
          .findDeficienciesByFacilityNumber(licenseNumber).stream()
          .map(facilityInspectionMapper::toFacilityInspectionDto).collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

  Set<ComplaintDTO> findComplaintsByFacilityId(String licenseNumber) {
    if (StringUtils.isNotBlank(licenseNumber)) {
      return complaintsService.getComplaintsByFacilityId(licenseNumber);
    }
    return Collections.emptySet();
  }

}
