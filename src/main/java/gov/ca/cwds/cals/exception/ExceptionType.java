package gov.ca.cwds.cals.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author CWDS CALS API Team
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExceptionType {

  JSON_PROCESSING_EXCEPTION("json_processing_exception"),
  VALIDATION_ERROR("constraint_validation"),
  UNEXPECTED_EXCEPTION("unexpected_exception"),
  EXPECTED_EXCEPTION("expected_exception");

  private String value;

  ExceptionType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

}
