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
public class SectionSummaryDTO extends BaseDTO implements RequestResponse {

  private static final long serialVersionUID = 1466581714306274681L;

  private String section;
  private Boolean filled;

  public SectionSummaryDTO() {
  }

  public SectionSummaryDTO(String section, Boolean filled) {
    this.section = section;
    this.filled = filled;
  }

  public String getSection() {
    return section;
  }

  public void setSection(String section) {
    this.section = section;
  }

  public Boolean getFilled() {
    return filled;
  }

  public void setFilled(Boolean filled) {
    this.filled = filled;
  }
}
