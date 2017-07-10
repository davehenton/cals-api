package gov.ca.cwds.cals.service.rfa;

import gov.ca.cwds.cals.persistence.dao.calsns.RFAExternalEntityDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAExternalEntity;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.rfa.RFAExternalEntityDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.service.rfa.factory.RFAExternalEntityFactory;
import gov.ca.cwds.rest.api.Request;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author CWDS CALS API Team
 */
public class AbstractRFAExternalEntitiesCollectionService<
    T extends RFAExternalEntity<D>, D extends RFAExternalEntityDTO>
    extends TypedCrudServiceAdapter<Long, Request, CollectionDTO<D>> {

  private final RFAExternalEntityFactory<T, D> factory;
  private RFAExternalEntityDao<T, D> dao;

  public AbstractRFAExternalEntitiesCollectionService(
      RFAExternalEntityDao<T, D> dao, RFAExternalEntityFactory<T, D> factory) {
    this.dao = dao;
    this.factory = factory;
  }

  @Override
  public CollectionDTO<D> find(Long formId) {
    Stream<T> entities =
        dao.findAllByFormId(formId);
    List<D> collectDTOs = entities.map(T::getEntityDTO).collect(Collectors.toList());
    return factory.createCollectionDTO(collectDTOs);
  }
}
