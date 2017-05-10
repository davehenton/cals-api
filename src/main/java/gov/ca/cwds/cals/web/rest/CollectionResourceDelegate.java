package gov.ca.cwds.cals.web.rest;

import gov.ca.cwds.rest.api.Request;

import javax.ws.rs.core.Response;
import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public interface CollectionResourceDelegate<P extends Serializable, Q extends Request> {
    Response get(P params);

    Response delete(P params);

    Response create(P params, Q request);

    Response update(P params, Q request);

    Response getAll(P params);
}