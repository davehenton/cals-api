package gov.ca.cwds.cals.service.dto.packet;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.cals.service.dto.BaseDTO;

/**
 * @author CWDS TPT-2
 */
@SuppressWarnings("squid:S2160")//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PersonSummaryDTO extends BaseDTO implements RequestResponse {

  private static final long serialVersionUID = 1466581714306274681L;

  private Long id;
  private String firstName;
  private String lastName;
  private Long rfa1bId;

  public PersonSummaryDTO() {
  }

  public PersonSummaryDTO(Long id, String firstName, String lastName, Long rfa1bId) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.rfa1bId = rfa1bId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Long getRfa1bId() {
    return rfa1bId;
  }

  public void setRfa1bId(Long rfa1bId) {
    this.rfa1bId = rfa1bId;
  }
}
