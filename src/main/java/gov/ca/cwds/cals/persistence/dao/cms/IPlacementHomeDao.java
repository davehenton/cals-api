package gov.ca.cwds.cals.persistence.dao.cms;

import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.data.BaseDao;
import gov.ca.cwds.data.CrudsDao;
import gov.ca.cwds.data.persistence.PersistentObject;
import java.util.Collection;

/**
 * @author CWDS TPT-2
 */
public interface IPlacementHomeDao<T extends BasePlacementHome> extends CrudsDao<T>, BaseDao<T> {
  T findByParameterObject(FacilityParameterObject parameterObject);

  Collection<T> findCollection(FacilityParameterObject parameterObject);
}
