package gov.ca.cwds.cals.service;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
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
import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Created by Alexander Serbin on 3/26/2018.
 */
public class CwsFacilityService {

  @Inject
  private ClientDao clientDao;

  @Inject
  private PlacementHomeDao placementHomeDao;

  @Inject
  private CountiesDao countiesDao;

  @Inject
  private LicenseStatusDao licenseStatusDao;

  @Inject
  private StateDao stateDao;

  @Inject
  private FacilityTypeDao facilityTypeDao;

  @Inject
  private FacilityMapper facilityMapper;

  @Inject
  private FacilityChildMapper facilityChildMapper;

  @Inject
  private AssignedWorkerService assignedWorkerService;

  /**
   * Load facility from CWS.
   */
  public FacilityDTO loadFacilityFromCwsCms(FacilityParameterObject parameterObject) {
    AtomicReference<FacilityDTO> facilityDTO = new AtomicReference<>();
    findFacilityById(parameterObject).ifPresent((p) -> {
      CwsDictionaryEntriesHolder dictionaryEntriesHolder = buildCwsDictionaryEntriesHolder(p);
      facilityDTO.set(facilityMapper.toFacilityDTO(p, dictionaryEntriesHolder));
    });
    return facilityDTO.get();
  }

  @UnitOfWork(CMS)
  protected List<FacilityChildDTO> findFacilityChildrenByLicenseNumber(Integer licenseNumber) {
    return clientDao.streamByLicenseNumber(licenseNumber)
        .map(facilityChildMapper::toFacilityChildDTO).map(facilityChildDTO -> facilityChildMapper
            .toFacilityChildDTO(facilityChildDTO,
                assignedWorkerService.findAssignedWorkerForClient(facilityChildDTO.getId()).orElse(null)))
        .collect(Collectors.toList());
  }

  @UnitOfWork(CMS)
  protected List<FacilityChildDTO> findFacilityChildrenByFacilityId(String facilityId) {
    return clientDao
        .streamByFacilityId(facilityId)
        .map(facilityChildMapper::toFacilityChildDTO)
        .collect(Collectors.toList());
  }

  @UnitOfWork(CMS)
  protected Optional<PlacementHome> findFacilityById(FacilityParameterObject parameterObject) {
    Optional<PlacementHome> placementHome = Optional
        .ofNullable(placementHomeDao.findByFacilityId(parameterObject.getFacilityId()));
    Optional<BaseStaffPerson> staffPerson = placementHome
        .map(PlacementHome::getCountyLicenseCase)
        .map(BaseCountyLicenseCase::getStaffPerson);
    staffPerson.ifPresent(
        person -> person.setCounty(countiesDao.findByLogicalId(person.getCntySpfcd())));
    return placementHome;
  }

  @UnitOfWork(CMS)
  protected CwsDictionaryEntriesHolder buildCwsDictionaryEntriesHolder(
      BasePlacementHome placementHome) {
    CwsDictionaryEntriesHolder dictionaryEntriesHolder = new CwsDictionaryEntriesHolder();
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

}
