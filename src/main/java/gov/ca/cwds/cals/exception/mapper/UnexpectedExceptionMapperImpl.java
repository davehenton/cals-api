package gov.ca.cwds.cals.exception.mapper;

import gov.ca.cwds.cals.Constants.ErrorMessages;
import gov.ca.cwds.cals.exception.BaseExceptionResponse;
import gov.ca.cwds.cals.exception.ExceptionType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author CWDS CALS API Team
 */

@Provider
public class UnexpectedExceptionMapperImpl implements ExceptionMapper<RuntimeException> {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(UnexpectedExceptionMapperImpl.class);

  public Response toResponse(RuntimeException ex) {
    LOGGER.error("EXCEPTION MAPPER: {}", ex.getMessage(), ex);
    BaseExceptionResponse unexpectedException = new BaseExceptionResponse();
    unexpectedException.setExceptionType(ExceptionType.UNEXPECTED_EXCEPTION);
    unexpectedException.setIncidentId(MDC.get("uniqueId"));
    unexpectedException.addUserMessage(ErrorMessages.BASE_ERROR_MESSAGE);
    unexpectedException.addTechnicalMessage(ex.getMessage());
    if (ex.getCause() != null) {
      unexpectedException.addTechnicalMessage(ex.getCause().getMessage());
      unexpectedException.setCauseStackTrace(
          StringEscapeUtils.escapeJson(ExceptionUtils.getStackTrace(ex.getCause())));
    }
    String stackTrace = ExceptionUtils.getStackTrace(ex);
    unexpectedException.setStackTrace(StringEscapeUtils.escapeJson(stackTrace));
    return Response.status(500).entity(unexpectedException).type(MediaType.APPLICATION_JSON)
        .build();
  }

}
