package gov.ca.cwds.cals.exception;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ValidationDetails implements Serializable {
  private static final long serialVersionUID = 42L;

  @NotNull
  private ExceptionType type;

  @NotNull
  private String userMessage;

  private String code;
  private String incidentId;
  private String technicalMessage;
  private String stackTrace;
  private String causeStackTrace;

  public ExceptionType getType() {
    return type;
  }

  public void setType(ExceptionType type) {
    this.type = type;
  }

  public String getUserMessage() {
    return userMessage;
  }

  public void setUserMessage(String userMessage) {
    this.userMessage = userMessage;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public void setIncidentId(String incidentId) {
    this.incidentId = incidentId;
  }

  public String getTechnicalMessage() {
    return technicalMessage;
  }

  public void setTechnicalMessage(String technicalMessage) {
    this.technicalMessage = technicalMessage;
  }

  public String getStackTrace() {
    return stackTrace;
  }

  public void setStackTrace(String stackTrace) {
    this.stackTrace = stackTrace;
  }

  public String getCauseStackTrace() {
    return causeStackTrace;
  }

  public void setCauseStackTrace(String causeStackTrace) {
    this.causeStackTrace = causeStackTrace;
  }
}
