package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.service.mapper.RemoveTrailingSpaces;
import gov.ca.cwds.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Dto for facility assigned worker.
 *
 * @author Alex Serbin
 */

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AssignedWorkerDto extends BaseDTO {

  @RemoveTrailingSpaces
  @JsonProperty("id")
  @NotNull
  @ApiModelProperty("Assigned worker code")
  private String id;

  @ApiModelProperty("Assigned worker phones")
  @Valid
  private ArrayList<PersonPhoneDTO> phones = new ArrayList<>();

  @RemoveTrailingSpaces
  @ApiModelProperty("Assigned worker email")
  private String email;

  @RemoveTrailingSpaces
  @NotNull
  @ApiModelProperty("Assigned worker full name")
  private String fullName;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ArrayList<PersonPhoneDTO> getPhones() {
    return phones;
  }

  public void setPhones(ArrayList<PersonPhoneDTO> phones) {
    this.phones = phones;
  }

  public void addPhone(PersonPhoneDTO phone) {
    phones.add(phone);
  }

}
