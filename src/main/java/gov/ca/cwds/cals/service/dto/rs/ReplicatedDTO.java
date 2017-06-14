package gov.ca.cwds.cals.service.dto.rs;

import gov.ca.cwds.cals.Identified;
import java.time.LocalDateTime;

/**
 * @author CWDS TPT-2
 */
public interface ReplicatedDTO extends Identified<String> {
  String getReplicationOperation();
  LocalDateTime getReplicationDate();
}
