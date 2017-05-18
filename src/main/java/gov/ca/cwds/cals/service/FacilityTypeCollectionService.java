package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.fas.FacilityTypeDao;
import gov.ca.cwds.cals.service.mapper.FacilityTypeMapper;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class FacilityTypeCollectionService extends CrudServiceAdapter {
    private FacilityTypeDao facilityTypeDao;
    private FacilityTypeMapper facilityTypeMapper;

    @Inject
    public FacilityTypeCollectionService(FacilityTypeDao facilityTypeDao, FacilityTypeMapper facilityTypeMapper) {
        this.facilityTypeDao = facilityTypeDao;
        this.facilityTypeMapper = facilityTypeMapper;
    }

    @Override
    public Response find(Serializable params) {
        return super.find(params);
    }
}
