package gov.ca.cwds.cals.service.dto.rs;

import gov.ca.cwds.cals.service.dto.FacilityDTO;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 * @author CWDS TPT-2
 */
public class ReplicatedFacilityDTO extends FacilityDTO {
  private static final long serialVersionUID = 1L;

  @NotNull
  /*
  @JsonProperty("capacity")
  @NotNull
  @ApiModelProperty(required = false, readOnly = false, value = "Capacity", example = "10")
   */
  private String replicationOperation;

  @NotNull
  private Date replicationDate;

  public String getReplicationOperation() {
    return replicationOperation;
  }

  public void setReplicationOperation(String replicationOperation) {
    this.replicationOperation = replicationOperation;
  }

  public Date getReplicationDate() {
    return replicationDate;
  }

  public void setReplicationDate(Date replicationDate) {
    this.replicationDate = replicationDate;
  }
}
