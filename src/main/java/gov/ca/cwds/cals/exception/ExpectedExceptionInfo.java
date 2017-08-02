package gov.ca.cwds.cals.exception;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author CWDS CALS API Team
 */

public enum ExpectedExceptionInfo {

  COMPLAINT_NOT_FOUND_BY_ID("1",
      "Facility Complaint is not found by Facility Number and Complaint Id"),

  DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN("2",
      "Disctrict office (lis_fac_file.fac_do_nbr) is unexpectedly empty"),

  RFA_1A_APPLICANT_NOT_FOUND_BY_ID("3", "Applicant is not found by Form Id and Applicant Id"),

  RFA_1A_APPLICATION_NOT_FOUND_BY_ID("5", "Application is not found by Form Id"),

  TRANSITION_BACK_TO_DRAFT_IS_NOT_ALLOWED(
      "6", "Transition Submitted Application to Draft status is not allowed");

  private final String code;

  private final String message;

  ExpectedExceptionInfo(String code, String message) {
    this.message = message;
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public String getCode() {
    return code;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

}
