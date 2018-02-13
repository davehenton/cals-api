
package gov.ca.cwds.cals.service.dto.placementhome.identification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * State
 * <p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "value"
})
public class StateDTO {

  @JsonProperty("id")
  private String id;
  @JsonProperty("value")
  private String value;

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  @JsonProperty("value")
  public String getValue() {
    return value;
  }

  @JsonProperty("value")
  public void setValue(String value) {
    this.value = value;
  }

}
