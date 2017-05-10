package gov.ca.cwds.cals.service;

import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.Service;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author CWDS CALS API Team
 */
public interface CollectionCrudService<P extends Serializable, Q extends Request> extends Service {
    Response find(P params);

    Response delete(P params);

    Response create(P params, Q request);

    Response update(P params, Q request);

    Collection<Response> getAll(P params);
}
