package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aFormsDao extends BaseDaoImpl<RFA1aForm> {

  @Inject
  public RFA1aFormsDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
