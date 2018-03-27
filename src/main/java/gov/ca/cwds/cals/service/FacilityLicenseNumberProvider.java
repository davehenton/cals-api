package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants.UnitOfWork;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.data.legacy.cms.dao.PlacementHomeDao;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;

/**
 * Calculates license number depending on source system: LIS or CWS/CMS.
 * Created by Alexander Serbin on 3/27/2018.
 */
public class FacilityLicenseNumberProvider {

  @Inject
  private PlacementHomeDao placementHomeDao;

  /**
   * Calculates license number depending on facility source
   */
  public String get(FacilityChildParameterObject parameterObject) {
    if (parameterObject.getUnitOfWork() == UnitOfWork.CMS) {
      //TODO replace with license number specific request to get rid of loading the whole PlacementHome
      PlacementHome placementHome = placementHomeDao.find(parameterObject.getFacilityId());
      if (placementHome != null) {
        return placementHome.getLicenseNo();
      }
    } else {
      return parameterObject.getFacilityId();
    }
    return null;
  }

}
