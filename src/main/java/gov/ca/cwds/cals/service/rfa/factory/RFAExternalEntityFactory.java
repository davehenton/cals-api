package gov.ca.cwds.cals.service.rfa.factory;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.CalsBaseEntity;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.dto.BaseDTO;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public interface RFAExternalEntityFactory<
    T extends CalsBaseEntity,
    D extends BaseDTO> {

  D createEntityDTO();

  T createEntity();

  Class<T> getEntityClass();

  String getFindAllByFormNamedQuery();

  String getFindByFormIdAndEntityIdNamedQuery();

  CollectionDTO<D> createCollectionDTO(List<D> collectDTOs);

}
