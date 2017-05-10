package gov.ca.cwds.cals.service;

import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author CWDS CALS API Team
 */
public class CollectionCrudServiceAdapter implements CollectionCrudService {
    @Override
    public Response find(Serializable params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response delete(Serializable params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response create(Serializable params, Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response update(Serializable params, Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Response> getAll(Serializable params) {
        throw new UnsupportedOperationException();
    }
}
