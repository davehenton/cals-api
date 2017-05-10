package gov.ca.cwds.cals.service;

import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildService extends CollectionCrudServiceAdapter<FacilityChildDTO, Request> {
    private LisFacFileDao lisFacFileDao;
    private FacilityMapper facilityMapper;

/*
    @Inject
    public FacilityChildService(LisFacFileDao lisFacFileDao, FacilityMapper facilityMapper) {
        this.lisFacFileDao = lisFacFileDao;
        this.facilityMapper = facilityMapper;
    }
*/

    @Override
    public Response find(Serializable id) {
        LisFacFile lisFacFile = lisFacFileDao.find(id);
        return facilityMapper.lisFacilityToFacilityDTO(lisFacFile);
    }

    @Override
    public Response delete(Serializable serializable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response create(Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response update(Serializable serializable, Request request) {
        throw new UnsupportedOperationException();
    }


}
