package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/**
 * @author CWDS TPT-2
 */
public class ExpandedFacilityDTO extends FacilityDTO {
  @JsonProperty("children")
  private List<FacilityChildDTO> children;

  @JsonProperty("inspections")
  private List<FacilityInspectionDTO> inspections;

  @JsonProperty("complaints")
  private List<ComplaintDTO> complaints;

  public List<FacilityChildDTO> getChildren() {
    return children;
  }

  public void setChildren(
      List<FacilityChildDTO> children) {
    this.children = children;
  }

  public List<FacilityInspectionDTO> getInspections() {
    return inspections;
  }

  public void setInspections(List<FacilityInspectionDTO> inspections) {
    this.inspections = inspections;
  }

  public List<ComplaintDTO> getComplaints() {
    return complaints;
  }

  public void setComplaints(
      List<ComplaintDTO> complaints) {
    this.complaints = complaints;
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
    ExpandedFacilityDTO that = (ExpandedFacilityDTO) o;
    return Objects.equals(children, that.children) &&
        Objects.equals(inspections, that.inspections) &&
        Objects.equals(complaints, that.complaints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), children, inspections, complaints);
  }
}
