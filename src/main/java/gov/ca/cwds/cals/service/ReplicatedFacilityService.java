package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.rs.ReplicatedPlacementHomeDao;
import gov.ca.cwds.cals.persistence.model.cms.BaseCountyLicenseCase;
import gov.ca.cwds.cals.persistence.model.cms.BaseStaffPerson;
import gov.ca.cwds.cals.persistence.model.cms.County;
import gov.ca.cwds.cals.service.dto.rs.ReplicatedFacilityDTO;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;

import java.util.stream.Stream;

/**
 * @author CWDS TPT-2
 */
public class ReplicatedFacilityService extends CrudServiceAdapter {

  private ReplicatedPlacementHomeDao placementHomeDao;
  private CountiesDao countiesDao;
  private FacilityMapper facilityMapper;

  // todo tests

  @Inject
  public ReplicatedFacilityService(ReplicatedPlacementHomeDao placementHomeDao,
      CountiesDao countiesDao, FacilityMapper facilityMapper) {
    this.placementHomeDao = placementHomeDao;
    this.countiesDao = countiesDao;
    this.facilityMapper = facilityMapper;
  }

  public Stream<ReplicatedFacilityDTO> facilitiesStream(FacilityParameterObject parameterObject) {
    return placementHomeDao.stream(parameterObject)
        //TODO move to SELECT
        .map(placementHome -> {
          BaseCountyLicenseCase countyLicenseCase = placementHome.getCountyLicenseCase();
          if (countyLicenseCase != null) {
            BaseStaffPerson staffPerson = countyLicenseCase.getStaffPerson();
            if (staffPerson != null) {
              County county = countiesDao.findByLogicalId(staffPerson.getCntySpfcd());
              staffPerson.setCounty(county);
            }
          }
          return placementHome;
        })
        .map(placementHome -> facilityMapper.toReplicatedFacilityDTO(placementHome));
  }
}
