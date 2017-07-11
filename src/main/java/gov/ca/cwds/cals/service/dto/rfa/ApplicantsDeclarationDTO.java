package gov.ca.cwds.cals.service.dto.rfa;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings({"squid:S3437", "squid:S2160"}) // Dates should be serialized
//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApplicantsDeclarationDTO extends BaseDTO implements RequestResponse {

  private static final long serialVersionUID = 6463110475391563958L;

  @ApiModelProperty(value = "List of applicant's signatures")
  @Valid
  private List<ApplicantSignature> applicantSignatures;

  public List<ApplicantSignature> getApplicantSignatures() {
    return applicantSignatures;
  }

  public void setApplicantSignatures(
      List<ApplicantSignature> applicantSignatures) {
    this.applicantSignatures = applicantSignatures;
  }

  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  public static class ApplicantSignature extends BaseDTO {

    private static final long serialVersionUID = -5249478870281720452L;

    @ApiModelProperty(value = "Applicant Id")
    private Long applicantId;

    @ApiModelProperty(value = "City of signature")
    private String signatureCity;

    @ApiModelProperty(value = "County of signature")
    //@CheckReferentialIntegrity
    private CountyType signatureCounty;

    @ApiModelProperty(value = "Date of signature", example = "2015-03-25")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate signatureDate;

    public Long getApplicantId() {
      return applicantId;
    }

    public void setApplicantId(Long applicantId) {
      this.applicantId = applicantId;
    }

    public String getSignatureCity() {
      return signatureCity;
    }

    public void setSignatureCity(String signatureCity) {
      this.signatureCity = signatureCity;
    }

    public CountyType getSignatureCounty() {
      return signatureCounty;
    }

    public void setSignatureCounty(
        CountyType signatureCounty) {
      this.signatureCounty = signatureCounty;
    }

    public LocalDate getSignatureDate() {
      return signatureDate;
    }

    public void setSignatureDate(LocalDate signatureDate) {
      this.signatureDate = signatureDate;
    }

  }

}
