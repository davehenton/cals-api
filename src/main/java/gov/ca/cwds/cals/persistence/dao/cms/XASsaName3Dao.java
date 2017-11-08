package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.XaCmsSessionFactory;
import gov.ca.cwds.data.legacy.cms.dao.SsaName3Dao;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */

public class XASsaName3Dao extends SsaName3Dao {

  private static final Logger LOGGER = LoggerFactory.getLogger(
      XASsaName3Dao.class);

  @Inject
  public XASsaName3Dao(@XaCmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }


}
