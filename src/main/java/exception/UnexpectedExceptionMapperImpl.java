package exception;

import gov.ca.cwds.cals.Constants.ErrorMessages;
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
    UnexpectedExceptionResponse unexpectedError = new UnexpectedExceptionResponse();
    unexpectedError.setIncidentId(MDC.get("uniqueId"));
    unexpectedError.getUserMessages().add(ErrorMessages.BASE_ERROR_MESSAGE);
    unexpectedError.getTechnicalMessages().add(ex.getMessage());
    if (ex.getCause() != null) {
      unexpectedError.addTechnicalMessage(ex.getCause().getMessage());
      unexpectedError.setCauseStackTrace(
          StringEscapeUtils.escapeJson(ExceptionUtils.getStackTrace(ex.getCause())));
    }
    String stackTrace = ExceptionUtils.getStackTrace(ex);
    unexpectedError.setStackTrace(StringEscapeUtils.escapeJson(stackTrace));
    return Response.status(500).entity(unexpectedError).type(MediaType.APPLICATION_JSON).build();
  }

}
