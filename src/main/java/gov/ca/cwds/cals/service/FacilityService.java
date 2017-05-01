package gov.ca.cwds.cals.service;

import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;

import java.io.Serializable;

/**
 *
 *  CRUD service for {@link FacilityDTO}
 *
 *  @author CALS API Team
 *
 */

public class FacilityService implements CrudsService {

    @Override
    public Response find(Serializable id) {
        return null;
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
