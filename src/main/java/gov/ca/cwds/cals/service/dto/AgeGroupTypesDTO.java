package gov.ca.cwds.cals.service.dto;

import gov.ca.cwds.cals.persistence.model.calsns.AgeGroupType;
import gov.ca.cwds.rest.api.Response;
import java.util.List;

/** @author CWDS CALS API Team */
public class AgeGroupTypesDTO extends BaseDTO implements Response {

  private static final long serialVersionUID = -6229487279451499830L;

  
  private List<AgeGroupType> ageGroupTypes;

  public List<AgeGroupType> getAgeGroupTypes() {
    return ageGroupTypes;
  }

  public void setAgeGroupTypes(List<AgeGroupType> ageGroupTypes) {
    this.ageGroupTypes = ageGroupTypes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AgeGroupTypesDTO)) {
      return false;
    }

    AgeGroupTypesDTO that = (AgeGroupTypesDTO) o;

    return ageGroupTypes != null ? ageGroupTypes.equals(that.ageGroupTypes)
        : that.ageGroupTypes == null;
  }

  @Override
  public int hashCode() {
    return ageGroupTypes != null ? ageGroupTypes.hashCode() : 0;
  }
}
