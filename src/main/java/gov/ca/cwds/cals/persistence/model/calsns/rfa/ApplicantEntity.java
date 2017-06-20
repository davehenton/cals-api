package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.data.persistence.PersistentObject;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S3437") // Dates should be serialized
@Entity
@Table(name = "rfa_1a_applicant")
public class ApplicantEntity implements PersistentObject {

  private static final long serialVersionUID = 7581768715451007632L;

  @Id
  @Column(name = "id")
  private long id;

  @Basic
  @Column(name = "create_user_id")
  private String createUserId;

  @Basic
  @Column(name = "create_datetime")
  private LocalDateTime createDateTime;

  @Basic
  @Column(name = "update_user_id")
  private String updateUserId;

  @Basic
  @Column(name = "update_datetime")
  private LocalDateTime updateDateTime;

  @JoinColumn(name = "application_id", referencedColumnName = "id", table = "rfa_1a")
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
