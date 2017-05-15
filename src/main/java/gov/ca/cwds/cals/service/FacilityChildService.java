package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.mapper.FacilityChildrenMapper;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildService extends CrudServiceAdapter {
    private PlacementHomeDao placementHomeDao;
    private FacilityChildrenMapper facilityChildrenMapper;

    @Inject
    public FacilityChildService(PlacementHomeDao placementHomeDao, FacilityChildrenMapper facilityChildrenMapper) {
        this.placementHomeDao = placementHomeDao;
        this.facilityChildrenMapper = facilityChildrenMapper;
    }

    // TODO Refactor me, implement proper mapping
    @Override
    public Response find(Serializable params) {
        String paramsString = (String) params;
        String[] paramsValues = paramsString.split(",");
        PlacementHome placementHome = placementHomeDao.findChild(paramsValues[0], paramsValues[1]);

        List<FacilityChildDTO> children = facilityChildrenMapper.toFacilityChildrenDTO(placementHome).getChildren();
        if (children.isEmpty()) {
            return null;
        }
        return children.get(0);
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
