package gov.ca.cwds.cals.rest.services;

import gov.ca.cwds.cals.rest.api.domain.Facility;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;

import java.io.Serializable;

/**
 *
 *  CRUD service for {@link Facility}
 *
 *  @author CALS API Team
 *
 */

//TODO: implement the service
public class FacilityService implements CrudsService {

    @Override
    public Response find(Serializable id) {
        return null;
    }

    @Override
    public Response delete(Serializable serializable) {
        return null;
    }

    @Override
    public Response create(Request request) {
        return null;
    }

    @Override
    public Response update(Serializable serializable, Request request) {
        return null;
    }

}
