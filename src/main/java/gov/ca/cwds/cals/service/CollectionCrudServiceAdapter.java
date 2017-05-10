package gov.ca.cwds.cals.service;

import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author CWDS CALS API Team
 */
public class CollectionCrudServiceAdapter<P extends Serializable, Q extends Request> implements CollectionCrudService<P, Q> {
    @Override
    public Response find(P params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response delete(P params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response create(P params, Q request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response update(P params, Q request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Response> getAll(P params) {
        throw new UnsupportedOperationException();
    }
}
