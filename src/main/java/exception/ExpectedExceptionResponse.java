package exception;

import static exception.ExceptionType.EXPECTED_EXCEPTION;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ExpectedExceptionResponse extends BaseExceptionResponse {

  private ExceptionType exceptionType = EXPECTED_EXCEPTION;

  private String incidentId;

  private String code;

  private List<String> technicalMessages = new ArrayList<>();

  public ExceptionType getExceptionType() {
    return exceptionType;
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

}
