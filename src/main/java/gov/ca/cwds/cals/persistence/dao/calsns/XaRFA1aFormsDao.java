package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.XaCalsnsSessionFactory;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class XaRFA1aFormsDao extends RFA1aFormsDao {

  @Inject
  public XaRFA1aFormsDao(@XaCalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
