package gov.ca.cwds.cals.service.dto.system;

import com.codahale.metrics.health.HealthCheck;
import gov.ca.cwds.cals.service.dto.BaseDTO;

import java.util.Map;

/**
 * @author CWDS CALS API Team
 */
public class HealthCheckResultDTO extends BaseDTO {
  private boolean healthy;
  private String message;
  private Throwable error;
  private Map<String, Object> details;
  private String timestamp;

  public void setResult(HealthCheck.Result result) {
    healthy = result.isHealthy();
    message = result.getMessage();
    error = result.getError();
    details = result.getDetails();
    timestamp = result.getTimestamp();
  }

  public boolean isHealthy() {
    return healthy;
  }

  public void setHealthy(boolean healthy) {
    this.healthy = healthy;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Throwable getError() {
    return error;
  }

  public void setError(Throwable error) {
    this.error = error;
  }

  public Map<String, Object> getDetails() {
    return details;
  }

  public void setDetails(Map<String, Object> details) {
    this.details = details;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }
}
