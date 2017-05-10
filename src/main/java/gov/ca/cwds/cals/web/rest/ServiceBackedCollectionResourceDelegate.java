package gov.ca.cwds.cals.web.rest;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import gov.ca.cwds.cals.service.CollectionCrudService;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.services.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author CWDS CALS API Team
 */
public class ServiceBackedCollectionResourceDelegate<P extends Serializable, Q extends Request> implements CollectionResourceDelegate<P, Q> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBackedCollectionResourceDelegate.class);
    private CollectionCrudService service;

    @Inject
    public ServiceBackedCollectionResourceDelegate(CollectionCrudService service) {
        this.service = service;
    }

    public Response get(P params) {
        gov.ca.cwds.rest.api.Response response = this.service.find(params);
        return response != null ? Response.ok(response).build() : Response.status(Response.Status.NOT_FOUND).entity(null).build();
    }

    public Response delete(P params) {
        gov.ca.cwds.rest.api.Response response = this.service.delete(params);
        return response != null ? Response.status(Response.Status.OK).build() : Response.status(Response.Status.NOT_FOUND).entity(null).build();
    }

    public Response create(P params, Q request) {
        Response response;

        try {
            response = Response.status(Response.Status.CREATED).entity(this.service.create(params, request)).build();
        } catch (ServiceException var4) {
            if(var4.getCause() instanceof EntityExistsException) {
                response = Response.status(Response.Status.CONFLICT).entity(null).build();
            } else {
                LOGGER.error("Unable to handle request", var4);
                response = Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(null).build();
            }
        }

        return response;
    }

    public Response update(Serializable id, Request request) {
        Response response;

        try {
            response = Response.ok().entity(this.service.update(id, request)).build();
        } catch (ServiceException se) {
            ImmutableMap entity = null;
            if(se.getCause() instanceof EntityNotFoundException) {
                if(StringUtils.isNotEmpty(se.getMessage())) {
                    ImmutableMap map = ImmutableMap.builder().put("message", se.getMessage()).build();
                    entity = map;
                }

                response = Response.status(Response.Status.NOT_FOUND).entity(entity).build();
            } else {
                LOGGER.error("Unable to handle request", se);
                response = Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(null).build();
            }
        }

        return response;
    }

    @Override
    public Response getAll(P params) {
        Collection<gov.ca.cwds.rest.api.Response> entities = this.service.getAll(params);
        GenericEntity<Collection<gov.ca.cwds.rest.api.Response>> response = new GenericEntity<Collection<gov.ca.cwds.rest.api.Response>>(entities) {};

        return response != null ? Response.ok(response).build() : Response.status(Response.Status.NOT_FOUND).entity(null).build();
    }
}
