package gov.ca.cwds.cals.service.builder;

import com.google.inject.Inject;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.data.legacy.cms.dao.PlacementHomeDao;
import org.apache.commons.lang3.math.NumberUtils;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;

/**
 * Created by Alexander Serbin on 1/23/2018.
 */
public class FacilityParameterObjectBuilder {

    @Inject
    private PlacementHomeDao placementHomeDao;

    public FacilityParameterObject createFacilityParameterObject(String facilityNumber) {
        FacilityParameterObject parameterObject;
        if (NumberUtils.isCreatable(facilityNumber)) {
            parameterObject = new FacilityParameterObject();
            parameterObject.setLicenseNumber(facilityNumber);
            parameterObject.setUnitOfWork(LIS);
        } else {
            parameterObject = new FacilityParameterObject();
            parameterObject.setFacilityId(facilityNumber);
            parameterObject.setUnitOfWork(CMS);
            parameterObject.setLicenseNumber(placementHomeDao.find(facilityNumber).getLicenseNo());
        }
        return parameterObject;
    }

    public FacilityParameterObject createExpandedFacilityParameterObject(String facilityNumber) {
        FacilityParameterObject parameterObject = createFacilityParameterObject(facilityNumber);
        parameterObject.setExpanded(true);
        return parameterObject;
    }

}
