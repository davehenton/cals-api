package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 * @author CWDS CALS API Team
 */

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuppressWarnings("squid:S00119")
public abstract class RFAExternalEntity<EntityDTO extends RFAExternalEntityDTO> extends
    RFABaseEntity {

  @Column(name = "application_id")
  private Long formId;

  public Long getFormId() {
    return formId;
  }

  public void setFormId(Long formId) {
    this.formId = formId;
  }

  public abstract EntityDTO getEntityDTO();

  public abstract void setEntityDTO(EntityDTO entityDTO);

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RFAExternalEntity base = (RFAExternalEntity) o;
    if (base.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), base.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }
}
