package gov.ca.cwds.cals.service.rfa;

import gov.ca.cwds.cals.persistence.dao.calsns.RFAExternalEntityDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAExternalEntity;
import gov.ca.cwds.cals.service.dto.rfa.RFAExternalEntityDTO;
import gov.ca.cwds.cals.service.rfa.factory.RFAExternalEntityFactory;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;

/**
 * Default version of RFAExternalEntityService.
 *
 * @author CWDS TPT-2 Team.
 */
public abstract class DefaultRFAExternalEntityService<T extends RFAExternalEntity<D>,
    D extends RFAExternalEntityDTO>
        extends AbstractRFAExternalEntityService<T, RFAExternalEntityUpdateParameterObject<D>, D> {

  public DefaultRFAExternalEntityService(
      RFAExternalEntityDao<T> dao,
      RFAExternalEntityFactory<T, D> configuration) {
    super(dao, configuration);
  }
}
