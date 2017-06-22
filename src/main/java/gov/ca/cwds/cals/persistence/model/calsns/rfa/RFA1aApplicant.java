package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import static gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant.NAMED_QUERY_FIND_ALL_BY_FORM;
import static gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant.NAMED_QUERY_FIND_BY_FORM_ID_AND_APPLICANT_ID;
import static gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant.PARAM_APPLICANT_ID;
import static gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant.PARAM_FORM_ID;
import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;
import static gov.ca.cwds.rest.api.domain.DomainObject.TIME_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import gov.ca.cwds.data.persistence.PersistentObject;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(
    name = NAMED_QUERY_FIND_ALL_BY_FORM,
    query = "FROM RFA1aApplicant a WHERE a.formId = :" + PARAM_FORM_ID
)
@NamedQuery(
    name = NAMED_QUERY_FIND_BY_FORM_ID_AND_APPLICANT_ID,
    query =
        "FROM RFA1aApplicant a WHERE a.id = :"
            + PARAM_APPLICANT_ID
            + " AND a.formId = :"
            + PARAM_FORM_ID
)
@SuppressWarnings("squid:S3437") // Dates should be serialized
@Entity
@Table(name = "rfa_1a_applicant")
public class RFA1aApplicant implements PersistentObject {
  private static final long serialVersionUID = 7581768715451007632L;

  public static final java.lang.String NAMED_QUERY_FIND_ALL_BY_FORM =
      "gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant.find.all.forFormId";
  public static final String NAMED_QUERY_FIND_BY_FORM_ID_AND_APPLICANT_ID =
      "gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant.find.ByFormIdAndApplicantId";

  public static final String PARAM_FORM_ID = "formId";
  public static final String PARAM_APPLICANT_ID = "applicantId";

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Basic
  @Column(name = "create_user_id", length = 50, nullable = false)
  private String createUserId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT + " " + TIME_FORMAT)
  @ApiModelProperty(
      required = true,
      readOnly = false,
      value = "yyyy-MM-dd HH:mm:ss",
      example = "2000-01-01 15:11:46"
  )
  @Basic
  @Column(name = "create_datetime")
  private LocalDateTime createDateTime;

  @Basic
  @Column(name = "update_user_id", length = 50, nullable = false)
  private String updateUserId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT + " " + TIME_FORMAT)
  @ApiModelProperty(
      required = true,
      readOnly = false,
      value = "yyyy-MM-dd HH:mm:ss",
      example = "2000-01-01 15:11:46"
  )
  @Basic
  @Column(name = "update_datetime")
  private LocalDateTime updateDateTime;

  @Column(name = "application_id")
  private Long formId;

  @Column(name = "applicant")
  @Type(type = "gov.ca.cwds.cals.persistence.model.calsns.rfa.ApplicantJsonType")
  private Applicant applicant;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCreateUserId() {
    return createUserId;
  }

  public void setCreateUserId(String createUserId) {
    this.createUserId = createUserId;
  }

  public LocalDateTime getCreateDateTime() {
    return createDateTime;
  }

  public void setCreateDateTime(LocalDateTime createDateTime) {
    this.createDateTime = createDateTime;
  }

  public String getUpdateUserId() {
    return updateUserId;
  }

  public void setUpdateUserId(String updateUserId) {
    this.updateUserId = updateUserId;
  }

  public LocalDateTime getUpdateDateTime() {
    return updateDateTime;
  }

  public void setUpdateDateTime(LocalDateTime updateDateTime) {
    this.updateDateTime = updateDateTime;
  }

  public Long getFormId() {
    return formId;
  }

  public void setFormId(Long formId) {
    this.formId = formId;
  }

  public Applicant getApplicant() {
    return applicant;
  }

  public void setApplicant(Applicant applicant) {
    this.applicant = applicant;
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
  public Serializable getPrimaryKey() {
    return id;
  }
}
