package gov.ca.cwds.cals.service;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.IPlacementHomeDao;
import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.County;
import gov.ca.cwds.cals.persistence.model.cms.CountyLicenseCase;
import gov.ca.cwds.cals.persistence.model.cms.StaffPerson;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.rest.api.Response;
import io.dropwizard.hibernate.UnitOfWork;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author CWDS TPT-2
 */
public class FacilityCollectionService extends CrudServiceAdapter {

  private IPlacementHomeDao placementHomeDao;
  private CountiesDao countiesDao;
  private FacilityMapper facilityMapper;

  // todo tests

  @Inject
  public FacilityCollectionService(IPlacementHomeDao placementHomeDao, CountiesDao countiesDao,
      FacilityMapper facilityMapper) {
    this.placementHomeDao = placementHomeDao;
    this.countiesDao = countiesDao;
    this.facilityMapper = facilityMapper;
  }

  @Override
  public Response find(Serializable params) {
    FacilityParameterObject parameterObject = (FacilityParameterObject) params;
    if (CMS.equals(parameterObject.getUnitOfWork())) {
      Collection<BasePlacementHome> placementHomeCollection = findFacilities(parameterObject);
      Collection<FacilityDTO> facilityDTOCollection = placementHomeCollection.stream().map(
          placementHome -> facilityMapper.toFacilityDTO(placementHome)).collect(
          Collectors.toList());
      return new CollectionDTO<>(facilityDTOCollection);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  @UnitOfWork(CMS)
  protected Collection<BasePlacementHome> findFacilities(FacilityParameterObject parameterObject) {
    Collection<BasePlacementHome> placementHomeCollection = placementHomeDao
        .findCollection(parameterObject);
    placementHomeCollection.forEach(placementHome -> {
      // todo refactor to java8
      // todo code duplication in FacilityService.findFacilityById
      if (placementHome != null) {
        CountyLicenseCase countyLicenseCase = placementHome.getCountyLicenseCase();
        if (countyLicenseCase != null) {
          StaffPerson staffPerson = countyLicenseCase.getStaffPerson();
          if (staffPerson != null) {
            County county = countiesDao.findByLogicalId(staffPerson.getCntySpfcd());
            staffPerson.setCounty(county);
          }
        }
      }
    });
    return placementHomeCollection;
  }
}
