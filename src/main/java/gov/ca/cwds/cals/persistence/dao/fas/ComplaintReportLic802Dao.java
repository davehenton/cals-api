package gov.ca.cwds.cals.persistence.dao.fas;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FasSessionFactory;
import gov.ca.cwds.cals.model.fas.ComplaintReportLic802;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */

public class ComplaintReportLic802Dao extends BaseDaoImpl<ComplaintReportLic802> {

    @Inject
    public ComplaintReportLic802Dao(@FasSessionFactory SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<ComplaintReportLic802> findComplaintsByFacilityNumber(Integer facilityNumber) {
        Session session = getSessionFactory().getCurrentSession();
        Class<ComplaintReportLic802> entityClass = getEntityClass();
        Query<ComplaintReportLic802> query = session
                .createNamedQuery(ComplaintReportLic802.FIND_COMPLAINTS_BY_FACILITY_ID, entityClass);
        query.setParameter(ComplaintReportLic802.PARAM_FACILITY_NUMBER, facilityNumber);
        return query.getResultList();
    }

    public ComplaintReportLic802 findComplaintByFacilityIdAndComplaintId(Integer facilityId, String complaintId) {
        Session session = getSessionFactory().getCurrentSession();
        Class<ComplaintReportLic802> entityClass = getEntityClass();
        Query<ComplaintReportLic802> query = session
                .createNamedQuery(ComplaintReportLic802.FIND_COMPLAINTS_BY_FACILITY_ID_AND_COMPLAINT_NUMBER, entityClass);
        query.setParameter(ComplaintReportLic802.PARAM_FACILITY_NUMBER, facilityId);
        query.setParameter(ComplaintReportLic802.PARAM_COMPLAINT_ID, complaintId);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
