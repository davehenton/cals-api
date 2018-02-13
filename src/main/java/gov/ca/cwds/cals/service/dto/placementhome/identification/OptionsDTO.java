
package gov.ca.cwds.cals.service.dto.placementhome.identification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "transitional",
    "on_hold",
    "inactivated",
    "at_capacity",
    "adoption_only"
})
public class OptionsDTO {

  /**
   * Transitional Housing Placement Program Facility
   * <p>
   */
  @JsonProperty("transitional")
  private Boolean transitional;
  /**
   * This home is on hold
   * <p>
   * Please see the Hold Status page
   */
  @JsonProperty("on_hold")
  @JsonPropertyDescription("Please see the Hold Status page")
  private Boolean onHold;
  /**
   * Home Inactivated by Process Placement Home Move
   * <p>
   */
  @JsonProperty("inactivated")
  private Boolean inactivated;
  /**
   * At Capacity
   * <p>
   */
  @JsonProperty("at_capacity")
  private Boolean atCapacity;
  /**
   * Adoption Only
   * <p>
   */
  @JsonProperty("adoption_only")
  private Boolean adoptionOnly;

  /**
   * Transitional Housing Placement Program Facility
   * <p>
   */
  @JsonProperty("transitional")
  public Boolean getTransitional() {
    return transitional;
  }

  /**
   * Transitional Housing Placement Program Facility
   * <p>
   */
  @JsonProperty("transitional")
  public void setTransitional(Boolean transitional) {
    this.transitional = transitional;
  }

  /**
   * This home is on hold
   * <p>
   * Please see the Hold Status page
   */
  @JsonProperty("on_hold")
  public Boolean getOnHold() {
    return onHold;
  }

  /**
   * This home is on hold
   * <p>
   * Please see the Hold Status page
   */
  @JsonProperty("on_hold")
  public void setOnHold(Boolean onHold) {
    this.onHold = onHold;
  }

  /**
   * Home Inactivated by Process Placement Home Move
   * <p>
   */
  @JsonProperty("inactivated")
  public Boolean getInactivated() {
    return inactivated;
  }

  /**
   * Home Inactivated by Process Placement Home Move
   * <p>
   */
  @JsonProperty("inactivated")
  public void setInactivated(Boolean inactivated) {
    this.inactivated = inactivated;
  }

  /**
   * At Capacity
   * <p>
   */
  @JsonProperty("at_capacity")
  public Boolean getAtCapacity() {
    return atCapacity;
  }

  /**
   * At Capacity
   * <p>
   */
  @JsonProperty("at_capacity")
  public void setAtCapacity(Boolean atCapacity) {
    this.atCapacity = atCapacity;
  }

  /**
   * Adoption Only
   * <p>
   */
  @JsonProperty("adoption_only")
  public Boolean getAdoptionOnly() {
    return adoptionOnly;
  }

  /**
   * Adoption Only
   * <p>
   */
  @JsonProperty("adoption_only")
  public void setAdoptionOnly(Boolean adoptionOnly) {
    this.adoptionOnly = adoptionOnly;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("transitional", transitional).append("onHold", onHold)
        .append("inactivated", inactivated).append("atCapacity", atCapacity)
        .append("adoptionOnly", adoptionOnly).toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(atCapacity).append(transitional).append(inactivated)
        .append(adoptionOnly).append(onHold).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof OptionsDTO)) {
      return false;
    }
    OptionsDTO rhs = ((OptionsDTO) other);
    return new EqualsBuilder().append(atCapacity, rhs.atCapacity)
        .append(transitional, rhs.transitional).append(inactivated, rhs.inactivated)
        .append(adoptionOnly, rhs.adoptionOnly).append(onHold, rhs.onHold).isEquals();
  }

}
