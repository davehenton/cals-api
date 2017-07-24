package gov.ca.cwds.cals.persistence.dao.lis;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.LisSessionFactory;
import gov.ca.cwds.cals.persistence.model.lisfas.FacilityType;
import org.hibernate.SessionFactory;
import java.util.List;

/** @author CWDS CALS API Team */
@Deprecated //https://www.pivotaltracker.com/story/show/148550507
public class FacilityTypeDao {

  @Inject
  public FacilityTypeDao(@LisSessionFactory SessionFactory sessionFactory) {

  }

  public List<FacilityType> findAll() {
    return null;
  }
}
