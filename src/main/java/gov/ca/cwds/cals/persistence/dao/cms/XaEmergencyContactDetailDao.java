package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.XaCmsSessionFactory;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.data.legacy.cms.entity.EmergencyContactDetail;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class XaEmergencyContactDetailDao extends BaseDaoImpl<EmergencyContactDetail> {

  @Inject
  public XaEmergencyContactDetailDao(@XaCmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}