package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.cms.EmergencyContactDetail;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class EmergencyContactDetailDao extends BaseDaoImpl<EmergencyContactDetail> {

  @Inject
  public EmergencyContactDetailDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}