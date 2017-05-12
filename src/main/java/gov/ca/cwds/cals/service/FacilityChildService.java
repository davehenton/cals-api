package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildService extends CrudServiceAdapter {
    private PlacementHomeDao placementHomeDao;
    private FacilityChildMapper facilityChildMapper;

    @Inject
    public FacilityChildService(PlacementHomeDao placementHomeDao, FacilityChildMapper facilityChildMapper) {
        this.placementHomeDao = placementHomeDao;
        this.facilityChildMapper = facilityChildMapper;
    }

    @Override
    public Response find(Serializable params) {
        String paramsString = (String) params;
        String[] paramsValues = paramsString.split(",");
        PlacementHome placementHome = placementHomeDao.findChild(paramsValues[0], paramsValues[1]);
        return facilityChildMapper.toFacilityChildDTO(placementHome);
    }

    @Override
    public Response delete(Serializable params) {
        return super.delete(params);
    }

    @Override
    public Response create(Request request) {
        return super.create(request);
    }

    @Override
    public Response update(Serializable params, Request request) {
        return super.update(params, request);
    }
}
