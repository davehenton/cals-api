package gov.ca.cwds.cals.persistence.dao.fas;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FasFfaSessionFactory;
import org.hibernate.SessionFactory;

/**
 * Created by Alexander Serbin on 6/6/2018.
 */
public class FasFfaComplaintDao extends BaseComplaintReportLic802Dao {

  @Inject
  public FasFfaComplaintDao(@FasFfaSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
