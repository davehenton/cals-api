package gov.ca.cwds.cals.service.builder;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;

import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by Alexander Serbin on 1/23/2018.
 */
public class FacilityParameterObjectBuilder {

  public FacilityParameterObject createFacilityParameterObject(String facilityNumber) {
    FacilityParameterObject parameterObject;
    if (NumberUtils.isCreatable(facilityNumber)) {
      parameterObject = new FacilityParameterObject();
      parameterObject.setFacilityId(facilityNumber);
      parameterObject.setUnitOfWork(LIS);
    } else {
      parameterObject = new FacilityParameterObject();
      parameterObject.setFacilityId(facilityNumber);
      parameterObject.setUnitOfWork(CMS);
    }
    return parameterObject;
  }

}
