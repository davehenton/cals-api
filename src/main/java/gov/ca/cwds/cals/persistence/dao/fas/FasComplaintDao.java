package gov.ca.cwds.cals.persistence.dao.fas;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FasSessionFactory;
import org.hibernate.SessionFactory;

/**
 * Created by Alexander Serbin on 6/6/2018.
 */
public class FasComplaintDao extends BaseComplaintReportLic802Dao {

  @Inject
  public FasComplaintDao(@FasSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
