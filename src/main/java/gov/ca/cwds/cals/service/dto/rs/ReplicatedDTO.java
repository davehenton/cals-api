package gov.ca.cwds.cals.service.dto.rs;

import gov.ca.cwds.cals.Identifiable;
import java.time.LocalDateTime;

/**
 * @author CWDS TPT-2
 */
public interface ReplicatedDTO extends Identifiable<String> {
  String getReplicationOperation();

  LocalDateTime getReplicationDate();
}
