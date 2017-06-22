package gov.ca.cwds.cals.persistence.dao.cms;

import gov.ca.cwds.cals.persistence.model.cms.BaseClient;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.data.BaseDao;
import gov.ca.cwds.data.CrudsDao;
import java.util.stream.Stream;

/**
 * @author CWDS TPT-2
 */
public interface IClientDao<T extends BaseClient> extends CrudsDao<T>, BaseDao<T> {
  T findByParameterObject(FacilityChildParameterObject parameterObject);

  Stream<T> stream(FacilityChildParameterObject parameterObject);
}
