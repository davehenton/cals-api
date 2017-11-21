package gov.ca.cwds.cals.service.dto.packet;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.dto.BaseDTO;
import java.util.List;

/**
 * @author CWDS TPT-2
 */
@SuppressWarnings("squid:S2160")//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PacketDTO extends BaseDTO implements RequestResponse {

  public PacketDTO() {
  }

  public PacketDTO(
      List<SectionSummaryDTO> rfa1aSections,
      List<PersonSummaryDTO> applicants,
      List<PersonSummaryDTO> otherAdults,
      List<PersonSummaryDTO> adultChildren) {
    this.rfa1aSections = rfa1aSections;
    this.applicants = applicants;
    this.otherAdults = otherAdults;
    this.adultChildren = adultChildren;
  }

  private static final long serialVersionUID = 1466581714306274681L;

  private List<SectionSummaryDTO> rfa1aSections;
  private List<PersonSummaryDTO> applicants;
  private List<PersonSummaryDTO> otherAdults;
  private List<PersonSummaryDTO> adultChildren;

  public List<SectionSummaryDTO> getRfa1aSections() {
    return rfa1aSections;
  }

  public void setRfa1aSections(
      List<SectionSummaryDTO> rfa1aSections) {
    this.rfa1aSections = rfa1aSections;
  }

  public List<PersonSummaryDTO> getApplicants() {
    return applicants;
  }

  public void setApplicants(
      List<PersonSummaryDTO> applicants) {
    this.applicants = applicants;
  }

  public List<PersonSummaryDTO> getOtherAdults() {
    return otherAdults;
  }

  public void setOtherAdults(
      List<PersonSummaryDTO> otherAdults) {
    this.otherAdults = otherAdults;
  }

  public List<PersonSummaryDTO> getAdultChildren() {
    return adultChildren;
  }

  public void setAdultChildren(
      List<PersonSummaryDTO> adultChildren) {
    this.adultChildren = adultChildren;
  }
}
