
package gov.ca.cwds.cals.service.dto.placementhome.identification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CapacityDTO {

  /**
   * #Placements
   * <p>
   */
  @JsonProperty("placements")
  private Integer placements;
  /**
   * Beds Available
   * <p>
   */
  @JsonProperty("beds_available")
  private Integer bedsAvailable;
  /**
   * Adjusted Capacity
   * <p>
   */
  @JsonProperty("adj_capacity")
  private Integer adjCapacity;

  /**
   * #Placements
   * <p>
   */
  @JsonProperty("placements")
  public Integer getPlacements() {
    return placements;
  }

  /**
   * #Placements
   * <p>
   */
  @JsonProperty("placements")
  public void setPlacements(Integer placements) {
    this.placements = placements;
  }

  /**
   * Beds Available
   * <p>
   */
  @JsonProperty("beds_available")
  public Integer getBedsAvailable() {
    return bedsAvailable;
  }

  /**
   * Beds Available
   * <p>
   */
  @JsonProperty("beds_available")
  public void setBedsAvailable(Integer bedsAvailable) {
    this.bedsAvailable = bedsAvailable;
  }

  /**
   * Adjusted Capacity
   * <p>
   */
  @JsonProperty("adj_capacity")
  public Integer getAdjCapacity() {
    return adjCapacity;
  }

  /**
   * Adjusted Capacity
   * <p>
   */
  @JsonProperty("adj_capacity")
  public void setAdjCapacity(Integer adjCapacity) {
    this.adjCapacity = adjCapacity;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("placements", placements)
        .append("bedsAvailable", bedsAvailable).append("adjCapacity", adjCapacity).toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(adjCapacity).append(placements).append(bedsAvailable)
        .toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof CapacityDTO)) {
      return false;
    }
    CapacityDTO rhs = ((CapacityDTO) other);
    return new EqualsBuilder().append(adjCapacity, rhs.adjCapacity)
        .append(placements, rhs.placements).append(bedsAvailable, rhs.bedsAvailable).isEquals();
  }

}
