package gov.ca.cwds.cals.persistence.dao.cms;

import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.data.BaseDao;
import gov.ca.cwds.data.CrudsDao;
import java.util.stream.Stream;

/**
 * @author CWDS TPT-2
 */
public interface IPlacementHomeDao<T extends BasePlacementHome> extends CrudsDao<T>, BaseDao<T> {
  T findByParameterObject(FacilityParameterObject parameterObject);

  Stream<T> stream(FacilityParameterObject parameterObject);
}
