package gov.ca.cwds.cals.service;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;

import com.google.inject.Inject;
import gov.ca.cwds.cals.exceptions.DictionaryEntryNotFoundException;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.FacilityType;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.cms.data.access.service.impl.PlacementHomeCoreService;
import gov.ca.cwds.data.legacy.cms.dao.ClientDao;
import gov.ca.cwds.data.legacy.cms.entity.BaseCountyLicenseCase;
import gov.ca.cwds.data.legacy.cms.entity.BasePlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.BaseStaffPerson;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.County;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.LicenseStatus;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.State;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Alexander Serbin on 3/26/2018.
 */
@SuppressWarnings("WeakerAccess")
public class CwsFacilityService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CwsFacilityService.class);

  @Inject
  private PlacementHomeCoreService placementHomeService;

  @Inject
  private ClientDao clientDao;

  @Inject
  private FacilityMapper facilityMapper;

  @Inject
  private FacilityChildMapper facilityChildMapper;

  @Inject
  private ChildAssignedWorkerService childAssignedWorkerService;

  @Inject
  private FacilityTypeService facilityTypeService;

  @Inject
  private LegacyDictionariesCache legacyDicCache;

  /**
   * Load facility from CWS.
   */
  public FacilityDTO loadFacilityFromCwsCms(FacilityParameterObject parameterObject) {
    AtomicReference<FacilityDTO> facilityDTO = new AtomicReference<>();
    findFacilityById(parameterObject).ifPresent(p -> {
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
                childAssignedWorkerService
                    .findAssignedWorkerForClient(facilityChildDTO.getId())
                    .orElse(null)))
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
    PlacementHome placementHomeEntity = placementHomeService
        .find(parameterObject.getFacilityId());
    Optional<PlacementHome> placementHome = Optional.ofNullable(placementHomeEntity);
    Optional<BaseStaffPerson> staffPerson = placementHome
        .map(PlacementHome::getCountyLicenseCase)
        .map(BaseCountyLicenseCase::getStaffPerson);
    staffPerson.ifPresent(
        person -> person.setCounty(legacyDicCache
            .find(County.class, county -> person.getCntySpfcd().equals(county.getLogicalId()))));
    return placementHome;
  }

  protected CwsDictionaryEntriesHolder buildCwsDictionaryEntriesHolder(
      BasePlacementHome placementHome) {

    CwsDictionaryEntriesHolder dictionaryEntriesHolder = buildCwsDictionaryHolder(placementHome);

    return enrichDictionaryHolderWithCalsNsData(dictionaryEntriesHolder, placementHome);
  }

  @UnitOfWork(CMS)
  protected CwsDictionaryEntriesHolder buildCwsDictionaryHolder(BasePlacementHome placementHome) {
    CwsDictionaryEntriesHolder dictionaryEntriesHolder = new CwsDictionaryEntriesHolder();
    dictionaryEntriesHolder.setApplicationCounty(
        legacyDicCache.findByPrimaryKey(County.class, placementHome.getGvrEntc()));
    dictionaryEntriesHolder.setLicenseStatus(
        legacyDicCache.findByPrimaryKey(LicenseStatus.class, placementHome.getLicStc()));
    dictionaryEntriesHolder.setStateCode(
        legacyDicCache.findByPrimaryKey(State.class, placementHome.getStateCode()));
    dictionaryEntriesHolder.setPayeeStateCode(
        legacyDicCache.findByPrimaryKey(State.class, placementHome.getPayeeStateCode()));
    return dictionaryEntriesHolder;
  }

  @UnitOfWork(CALSNS)
  protected CwsDictionaryEntriesHolder enrichDictionaryHolderWithCalsNsData(
      CwsDictionaryEntriesHolder dictionaryEntriesHolder, BasePlacementHome placementHome) {
    if (placementHome.getFacilityType() != null) {
      FacilityType facilityType;
      try {
        facilityType = facilityTypeService
            .getFacilityTypeByCmsFacilityTypeId(placementHome.getFacilityType());
      } catch (DictionaryEntryNotFoundException e) {
        facilityType = null;
        StringBuilder  sb = new StringBuilder("Can't find facility type for code ")
            .append(placementHome.getFacilityType());
        LOGGER.warn(sb.toString(), e);
      }
      dictionaryEntriesHolder.setFacilityType(facilityType);
    }
    return dictionaryEntriesHolder;
  }
}
