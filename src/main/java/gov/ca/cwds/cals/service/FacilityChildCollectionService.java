package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildCollectionService extends CrudServiceAdapter {
    private PlacementHomeDao placementHomeDao;
    private FacilityChildMapper facilityChildMapper;

    @Inject
    public FacilityChildCollectionService(PlacementHomeDao placementHomeDao, FacilityChildMapper facilityChildMapper) {
        this.placementHomeDao = placementHomeDao;
        this.facilityChildMapper = facilityChildMapper;
    }

    @Override
    public Response find(Serializable params) {
        PlacementHome placementHome = placementHomeDao.findChildren((String) params);
        return facilityChildMapper.toFacilityChildDTO(placementHome);
    }
}
