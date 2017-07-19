package gov.ca.cwds.cals.service.rfa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import gov.ca.cwds.cals.service.dto.rfa.RFAApplicationStatusDTO;

/**
 * @author CWDS CALS API Team
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RFAApplicationStatus {

  DRAFT("draft"),
  SUBMITTED("submitted");

  private final String name;

  RFAApplicationStatus(String name) {
    this.name = name;
  }

  @JsonValue
  public String getName() {
    return name;
  }

  public RFAApplicationStatusDTO toDTO() {
    return new RFAApplicationStatusDTO(this);
  }

}
