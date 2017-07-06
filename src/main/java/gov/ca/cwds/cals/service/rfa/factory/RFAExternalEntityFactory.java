package gov.ca.cwds.cals.service.rfa.factory;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFABaseEntity;
import gov.ca.cwds.cals.service.dto.BaseDTO;

/**
 * @author CWDS CALS API Team
 */
public interface RFAExternalEntityFactory<
    T extends RFABaseEntity,
    D extends BaseDTO> {

  D createEntityDTO();

  T createEntity();

  Class<T> getEntityClass();

  String getFindAllByFormNamedQuery();

  String getFindByFormIdAndEntityIdNamedQuery();

}
