package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.Identified;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.ApplicantRelationshipType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S3437") // Dates should be serialized
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MinorChild extends BaseDTO implements Request, Response, Identified<Long> {

  private static final long serialVersionUID = 1367746149537559411L;

  private Long id;

  private ApplicantRelationshipType relationshipToApplicant;

  private List<Long> childRelatedTo;

  private String otherRelativeFirstName;

  private String otherRelativeMiddleName;

  private String otherRelativeLastName;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate dateOfBirth;

  @Override
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ApplicantRelationshipType getRelationshipToApplicant() {
    return relationshipToApplicant;
  }

  public void setRelationshipToApplicant(ApplicantRelationshipType relationshipToApplicant) {
    this.relationshipToApplicant = relationshipToApplicant;
  }

  public List<Long> getChildRelatedTo() {
    return childRelatedTo;
  }

  public void setChildRelatedTo(List<Long> childRelatedTo) {
    this.childRelatedTo = childRelatedTo;
  }

  public String getOtherRelativeFirstName() {
    return otherRelativeFirstName;
  }

  public void setOtherRelativeFirstName(String otherRelativeFirstName) {
    this.otherRelativeFirstName = otherRelativeFirstName;
  }

  public String getOtherRelativeMiddleName() {
    return otherRelativeMiddleName;
  }

  public void setOtherRelativeMiddleName(String otherRelativeMiddleName) {
    this.otherRelativeMiddleName = otherRelativeMiddleName;
  }

  public String getOtherRelativeLastName() {
    return otherRelativeLastName;
  }

  public void setOtherRelativeLastName(String otherRelativeLastName) {
    this.otherRelativeLastName = otherRelativeLastName;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public String toString() {
    return "MinorChild{"
        + "id="
        + id
        + ", relationshipToApplicant="
        + relationshipToApplicant
        + ", childRelatedTo="
        + childRelatedTo
        + ", otherRelativeFirstName='"
        + otherRelativeFirstName
        + '\''
        + ", otherRelativeMiddleName='"
        + otherRelativeMiddleName
        + '\''
        + ", otherRelativeLastName='"
        + otherRelativeLastName
        + '\''
        + ", dateOfBirth="
        + dateOfBirth
        + '}';
  }
}
