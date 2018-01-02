package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import static gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant.PARAM_ENTITY_ID;
import static gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant.PARAM_FORM_ID;

import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.data.persistence.PersistentObject;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(
    name = RFA1aApplicant.NAMED_QUERY_FIND_ALL_BY_FORM,
    query = "FROM RFA1aApplicant a WHERE a.formId = :" + PARAM_FORM_ID + " ORDER BY id"
)
@NamedQuery(
    name = RFA1aApplicant.NAMED_QUERY_FIND_BY_FORM_ID_AND_APPLICANT_ID,
    query =
        "FROM RFA1aApplicant a WHERE a.id = :"
            + PARAM_ENTITY_ID
            + " AND a.formId = :"
            + PARAM_FORM_ID
)
@SuppressWarnings("squid:S3437") // Dates should be serialized
@Entity
@Table(name = "rfa_1a_applicant")
public class RFA1aApplicant extends RFAExternalEntity<ApplicantDTO> implements PersistentObject {
  private static final long serialVersionUID = 7581768715451007632L;

  public static final java.lang.String NAMED_QUERY_FIND_ALL_BY_FORM =
      "gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant.find.all.forFormId";
  public static final String NAMED_QUERY_FIND_BY_FORM_ID_AND_APPLICANT_ID =
      "gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant.find.ByFormIdAndApplicantId";

  @Column(name = "applicant")
  @Type(type = "ApplicantJsonType")
  private ApplicantDTO applicant;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
  @JoinColumn(name = "fra_1b_form_id")
  private RFA1bForm rfa1bForm;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
  @JoinColumn(name = "lic_198b_form_id")
  private LIC198bForm lic198bForm;

  public ApplicantDTO getApplicant() {
    return applicant;
  }

  public void setApplicant(ApplicantDTO applicant) {
    this.applicant = applicant;
  }

  public RFA1bForm getRfa1bForm() {
    return rfa1bForm;
  }

  public void setRFA1bForm(RFA1bForm rfa1bForm) {
    this.rfa1bForm = rfa1bForm;
  }

  public LIC198bForm getLic198bForm() {
    return lic198bForm;
  }
  public void setLic198bForm(LIC198bForm lic198bForm) {
    this.lic198bForm = lic198bForm;
  }

  @Override
  public ApplicantDTO getEntityDTO() {
    return getApplicant();
  }

  @Override
  public void setEntityDTO(ApplicantDTO applicant) {
    setApplicant(applicant);
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
