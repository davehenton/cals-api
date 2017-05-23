package gov.ca.cwds.cals.persistence.dao.fas;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FasSessionFactory;
import gov.ca.cwds.cals.model.fas.Rrcpoc;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class InspectionDao extends BaseDaoImpl<Rrcpoc> {
    private static final long serialVersionUID = 839402158987943034L;

    @Inject
    public InspectionDao(@FasSessionFactory SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Rrcpoc> findByFacilityNumber(Integer facilityNumber) {
        Session currentSession = getSessionFactory().getCurrentSession();
        Query<Rrcpoc> namedQuery = currentSession.createNamedQuery(Rrcpoc.NQ_FIND_BY_FACILITY_NUMBER, Rrcpoc.class);
        namedQuery.setParameter("facilityNumber", facilityNumber);
        return namedQuery.list();
    }

    public Rrcpoc getByFacilityNumberAndInspectionId(Integer facilityNumber, String inspectionId) {
        Session currentSession = getSessionFactory().getCurrentSession();
        Query<Rrcpoc> namedQuery = currentSession.createNamedQuery(
                Rrcpoc.NQ_FIND_BY_FACILITY_NUMBER_AND_INSPECTION_ID, Rrcpoc.class);
        namedQuery.setParameter("facilityNumber", facilityNumber);
        namedQuery.setParameter("inspectionId", inspectionId);
        return namedQuery.getSingleResult();
    }
}
