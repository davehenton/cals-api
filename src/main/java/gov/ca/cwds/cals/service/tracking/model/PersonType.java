package gov.ca.cwds.cals.service.tracking.model;

public enum PersonType {

  APPLICANT("Applicant"),
  OTHER_ADULT("Other Adult");
  private String typeName;

  PersonType(String typeName) {
    this.typeName = typeName;
  }

  public String toString() {
    return typeName;
  }
}
