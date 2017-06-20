package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.ApplicantEntity;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantDao extends BaseDaoImpl<ApplicantEntity> {

  @Inject
  public RFA1aApplicantDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }


}
