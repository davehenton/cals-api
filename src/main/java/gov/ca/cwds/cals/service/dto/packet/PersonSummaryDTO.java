package gov.ca.cwds.cals.service.dto.packet;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.dto.BaseDTO;

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
  private Boolean rfa1bFilled;

  public PersonSummaryDTO() {
  }

  public PersonSummaryDTO(Long id, String firstName, String lastName,
      Boolean rfa1bFilled) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.rfa1bFilled = rfa1bFilled;
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

  public Boolean getRfa1bFilled() {
    return rfa1bFilled;
  }

  public void setRfa1bFilled(Boolean rfa1bFilled) {
    this.rfa1bFilled = rfa1bFilled;
  }
}
