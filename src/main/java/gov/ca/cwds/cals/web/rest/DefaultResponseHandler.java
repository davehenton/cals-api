package gov.ca.cwds.cals.web.rest;

import com.google.common.collect.ImmutableMap;
import gov.ca.cwds.rest.services.ServiceException;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Source code is copy-pasted from gov.ca.cwds.rest.resources.ServiceBackedResourceDelegate.
 * Created by Alexander Serbin on 6/6/2018.
 */
public final class DefaultResponseHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultResponseHandler.class);

  private DefaultResponseHandler() {
  }

  /**
   * Provides default REST GET handling for domain object.
   */
  public static Response get(gov.ca.cwds.rest.api.Response response) {
    if (response != null) {
      return Response.ok(response).build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).entity(null).build();
    }
  }

  /**
   * Provides default REST DELETE handling for domain object.
   */
  public static Response delete(gov.ca.cwds.rest.api.Response response) {
    if (response != null) {
      return Response.status(Response.Status.OK).build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).entity(null).build();
    }
  }

  /**
   * Provides default REST CREATE handling for domain object.
   */
  public static Response create(gov.ca.cwds.rest.api.Response response) {
    Object entity;
    Response.Status responseStatus;
    if (response.hasMessages()) {
      entity = response.getMessages();
      responseStatus = Response.Status.BAD_REQUEST;
    } else {
      entity = response;
      responseStatus = Response.Status.CREATED;
    }
    return Response.status(responseStatus).entity(entity).build();
  }

  /**
   *  Provides default REST UPDATE handling for domain object.
   */
  public static Response update(gov.ca.cwds.rest.api.Response serviceResponse) {
    Response response;
    try {
      response = Response.status(Response.Status.OK).entity(serviceResponse).build();
    } catch (ServiceException e) {
      Object entity = null;
      if (e.getCause() instanceof EntityNotFoundException) {
        if (StringUtils.isNotEmpty(e.getMessage())) {
          entity = ImmutableMap.<String, String>builder().put("message", e.getMessage()).build();
        }
        response = Response.status(Response.Status.NOT_FOUND).entity(entity).build();
      } else {
        LOGGER.error("Unable to handle request", e);
        response = Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(null).build();
      }
    }
    return response;
  }

}
