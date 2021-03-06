package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.service.rfa.factory.ApplicantFactory;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantDao extends RFAExternalEntityDao<RFA1aApplicant> {

  @Inject
  public RFA1aApplicantDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory, ApplicantFactory.INSTANCE);
  }

}
