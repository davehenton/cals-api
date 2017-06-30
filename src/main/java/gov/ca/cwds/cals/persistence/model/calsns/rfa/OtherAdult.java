package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S3437") // Dates should be serialized
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OtherAdult extends RFAExternalEntityDTO {

  private String firstName;

  private String middleName;

  private String lastName;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate dateOfBirth;

  private List<RelationshipToApplicant> relationshipToApplicants = new ArrayList<>();

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public List<RelationshipToApplicant> getRelationshipToApplicants() {
    return relationshipToApplicants;
  }

  public void setRelationshipToApplicants(
      List<RelationshipToApplicant> relationshipToApplicants) {
    this.relationshipToApplicants = relationshipToApplicants;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

}
