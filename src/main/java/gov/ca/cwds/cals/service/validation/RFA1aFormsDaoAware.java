package gov.ca.cwds.cals.service.validation;

import gov.ca.cwds.cals.inject.InjectorHolder;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;

/**
 * @author CWDS CALS API Team
 */
public interface RFA1aFormsDaoAware {

  default RFA1aFormsDao getFormDao() {
    return InjectorHolder.INSTANCE.getInstance(RFA1aFormsDao.class);
  }
}
