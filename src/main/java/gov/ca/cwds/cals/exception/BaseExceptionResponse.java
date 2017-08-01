package gov.ca.cwds.cals.exception;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * @author CWDS CALS API Team
 */

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaseExceptionResponse implements Serializable {

  @NotNull
  private ExceptionType exceptionType;

  @NotNull
  private List<String> userMessages = new ArrayList<>();

  private String code;
  private String incidentId;
  private List<String> technicalMessages;
  private String stackTrace;
  private String causeStackTrace;

  public void setUserMessages(List<String> userMessages) {
    this.userMessages = userMessages;
  }

  public void setExceptionType(ExceptionType exceptionType) {
    this.exceptionType = exceptionType;
  }

  public List<String> getUserMessages() {
    return userMessages;
  }

  public void addUserMessage(String userMessage) {
    if (userMessages == null) {
      userMessages = new ArrayList<>();
    }
    userMessages.add(userMessage);
  }

  public ExceptionType getExceptionType() {
    return exceptionType;
  }

  public String getStackTrace() {
    return stackTrace;
  }

  public void setStackTrace(String stackTrace) {
    this.stackTrace = stackTrace;
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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
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
    if (technicalMessages == null) {
      technicalMessages = new ArrayList<>();
    }
    technicalMessages.add(message);
  }

}
