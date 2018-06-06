package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "person_id",
    "person_name",
    "person_type",
    "person_documents"
})
public class PeopleDocumentsDTO implements Serializable {

  private final static long serialVersionUID = -6461564888712154125L;

  @JsonProperty("person_id")
  private Integer personId;
  @JsonProperty("person_name")
  private String personName;
  @JsonProperty("person_type")
  private String personType;
  @JsonProperty("person_documents")
  private PersonDocumentsDTO personDocuments;


  public Integer getPersonId() {
    return personId;
  }

  public void setPersonId(Integer personId) {
    this.personId = personId;
  }

  public String getPersonName() {
    return personName;
  }

  public void setPersonName(String personName) {
    this.personName = personName;
  }

  public String getPersonType() {
    return personType;
  }

  public void setPersonType(String personType) {
    this.personType = personType;
  }

  public PersonDocumentsDTO getPersonDocuments() {
    return personDocuments;
  }

  public void setPersonDocuments(PersonDocumentsDTO personDocuments) {
    this.personDocuments = personDocuments;
  }

}
