package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;
import static gov.ca.cwds.rest.api.domain.DomainObject.TIME_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.data.persistence.PersistentObject;
import gov.ca.cwds.rest.api.Response;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S3437") //LocalDateTime is serializable
@NamedQuery(name = RFA1aForm.NAMED_QUERY_FIND_ALL, query = "FROM RFA1aForm ORDER BY id ASC")
@Entity
@Table(name = "rfa_1a")
public class RFA1aForm extends BaseDTO implements PersistentObject, Response {

  private static final long serialVersionUID = -6201382973500280111L;
  public static final String NAMED_QUERY_FIND_ALL =
      "gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm.find.all";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "create_user_id", length = 50, nullable = false)
  private String createUserId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT + " " + TIME_FORMAT)
  @ApiModelProperty(
      required = false,
      readOnly = true,
      value = "yyyy-MM-dd HH:mm:ss",
      example = "2000-01-01 15:11:46"
  )
  @Column(name = "create_datetime")
  private LocalDateTime createDateTime;

  @Column(name = "update_user_id", length = 50, nullable = false)
  private String updateUserId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT + " " + TIME_FORMAT)
  @ApiModelProperty(
      required = true,
      readOnly = false,
      value = "yyyy-MM-dd HH:mm:ss",
      example = "2000-01-01 15:11:46"
  )
  @Column(name = "update_datetime")
  private LocalDateTime updateDateTime;

  @OneToMany
  @JoinColumn(name = "applicants", referencedColumnName = "formId")
  private List<ApplicantEntity> applicantEntities;



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RFA1aForm form = (RFA1aForm) o;
    if (form.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), form.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  @JsonIgnore
  public Serializable getPrimaryKey() {
    return id;
  }
}
