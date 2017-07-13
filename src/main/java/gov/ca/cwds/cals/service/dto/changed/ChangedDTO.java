package gov.ca.cwds.cals.service.dto.changed;

import gov.ca.cwds.cals.Identifiable;
import gov.ca.cwds.cals.RecordChangeOperation;
import gov.ca.cwds.cals.service.dto.BaseDTO;

/**
 * @author CWDS TPT-2
 */
public interface ChangedDTO extends Identifiable<String> {

  RecordChangeOperation getRecordChangeOperation();

  BaseDTO getDTO();
}
