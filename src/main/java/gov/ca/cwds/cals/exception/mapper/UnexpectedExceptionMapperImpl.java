package gov.ca.cwds.cals.exception.mapper;

import gov.ca.cwds.cals.Constants.Validation.Error;
import gov.ca.cwds.cals.exception.BaseExceptionResponse;
import gov.ca.cwds.cals.exception.IssueDetails;
import gov.ca.cwds.cals.exception.IssueType;
import gov.ca.cwds.logging.LoggingContext.LogParameter;
import java.util.HashSet;
import java.util.Set;
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
    IssueDetails details = new IssueDetails();

    details.setType(IssueType.UNEXPECTED_EXCEPTION);
    details.setIncidentId(MDC.get(LogParameter.UNIQUE_ID.name()));
    details.setUserMessage(Error.BASE_MESSAGE);
    details.setTechnicalMessage(ex.getMessage());

    if (ex.getCause() != null) {
      details.setTechnicalMessage(ex.getCause().getMessage());
      details.setCauseStackTrace(
          StringEscapeUtils.escapeJson(ExceptionUtils.getStackTrace(ex.getCause())));
    }
    String stackTrace = ExceptionUtils.getStackTrace(ex);
    details.setStackTrace(StringEscapeUtils.escapeJson(stackTrace));

    Set<IssueDetails> detailsList = new HashSet<>();
    detailsList.add(details);
    BaseExceptionResponse unexpectedException = new BaseExceptionResponse();
    unexpectedException.setIssueDetails(detailsList);

    return Response.status(500).entity(unexpectedException).type(MediaType.APPLICATION_JSON)
        .build();
  }

}
