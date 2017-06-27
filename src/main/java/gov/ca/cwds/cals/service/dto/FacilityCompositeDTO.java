package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author CWDS TPT-2
 */
public class FacilityCompositeDTO extends FacilityDTO {

  @JsonProperty("facilityChildren")
  private List<FacilityChildDTO> facilityChildren = new ArrayList<>();

  public List<FacilityChildDTO> getFacilityChildren() {
    return facilityChildren;
  }

  public void setFacilityChildren(
      List<FacilityChildDTO> facilityChildren) {
    this.facilityChildren = facilityChildren;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    FacilityCompositeDTO that = (FacilityCompositeDTO) o;
    return Objects.equals(facilityChildren, that.facilityChildren);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), facilityChildren);
  }
}
