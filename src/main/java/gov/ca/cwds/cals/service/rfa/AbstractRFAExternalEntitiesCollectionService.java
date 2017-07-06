package gov.ca.cwds.cals.service.rfa;

import gov.ca.cwds.cals.persistence.dao.calsns.RFAExternalEntityDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAExternalEntity;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAExternalEntityDTO;
import gov.ca.cwds.cals.service.CrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityParameterObject;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CWDS CALS API Team
 */
public class AbstractRFAExternalEntitiesCollectionService<
    T extends RFAExternalEntity<D>,
    D extends RFAExternalEntityDTO>
    extends CrudServiceAdapter {

  private RFAExternalEntityDao<T, D> dao;

  public AbstractRFAExternalEntitiesCollectionService(
      RFAExternalEntityDao<T, D> dao) {
    this.dao = dao;
  }

  @Override
  public Response find(Serializable params) {
    if (!(params instanceof RFAExternalEntityParameterObject)) {
      throw new IllegalStateException("RFA1aApplicantParameterObject is expected here");
    }
    List<T> entities =
        dao.findAllByFormId(((RFAExternalEntityParameterObject) params).getFormId());
    List<D> collectDTOs =
        entities.stream().map(T::getEntityDTO).collect(Collectors.toList());
    return new CollectionDTO<>(collectDTOs);
  }
}
