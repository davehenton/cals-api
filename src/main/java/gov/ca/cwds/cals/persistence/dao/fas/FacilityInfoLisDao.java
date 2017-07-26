package gov.ca.cwds.cals.persistence.dao.fas;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FasSessionFactory;
import gov.ca.cwds.cals.persistence.model.fas.FacilityInfoLis;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class FacilityInfoLisDao  extends BaseDaoImpl<FacilityInfoLis> {

  @Inject
  public FacilityInfoLisDao(@FasSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
