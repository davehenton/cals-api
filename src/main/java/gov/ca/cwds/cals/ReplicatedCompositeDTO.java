package gov.ca.cwds.cals;

import gov.ca.cwds.cals.service.dto.BaseDTO;

/**
 * @author CWDS TPT-2
 */
public interface ReplicatedCompositeDTO extends Identified<String> {

  RecordChangeOperation getRecordChangeOperation();

  BaseDTO getDTO();
}
