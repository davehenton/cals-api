package gov.ca.cwds.cals.web.rest;

import gov.ca.cwds.rest.api.Request;

import javax.ws.rs.core.Response;
import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public interface CollectionResourceDelegate {
    Response get(Serializable params);

    Response delete(Serializable params);

    Response create(Serializable params, Request request);

    Response update(Serializable params, Request request);

    Response getAll(Serializable params);
}