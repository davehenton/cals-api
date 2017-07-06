package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aMinorChild;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.rfa.factory.MinorChildFactory;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public class RFA1aMinorChildDao extends
    RFAExternalEntityDao<RFA1aMinorChild, MinorChildDTO> {

  @Inject
  public RFA1aMinorChildDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory, MinorChildFactory.INSTANCE);
  }

}
