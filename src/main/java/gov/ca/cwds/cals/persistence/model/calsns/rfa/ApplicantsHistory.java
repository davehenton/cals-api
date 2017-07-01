package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team
 */

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApplicantsHistory extends BaseDTO implements Request, Response {

  private List<FormerSpouse> formerSpouses = new ArrayList<>();

  private List<AdultChild> adultChildren = new ArrayList<>();

  public List<FormerSpouse> getFormerSpouses() {
    return formerSpouses;
  }

  public void setFormerSpouses(
      List<FormerSpouse> formerSpouses) {
    this.formerSpouses = formerSpouses;
  }

  public List<AdultChild> getAdultChildren() {
    return adultChildren;
  }

  public void setAdultChildren(
      List<AdultChild> adultChildren) {
    this.adultChildren = adultChildren;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ApplicantsHistory)) {
      return false;
    }
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

}
