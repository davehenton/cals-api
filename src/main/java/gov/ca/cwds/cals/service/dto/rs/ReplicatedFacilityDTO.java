package gov.ca.cwds.cals.service.dto.rs;

import gov.ca.cwds.cals.service.dto.FacilityDTO;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * @author CWDS TPT-2
 */
@SuppressWarnings("squid:S3437") //LocalDate is serializable
public class ReplicatedFacilityDTO extends FacilityDTO {
  private static final long serialVersionUID = 1L;

  @NotNull
  private String replicationOperation;

  @NotNull
  private LocalDateTime replicationDate;

  public String getReplicationOperation() {
    return replicationOperation;
  }

  public void setReplicationOperation(String replicationOperation) {
    this.replicationOperation = replicationOperation;
  }

  public LocalDateTime getReplicationDate() {
    return replicationDate;
  }

  public void setReplicationDate(LocalDateTime replicationDate) {
    this.replicationDate = replicationDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ReplicatedFacilityDTO replicatedFacilityDTO = (ReplicatedFacilityDTO) o;

    return Objects.equals(getId(), replicatedFacilityDTO.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }
}
