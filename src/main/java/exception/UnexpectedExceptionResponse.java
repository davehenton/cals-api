package exception;

import static exception.ExceptionType.UNEXPECTED_EXCEPTION;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UnexpectedExceptionResponse extends BaseExceptionResponse {

  private String stackTrace;

  private String causeStackTrace;

  private ExceptionType errorType = UNEXPECTED_EXCEPTION;

  private String incidentId;

  private List<String> technicalMessages = new ArrayList<>();

  public String getStackTrace() {
    return stackTrace;
  }

  public void setStackTrace(String stackTrace) {
    this.stackTrace = stackTrace;
  }

  public ExceptionType getErrorType() {
    return errorType;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public void setIncidentId(String incidentId) {
    this.incidentId = incidentId;
  }

  public List<String> getTechnicalMessages() {
    return technicalMessages;
  }

  public void setTechnicalMessages(List<String> technicalMessages) {
    this.technicalMessages = technicalMessages;
  }

  public void setCauseStackTrace(String causeStackTrace) {
    this.causeStackTrace = causeStackTrace;
  }

  public String getCauseStackTrace() {
    return causeStackTrace;
  }

  public void addTechnicalMessage(String message) {
    this.technicalMessages.add(message);
  }
}
