package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.model.cms.Client;
import gov.ca.cwds.cals.persistence.dao.cms.ClientDao;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildService extends CrudServiceAdapter {
    private ClientDao clientDao;
    private FacilityChildMapper facilityChildMapper;

    @Inject
    public FacilityChildService(ClientDao clientDao, FacilityChildMapper facilityChildMapper) {
        this.clientDao = clientDao;
        this.facilityChildMapper = facilityChildMapper;
    }

    @Override
    public Response find(Serializable params) {
        FacilityChildParameterObject parameterObject = (FacilityChildParameterObject) params;
        Client client = clientDao.find(parameterObject);
        return facilityChildMapper.toFacilityChildDTO(client);
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
