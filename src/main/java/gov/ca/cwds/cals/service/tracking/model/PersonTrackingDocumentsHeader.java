package gov.ca.cwds.cals.service.tracking.model;

public class PersonTrackingDocumentsHeader {
  private Long personId;
  private String personName;
  private PersonType personType;

  public Long getPersonId() {
    return personId;
  }

  public void setPersonId(Long personId) {
    this.personId = personId;
  }

  public String getPersonName() {
    return personName;
  }

  public void setPersonName(String personName) {
    this.personName = personName;
  }

  public PersonType getPersonType() {
    return personType;
  }

  public void setPersonType(PersonType personType) {
    this.personType = personType;
  }
}
