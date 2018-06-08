package gov.ca.cwds.cals.persistence.dao.fas;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.model.fas.ComplaintReportLic802;
import gov.ca.cwds.data.BaseDaoImpl;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dao for finding Complaints by facility id or complaint id.
 *
 * @author CWDS CALS API Team
 */
public abstract class BaseComplaintReportLic802Dao extends BaseDaoImpl<ComplaintReportLic802> {

  private static final Logger LOG = LoggerFactory.getLogger(BaseComplaintReportLic802Dao.class);

  @Inject
  public BaseComplaintReportLic802Dao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  /**
   * Finds complaints by facility number.
   */
  public List<ComplaintReportLic802> findComplaintsByFacilityNumber(String facilityNumber) {
    Session session = getSessionFactory().getCurrentSession();
    Class<ComplaintReportLic802> entityClass = getEntityClass();
    Query<ComplaintReportLic802> query =
        session.createNamedQuery(ComplaintReportLic802.FIND_COMPLAINTS_BY_FACILITY_ID, entityClass);
    query.setParameter(ComplaintReportLic802.PARAM_FACILITY_NUMBER, facilityNumber);
    return query.getResultList();
  }

  /**
   * Finds complaint by facility number and complaint id.
   */
  public ComplaintReportLic802 findComplaintByFacilityIdAndComplaintId(
      String facilityId, String complaintId) {
    Session session = getSessionFactory().getCurrentSession();
    Class<ComplaintReportLic802> entityClass = getEntityClass();
    Query<ComplaintReportLic802> query =
        session.createNamedQuery(
            ComplaintReportLic802.FIND_COMPLAINTS_BY_FACILITY_ID_AND_COMPLAINT_NUMBER, entityClass);
    query.setParameter(ComplaintReportLic802.PARAM_FACILITY_NUMBER, facilityId);
    query.setParameter(ComplaintReportLic802.PARAM_COMPLAINT_ID, complaintId);
    ComplaintReportLic802 res = null;
    try {
      res = query.getSingleResult();
    } catch (NoResultException e) {
      LOG.warn(
          "There is no result for facilityNumber: {} and complaintId: {}", facilityId, complaintId);
      LOG.debug(e.getMessage(), e);
    }
    return res;
  }
}
