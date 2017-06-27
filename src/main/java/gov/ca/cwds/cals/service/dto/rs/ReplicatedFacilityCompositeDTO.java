package gov.ca.cwds.cals.service.dto.rs;

import gov.ca.cwds.cals.ReplicatedCompositeDTO;
import gov.ca.cwds.cals.ReplicationOperation;
import gov.ca.cwds.cals.service.dto.FacilityCompositeDTO;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * @author CWDS TPT-2
 */
public class ReplicatedFacilityCompositeDTO implements ReplicatedCompositeDTO, Serializable {
  private static final long serialVersionUID = 1L;

  @NotNull
  private FacilityCompositeDTO facilityCompositeDTO;

  @NotNull
  private ReplicationOperation replicationOperation;

  public ReplicatedFacilityCompositeDTO(
      FacilityCompositeDTO facilityCompositeDTO, ReplicationOperation replicationOperation) {
    this.facilityCompositeDTO = facilityCompositeDTO;
    this.replicationOperation = replicationOperation;
  }

  @Override
  public ReplicationOperation getReplicationOperation() {
    return replicationOperation;
  }

  @Override
  public FacilityCompositeDTO getDTO() {
    return facilityCompositeDTO;
  }

  @Override
  public String getId() {
    return facilityCompositeDTO.getId();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReplicatedFacilityCompositeDTO that = (ReplicatedFacilityCompositeDTO) o;
    return Objects.equals(facilityCompositeDTO, that.facilityCompositeDTO) &&
        replicationOperation == that.replicationOperation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(facilityCompositeDTO, replicationOperation);
  }
}
