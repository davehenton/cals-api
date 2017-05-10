package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.fas.LisFacFileDao;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildService extends CollectionCrudServiceAdapter<Serializable, Request> {
    private LisFacFileDao lisFacFileDao;
    private FacilityMapper facilityMapper;

    @Inject
    public FacilityChildService(LisFacFileDao lisFacFileDao, FacilityMapper facilityMapper) {
        this.lisFacFileDao = lisFacFileDao;
        this.facilityMapper = facilityMapper;
    }

    @Override
    public Response find(Serializable params) {
        return super.find(params);
    }

    @Override
    public Response delete(Serializable params) {
        return super.delete(params);
    }

    @Override
    public Response create(Serializable params, Request request) {
        return super.create(params, request);
    }

    @Override
    public Response update(Serializable params, Request request) {
        return super.update(params, request);
    }

    @Override
    public Collection<Response> getAll(Serializable params) {
        return super.getAll(params);
    }
}
