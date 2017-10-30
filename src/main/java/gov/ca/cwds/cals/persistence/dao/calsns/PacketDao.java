package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public class PacketDao extends BaseDaoImpl<CountyType> {

  @Inject
  public PacketDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
