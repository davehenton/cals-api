package gov.ca.cwds.cals.service.dto.rfa.lic198b;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.dto.BaseDTO;
import java.time.LocalDate;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S2160", "squid:S3437"}) //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SignaturesDTO extends BaseDTO {

  private static final long serialVersionUID = 8348846581129456617L;


  private String signatureOfApplicant;
  private LocalDate signDateOfApplicants;

  private String signatureOfWitness;
  private LocalDate signDateOfWitness;


  public String getSignatureOfApplicant() {
    return signatureOfApplicant;
  }

  public void setSignatureOfApplicant(String signatureOfApplicant) {
    this.signatureOfApplicant = signatureOfApplicant;
  }

  public LocalDate getSignDateOfApplicants() {
    return signDateOfApplicants;
  }

  public void setSignDateOfApplicants(LocalDate signDateOfApplicants) {
    this.signDateOfApplicants = signDateOfApplicants;
  }

  public String getSignatureOfWitness() {
    return signatureOfWitness;
  }

  public void setSignatureOfWitness(String signatureOfWitness) {
    this.signatureOfWitness = signatureOfWitness;
  }

  public LocalDate getSignDateOfWitness() {
    return signDateOfWitness;
  }

  public void setSignDateOfWitness(LocalDate signDateOfWitness) {
    this.signDateOfWitness = signDateOfWitness;
  }
}
