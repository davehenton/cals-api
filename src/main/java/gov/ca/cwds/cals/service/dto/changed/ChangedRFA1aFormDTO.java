package gov.ca.cwds.cals.service.dto.changed;

import gov.ca.cwds.cals.RecordChangeOperation;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * @author CWDS TPT-2
 */
public class ChangedRFA1aFormDTO implements ChangedDTO, Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  private RFA1aFormDTO rfa1aFormDTO;

  @NotNull
  private RecordChangeOperation recordChangeOperation;

  public ChangedRFA1aFormDTO(RFA1aFormDTO rfa1aFormDTO,
      RecordChangeOperation recordChangeOperation) {
    this.rfa1aFormDTO = rfa1aFormDTO;
    this.recordChangeOperation = recordChangeOperation;
  }

  @Override
  public String getId() {
    return String.valueOf(rfa1aFormDTO.getId());
  }

  @Override
  public RecordChangeOperation getRecordChangeOperation() {
    return recordChangeOperation;
  }

  @Override
  public BaseDTO getDTO() {
    return rfa1aFormDTO;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangedRFA1aFormDTO that = (ChangedRFA1aFormDTO) o;
    return recordChangeOperation == that.recordChangeOperation && Objects
        .equals(rfa1aFormDTO, that.rfa1aFormDTO);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rfa1aFormDTO, recordChangeOperation);
  }
}
