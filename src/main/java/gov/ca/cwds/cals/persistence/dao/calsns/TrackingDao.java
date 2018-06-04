package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * Tracking Data Access Object.
 *
 * @author CWDS TPT-2 Team
 */
public class TrackingDao extends CalsBaseEntityDao<Tracking> {

  @Inject
  public TrackingDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  /**
   * Finds Tracking by RFA_1a Form Id.
   *
   * @param rfa1aId Rfa1a Id
   * @return Tracking
   */
  public Tracking findByRfa1aId(Long rfa1aId) {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<Tracking> query =
        session.createNamedQuery(Tracking.NAMED_QUERY_FIND_BY_RFA_1A_ID, Tracking.class);
    query.setParameter("rfa1aId", rfa1aId);
    return query.uniqueResult();
  }

  /**
   * Finds Tracking by License number.
   *
   * @param licenseNumber License number
   * @return Tracking
   */
  public Tracking findByLicenseNumber(String licenseNumber) {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<Tracking> query =
        session.createNamedQuery(Tracking.NAMED_QUERY_FIND_BY_LICENSE_NUMBER, Tracking.class);
    query.setParameter("licenseNumber", licenseNumber);
    return query.uniqueResult();
  }

}
