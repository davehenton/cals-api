package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.inject.Inject;
import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.inject.CmsSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * @author CWDS CALS API Team
 */
public class PlacementHomeDao extends BaseDaoImpl<PlacementHome> {

    @Inject
    public PlacementHomeDao(@CmsSessionFactory SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public PlacementHome findChildren(String facilityNumber) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createNamedQuery("gov.ca.cwds.cals.model.cms.PlacementHome.findChildren");
        query.setParameter("facilityNumber", facilityNumber);
        PlacementHome placementHome = (PlacementHome) query.getSingleResult();
        return placementHome;
    }

    public PlacementHome findChild(String facilityNumber, String childId) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createNamedQuery("gov.ca.cwds.cals.model.cms.PlacementHome.findChild");
        query.setParameter("facilityNumber", facilityNumber);
        query.setParameter("childId", childId);
        //todo
        Object[] result  = (Object[]) query.getSingleResult();
        return (PlacementHome) result[0];
    }

}