package gov.ca.cwds.cals.service.builder;

import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import io.dropwizard.hibernate.UnitOfWork;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;

/**
 * Created by Alexander Serbin on 1/24/2018.
 */
public class FacilityParameterObjectCMSAwareBuilder extends FacilityParameterObjectBuilder {

    @Override
    @UnitOfWork(CMS)
    public FacilityParameterObject createFacilityParameterObject(String facilityNumber) {
        return super.createFacilityParameterObject(facilityNumber);
    }

}
