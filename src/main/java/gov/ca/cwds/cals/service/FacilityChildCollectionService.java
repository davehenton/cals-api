package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.service.mapper.FacilityChildrenMapper;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildCollectionService extends CrudServiceAdapter {
    private PlacementHomeDao placementHomeDao;
    private FacilityChildrenMapper facilityChildrenMapper;

    @Inject
    public FacilityChildCollectionService(PlacementHomeDao placementHomeDao, FacilityChildrenMapper facilityChildrenMapper) {
        this.placementHomeDao = placementHomeDao;
        this.facilityChildrenMapper = facilityChildrenMapper;
    }

    @Override
    public Response find(Serializable params) {
        Response resp = null;
        PlacementHome placementHome = placementHomeDao.findChildren((String) params);
        if (placementHome != null) {
            resp = facilityChildrenMapper.toFacilityChildrenDTO(placementHome);
        }
        return resp;
    }
}
